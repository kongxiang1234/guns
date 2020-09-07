package cn.stylefeng.guns.modular.e365.mapper;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.e365.entity.DailyPoints;
import cn.stylefeng.guns.modular.e365.entity.WeeklyPoints;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Alan _孔
 * @since 2020-04-17
 */
public interface WeeklyPointsMapper extends BaseMapper<WeeklyPoints> {


    List<WeeklyPoints> listMeizhou(@Param("name") String name);
}
