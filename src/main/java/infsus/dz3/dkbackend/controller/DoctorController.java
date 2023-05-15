package infsus.dz3.dkbackend.controller;

import infsus.dz3.dkbackend.model.Patient;
import infsus.dz3.dkbackend.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
/**
 * Prima sve zahtjeve doktora
 */
public class DoctorController {
    DoctorService doctorService;

    @PostMapping(value="/getPatients")
    public List<Patient> getPatients(@RequestBody int doctorId){
        return doctorService.getPatients(doctorId);
    }
}
