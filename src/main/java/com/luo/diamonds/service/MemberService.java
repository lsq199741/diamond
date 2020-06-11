package com.luo.diamonds.service;

import com.luo.diamonds.model.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author shuqiang
 * @since 2019-07-19
 */
public interface MemberService extends IService<Member> {
    Member findByUserName(@Param("userName") String userName);
}
