package com.luo.diamonds.model.param.login;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@ApiModel("用户登陆参数")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LoginParam {

    @ApiModelProperty(notes = "账号")
    @NotBlank(message = "账号不能为空！")
    private String account;

    @ApiModelProperty(notes = "密码")
    @NotBlank(message = "密码不能为空！")
    private String password;
}
