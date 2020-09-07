package cn.stylefeng.guns.modular.e365.mapper;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.e365.entity.Answer;
import cn.stylefeng.guns.modular.e365.entity.DailyPoints;
import cn.stylefeng.guns.modular.e365.model.params.AnswerParam;
import cn.stylefeng.guns.modular.e365.model.result.AnswerResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Alan _孔
 * @since 2020-04-17
 */
public interface DailyPointsMapper extends BaseMapper<DailyPoints> {


    List<DailyPoints>  listMeiri(@Param("name") String name);


}
