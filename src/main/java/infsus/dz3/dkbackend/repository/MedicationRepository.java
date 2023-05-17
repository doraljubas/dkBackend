package infsus.dz3.dkbackend.repository;

import infsus.dz3.dkbackend.model.Medication;
import infsus.dz3.dkbackend.model.Report;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class MedicationRepository {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void insertMedication(Medication medication){
        String SQL=""" 
        INSERT INTO Medication(name_medication, type_medication, ID_company, inUseFlag)
        VALUES(:name_medication, :type_medication, :ID_company, TRUE)
        """;
        SqlParameterSource namedParameters = new MapSqlParameterSource(":name_medication", medication.getMedicationName())
                .addValue(":type_medication", medication.getMedicationType())
                .addValue(":ID_company", medication);
        namedParameterJdbcTemplate.update(SQL, namedParameters);
    }

    public void deleteMedication(int medicationId){
        String SQL="DELETE FROM Medication WHERE id_medication = :medicationId";
        SqlParameterSource namedParameters = new MapSqlParameterSource(":medicationId", medicationId);
        namedParameterJdbcTemplate.update(SQL, namedParameters);
    }
}
