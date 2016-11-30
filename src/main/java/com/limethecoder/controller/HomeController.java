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

    static int cnt = 0;
    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = GET)
    public String home(Model model) {
        model.addAttribute("message", "It's working!!");
        Role role = new Role("ADMIN");
        roleService.add(role);
        return "home";
    }

}
