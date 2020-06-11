package com.luo.diamonds.config;

import com.luo.diamonds.model.entity.Member;
import com.luo.diamonds.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import com.luo.diamonds.service.PermissionService;
import com.luo.diamonds.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description
 * @Author sgl
 * @Date 2018-06-11 17:07
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private MemberService memberService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("doGetAuthorizationInfo");
        Member member = (Member) principals.getPrimaryPrincipal();

        List<String> sysPermissions = permissionService.getPermissionByMemberId(member.getId());
        List<String> roles = roleService.selectRoleByAdminId(member.getId());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(sysPermissions);
        info.addRoles(roles);

        log.info("doGetAuthorizationInfo");
        return info;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        Member member = memberService.findByUserName(token.getUsername());
        if (member == null) {
            return null;
        }
        log.info("doGetAuthenticationInfo");
        return new SimpleAuthenticationInfo(member, member.getPassword().toCharArray(), ByteSource.Util.bytes(member.getSalt()), getName());
    }
}
