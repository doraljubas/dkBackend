package infsus.dz3.dkbackend.service;

import infsus.dz3.dkbackend.model.Patient;
import infsus.dz3.dkbackend.model.Prescription;
import infsus.dz3.dkbackend.model.Report;
import infsus.dz3.dkbackend.repository.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DoctorService {
    DoctorRepository doctorRepository;

    public List<Patient> getPatients(int doctorId){
        return doctorRepository.getPatients(doctorId);
    }

   //Dohvat svih nalaza pacijenta za zadani patientId
    public List<Report> getPatientReports(int patientId){
        return doctorRepository.getPatientReports(patientId);
    }
    //Dohvat svih recepata nalaza za zadani reportId
    public List<Prescription> getPrescriptions(int reportId){
        return doctorRepository.getPrescriptions(reportId);
    }

}
