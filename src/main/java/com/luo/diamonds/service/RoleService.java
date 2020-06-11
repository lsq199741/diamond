package com.luo.diamonds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luo.diamonds.model.entity.Role;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author shuqiang
 * @since 2019-04-30
 */
public interface RoleService extends IService<Role> {

    List<String> selectRoleByAdminId(Integer adminId);

    String getMemberRole(Integer id);
}
