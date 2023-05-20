package infsus.dz3.dkbackend.service;


import infsus.dz3.dkbackend.dto.*;
import infsus.dz3.dkbackend.model.Prescription;
import infsus.dz3.dkbackend.model.Report;
import infsus.dz3.dkbackend.repository.PrescriptionRepository;
import infsus.dz3.dkbackend.repository.ReportRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ReportService {

    private PatientService patientService;
    private DoctorService doctorService;
    private ModelMapper modelMapper;
    ReportRepository reportRepository;
    PrescriptionRepository prescriptionRepository;

    public List<ReportDto> getReports(long patientId){
        List<ReportDto> reportsDto = new ArrayList<>();
        PatientDto patient = patientService.getPatient(patientId);
        for(Report report : reportRepository.getReports(patientId)){
            DoctorDto doctor = doctorService.getDoctor(report.getIdDoctor());
            ReportDto reportDto = modelMapper.map(report, ReportDto.class);
            reportDto.setPatient(patient);
            reportDto.setDoctor(doctor);
            reportsDto.add(reportDto);
        }
        return reportsDto;
    }

    public void insertReport(RepPrescDto repPresc){
        Report report = modelMapper.map(repPresc.getReport(), Report.class);
        report.setIdDoctor(repPresc.getReport().getDoctor().getIdDoctor());
        report.setIdPatient(repPresc.getReport().getPatient().getIdPatient());
        int id_report = reportRepository.insertReport(report);
        report.setIdReport(id_report);
        for(PrescriptionDto prescriptionDto : repPresc.getPrescriptions()){
            Prescription prescription = modelMapper.map(prescriptionDto, Prescription.class);
            prescriptionRepository.insertPrescription(prescription);
        }
    }

    public void deleteReport(long reportId){
        reportRepository.deleteReport(reportId);
        List<Prescription> prescriptions = prescriptionRepository.getPrescriptions(reportId);
        for(Prescription prescription : prescriptions){
            prescriptionRepository.deletePrescription(prescription.getIdPrescription());
        }
    }
}
