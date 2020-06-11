package com.luo.diamonds.controller.member;

import com.luo.diamonds.controller.BaseRestController;
import com.luo.diamonds.model.entity.Member;
import com.luo.diamonds.model.entity.Permission;
import com.luo.diamonds.service.MemberService;
import com.luo.diamonds.service.PermissionService;
import com.luo.diamonds.service.RoleService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "工会成员管理模块API", value = "backMember")
@Slf4j
@RequiresUser
@RequiresPermissions("member")
@RequiresRoles({"feeder", "fatChicken", "smellyPig"})
@RestController
@RequestMapping("/backMember")
public class MemberRestController extends BaseRestController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;


}
