/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tts.kapita.tts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import tts.kapita.tts.services.LoginRestService;
import tts.kapita.tts.entities.rest.LoginInput;

/**
 *
 * @author cnugr
 */
@Controller
public class LoginRestController {

    @Autowired
    LoginRestService service;

    @GetMapping("")
    public String index() {
        LoginInput input = new LoginInput();
        input.setEmail("cnugroho211@gmail.com");
        input.setPassword("aa");
        System.out.println(service.login(input));

        return "index";
    }
}
