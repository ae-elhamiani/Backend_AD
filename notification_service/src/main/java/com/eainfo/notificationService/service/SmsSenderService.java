package com.eainfo.notificationService.service;

import com.eainfo.notificationService.config.TwilioConfiguration;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("twilio")
@RequiredArgsConstructor
@Slf4j
public class SmsSenderService {

    private final TwilioConfiguration twilioConfiguration;


    public void sendOtpSms(String keyPhone, String numPhone, String message) {
        try {
            String phoneNumber = keyPhone + numPhone;
            PhoneNumber to = new PhoneNumber(phoneNumber);
            PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());


            MessageCreator creator = Message.creator(to, from, message);
            creator.create();

        } catch (Exception e ){
            e.printStackTrace();
        }

    }

}
