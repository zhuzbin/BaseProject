package com.zhuzb.web.controller;

import com.zhuzb.util.PeopertiesConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;
import java.util.UUID;

/**
 * Desc：
 * User：ZhuZhiBin
 * Date：2018/5/22
 * Time：11:02
 */
@Controller
@RequestMapping("/uploadFile")
public class UploadFileController {

    @Autowired
    private PeopertiesConf peopertiesConf;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView view(){
        return  new ModelAndView("upload/upload");
    }

    @RequestMapping(value = "/upload")
    @ResponseBody
    public String upload(HttpServletRequest request, HttpServletResponse response){
        try {
            MultipartHttpServletRequest multipartRequest =
                    (MultipartHttpServletRequest) request;
            Iterator<String> fileNames = multipartRequest.getFileNames();
            MultipartFile multiFile = multipartRequest.getFile(fileNames.next());
            //获得上传文件的目录(录入：C:\develop\apache-tomcat-7.0.75\webapps\ROOT\)
            String savePath = System.getProperty("user.dir").replace("bin", "webapps");
            //savePath = savePath.substring(0,savePath.indexOf("webapps"))+ PeopertiesConf.getValue("uploadFileName");

            //获取文件的类型
            String name = multiFile.getOriginalFilename();//获取名称
            String type = name.substring(name.indexOf("."),name.length());
            //重新生成文件名
            String nameUUID = UUID.randomUUID().toString();

            File files = new File(savePath+"\\"+peopertiesConf.getProperty("uploadFileName"));
            if (!files.exists()) {
                files.mkdir();
            }
            multiFile.transferTo(new File(savePath+"\\"+peopertiesConf.getProperty("uploadFileName")+"\\"+nameUUID+type));

            //FileUtils.writeByteArrayToFile(files, multiFile.getBytes());

            //返回请求图片地址
            return nameUUID+type;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
