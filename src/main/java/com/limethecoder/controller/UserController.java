package com.limethecoder.controller;

import com.limethecoder.data.domain.Role;
import com.limethecoder.data.domain.User;
import com.limethecoder.data.service.RoleService;
import com.limethecoder.data.service.UserService;
import com.limethecoder.util.Util;
import com.limethecoder.util.editor.RoleEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory
            .getLogger(UserController.class);

    private UserService userService;
    private RoleService roleService;

    @Autowired
    private RoleEditor roleEditor;

    private final static int PAGE_SIZE = 30;
    private final static int PAGES_ON_VIEW = 5;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Role.class, roleEditor);
    }

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(method=RequestMethod.GET)
    public String usersList(@RequestParam(name = "page", defaultValue = "1")
                                        int pageNumber, Model model) {
        if(pageNumber > 0) {
            PageRequest pageRequest = new PageRequest(pageNumber - 1, PAGE_SIZE);
            Page<User> page = userService.findAll(pageRequest);

            int begin = Math.max(1, pageNumber - PAGES_ON_VIEW / 2);
            int end = Math.min(begin + PAGES_ON_VIEW - 1, page.getTotalPages());

            if(pageNumber > end) {
                model.addAttribute("message", "Page number out of range");
                return "error";
            }

            if(end - pageNumber < PAGES_ON_VIEW / 2) {
                begin = Math.max(1, end - PAGES_ON_VIEW + 1);
            }

            model.addAttribute("current", pageNumber);
            model.addAttribute("begin", begin);
            model.addAttribute("end", end);
            model.addAttribute("users", page);

        } else {
            model.addAttribute("message", "Page number can't be less that 1");
            return "error";
        }

        return "users";
    }

    @RequestMapping(value = "/{login}", method = RequestMethod.GET)
    public String userDetails(@PathVariable String login, Model model) {
        User user = userService.findOne(login);
        if(user == null) {
            model.addAttribute("message", "No user with login " + login);
            return "error";
        }

        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.findAll());

        return "user_details";
    }
    @RequestMapping(value = "/{login}", method = RequestMethod.POST)
    public String userEdit(@PathVariable String login,
                           Model model,
                           @ModelAttribute("user") @Valid User user,
                           BindingResult result) {
        return "user_details";
    }

    @RequestMapping(path = "/registration", method = RequestMethod.GET)
    public String showFormForRegistration(Model model, Authentication authentication) {

        /* Prevent from creating new account to already registered user */
        if(authentication != null &&
                !authentication.getAuthorities()
                        .contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            System.out.println(authentication.getName());
            return "redirect:/";
        }

        User user = new User();
        model.addAttribute("user", user);

        if(authentication != null) {
            model.addAttribute("roles", roleService.findAll());
        }

        return "registration";
    }

    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    public String registerUser(
            @ModelAttribute("user") @Valid User userInfo,
            BindingResult result, HttpServletRequest request,
            Model model, Authentication authentication) {

        /* Prevent from creating new account to already registered user */
        if(authentication != null &&
                !authentication.getAuthorities()
                        .contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "redirect:/";
        }

        if(!result.hasErrors()) {
            Util.saveFile(userInfo.getPhoto(), userInfo.getPhoto().getOriginalFilename());

            userInfo.setPhotoUrl(userInfo.getPhoto().getOriginalFilename());
            userInfo.setEnabled(true);

            User user = userService.add(userInfo);
            if(user != null) {
                /* if user is not authenticated, then log in user */
                if(authentication == null) {
                    try {
                        request.login(userInfo.getLogin(), userInfo.getPassword());
                    } catch (ServletException e) {
                        logger.error("Unable to authenticate user" + e.getMessage());
                    }
                    return "redirect:/";
                }
                return "redirect:/users";
            } else {
                result.rejectValue("login", "",
                        "User with such login already exists");
            }
        }

        model.addAttribute("user", userInfo);
        model.addAttribute("title", "Create new account");
        model.addAttribute("roles", roleService.findAll());

        return "registration";
    }
}
