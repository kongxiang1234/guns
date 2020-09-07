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
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户传输bean
 *
 * @author stylefeng
 * @Date 2017/5/5 22:40
 */
@Data
public class UserDto implements Serializable, BaseValidatingParam {

    private Long userId;


    @Excel(name = "账号")
    private String account;

    @NotBlank
    private String password;


    @Excel(name = "姓名")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private String sex;


    @Excel(name = "身份证号码")
    private String email;
    @Excel(name = "电话")
    private String phone;

    private String roleId;

    @NotNull
    private Long deptId;

    private String status;

    private String avatar;


    @Excel(name = "职位名称")
    private String position;

    @Excel(name = "部门名称")
    private String deptName;

    @Excel(name = "所属专业")
    private String specialty;
    @Excel(name = "有效期(年)")
    private String year;

    private Integer monCount;
    private Integer weekCount;
    private Integer dayCount;
    private Integer integralCount;

    public UserDto() {

    }
}
