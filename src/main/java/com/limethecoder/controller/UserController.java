package com.limethecoder.controller;

import com.limethecoder.data.domain.User;
import com.limethecoder.data.dto.UserDto;
import com.limethecoder.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private final static int PAGE_SIZE = 30;
    private final static int PAGES_ON_VIEW = 5;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
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
    public String showFormForRegistration(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    public String registerUser(
            @ModelAttribute("user") @Valid UserDto userDto,
            BindingResult result, HttpServletRequest request,
            Model model){

        if(!result.hasErrors()) {
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

            User user = userService.registerNewUser(userDto);
            if(user != null) {
                return "redirect:/";
            } else {
                result.rejectValue("login", "",
                        "User with such login already exists");
            }
        }

        model.addAttribute("user", userDto);
        return "registration";
    }
}
