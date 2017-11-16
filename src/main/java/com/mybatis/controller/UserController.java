package com.mybatis.controller;

import com.mybatis.forms.UserView;
import com.mybatis.model.User;
import com.mybatis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * User: yli
 * Date: 2017/9/7
 * Time: 18:19
 */
@Controller
@RequestMapping
public class UserController {
    @Autowired
    private IUserService userService;

    private static final String USER = "user";

    //@ModelAttribute修饰的方法会先执行,可以用来赋值等操作
//    @ModelAttribute
    public void userModel(Model model){
        UserView user = new UserView();
        user.setUsername("小米");
        model.addAttribute(USER,user);
        System.out.println("初始化模块---");
    }


    @RequestMapping(value = "/user")
    public String userModel(HttpServletRequest request, HttpServletResponse response, Model model){
        //模型数据传递:Model 或者 ModelMap
        UserView userInfo = (UserView) model.asMap().get(USER);
        System.out.println("userInfo:"+userInfo);

        User user = userService.findUsers(Long.valueOf(1));

        System.out.println("数据库用户信息"+user);
        return "success";
    }

    @RequestMapping(value = "/user1")
    public ModelAndView userModelAndView(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new ModelAndView();
        UserView userInfo = (UserView)mv.getModelMap().get(USER);
        System.out.println("userInfo:"+userInfo);
        mv.setViewName("success");
        return mv;
    }

    @RequestMapping(value = "/login")
    //@RequestParam注解:使用注解时require默认为true,使用defaultValue属性,require默认为false)
    //@CookieValue注解,获取cookie信息,@RequestHeader同样用法获取头信息
    public String login(@RequestParam("name") String username, @RequestParam(name = "age", defaultValue = "18") int userAge,@CookieValue(value = "JSESSIONID") String sessionId){
        System.out.println(username+userAge+",sessionId"+sessionId);
        return "success";
    }

    @RequestMapping(value = "/{formName}")
    public String registerForm(@PathVariable String formName, Model model){
        UserView user = new UserView();
        model.addAttribute(USER,user);
        return formName;
    }

    @RequestMapping(value = "/register")
    //@ModelAttribute注解:userView是
    public String register(@Valid @ModelAttribute(USER) UserView userView, BindingResult bindingResult, Model model){
        System.out.println(userView);
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return "registerForm";
        }
        model.addAttribute(USER,userView);
        return "success";
    }
}
