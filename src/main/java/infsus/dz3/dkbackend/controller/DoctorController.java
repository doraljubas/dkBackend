package infsus.dz3.dkbackend.controller;

import infsus.dz3.dkbackend.model.Patient;
import infsus.dz3.dkbackend.model.Prescription;
import infsus.dz3.dkbackend.model.Report;
import infsus.dz3.dkbackend.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
/**
 * Prima sve zahtjeve doktora
 */
public class DoctorController {
    DoctorService doctorService;

    @GetMapping(value="/getPatients")
    public List<Patient> getPatients(@RequestParam("doctorId") int doctorId){
        return doctorService.getPatients(doctorId);
    }

    @GetMapping(value="/getPatientReports")
    public List<Report> getPatientReports(@RequestParam("patientId") int patientId){
        return doctorService.getPatientReports(patientId);
    }

    @GetMapping(value="/getPrescriptions")
    public List<Prescription> getPrescriptions(@RequestParam("reportId") int reportId){
        return doctorService.getPrescriptions(reportId);
    }
}
