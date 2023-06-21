package com.hust310.SkillsMaster.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hust310.SkillsMaster.domain.*;
import com.hust310.SkillsMaster.service.BlogcommentsService;
import com.hust310.SkillsMaster.service.BlogsService;
import com.hust310.SkillsMaster.service.FollowService;
import com.hust310.SkillsMaster.service.UserService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

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
    @Autowired
    private BlogsService blogsService;
    @Autowired
    private BlogcommentsService blogcommentsService;

    @Autowired
    private FollowService followService;

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

    @GetMapping("/user/userInfo")
    public User getUserInfo(HttpSession session) {
        //Integer uid = (Integer) session.getAttribute("uid");
        Integer uid = 1;
        User user = userService.getOne(new QueryWrapper<User>().eq("account", uid));
        user.setPassword(null);
        return user;
    }

    /* 访问博主主页，获取博主个人信息*/
    @PostMapping("/user/accessBlogger")
    public BloggerInfo getBloggerInfo(@RequestBody Map<String, Integer> request) {
        Integer account = request.get("account");
        User user = userService.getOne(new QueryWrapper<User>().eq("account", account));
        BloggerInfo bloggerInfo = new BloggerInfo();
        bloggerInfo.setUsername(user.getUsername());
        bloggerInfo.setAvatar(user.getAvatar());
        bloggerInfo.setFans(followService.count(new QueryWrapper<Follow>().eq("blogger", account)));
        bloggerInfo.setArticles(blogsService.count(new QueryWrapper<Blogs>().eq("owner", account)));
        return bloggerInfo;
    }

    @PostMapping("/user/logout")
    public void logOut(HttpSession session) {
        session.setAttribute("uid", null);
    }


    @GetMapping("/getUserInfo")
    public User getUserInfo1(HttpSession session) {
        session.setAttribute("uid", 1);
        Integer uid = (Integer) session.getAttribute("uid");
        User user = userService.getOne(new QueryWrapper<User>().eq("account", uid));
        user.setPassword("");
        return user;
    }


    @PostMapping("/modifyUserInfo")
    public String modifyUserInfo(StandardMultipartHttpServletRequest request, HttpSession session) throws IOException {
        User user = new User();
        user.setAccount((Integer) session.getAttribute("uid"));

        user.setUsername(request.getParameter("username"));
        user.setSignature(request.getParameter("signature"));
        user.setGender(request.getParameter("gender"));

        MultiValueMap<String, MultipartFile> multiFileMap = request.getMultiFileMap();
        List<MultipartFile> files = request.getMultiFileMap().get("avatar");
        if (files != null) {
            MultipartFile file = files.get(0);

            String fileName = UUID.randomUUID() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
            File file1 = new File(this.getClass().getResource("/")
                    .getPath().substring(1) + "static/img", fileName);
            FileUtils.writeByteArrayToFile(file1, file.getBytes());

            user.setAvatar("img/" + fileName);
        }
        userService.saveOrUpdate(user);
        return "success";
    }

    @PostMapping("/changePassword")
    public String changePassword(HttpSession session, @RequestBody Map<String, String> password) {
        Integer uid = (Integer) session.getAttribute("uid");
        User user = userService.getById(uid);
        if (user.getPassword().equals(password.get("password"))) {
            User insert = new User();
            insert.setAccount(uid);
            insert.setPassword(password.get("newPassword"));
            userService.saveOrUpdate(insert);
            return "success";
        } else {
            return "password error";
        }
    }

    @GetMapping("/Manage/get")
    public Map<String, Object> getData(HttpSession session) {
//        session.setAttribute("uid", 1);
        Integer uid = (Integer) session.getAttribute("uid");
        List<Blogs> blogs = blogsService.list(new QueryWrapper<Blogs>().eq("owner", uid));
        long likes = 0;
        long comments = 0;
        for (Blogs blog : blogs) {
            likes += blog.getLikes();
            comments += blogcommentsService.count(new QueryWrapper<Blogcomments>().eq("receiver", blog.getUid()));
        }
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("viewNumber", likes);
        map.put("commentNumber", comments);
        map.put("blogNumber", blogs.size());
        return map;
    }

    @PostMapping("/administrator/Banned")
    public String ban(@RequestBody Map<String, Object> param) {
        User user = new User();
        user.setAccount((Integer) param.get("Account"));
        user.setState("C");
        userService.saveOrUpdate(user);
        return "success";
    }

    @PostMapping("/administrator/Unblock")
    public String unBan(@RequestBody Map<String, Object> param) {
        User user = new User();
        user.setAccount((Integer) param.get("Account"));
        user.setState("D");
        userService.saveOrUpdate(user);
        return "success";
    }

    @GetMapping("/getname")
    public String getUserName(HttpSession session) {
        if (session.getAttribute("uid") == null) {
            return "";
        } else {
            return userService.getById((Integer) session.getAttribute("uid")).getUsername();
        }
    }
}
