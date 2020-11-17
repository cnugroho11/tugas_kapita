///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package tts.kapita.tts.providers;
//
//import java.util.ArrayList;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.stereotype.Component;
//import tts.kapita.tts.services.LoginRestService;
//import tts.kapita.tts.entities.rest.LoginInput;
//import tts.kapita.tts.entities.rest.LoginOutput;
//
///**
// *
// * @author cnugr
// */
//@Component
//public class CustomAuthProvider implements AuthenticationProvider{
//    @Autowired
//    LoginRestService service;
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        System.out.println("begin auth");
//        String email = authentication.getName();
//        String password = authentication.getCredentials().toString();
//        LoginInput input = new LoginInput();
//        input.setEmail(email);
//        input.setPassword(password);
//        LoginOutput output = service.login(input);
//        if(output.getUser()!=null){
//            
//        }
//        
//        if (output.getStatus().equals("Verified")) {
//            return new UsernamePasswordAuthenticationToken(output, email, new ArrayList<>());
////            return new UsernamePasswordAuthenticationToken(
////              email, password, new ArrayList<>());
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//    
//}