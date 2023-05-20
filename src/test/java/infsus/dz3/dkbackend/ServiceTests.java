package infsus.dz3.dkbackend;

import infsus.dz3.dkbackend.dto.MedicationDto;
import infsus.dz3.dkbackend.model.HealthcareCompany;
import infsus.dz3.dkbackend.model.Medication;
import infsus.dz3.dkbackend.repository.HealthcareCompanyRepository;
import infsus.dz3.dkbackend.repository.MedicationRepository;
import infsus.dz3.dkbackend.service.MedicationService;
import infsus.dz3.dkbackend.utils.filters.domain.Filter;
import org.junit.jupiter.api.Test;
import org.junit.Before;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ServiceTests {

    @MockBean
    private MedicationRepository medicationRepository;
    HealthcareCompanyRepository healthcareCompanyRepository;
    ModelMapper modelMapper;

    @Mock
    private MedicationService medicationService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this); // this is needed for inititalizytion of mocks, if you use @Mock
        medicationService = new MedicationService(medicationRepository,healthcareCompanyRepository,modelMapper);
    }


    @Test
    public void testGetMedications() {
        HealthcareCompany compA = new HealthcareCompany(1, "CompA");
        MedicationDto medication1 = new MedicationDto(1, "MedA", "TypeA", compA, true);
        HealthcareCompany compB = new HealthcareCompany(2, "CompB");
        MedicationDto medication2 = new MedicationDto(2, "MedB", "TypeB", compB, true);
        List<Filter> list = new ArrayList<>();
        when(medicationService.getMedications(list)).thenReturn(Arrays.asList(medication1, medication2));
        assertEquals(medicationService.getMedications(list).size(), 2);
        assertEquals(medicationService.getMedications(list).get(0), medication1);
        assertEquals(medicationService.getMedications(list).get(1), medication2);
        assertEquals(medicationService.getMedications(list).get(0).getCompany(), compA);
        assertEquals(medicationService.getMedications(list).get(1).getCompany(), compB);
        assertNotEquals(medicationService.getMedications(list).get(1), null);
    }

    @Test
    public void testInsertMedication() {
        HealthcareCompany compA = new HealthcareCompany(1, "CompA");
        MedicationDto medication1 = new MedicationDto(1, "MedA", "TypeA", compA, true);
        Medication medication2 = new Medication(2, "MedA", "TypeA", 1, true);
        Medication medication3 = new Medication(2, "MedA", "TypeB", 1, true);

        when(medicationRepository.getMedications(new ArrayList<>())).thenReturn(Arrays.asList(medication2));
        // when -  action or the behaviour that we are going test
        int ins2 = medicationService.insertMedication(medication1);
        // then - verify the output
        assertEquals(2,ins2);
    }

    @Test
    public void testDeleteMedication() {
        HealthcareCompany compA = new HealthcareCompany(1, "CompA");
        MedicationDto medication1 = new MedicationDto(1, "MedA", "TypeA", compA, true);
        medicationService.insertMedication(medication1);
        verify(medicationService, times(1)).insertMedication(medication1);
        ArgumentCaptor<MedicationDto> medArgumentCaptor = ArgumentCaptor.forClass(MedicationDto.class);
        verify(medicationService).insertMedication(medArgumentCaptor.capture());
        assertEquals(1, medArgumentCaptor.getValue().getIdMedication());
    }



}
