package com.luo.diamonds.service.impl;

import com.luo.diamonds.model.entity.RolePermission;
import com.luo.diamonds.mapper.RolePermissionMapper;
import com.luo.diamonds.service.RolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 会阶权限表 服务实现类
 * </p>
 *
 * @author shuqiang
 * @since 2019-07-19
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    @Override
    public List<Integer> selectPermissionByRoleId(Integer roleId) {
        return this.baseMapper.selectPermissionByRoleId(roleId);
    }
}
