package infsus.dz3.dkbackend.service;

import infsus.dz3.dkbackend.dto.DoctorDto;
import infsus.dz3.dkbackend.dto.PatientDto;
import infsus.dz3.dkbackend.model.Patient;
import infsus.dz3.dkbackend.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PatientService {
    private ModelMapper modelMapper;
    private DoctorService doctorService;
    PatientRepository patientRepository;

    public List<PatientDto> getPatients(long doctorId){
        DoctorDto doctor = doctorService.getDoctor(doctorId);
        List<PatientDto> patients =  new ArrayList<>();
        for(Patient patient : patientRepository.getPatients(doctorId)){
            PatientDto patientdto = modelMapper.map(patient, PatientDto.class);
            patientdto.setFamilyDoctor(doctor);
            patients.add(patientdto);
        }
        return patients;
    }

    public PatientDto getPatient(long patientId){
        Patient patient = patientRepository.getPatient(patientId);
        PatientDto patientdto = modelMapper.map(patient, PatientDto.class);
        DoctorDto doctor = doctorService.getDoctor(patient.getIdFamilyDoctor());
        patientdto.setFamilyDoctor(doctor);
        return patientdto;
    }
}
