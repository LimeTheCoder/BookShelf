package com.limethecoder.controller;

import com.limethecoder.data.domain.User;
import com.limethecoder.data.dto.UserDto;
import com.limethecoder.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String showFormForRegistration(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView registerUser(
            @ModelAttribute("user") @Valid UserDto userDto,
            BindingResult result) {

        if(!result.hasErrors()) {
            User user = userService.registerNewUser(userDto);
            if(user != null) {
                return new ModelAndView("home", "message", user.getName());
            } else {
                result.rejectValue("login", "",
                        "User with such login already exists");
            }
        }

        return new ModelAndView("registration", "user", userDto);
    }
}
