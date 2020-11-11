/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tts.kapita.tts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import tts.kapita.tts.services.LoginRestService;
import tts.kapita.tts.entities.rest.LoginInput;
import tts.kapita.tts.services.ProfileService;

/**
 *
 * @author cnugr
 */
@Controller
public class RestController {

    @Autowired
    LoginRestService service;
    

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("logininput", new LoginInput());
        return "login";
    }
    
    @PostMapping("login")
    public String login(LoginInput input){
        System.out.println(input);
        System.out.println(service.login(input));
        return "index";
    }
    
    @Autowired
    ProfileService profileService;
    
    @GetMapping("/profile/")
    public String profileBasic(Model model){
        model.addAttribute("profile", profileService.getProfileInfo("USER-00013"));
        System.out.println(profileService.getProfileInfo("USER-00013"));
        return "profile";
    }
}