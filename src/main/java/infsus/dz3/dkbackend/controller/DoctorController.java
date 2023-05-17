package infsus.dz3.dkbackend.controller;

import infsus.dz3.dkbackend.model.Doctor;
import infsus.dz3.dkbackend.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class DoctorController {
    DoctorService doctorService;


    @GetMapping(value="/getDoctor")
    public Doctor getDoctor(@RequestParam("doctorId") int doctorId){
        return doctorService.getDoctor(doctorId);
    }

}
