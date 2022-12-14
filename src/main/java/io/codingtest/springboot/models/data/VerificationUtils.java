package io.codingtest.springboot.models.data;

import org.springframework.stereotype.Component;

import java.util.Base64;


@Component
public class VerificationUtils {
    public byte[] convertBase64StringToByteArray(String base64String) {
        return Base64.getDecoder().decode(base64String);
    }

}
