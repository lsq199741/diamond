package com.luo.diamonds.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luo.diamonds.model.entity.Permission;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author shuqiang
 * @since 2019-04-30
 */
public interface PermissionService extends IService<Permission> {

    List<String> getPermissionByMemberId(Integer userId);



}
