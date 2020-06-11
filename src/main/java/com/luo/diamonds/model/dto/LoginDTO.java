package com.luo.diamonds.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@ApiModel("会员登陆返回值")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LoginDTO {

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("账号")
    private String account;

    @ApiModelProperty("战网昵称")
    private String username;

    @ApiModelProperty("战网邮箱")
    private String email;

    @ApiModelProperty("是否补充个人信息 0:未补充 1:已补充")
    private Integer info;

    @ApiModelProperty("备注")
    private String note;

    @ApiModelProperty("会阶")
    private String role;

    @ApiModelProperty("权限")
    private List permission;
}
