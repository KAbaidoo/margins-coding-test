package io.codingtest.springboot.security.services.data;

import io.codingtest.springboot.models.data.Center;
import io.codingtest.springboot.models.data.Verification;


import java.util.List;
import java.util.Optional;

public interface VerificationService {

    Verification getData(String pinNumber);

    Boolean existsByPinNumber(String pinNumber);

    Verification saveData(Verification verification);

    List<Verification> getAllData();

    void addCenterToData(String pinNumber, String name);



    Optional<Center> getCenter(String centerName);

}
