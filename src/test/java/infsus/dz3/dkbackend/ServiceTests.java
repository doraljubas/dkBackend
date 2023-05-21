package infsus.dz3.dkbackend;

import infsus.dz3.dkbackend.dto.MedicationDto;
import infsus.dz3.dkbackend.model.HealthcareCompany;
import infsus.dz3.dkbackend.model.Medication;
import infsus.dz3.dkbackend.repository.HealthcareCompanyRepository;
import infsus.dz3.dkbackend.repository.MedicationRepository;
import infsus.dz3.dkbackend.service.MedicationService;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class ServiceTests {

    @Mock
    private MedicationRepository medicationRepository;
    @Mock
    private HealthcareCompanyRepository healthcareCompanyRepository;
    @Spy
    ModelMapper modelMapper;

    @InjectMocks
    private MedicationService medicationService;

    List<Medication> lista = new ArrayList<>();
    HealthcareCompany compA;
    HealthcareCompany compB;
    Medication medicationIn1;
    Medication medicationIn2;

    @BeforeEach
    void setup(){
        ModelMapper modelMapper = new ModelMapper();

        compA = new HealthcareCompany(1, "CompA");
        compB = new HealthcareCompany(2, "CompB");

        medicationIn1 = new Medication(3, "MedA", "TypeA", 1, true);
        medicationIn2 = new Medication(4, "MedB", "TypeB", 1, true);

        lista.addAll(Arrays.asList(medicationIn1,medicationIn2));
        when(medicationRepository.getMedications(anyList())).thenReturn(lista);
    }

    @Test
    public void testGetMedications() {
        assertEquals(2, medicationService.getMedications(new ArrayList<>()).size());
        assertEquals(medicationIn1.getIdMedication(), medicationService.getMedications(new ArrayList<>()).get(0).getIdMedication());
        assertEquals(medicationIn2.getIdMedication(), medicationService.getMedications(new ArrayList<>()).get(1).getIdMedication());
    }
    @Test
    public void testInsertMedicationSuccess() {
        MedicationDto medicationSuccess = new MedicationDto(1, "MedC", "TypeC", compA, true);
        assertEquals(0, medicationService.insertMedication(medicationSuccess));
    }
    @Test
    public void testInsertMedicationFailedDouble() {
        MedicationDto medicationDouble = new MedicationDto(1, "MedA", "TypeA", compA, true);
        assertEquals(2, medicationService.insertMedication(medicationDouble));
    }

    @Test
    public void testInsertMedicationFailedIllegal() {
        MedicationDto medicationIllegal = new MedicationDto(2, "MedB", "TypeA", compB, true);
        assertEquals(1, medicationService.insertMedication(medicationIllegal));
    }

    @Test
    public void testUpdateMedicationSuccess() {
        MedicationDto medicationSuccess = new MedicationDto(3, "MedA", "TypeC", compA, true);
        assertEquals(0, medicationService.updateMedication(medicationSuccess));
    }
    @Test
    public void testUpdateMedicationDouble() {
        MedicationDto medicationDouble = new MedicationDto(3, "MedB", "TypeB", compA, true);
        assertEquals(2, medicationService.updateMedication(medicationDouble));
    }
    @Test
    public void testUpdateMedicationIllegal() {
        MedicationDto medicationIllegal = new MedicationDto(3, "MedB", "TypeC", compA, true);
        assertEquals(1, medicationService.updateMedication(medicationIllegal));
    }



}
