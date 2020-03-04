package com.wy.springbootshiro.controller;



import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
      return  "HelloWorld";
    }

    /**
     * 测试themeleaf
     */
    @RequestMapping("/testThymeleaf")
    public String testThymeleaf(Model model ) {
        model.addAttribute("name","24kGentle");
        //返回hello.html
        return "hello";
    }

    @RequestMapping("/add")
    public String testAdd() {
        //返回add.html
        return "user/add";
    }

    @RequestMapping("/update")
    public String testUpdate() {
        //返回update.html
        return "user/update";
    }


    @RequestMapping("/toLogin")
    public String testLogin() {
        //返回update.html
        return "user/login";
    }

    @RequestMapping("login")
    public String login(String name,String password,Model model) {
        /**
         * 使用shiro编写认证操作
         */
        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();

        //2.封装用户数据
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(name,password);

        //3.执行登录方法
        try {
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e) {
            //e.printStackTrace();
            //登录失败：用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "forward:toLogin";
        }catch (IncorrectCredentialsException e) {
            model.addAttribute("msg","密码错误");
            return "user/login";
        }
        return "redirect:/testThymeleaf";

    }

    @RequestMapping("/unAuth")
    public String testUnAuth() {
        return "user/noAuth";
    }

}
