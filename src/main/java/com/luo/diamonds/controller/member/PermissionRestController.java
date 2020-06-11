package com.luo.diamonds.controller.member;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.luo.diamonds.controller.BaseRestController;
import com.luo.diamonds.model.dto.Result;
import com.luo.diamonds.model.entity.Permission;
import com.luo.diamonds.model.param.PermissionAddParam;
import com.luo.diamonds.model.param.PermissionUpdateParam;
import com.luo.diamonds.service.PermissionService;
import com.luo.diamonds.service.RolePermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(value = "/adminLogin", tags = "管理员许可管理模块")
@Slf4j
@Validated
@RequiresUser
@RestController
@RequestMapping("/adminPermission")
public class PermissionRestController extends BaseRestController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RolePermissionService rolePermissionService;


    @ApiOperation("获取许可列表")
    @RequiresRoles("sys_admin")
    @GetMapping("/permissionList")
    public Result permissionList(@RequestParam String name) {

        QueryWrapper wrapper = new QueryWrapper();
        if (!name.equals("all"))
            wrapper.like("name", name);

        List<Permission> data = permissionService.list(wrapper);
        Integer count = permissionService.count(wrapper);

        return success(data, count);
    }

    @ApiOperation("许可新增")
    @RequiresRoles("sys_admin")
    @PostMapping("/permissionAdd")
    public Result permissionAdd(@RequestBody @Validated PermissionAddParam permissionAddParam) {
        QueryWrapper wrapper = new QueryWrapper();
        String permission = permissionAddParam.getPermission();
        wrapper.eq("permission", permission);
        Permission exists = permissionService.getOne(wrapper);

        if (exists == null) {
            boolean r = permissionService.save(permissionAddParam.convert(Permission.class));
            if (!r)
                return error("新增失败，请重试");
            else
                return success();
        } else {
            return error("该许可名已存在");
        }
    }

    @ApiOperation("许可修改")
    @RequiresRoles("sys_admin")
    @PostMapping("/permissionUpdate")
    public Result permissionUpdate(@RequestBody @Validated PermissionUpdateParam permissionUpdateParam) {
        Permission exists = permissionService.getById(permissionUpdateParam.getId());
        if (exists != null) {
            boolean r = permissionService.updateById(permissionUpdateParam.convert(Permission.class));
            if (!r) return error("修改失败，请重试");
        } else {
            return error("该许可不存在！");
        }
        return success();
    }

    @ApiOperation("许可删除")
    @RequiresRoles("sys_admin")
    @PostMapping("/permissionDelete")
    public Result permissionDelete(@RequestParam Integer id) {
        Permission exists = permissionService.getById(id);
        if (exists != null) {
            boolean r = permissionService.removeById(id);
            if (!r) return error("许可删除失败，请重试");
        } else {
            return error("该许可不存在！");
        }
        return success();
    }

    @ApiOperation("许可详情")
    @RequiresRoles("sys_admin")
    @GetMapping("/permissionInfo/{id}")
    public Result permissionInfo(@PathVariable Integer id) {
        Permission data = permissionService.getById(id);
        if (data == null) return error("该许可不存在");
        return success(data);
    }


}
