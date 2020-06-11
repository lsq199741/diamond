package com.luo.diamonds.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@ApiModel("返回类")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Result<T> {

    @ApiModelProperty("r 1:成功 0：失败")
    private Integer r;

    @ApiModelProperty("返回对象")
    private T data;

    @ApiModelProperty("返回对象数量，默认为0")
    private Integer count;

    @ApiModelProperty("描述")
    private String msg;
}
