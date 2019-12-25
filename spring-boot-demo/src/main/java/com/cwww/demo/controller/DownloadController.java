package com.cwww.demo.controller;

import com.cwww.demo.dto.Student;
import com.cwww.demo.dto.User;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @author cWww
 * @Title DownloadController
 * @Description 下载controller
 * @date: 2019/7/22  10:36
 */
@Controller
@RequestMapping("/down")
public class DownloadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DownloadController.class);

    @RequestMapping("/file")
    public void downFile(final HttpServletResponse response) throws IOException {

        // 设置导出文件头
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String("nidemingzi.jpg".getBytes("UTF-8"), "iso-8859-1"));

        StreamUtils.copy(FileUtils.openInputStream(new File("D:\\nidemingzi.jpg")),response.getOutputStream());

        response.getOutputStream().flush();

    }

    @RequestMapping(value = "/user",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public User user(@RequestBody(required = false) User user){
        if (null == user) {
            user = new Student();
        }
        LOGGER.info("user:{}",user);
        return user;
    }

}
