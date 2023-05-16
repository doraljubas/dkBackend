package infsus.dz3.dkbackend.controller;

import infsus.dz3.dkbackend.model.Prescription;
import infsus.dz3.dkbackend.service.PerscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class PrescriptionController {

    PerscriptionService perscriptionService;

    @GetMapping(value="/getPrescriptions")
    public List<Prescription> getPrescriptions(@RequestParam("reportId") int reportId){
        return perscriptionService.getPrescriptions(reportId);
    }
}
