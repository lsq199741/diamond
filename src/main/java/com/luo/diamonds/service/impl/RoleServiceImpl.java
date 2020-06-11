package com.luo.diamonds.service.impl;

import com.luo.diamonds.model.entity.Role;
import com.luo.diamonds.mapper.RoleMapper;
import com.luo.diamonds.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 会阶表 服务实现类
 * </p>
 *
 * @author shuqiang
 * @since 2019-07-19
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public List<String> selectRoleByAdminId(Integer adminId) {
        return this.baseMapper.selectRoleByAdminId(adminId);
    }

    @Override
    public String getMemberRole(Integer id) {
        return this.baseMapper.getMemberRole(id);
    }
}
