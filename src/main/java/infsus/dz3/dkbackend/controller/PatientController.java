package infsus.dz3.dkbackend.controller;

import infsus.dz3.dkbackend.dto.PatientDto;
import infsus.dz3.dkbackend.model.Patient;
import infsus.dz3.dkbackend.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class PatientController {

    PatientService patientService;

    @GetMapping(value="/getPatients")
    public List<PatientDto> getPatients(@RequestParam("doctorId") long doctorId){
        return patientService.getPatients(doctorId);
    }

    @GetMapping(value="/getPatient")
    public PatientDto getPatient(@RequestParam("patientId") long patientId){
        return patientService.getPatient(patientId);
    }
}
