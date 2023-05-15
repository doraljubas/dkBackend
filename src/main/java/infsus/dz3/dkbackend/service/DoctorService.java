package infsus.dz3.dkbackend.service;

import infsus.dz3.dkbackend.model.Patient;
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
}
