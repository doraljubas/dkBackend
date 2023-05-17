package infsus.dz3.dkbackend.controller;

import infsus.dz3.dkbackend.dto.ReportDto;
import infsus.dz3.dkbackend.model.Report;
import infsus.dz3.dkbackend.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ReportController {
    ReportService reportService;

    @GetMapping(value="/getReports")
    public List<Report> getReports(@RequestParam("patientId") int patientId){
        return reportService.getReports(patientId);
    }

    @PostMapping(value="/postReport")
    public void postReport(@RequestBody ReportDto reportDto){
        reportService.insertReport(reportDto);
    }

    @GetMapping(value="/deleteReport")
    public void postReport(@RequestParam("reportId") int reportId){
        reportService.deleteReport(reportId);
    }

}
