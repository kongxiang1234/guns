package cn.stylefeng.guns.sys.modular.system.service;

import cn.stylefeng.guns.base.pojo.node.LayuiTreeNode;
import cn.stylefeng.guns.base.pojo.node.TreeviewNode;
import cn.stylefeng.guns.base.pojo.node.ZTreeNode;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.sys.core.exception.enums.BizExceptionEnum;
import cn.stylefeng.guns.sys.core.log.LogObjectHolder;
import cn.stylefeng.guns.sys.modular.system.entity.Dept;
import cn.stylefeng.guns.sys.modular.system.mapper.DeptMapper;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-07
 */
@Service
public class DeptService extends ServiceImpl<DeptMapper, Dept> {

    @Resource
    private DeptMapper deptMapper;

    /**
     * 新增部门
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:00 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void addDept(Dept dept) {

        if (ToolUtil.isOneEmpty(dept, dept.getSimpleName(), dept.getPidsV())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        //完善pids,根据pid拿到pid的pids
        this.deptSetPids(dept);


    }

    /**
     * 修改部门
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:00 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void editDept(Dept dept) {

        if (ToolUtil.isOneEmpty(dept, dept.getDeptId(), dept.getSimpleName(),  dept.getPidsV())) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        //完善pids,根据pid拿到pid的pids
        this.deptSetPidsUpdate(dept);
    }
    private void deptSetPidsUpdate(Dept dept) {
        String[] str=dept.getPidsV().split(",");
        Dept dept1=(Dept)LogObjectHolder.me().get();
        List<Dept> depts=this.baseMapper.selectList(new QueryWrapper<Dept>().eq("simple_name",dept1.getSimpleName()));
        if (ToolUtil.isEmpty(dept.getPidsV()) || dept.getPidsV().equals("0")) {
            dept.setPid(0L);
            dept.setPids("[0],");
            this.updateById(dept);
        } else {
            if(depts.size()>1){
                for (int i = 0; i < str.length; i++) {
                    Long pid = new Long(str[i]);
                    Dept temp = this.getById(str[i]);
                    String pids = temp.getPids();
                    dept.setPid(pid);
                    dept.setPids(pids + "[" + pid + "],");
                    dept.setDeptId(depts.get(0).getDeptId());
                    depts.remove(depts.get(0));
                    this.updateById(dept);
                }
                for (Dept dept2:depts){
                    this.deleteDept(dept2.getDeptId());
                }
            }else {
                if(str.length>1){
                    this.deleteDept(dept.getDeptId());
                    for (String string:str){
                        Long pid = new Long(string);
                        Dept temp = this.getById(string);
                        String pids = temp.getPids();
                        dept.setPid(pid);
                        dept.setPids(pids + "[" + pid + "],");
                        this.save(dept);
                    }
                }else {
                    for (String string:str){
                        Long pid = new Long(string);
                        Dept temp = this.getById(string);
                        String pids = temp.getPids();
                        dept.setPid(pid);
                        dept.setPids(pids + "[" + pid + "],");
                        this.updateById(dept);
                    }
                }

            }

        }
    }

    /**
     * 删除部门
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:16 PM
     */
    @Transactional
    public void deleteDept(Long deptId) {
        Dept dept = deptMapper.selectById(deptId);

        //根据like查询删除所有级联的部门
        List<Dept> subDepts = deptMapper.likePids(dept.getDeptId());
        List<Dept> depts=this.baseMapper.selectList(new QueryWrapper<Dept>().eq("simple_name",dept.getSimpleName()));
        for (Dept temp : subDepts) {
            this.removeById(temp.getDeptId());
        }
        for (Dept temp1 : depts) {
            this.removeById(temp1.getDeptId());
        }
        this.removeById(dept.getDeptId());
    }

    /**
     * 获取ztree的节点列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:16 PM
     */
    public List<ZTreeNode> tree() {
        return this.baseMapper.tree();
    }

    /**
     * 获取layuiTree的节点列表
     *
     * @author fengshuonan
     * @Date 2019-8-27 15:23
     */
    public List<LayuiTreeNode> layuiTree() {
        return this.baseMapper.layuiTree();
    }

    /**
     * 获取ztree的节点列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:16 PM
     */
    public List<TreeviewNode> treeviewNodes() {
        return this.baseMapper.treeviewNodes();
    }

    /**
     * 获取所有部门列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:16 PM
     */
    public Page<Map<String, Object>> list(String condition, Long deptId, String type) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.list(page, condition, deptId,type);
    }

    /**
     * 设置部门的父级ids
     *
     * @author fengshuonan
     * @Date 2018/12/23 4:58 PM
     */
    private void deptSetPids(Dept dept) {
        if (ToolUtil.isEmpty(dept.getPidsV()) || dept.getPidsV().equals("0")) {
            dept.setPid(0L);
            dept.setPids("[0],");
            this.save(dept);
        } else {
            for (String string:dept.getPidsV().split(",")){
                Long pid = new Long(string);
                Dept temp = this.getById(string);
                String pids = temp.getPids();
                dept.setPid(pid);
                dept.setPids(pids + "[" + pid + "],");
                this.save(dept);
            }
        }

    }
}
