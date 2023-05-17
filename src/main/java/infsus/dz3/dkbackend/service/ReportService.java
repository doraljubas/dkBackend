package infsus.dz3.dkbackend.service;


import infsus.dz3.dkbackend.dto.ReportDto;
import infsus.dz3.dkbackend.model.Prescription;
import infsus.dz3.dkbackend.model.Report;
import infsus.dz3.dkbackend.repository.PrescriptionRepository;
import infsus.dz3.dkbackend.repository.ReportRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReportService {
    ReportRepository reportRepository;
    PrescriptionRepository prescriptionRepository;

    public List<Report> getReports(int patientId){
        return reportRepository.getReports(patientId);
    }

    public void insertReport(ReportDto report){
        int id_report = reportRepository.insertReport(report.getReport());
        report.getReport().setIdReport(id_report);
        for(Prescription prescription : report.getPrescriptions()){
            prescriptionRepository.insertPrescription(prescription);
        }
    }

    public void deleteReport(int reportId){
        reportRepository.deleteReport(reportId);
        List<Prescription> prescriptions = prescriptionRepository.getPrescriptions(reportId);
        for(Prescription prescription : prescriptions){
            prescriptionRepository.deletePrescription(prescription.getIdPrescription());
        }
    }
}
