package com.luo.diamonds.framework.model;

import com.luo.diamonds.framework.model.convert.Convert;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BaseModel extends Convert {

    protected Integer id;

}
