package com.FutureClassroom.vod.controller;

import org.springframework.web.bind.annotation.*;
import result.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Param:
 * return:
 * Author: KIKI
 * Date: 2023-04-01
 */
@RestController
@RequestMapping("/admin/vod/user")
@CrossOrigin   //解决跨越问题
public class UserLoginCotroller {

    @PostMapping("login")
    public Result login() {
        Map<String,Object> map = new HashMap<>();
        map.put("token","admin");
        return Result.ok(map);
    }
    @GetMapping("info")
    public Result info() {
        Map<String,Object> map = new HashMap<>();
        map.put("roles","[admin]");
        map.put("name","admin");
        map.put("avatar","https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
        return Result.ok(map);
    }

//    退出
    @PostMapping("logout")
    public Result logout() {
        return Result.ok(null);
    }
}
