package com.luo.diamonds.service.impl;

import com.luo.diamonds.model.entity.Member;
import com.luo.diamonds.mapper.MemberMapper;
import com.luo.diamonds.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author shuqiang
 * @since 2019-07-19
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Override
    public Member findByUserName(String userName) {
        return this.baseMapper.findByUserName(userName);
    }
}
