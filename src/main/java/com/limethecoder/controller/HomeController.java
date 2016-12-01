package com.limethecoder.controller;


import com.limethecoder.data.domain.*;
import com.limethecoder.data.service.BookService;
import com.limethecoder.data.service.GenreService;
import com.limethecoder.data.service.RoleService;
import com.limethecoder.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Date;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/")
public class HomeController {
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private BookService bookService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = GET)
    public String home(Model model) {
        model.addAttribute("message", "It's working!!");
        User user = new User();
        Role role = roleService.findOne("ADMIN");
        if(userService.findOne("laymont") == null) {
            user.setCity("Kyiv");
            user.setLogin("laymont");
            user.setName("Lesley");
            user.setSurname("Jagger");
            user.setPassword("test");
            user.setPhotoUrl("url");
            user.setRoles(Arrays.asList(role));
            userService.add(user);
            System.out.println(userService.findOne("laymont").getRoles().get(0).getName());
        }
        return "home";
    }

}
