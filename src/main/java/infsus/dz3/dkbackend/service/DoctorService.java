package infsus.dz3.dkbackend.service;

import infsus.dz3.dkbackend.model.Doctor;
import infsus.dz3.dkbackend.repository.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DoctorService {
    DoctorRepository doctorRepository;

    public Doctor getDoctor(int doctorId){
        return doctorRepository.getDoctor(doctorId);
    }
}
