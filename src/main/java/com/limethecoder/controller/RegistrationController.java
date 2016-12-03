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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

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
