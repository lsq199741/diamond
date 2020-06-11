package com.luo.diamonds.model.param.login;

import com.luo.diamonds.framework.model.convert.Convert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ApiModel("后台账号注册参数")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RegisterParam extends Convert {

    @ApiModelProperty("账号")
    @Email(message = "账号格式有误")
    private String account;

    @ApiModelProperty("密码")
    @Size(min = 8, max = 16, message = "密码长度有误")
    @NotBlank(message = "密码有误")
    private String password;

}
