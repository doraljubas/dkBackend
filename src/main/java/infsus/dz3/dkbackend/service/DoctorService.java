package infsus.dz3.dkbackend.service;

import infsus.dz3.dkbackend.dto.DoctorDto;
import infsus.dz3.dkbackend.model.Doctor;
import infsus.dz3.dkbackend.model.Facility;
import infsus.dz3.dkbackend.model.Specialization;
import infsus.dz3.dkbackend.repository.DoctorRepository;
import infsus.dz3.dkbackend.repository.FacilityRepository;
import infsus.dz3.dkbackend.repository.SpecializationRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DoctorService {
    private ModelMapper modelMapper;
    DoctorRepository doctorRepository;
    FacilityRepository facilityRepository;
    SpecializationRepository specializationRepository;

    public DoctorDto getDoctor(long doctorId){
        Doctor doctor = doctorRepository.getDoctor(doctorId);
        DoctorDto doctorDto = this.modelMapper.map(doctor, DoctorDto.class);
        doctorDto.setFacility(facilityRepository.getFacility(doctor.getIdFacility()));
        doctorDto.setSpecialization(specializationRepository.getSpecialization(doctor.getIdSpecialization()));
        return doctorDto;
    }
}
