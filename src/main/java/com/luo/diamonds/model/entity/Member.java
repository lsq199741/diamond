package com.luo.diamonds.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.luo.diamonds.framework.model.convert.Convert;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 会员表
 * </p>
 *
 * @author shuqiang
 * @since 2019-07-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Member extends Convert {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /*
     * 密码盐值
     */
    private String salt;

    /**
     * 邮箱：战网邮箱
     */
    private String email;

    /**
     * 名称
     */
    private String username;

    /**
     * 昵称：游戏id
     */
    private String nickname;

    /**
     * 备注
     */
    private String note;

    private Integer info;

    private Integer status;

    /**
     * 账号创建时间
     */
    private LocalDateTime ctime;


}
