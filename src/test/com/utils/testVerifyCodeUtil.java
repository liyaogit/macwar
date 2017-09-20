package com.utils;

import com.util.VerifyCodeUtil;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * User: yli
 * Date: 2017/7/3
 * Time: 12:28
 */
public class testVerifyCodeUtil {

    @Test
    public void testGenerateVerifyCode(){

        for(int i = 0 ; i <100; i++){
            String code = VerifyCodeUtil.generateVerifyCode(4);
            System.out.print("\n"+code);
        }
    }

    @Test
    public void getImage(){
        String code = VerifyCodeUtil.generateVerifyCode(4);
        //2.转为图像
        try {
            String path = "H:\\下载\\pictures";
            File file = new File(path, code + ".jpg");
            FileOutputStream fos = new FileOutputStream(file);

            BufferedImage image = VerifyCodeUtil.getImage(200,80,code);
            ImageIO.write(image, "jpg", fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIp(){
        InetAddress address = null;//获取的是本地的IP地址
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println(address);//PC-20140317PXKX/192.168.0.121
         System.out.println(address.getHostAddress());//192.168.0.121
    }

}
