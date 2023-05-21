package infsus.dz3.dkbackend.controller;

import infsus.dz3.dkbackend.dto.RepPrescDto;
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
    public List<RepPrescDto> getReports(@RequestParam("patientId") long patientId){
        return reportService.getReports(patientId);
    }

    @PostMapping(value="/addReport")
    public void postReport(@RequestBody RepPrescDto repPresc){
        reportService.insertReport(repPresc);
    }

    @GetMapping(value="/deleteReport")
    public void postReport(@RequestParam("reportId") long reportId){
        reportService.deleteReport(reportId);
    }

}
