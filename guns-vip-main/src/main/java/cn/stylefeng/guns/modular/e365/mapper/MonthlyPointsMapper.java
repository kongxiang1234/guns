package cn.stylefeng.guns.modular.e365.mapper;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.e365.entity.DailyPoints;
import cn.stylefeng.guns.modular.e365.entity.MonthlyPoints;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface MonthlyPointsMapper extends BaseMapper<MonthlyPoints> {


    List<Map> listPaper(@Param("userId") Long userId);

    List<Map> listMeiyue(@Param("map") Map map);
}
