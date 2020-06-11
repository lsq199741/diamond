package com.luo.diamonds.mapper;

import com.luo.diamonds.model.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author shuqiang
 * @since 2019-07-19
 */
public interface MemberMapper extends BaseMapper<Member> {
    Member findByUserName(String userName);
}
