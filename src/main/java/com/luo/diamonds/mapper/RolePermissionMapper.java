package com.luo.diamonds.mapper;

import com.luo.diamonds.model.entity.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 会阶权限表 Mapper 接口
 * </p>
 *
 * @author shuqiang
 * @since 2019-07-19
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    List<Integer> selectPermissionByRoleId(Integer roleId);
}
