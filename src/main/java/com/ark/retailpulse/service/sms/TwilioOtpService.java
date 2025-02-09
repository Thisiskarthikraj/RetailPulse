package com.ark.retailpulse.service.sms;

import com.ark.retailpulse.model.Otp;
import com.ark.retailpulse.repository.OtpRepository;
import com.ark.retailpulse.util.CodeGeneratorUtil;
import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class TwilioOtpService {

    private static final Logger logger = LoggerFactory.getLogger(TwilioOtpService.class);

    private final OtpRepository otpRepository;
    private final CodeGeneratorUtil codeGeneratorUtil;

    @Value("${twilio.accountSid}")
    private String accountSid;

    @Value("${twilio.authToken}")
    private String authToken;

    @Value("${twilio.phoneNumber}")
    private String fromPhoneNumber;

    // Initializes Twilio with account SID and auth token
    @PostConstruct
    public void init() {
        Twilio.init(accountSid, authToken);
    }

    /**
     * Method to send an OTP via Twilio.
     * @param otp The OTP object containing details such as phone number and OTP code.
     */
    public void sendOtp(Otp otp) {
        try {
            // Save the OTP object to the repository
            otpRepository.save(otp);
//             todo: Uncomment for sending sms otp
//             Message.creator(
//                 new PhoneNumber(otp.getPhoneNumber()),
//                 new PhoneNumber(fromPhoneNumber),
//                 "Your OTP code is: " + otp.getSmsOtpCode()
//             ).create();

            logger.info("Sent OTP: {}", otp.getSmsOtpCode());
        } catch (ApiException ex) {
            logger.error("Failed to send OTP: {}", ex.getMessage());
            throw new RuntimeException("Failed to send OTP via Twilio.");
        }
    }

}
