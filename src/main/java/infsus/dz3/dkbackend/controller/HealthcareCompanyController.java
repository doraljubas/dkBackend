package infsus.dz3.dkbackend.controller;

import infsus.dz3.dkbackend.model.HealthcareCompany;
import infsus.dz3.dkbackend.service.HealthcareCompanyService;
import lombok.AllArgsConstructor;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HealthcareCompanyController{
    HealthcareCompanyService healthcareCompanyService;

    @GetMapping(value="getCompanies")
    public List<HealthcareCompany> getCompanies(){
        return healthcareCompanyService.getCompanies();
    }
}
