package com.luo.diamonds.controller.member;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.luo.diamonds.controller.BaseRestController;
import com.luo.diamonds.model.dto.LoginDTO;
import com.luo.diamonds.model.dto.Result;
import com.luo.diamonds.model.entity.Member;
import com.luo.diamonds.model.entity.MemberRole;
import com.luo.diamonds.model.entity.Role;
import com.luo.diamonds.model.param.login.LoginParam;
import com.luo.diamonds.model.param.MemberUpdatePasswordParam;
import com.luo.diamonds.model.param.login.RegisterParam;
import com.luo.diamonds.service.MemberService;
import com.luo.diamonds.service.PermissionService;
import com.luo.diamonds.service.RoleService;
import com.luo.diamonds.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.jvm.hotspot.asm.Register;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Api(value = "/backLogin", tags = "会员登陆模块")
@Slf4j
@Validated
@RestController
@RequestMapping("/backLogin")
public class LoginRestController extends BaseRestController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @ApiOperation("注册")
    @PostMapping("/register")
    public Result register(@RequestBody @Validated RegisterParam param) {
        String account = param.getAccount();
        String password = MD5Utils.getPassword(param.getPassword(), "salt");
        if (memberExists(account)) {
            Member member = new Member();
            member.setAccount(account);
            member.setPassword(password);
            boolean r = memberService.save(member);
            if (!r) {
                return error("注册失败，请联系你强哥！");
            } else {
                return login(new LoginParam().setAccount(account).setPassword(param.getPassword()));
            }
        } else {
            return error("当前账号已存在");
        }
    }


    @ApiOperation("登陆")
    @PostMapping(value = "/login")
    public Result<LoginDTO> login(@RequestBody @Validated LoginParam loginParam) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginParam.getAccount(), loginParam.getPassword(), true);
        try {
            //shiro帮我们匹配密码什么的，我们只需要把东西传给它，它会根据我们在UserRealm里认证方法设置的来验证
            subject.login(token);

            Session session = subject.getSession();
            session.setAttribute("member", subject.getPrincipal());
            Member member = (Member) subject.getPrincipal();

            String role = roleService.getMemberRole(member.getId());
            List<String> permission = permissionService.getPermissionByMemberId(member.getId());

            LoginDTO data = member.convert(LoginDTO.class);
            data.setRole(role);
            data.setPermission(permission);

            return success(data);
        } catch (UnknownAccountException e) {
            //账号不存在和下面密码错误一般都合并为一个账号或密码错误，这样可以增加暴力破解难度
            return error("账号不存在");
        } catch (DisabledAccountException e) {
            return error("账号未启用");
        } catch (IncorrectCredentialsException e) {
            return error("密码错误");
        } catch (Throwable e) {
            return error("未知错误！");
        }
    }

    @RequiresUser
    @ApiOperation("登出")
    @PostMapping(value = "/logout")
    public Map logout() {
        Map<String, Object> rMap = new HashMap<>();
        rMap.put("r", 1);

        Subject admin = SecurityUtils.getSubject();

        Session session = admin.getSession();

        Member admin1 = (Member) session.getAttribute("member");

        admin.logout();

        return rMap;
    }


    @RequiresUser
    @ApiOperation("修改密码")
    @PostMapping(value = "/updatePassword")
    public Result updatePassword(@RequestBody @Validated MemberUpdatePasswordParam adminUpdatePasswordParam) {
        Subject subject = SecurityUtils.getSubject();
        Member admin = (Member) subject.getPrincipal();

        String oldPassword = MD5Utils.getPassword(adminUpdatePasswordParam.getOldPassword(), admin.getSalt());
        String newPassword = MD5Utils.getPassword(adminUpdatePasswordParam.getNewPassword(), admin.getSalt());

        if (oldPassword.equals(admin.getPassword())) {
            if (!oldPassword.equals(newPassword)) {
                admin.setPassword(newPassword);
                boolean r = memberService.updateById(admin);
                if (!r) return error("修改失败，请重试！");
                else return success();
            } else return error("新密码不能与旧密码相同！");
        } else return error("密码有误，请重新输入！");
    }

    /*
     * @Author shuqiang
     * @Desc 判断当前注册账号是否存在
     * @Date 2019-07-20 17:36
     */
    private boolean memberExists(String account) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("account", account);
        Member member = memberService.getOne(wrapper);
        return member == null ? true : false;
    }

    private void addRole(String account) {
        QueryWrapper roleWrapper = new QueryWrapper();
        roleWrapper.eq("name", "dog");
        Role role = roleService.getOne(roleWrapper);
        Member member = memberService.findByUserName(account);
        if (member != null && member.getStatus() == 1 && role != null) {
            MemberRole memberRole = new MemberRole();
            memberRole.setMid(member.getId());
            memberRole.setRid(role.getId());
            boolean r = memberRole.insert();
        }
    }
}
