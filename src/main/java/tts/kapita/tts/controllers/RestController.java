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
import tts.kapita.tts.services.RegisterRestService;
import tts.kapita.tts.entities.rest.LoginInput;
import tts.kapita.tts.entities.rest.ProfileAddress;
import tts.kapita.tts.entities.rest.ProfileContact;
import tts.kapita.tts.entities.rest.ProfileEducation;
import tts.kapita.tts.entities.rest.ProfileInfo;
import tts.kapita.tts.entities.rest.RegisterInfo;
import tts.kapita.tts.entities.rest.ProfileOccupation;
import tts.kapita.tts.services.ProfileService;

/**
 *
 * @author cnugr
 */
@Controller
public class RestController {

    @Autowired
    LoginRestService service;
    
    String id;
    
    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("logininput", new LoginInput());
        model.addAttribute("registerinput", new RegisterInfo());
        return "login";
    }
    
    @Autowired
    RegisterRestService registerService;
    
    //register
    
    @PostMapping("/register/")
    public String saveRegister(RegisterInfo registerInfo){
        System.out.println(registerInfo);
        registerService.register(registerInfo);
        return "redirect:/";
    }
    
    @PostMapping("login")
    public String login(LoginInput input){   
        System.out.println(service.login(input));
        id = service.getLoginId(service.login(input));
        System.out.println(id);
        return "redirect:/profile/";
    }
    //
    
    @Autowired
    ProfileService profileService;
    
    @GetMapping("/profile/")
    public String profileBasic(Model model){
        model.addAttribute("profile", profileService.getProfileInfo(id));
        model.addAttribute("address", profileService.getProfileAddress(id));
        model.addAttribute("contact", profileService.getProfileContact(id));
        model.addAttribute("occupation", profileService.getProfileOccupation(id));
        model.addAttribute("education", profileService.getProfileEducation(id));
//        System.out.println(profileService.getProfileInfo("USER-00013"));
//        System.out.println(profileService.getProfileAddress("USER-00013"));
//        System.out.println(profileService.getProfileContact("USER-00013"));
//        System.out.println(profileService.getProfileOccupation("USER-00013"));
//        System.out.println(profileService.getProfileEducation("USER-00013"));
        return "profile";
    }
    
    @PostMapping("/profile/save/info")
    public String editInfo(ProfileInfo profileInfo) throws InterruptedException{
        Thread.sleep(1500);
        profileService.editProfileInfo(profileInfo);
        return "redirect:/profile/";
    }
    
    @PostMapping("/profile/save/address")
    public String editAddress(ProfileAddress profileAddress) throws InterruptedException{
        Thread.sleep(1500);
        profileService.editAddressInfo(profileAddress);
        return "redirect:/profile/";
    }
    
    @PostMapping("/profile/save/contact")
    public String editContact(ProfileContact profileContact) throws InterruptedException{
        Thread.sleep(1500);
        profileService.editContactInfo(profileContact);
        return "redirect:/profile/";
    }
    
    @PostMapping("/profile/save/occupation")
    public String editOccupation(ProfileOccupation profileOccupation) throws InterruptedException{
        Thread.sleep(1500);
        profileService.editOccupationInfo(profileOccupation);
        return "redirect:/profile/";
    }
    
    @PostMapping("/profile/save/education")
    public String editEducation(ProfileEducation profileEducation) throws InterruptedException{
        Thread.sleep(1500);
        profileService.editEducationInfo(profileEducation);
        return "redirect:/profile/";
    }
}
