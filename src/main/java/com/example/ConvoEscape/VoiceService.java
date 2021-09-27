package com.example.ConvoEscape;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class VoiceService {

    private final VoiceConfiguration voiceConfiguration;

    @Autowired
    public VoiceService(VoiceConfiguration voiceConfiguration) {
        this.voiceConfiguration = voiceConfiguration;
    }

    public void makePhoneCall(String phoneNumber){

        if ( phoneNumber.contains("[a-zA-Z]+")  || phoneNumber.length() != 11){
            throw new IllegalStateException("Not valid phone number...");
        }

        Twilio.init(
                voiceConfiguration.getAccountSid(),
                voiceConfiguration.getAuthToken()
        );
        PhoneNumber from = new PhoneNumber(voiceConfiguration.getTrialNumber());
        PhoneNumber to = new PhoneNumber("+" + phoneNumber);

        Call call = Call.creator(
                to,
                from,
                URI.create("http://demo.twilio.com/docs/voice.xml")
        ).create();

        System.out.println("Call made with sid " + call.getSid());
        System.out.println("Called number " + phoneNumber);
    }
}
