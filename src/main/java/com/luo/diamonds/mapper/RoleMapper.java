package com.luo.diamonds.mapper;

import com.luo.diamonds.model.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 会阶表 Mapper 接口
 * </p>
 *
 * @author shuqiang
 * @since 2019-07-19
 */
public interface RoleMapper extends BaseMapper<Role> {
    List<String> selectRoleByAdminId(Integer adminId);

    String getMemberRole(Integer id);
}
