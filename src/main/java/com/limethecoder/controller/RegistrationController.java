package com.limethecoder.controller;

import com.limethecoder.data.dto.UserDto;
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
    @RequestMapping(method = RequestMethod.GET)
    public String showFormForRegistration(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute(userDto);
        return "registration";
    }

    public ModelAndView registerUser(WebRequest request,
                                     @ModelAttribute("user") @Valid UserDto userDto,
                                     Errors errors,
                                     BindingResult bindingResult) {
        return null;
    }
}
