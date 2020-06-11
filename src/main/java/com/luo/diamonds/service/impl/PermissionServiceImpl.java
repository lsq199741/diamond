package com.luo.diamonds.service.impl;

import com.luo.diamonds.model.entity.Permission;
import com.luo.diamonds.mapper.PermissionMapper;
import com.luo.diamonds.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 会员权限表 服务实现类
 * </p>
 *
 * @author shuqiang
 * @since 2019-07-19
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Override
    public List<String> getPermissionByMemberId(Integer userId) {
        return this.baseMapper.getPermissionByMemberId(userId);
    }
}
