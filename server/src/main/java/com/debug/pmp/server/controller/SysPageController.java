package com.debug.pmp.server.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: PermissonManagementPlatform
 * @description:
 * @version: 1.0
 * @author: LiuJiaQi
 * @create: 2021-03-27 22:02
 **/
@Controller
public class SysPageController {

    @RequestMapping(value = {"index.html","/"} )
    public String index(){
        return "index";
    }

    @RequestMapping("login.html")
    public String login(){
//        if (SecurityUtils.getSubject().isAuthenticated()){
//            return "redirect:index.html";
//        }
        return "login";
    }

    @RequestMapping("main.html")
    public String main(){
        return "main";
    }

    @RequestMapping("404.html")
    public String notFound(){
        return "404";
    }
}
