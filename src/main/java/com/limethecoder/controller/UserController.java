package com.limethecoder.controller;

import com.limethecoder.data.domain.Role;
import com.limethecoder.data.domain.User;
import com.limethecoder.data.dto.UserDto;
import com.limethecoder.data.service.RoleService;
import com.limethecoder.data.service.UserService;
import com.limethecoder.util.editor.RoleEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
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
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/users")
public class UserController {

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
                model.addAttribute("error", "Page number out of range");
                return "users";
            }

            if(end - pageNumber < PAGES_ON_VIEW / 2) {
                begin = Math.max(1, end - PAGES_ON_VIEW + 1);
            }

            model.addAttribute("current", pageNumber);
            model.addAttribute("begin", begin);
            model.addAttribute("end", end);
            model.addAttribute("users", page);

        } else {
            model.addAttribute("error", "Page number can't be less that 1");
        }

        return "users";
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

        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        model.addAttribute("title", "Create new account");

        if(authentication != null) {
            model.addAttribute("roles", roleService.findAll());
        }

        return "registration";
    }

    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    public String registerUser(
            @ModelAttribute("user") @Valid UserDto userDto,
            BindingResult result, HttpServletRequest request,
            Model model, Authentication authentication) {

        /* Prevent from creating new account to already registered user */
        if(authentication != null &&
                !authentication.getAuthorities()
                        .contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "redirect:/";
        }

        if(!result.hasErrors()) {
            saveImage(userDto, request);

            User user = userService.registerNewUser(userDto);
            if(user != null) {
                /* if user is not authenticated, then log in user */
                if(authentication == null) {
                    try {
                        request.login(userDto.getLogin(), userDto.getPassword());
                    } catch (ServletException e) {
                        System.out.println("Unable to authenticate user" + e.getMessage());
                    }
                 }
                return "redirect:/";
            } else {
                result.rejectValue("login", "",
                        "User with such login already exists");
            }
        }

        model.addAttribute("user", userDto);
        model.addAttribute("title", "Create new account");
        model.addAttribute("roles", roleService.findAll());

        return "registration";
    }

    private void saveImage(UserDto userDto, HttpServletRequest request) {
        if(userDto.getPhoto() != null && !userDto.getPhoto().isEmpty()) {
            String realPath = request.getServletContext().getRealPath("/") +
                    userDto.getPhoto().getOriginalFilename();
            try {
                userDto.getPhoto().transferTo(new File(realPath));
                userDto.setPhotoUrl("/" +
                        userDto.getPhoto().getOriginalFilename());
                System.out.println("File saved: " + realPath);

            } catch (IOException e) {
                System.out.println("Cannot save file " + e.getMessage());
            }
        }
    }
}
