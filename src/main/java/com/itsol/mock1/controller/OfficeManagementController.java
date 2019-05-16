package com.itsol.mock1.controller;

import com.itsol.mock1.model.Post;
import com.itsol.mock1.model.User;
import com.itsol.mock1.service.PostService;
import com.itsol.mock1.service.UserService;
//import org.hibernate.boot.jaxb.internal.stax.LocalXmlResourceResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@ComponentScan
@EnableAutoConfiguration
@RequestMapping(value = {"/home","/"})
public class OfficeManagementController {
    private PostService postService;
    @Autowired
    private UserService userService;
    public static String uploadDirectory = System.getProperty("user.dir")+ "/src/main/resources/static/uploads/";
    @Autowired
    public OfficeManagementController(PostService postService){
        this.postService = postService;
    }
    @GetMapping
    public String showHomePage(Model model, HttpSession session){
        List<Post> posts = postService.findAllAndSort();
        User user = (User)session.getAttribute("current_user");
        if(user != null){
            session.setAttribute("current_user",userService.findById(user.getId()));
            model.addAttribute("users", userService.findAll());
            model.addAttribute("postList",posts);
        }

      return "home";
    }
    @RequestMapping(value = "/post",method = RequestMethod.GET)
    public String showPostPage(Model model){
        model.addAttribute("post", new Post());
        return "new_post";

    }
   @RequestMapping(value = "/post" ,method = RequestMethod.POST)
    public String createNewPost (Model model , @ModelAttribute("post") Post post ,
                                 @RequestParam String date , @RequestParam (value = "file")MultipartFile multipartFile ,HttpSession session)
   {
       Random rd = new Random();
       int randomNum = rd.nextInt(1000000);
       String fileName = "";
       String dateR = "";
       User user = (User) session.getAttribute("current_user");
       SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
       try{
           Date date1 = formatter.parse(date);
           dateR = formatter.format(date1);
           post.setDate(dateR);

       }
       catch (ParseException e){

       e.getMessage();
       }
       if(!multipartFile.isEmpty()){
           try {
               fileName = randomNum+"_"+ multipartFile.getOriginalFilename();
               byte[] bytes = multipartFile.getBytes();
               Path path = Paths.get(uploadDirectory + fileName);
               Files.write(path,bytes);

           } catch (IOException e) {
               e.printStackTrace();
           }
       }
       post.setUser(userService.findUserByUserName(user.getUsername()));
       post.setThumbnail(fileName);


       postService.save(post);
       return "redirect:/";
   }





}
