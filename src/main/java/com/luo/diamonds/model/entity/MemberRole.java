package com.luo.diamonds.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 会员会阶表
 * </p>
 *
 * @author shuqiang
 * @since 2019-07-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MemberRole extends Model<MemberRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 管理员编号
     */
    @TableId(value = "mid", type = IdType.AUTO)
    private Integer mid;

    /**
     * 角色编号
     */
    private Integer rid;


    @Override
    protected Serializable pkVal() {
        return this.mid;
    }

}
