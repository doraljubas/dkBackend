package infsus.dz3.dkbackend;
import infsus.dz3.dkbackend.model.Medication;
import infsus.dz3.dkbackend.repository.MedicationRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Medication.class)
@TestPropertySource(locations = "classpath:app_testing.properties")
public class RepositoryTests {
    @Mock
    private MedicationRepository medicationRepository;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    SqlParameterSource namedParameters;

    Medication medicationIn1;
    Medication medicationIn2;
    @BeforeEach
    void setup(){
        List<String> comp_names = Arrays.asList("Belupo","Sandoz");
        String SQL_companies = "INSERT INTO HealthcareCompany(company_name) VALUES(:compName);";
        for(String name : comp_names){
            namedParameters = new MapSqlParameterSource("name_medication", name);
            namedParameterJdbcTemplate.update(SQL_companies, namedParameters);
        }
        medicationIn1 = new Medication(1, "MedA", "TypeA", 1, true);
        medicationIn2 = new Medication(2, "MedB", "TypeB", 1, true);
        List<Medication> meds = Arrays.asList(medicationIn1,medicationIn2);
        String SQL_meds = """
                       INSERT INTO Medication(name_medication, type_medication, ID_company, inUseFlag)
                       VALUES(:name_medication, :type_medication, :ID_company, TRUE)""";
        for(Medication medication : meds){
            namedParameters = new MapSqlParameterSource("name_medication", medication.getNameMedication())
                    .addValue("type_medication", medication.getTypeMedication())
                    .addValue("ID_company", medication.getIdCompany());
            namedParameterJdbcTemplate.update(SQL_meds, namedParameters);
        }
    }
    @AfterEach
    void clear(){
        String clear_comp = "DELETE FROM HealthcareCompany WHERE 1=1";
        String clear_meds = "DELETE FROM Medication WHERE 1=1";
    }

    @Test
    public void testGetMedications() {
        assertEquals(2, medicationRepository.getMedications(new ArrayList<>()).size());
        assertEquals(medicationIn1.getNameMedication(), medicationRepository.getMedications(new ArrayList<>()).get(0).getNameMedication());
        assertEquals(medicationIn2.getNameMedication(), medicationRepository.getMedications(new ArrayList<>()).get(1).getNameMedication());
    }
    @Test
    public void testInsertMedication() {
        Medication medication = new Medication(1, "MedC", "TypeC", 1, true);
        medicationRepository.insertMedication(medication);
        assertEquals(3, medicationRepository.getMedications(new ArrayList<>()).size());
        assertEquals(medication.getNameMedication(), medicationRepository.getMedications(new ArrayList<>()).get(2).getNameMedication());
    }




}
