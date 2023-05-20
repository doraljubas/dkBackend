package infsus.dz3.dkbackend.service;

import infsus.dz3.dkbackend.model.HealthcareCompany;
import infsus.dz3.dkbackend.repository.HealthcareCompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HealthcareCompanyService {
    HealthcareCompanyRepository healthcareCompanyRepository;

    public List<HealthcareCompany> getCompanies(){
        return healthcareCompanyRepository.getHealhcareCompanies();
    }
}
