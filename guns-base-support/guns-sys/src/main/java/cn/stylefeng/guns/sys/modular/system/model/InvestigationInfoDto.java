/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.guns.sys.modular.system.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户传输bean
 *
 * @author hujt
 * @Date 2020/9/10 22:40
 */
@Data
public class InvestigationInfoDto implements Serializable, BaseValidatingParam {

    private Long content_id;


    @Excel(name = "姓名（单位）")
    private String name_company;

    @Excel(name = "身份证号（信用代码，银行卡号）")
    private String card_number;


    @Excel(name = "备注")
    private String undertaker;

    @Excel(name = "协查单位")
    private String unit;


    public InvestigationInfoDto() {

    }
}
