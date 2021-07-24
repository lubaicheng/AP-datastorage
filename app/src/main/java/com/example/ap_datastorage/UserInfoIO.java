package com.example.ap_datastorage;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserInfoIO {
    //用户数据的写入
    public static boolean saveUserInfo(String username, String password, Context context){
           //openFileOutput(要操作的文件类型，文件的访问模式)
        String msg=null;
        FileOutputStream fos=null;
        try {
            fos=context.openFileOutput("MyData.txt",Context.MODE_PRIVATE);
            msg=username+":"+password;
            //getBytes()将字符串转换为0和1样式的字节。
            fos.write(msg.getBytes());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //用户数据的读取
    public static Map<String,String> getuserInfo(Context context){
        //获取文件输入流
        FileInputStream fis=null;
        try {
            fis=context.openFileInput("MyData.txt");
            byte[] buffer=new byte[fis.available()];
            //读取的内容存放到buffer里
            fis.read(buffer);
            String msg=new String(buffer);
            String[] userinfo=msg.split(":");
            Map<String,String> userMap=new HashMap<>();
            userMap.put("username",userinfo[0]);
            userMap.put("password",userinfo[1]);
            return userMap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
