package io.codingtest.springboot.security.services.data;

import io.codingtest.springboot.models.data.Center;
import io.codingtest.springboot.models.data.Verification;
import io.codingtest.springboot.repository.CenterRepository;
import io.codingtest.springboot.repository.VerificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class VerificationServicesImpl implements VerificationService {
    @Autowired
    private CenterRepository centerRepo;
    @Autowired
    private VerificationRepository verificationRepo;



    @Override
    public Verification getData(String pinNumber) {
        Optional<Verification> data = verificationRepo.findByPinNumber(pinNumber);
        data.orElseThrow(()->new EntityNotFoundException("Not found: "+ pinNumber));
        return data.get();
    }

    @Override
    public Boolean existsByPinNumber(String pinNumber) {
        return verificationRepo.existsByPinNumber(pinNumber);
    }

    @Override
    public Verification saveData(Verification verification) {
        return verificationRepo.save(verification);
    }

    @Override
    public List<Verification> getAllData() {
        return verificationRepo.findAll();
    }

    @Override
    public void addCenterToData(String pinNumber, String name) {
        Optional<Verification> data = verificationRepo.findByPinNumber(pinNumber);
        data.orElseThrow(()->new EntityNotFoundException("Not found: "+ pinNumber));
        Optional<Center> center = centerRepo.findByName(name);
        data.get().setCenter(center.get());
    }

    @Override
    public Optional<Center> getCenter(String centerName) {
        return centerRepo.findByName(centerName);
    }
}
