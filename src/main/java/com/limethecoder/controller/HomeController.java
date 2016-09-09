package com.limethecoder.controller;


import com.limethecoder.data.domain.ZipCode;
import com.limethecoder.data.repository.ZipCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    ZipCodeRepository repository;

    @RequestMapping(method = GET)
    public String home(Model model) {
        model.addAttribute("message", "It's working!!");
        ZipCode zip = repository.findByCity("Monaco");
        System.out.print(zip.getCountry());
        return "home";
    }

}
