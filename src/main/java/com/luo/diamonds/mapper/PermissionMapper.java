package com.luo.diamonds.mapper;

import com.luo.diamonds.model.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 会员权限表 Mapper 接口
 * </p>
 *
 * @author shuqiang
 * @since 2019-07-19
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<String> getPermissionByMemberId(Integer id);

}
