package com.ark.retailpulse.util;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Utility class for generating confirmation codes.
 * This class provides a method to generate a random 6-digit confirmation code.
 */
@Component
public class CodeGeneratorUtil {

    /**
     * Generates a random 6-digit confirmation code.
     * The code is generated by producing a random number between 100000 and 999999.
     *
     * @return a 6-digit confirmation code as a string
     */
    public String generateConfirmationCode() {
        Random random = new Random();

        // Generates a random 6-digit number between 100000 and 999999
        int code = 100000 + random.nextInt(900000);

        // Returns the code as a string
        return String.valueOf(code);
    }
}
