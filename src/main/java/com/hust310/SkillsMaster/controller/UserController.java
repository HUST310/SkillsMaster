package com.hust310.SkillsMaster.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hust310.SkillsMaster.domain.User;
import com.hust310.SkillsMaster.service.UserService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(HttpSession session, @RequestBody User user) {
        User sqluser = userService.getById(user.getAccount());
        if (sqluser == null) {
            return "account error";
        } else if (!user.getPassword().equals(sqluser.getPassword())) {
            return "password error";
        } else {
            session.setAttribute("uid", user.getAccount());
            return "success";
        }
    }

    @PostMapping("/register")
    public Integer register(@RequestBody User user) {
        if (userService.getOne(
                new QueryWrapper<User>().
                        eq("username", user.getUsername())) == null) {
            userService.save(user);
            return userService.
                    getOne(new QueryWrapper<User>().
                            eq("username", user.getUsername())).getAccount();
        } else {
            return null;
        }
    }

    @GetMapping("/getUserInfo")
    public User getUserInfo(HttpSession session) {
        session.setAttribute("uid", 3);
        Integer uid = (Integer) session.getAttribute("uid");
        return userService.getById(uid);
    }

    @PostMapping("/modifyUserInfo")
    public String modifyUserInfo(StandardMultipartHttpServletRequest request, HttpSession session) throws IOException {
        User user = new User();
        session.setAttribute("uid", 3);
        user.setAccount((Integer) session.getAttribute("uid"));

        user.setUsername(request.getParameter("username"));
        user.setSignature(request.getParameter("signature"));
        user.setGender(request.getParameter("gender"));
//        user.setAvatar();
        MultiValueMap<String, MultipartFile> multiFileMap = request.getMultiFileMap();
        MultipartFile file = request.getMultiFileMap().get("avatar").get(0);
        UUID.randomUUID().toString();
        String fileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        File file1 = new File("src/main/resources/static/img/", fileName);
        FileUtils.writeByteArrayToFile(file1, file.getBytes());

        user.setAvatar("img/" + fileName);
        userService.saveOrUpdate(user);
        return "success";
    }

}
