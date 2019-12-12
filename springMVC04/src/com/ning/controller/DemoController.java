package com.ning.controller;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/*
*   在web.xml中配置前端控制器DispatcherServlet
* */
@Controller
public class DemoController {
    /*
    *   访问资源时响应头如果没有设置Content-Disposition,浏览器默认按照inline值进行处理
    *   inline能显示就显示，不能显示就下载 只需要修改响应头中Content-Disposition="attachment;filename=文件名"
    *   attachment下载  以附件的形式下载
    *   filename=值 值就是下载式显示的下载文件名
    * */
    @RequestMapping("download")
    public void download(String fileName,HttpServletResponse res,HttpServletRequest req) throws IOException{
        //设置响应流中文件进行下载
        res.setHeader("Content-Disposition", "attachment;filename="+fileName);
        //把二进制流放入到响应体中.
        ServletOutputStream os = res.getOutputStream();
        String path = req.getServletContext().getRealPath("files");
        System.out.println(path);
        File file = new File(path, fileName);
        byte[] bytes = FileUtils.readFileToByteArray(file);
        os.write(bytes);
        os.flush();
        os.close();
    }
}
