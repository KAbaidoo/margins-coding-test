package io.codingtest.springboot.controllers;


import io.codingtest.springboot.models.data.Center;
import io.codingtest.springboot.models.data.Verification;
import io.codingtest.springboot.models.data.VerificationUtils;
import io.codingtest.springboot.models.data.EDataType;
import io.codingtest.springboot.payload.request.VerificationRequest;
import io.codingtest.springboot.payload.response.MessageResponse;
import io.codingtest.springboot.security.services.data.VerificationServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/api/verification")
public class VerifyController {
    @Autowired
    VerificationServicesImpl dataServices;

    @Autowired
    VerificationUtils verificationUtils;


    @PostMapping("/base_64")
    @PreAuthorize("hasRole('MERCHANT')")
    public ResponseEntity<?> verify(@Valid @RequestBody VerificationRequest verificationRequest) {

        if (dataServices.existsByPinNumber(verificationRequest.getPinNumber())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: PIN number already exists in database !"));
        }

        Optional<Center> center = dataServices.getCenter(verificationRequest.getCenter());
        center.orElseThrow(() -> new RuntimeException("Error: Center not found!"));

        byte[] imageBytes = verificationUtils.convertBase64StringToByteArray(verificationRequest.getImage());

        EDataType type = null;
        String dataType = verificationRequest.getCenter();
        switch (dataType) {
            case "jpeg":
                type = EDataType.JPEG;
                break;
            default:
                type = EDataType.PNG;
        }

        Verification verification = new Verification(verificationRequest.getPinNumber(), imageBytes, type, center.get(), verificationRequest.getMerchantKey());
        dataServices.saveData(verification);
        return ResponseEntity.ok(new MessageResponse("Data saved successfully!"));
    }

}
