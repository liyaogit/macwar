package com.mybatis.controller;

import com.mybatis.model.User;
import com.mybatis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    @ModelAttribute
    public void userModel(Model model){
        User user = new User();
        user.setName("小花");
        user.setAddress("五大道");

        model.addAttribute(USER,user);
        System.out.println(user);
    }


    @RequestMapping(value = "/user")
    public String userModel(HttpServletRequest request, HttpServletResponse response, Model model){
        //模型数据传递:Model 或者 ModelMap
        User userInfo = (User) model.asMap().get(USER);
        System.out.println("hello world"+userInfo);
        request.getSession().setAttribute("code",4444);
        User user = userService.findUsers(Long.valueOf(1));

        System.out.println(user.getAddress()+request.getSession().getAttribute("code"));
        return "success";
    }

    @RequestMapping(value = "/user1")
    public ModelAndView userModelAndView(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new ModelAndView();
        User userInfo = (User)mv.getModelMap().get(USER);
        System.out.println("hello world"+userInfo);
        mv.setViewName("success");
        return mv;
    }

    @RequestMapping(value = "/login")
    //@RequestParam注解:使用注解时require默认为true,使用defaultValue属性,require默认为false)
    //@CookieValue注解,获取cookie信息,@RequestHeader同样用法获取头信息
    public String login(@RequestParam("name") String userName, @RequestParam(name = "age", defaultValue = "18") int userAge,@CookieValue(value = "JSESSIONID") String sessionId){
        System.out.println(userName+userAge+",sessionId"+sessionId);
        return "success";
    }
}
