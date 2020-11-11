/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tts.kapita.tts.services;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tts.kapita.tts.entities.rest.ProfileInfo;
import tts.kapita.tts.entities.rest.ProfileAddress;
import tts.kapita.tts.entities.rest.ProfileContact;
import tts.kapita.tts.entities.rest.ProfileOccupation;
import tts.kapita.tts.entities.rest.ProfileEducation;

/**
 *
 * @author cnugr
 */
@Service
public class ProfileService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${api.uri}")
    private String uri;

    public ProfileInfo getProfileInfo(String id) {
        ProfileInfo result;
        Map<String, String> param = new HashMap<>();
        param.put("id", id);
        result = restTemplate.getForObject(uri + "profile/basic/{id}", ProfileInfo.class, param);
        return result;
    }

    public ProfileAddress getProfileAddress(String id) {
        ProfileAddress result;
        Map<String, String> param = new HashMap<>();
        param.put("id", id);
        result = restTemplate.getForObject(uri + "profile/address/{id}", ProfileAddress.class, param);
        return result;
    }

    public ProfileContact getProfileContact(String id) {
        ProfileContact result;
        Map<String, String> param = new HashMap<>();
        param.put("id", id);
        result = restTemplate.getForObject(uri + "profile/contact/{id}", ProfileContact.class, param);
        return result;
    }

    public ProfileOccupation getProfileOccupation(String id) {
        ProfileOccupation result;
        Map<String, String> param = new HashMap<>();
        param.put("id", id);
        result = restTemplate.getForObject(uri + "profile/currentoccupation/{id}", ProfileOccupation.class, param);
        return result;
    }

    public ProfileEducation getProfileEducation(String id) {
        ProfileEducation result;
        Map<String, String> param = new HashMap<>();
        param.put("id", id);
        result = restTemplate.getForObject(uri + "profile/education/{id}", ProfileEducation.class, param);
        return result;
    }

    public boolean editProfileInfo(ProfileInfo profileInfo) {
        boolean result = true;
        try {
            restTemplate.postForObject(uri + "profile/basic", profileInfo, ProfileInfo.class);
            return result;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public boolean editAddressInfo(ProfileAddress profileAdrress) {
        boolean result = true;
        try {
            restTemplate.postForObject(uri + "profile/address", profileAdrress, ProfileAddress.class);
            return result;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }
    
    public boolean editContactInfo(ProfileContact profileContact) {
        boolean result = true;
        try {
            restTemplate.postForObject(uri + "profile/contact", profileContact, ProfileContact.class);
            return result;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }
    
    public boolean editOccupationInfo(ProfileOccupation profileOccupation) {
        boolean result = true;
        try {
            restTemplate.postForObject(uri + "profile/currentoccupation", profileOccupation, ProfileOccupation.class);
            return result;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }
    
    public boolean editEducationInfo(ProfileEducation profileEducation) {
        boolean result = true;
        try {
            restTemplate.postForObject(uri + "profile/education", profileEducation, ProfileEducation.class);
            return result;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }
}
