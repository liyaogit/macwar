package com.mybatis.controller;

import com.mybatis.service.IUserService;
import com.mybatis.model.User;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.util.VerifyCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * User: yli
 * Date: 2017/5/24
 * Time: 17:09
 */
@Controller
@RequestMapping(value = "/hello")
public class HelloWorld {


    @RequestMapping(value = "/hello",method = {RequestMethod.GET,RequestMethod.POST})
    public String hello(HttpServletRequest request, HttpServletResponse response){

        System.out.println("hello world");

        return "success";
    }


    @RequestMapping("/code") //只有唯一属性,可省略属性名称
    @ResponseBody
    public void getCode(HttpServletRequest request, HttpServletResponse response){
        System.out.println("getCode");
        //1.生成随机字串
        String code = VerifyCodeUtil.generateVerifyCode(4);
        request.getSession().setAttribute("code",code);
        System.out.println(request.getSession().getId());
        try {
            BufferedImage image = VerifyCodeUtil.getImage(200,80,code);
            ServletOutputStream out = response.getOutputStream();
            ImageIO.write(image, "jpg", out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return;
    }
}
