package com.example.ConvoEscape;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoiceController {

    private final VoiceService voiceService;

    @Autowired
    public VoiceController(VoiceService voiceService) {
        this.voiceService = voiceService;
    }

    @PostMapping(path="call/{phoneNumber}")
    public void makePhoneCall(
            @PathVariable("phoneNumber") String phoneNumber
    ){
        voiceService.makePhoneCall(phoneNumber);
    }
}
