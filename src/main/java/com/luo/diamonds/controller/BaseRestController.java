package com.luo.diamonds.controller;

import com.alibaba.fastjson.JSONObject;
import com.luo.diamonds.model.dto.Result;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseRestController {


    /**
     * 登录认证异常
     */
    @ExceptionHandler({UnauthenticatedException.class, AuthenticationException.class})
    public String authenticationException(HttpServletRequest request, HttpServletResponse response) {
//        if (WebUtilsPro.isAjaxRequest(request)) {
        // 输出JSON
        Map<String, Object> map = new HashMap<>();
        map.put("r", "0");
        map.put("msg", "未登录");
        writeJson(map, response);
        return null;
//        } else {
//            return "redirect:/system/login";
//        }
    }

    /**
     * 权限异常
     */
    @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
    @ResponseBody
    public String authorizationException(HttpServletRequest request, HttpServletResponse response) {
//        if (WebUtilsPro.isAjaxRequest(request)) {
        // 输出JSON
        Map<String, Object> map = new HashMap<>();
        map.put("r", "0");
        map.put("msg", "无权限");
        writeJson(map, response);
        return null;
//        } else {
//            return "redirect:/system/403";
//        }
    }

    /**
     * 输出JSON
     *
     * @param response
     * @author SHANHY
     * @create 2017年4月4日
     */
    private void writeJson(Map<String, Object> map, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            out = response.getWriter();
            out.write(new JSONObject(map).toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    protected Result success(Object data) {
        Result result = new Result();
        result.setCount(0);
        result.setData(data);
        result.setMsg("");
        result.setR(1);
        return result;
    }

    protected Result success(Object data, Integer count) {
        Result result = new Result();
        result.setCount(count);
        result.setData(data);
        result.setMsg("");
        result.setR(1);
        return result;
    }

    protected Result success() {
        Result result = new Result();
        result.setR(1);
        return result;
    }

    protected Result error(String msg) {
        Result result = new Result();
        result.setR(0);
        result.setMsg(msg);
        return result;
    }


}
