package com.itsol.mock1.controller;

import com.itsol.mock1.model.User;
import com.itsol.mock1.service.PostService;
import com.itsol.mock1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

@Controller
@ComponentScan
@EnableAutoConfiguration // them vao de hoan thanh autowired
public class UserController implements Serializable {
    private static final long serialVersionUID = 1L;

    private UserService userService;
    @Autowired
    private PostService postService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegisterPage(Model model) {
        User user = new User();
        model.addAttribute(user);
        return "register";

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String createNewAccount(Model model, @Valid @ModelAttribute("user") User user,
                                   BindingResult bindingResult) {
        this.userService.validate(user, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return "register";
        } else {
            user.setAvatar("https://img.over-blog-kiwi.com/300x300-ct/2/23/63/28/20170615/ob_1024dd_avatar-hoat-hinh-anime-chibi-10.jpg");
            this.userService.save(user);
            return "redirect:/login";
        }

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage(Model model) {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, @RequestParam("username")
            String username, @RequestParam("password")
                                String password, HttpSession session) {
        if (this.userService.findUserByUsernameAndPassword(username, password) != null) {
            User user = this.userService.findUserByUsernameAndPassword(username, password);
            return "redirect:/home";
        } else {
            model.addAttribute("error", "invalid user!, please try again!");
            return "login";
        }

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String showLogoutPage(Model model, HttpSession session) {
        session.removeAttribute("current_user");
        return "redirect";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String showUserPage(@PathVariable int id, Model model, HttpSession session) {
        User user = (User) session.getAttribute("current_user");
        if (user != null) {
            session.setAttribute("current_user", userService.findById(user.getId()));
        }
        model.addAttribute("d_user", this.userService.findById(id));
        model.addAttribute("posts", this.postService.findPostsByUserIdAndOrderByIdDesc(id));
        model.addAttribute("users", this.userService.findAll());

        return "user";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
    public String updateAvartar(@PathVariable int id, Model model, HttpSession session,
                                @RequestParam(value = "avatar") MultipartFile multipartFile) {
        String directory = OfficeManagementController.uploadDirectory;
        Random rd = new Random();
        int ramdomNumber = rd.nextInt(1000000);
        String filename = "";
        User user = (User) session.getAttribute("current");
        User q_user = null;
        if (multipartFile.isEmpty()) {
            try {
                filename = ramdomNumber + "-avatar" + multipartFile.getOriginalFilename();
                byte[] bytes = multipartFile.getBytes();
                Path path = Paths.get(directory + filename);

                Files.write(path, bytes);
                q_user = userService.findById(user.getId());
                q_user.setAvatar("/images/" + filename);
                userService.save(q_user);
            } catch (IOException e) {
                e.printStackTrace();
            }
            session.setAttribute("current", q_user);
            model.addAttribute("d_user", this.userService.findById(id));
            model.addAttribute("posts", this.userService.findById(id).getPosts());
            model.addAttribute("users", this.userService.findAll());
        }
        return "user";


    }
}




