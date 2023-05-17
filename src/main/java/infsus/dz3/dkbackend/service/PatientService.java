package infsus.dz3.dkbackend.service;

import infsus.dz3.dkbackend.model.Patient;
import infsus.dz3.dkbackend.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PatientService {

    PatientRepository patientRepository;

    public List<Patient> getPatients(int doctorId){
        return patientRepository.getPatients(doctorId);
    }

    public Patient getPatient(int patientId){
        return patientRepository.getPatient(patientId);
    }
}
