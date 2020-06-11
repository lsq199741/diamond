package com.luo.diamonds.model.param;

import com.luo.diamonds.framework.model.convert.Convert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel("管理员角色添加参数")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RoleUpdateParam extends Convert {

    @ApiModelProperty("角色ID")
    @NotNull(message = "角色ID不能为空")
    private String id;

    @ApiModelProperty("角色名称")
    @NotBlank(message = "角色名称不能为空")
    private String name;

    @ApiModelProperty("角色描述")
    @NotBlank(message = "角色描述不能为空")
    private String title;

}
