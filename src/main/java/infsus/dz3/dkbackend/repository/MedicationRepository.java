package infsus.dz3.dkbackend.repository;

import infsus.dz3.dkbackend.model.Medication;
import infsus.dz3.dkbackend.model.Report;
import infsus.dz3.dkbackend.utils.filters.FilterHelper;
import infsus.dz3.dkbackend.utils.filters.domain.Filter;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class MedicationRepository {
    private static final Map<String, Pair<String, String>> tableAndColumnOptions=Map.ofEntries(
            Map.entry("idMedication", Pair.of("Medication", "id_medication")),
            Map.entry("nameMedication", Pair.of("Medication", "name_medication")),
            Map.entry("typeMedication", Pair.of("Medication", "type_medication")),
            Map.entry("idCompany", Pair.of("Medication", "ID_company")),
            Map.entry("inUseFlag", Pair.of("Medication", "inUseFlag"))
    );
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void insertMedication(Medication medication){
        String SQL=""" 
        INSERT INTO Medication(name_medication, type_medication, ID_company, inUseFlag)
        VALUES(:name_medication, :type_medication, :ID_company, TRUE)
        """;
        SqlParameterSource namedParameters = new MapSqlParameterSource(":name_medication", medication.getNameMedication())
                .addValue(":type_medication", medication.getTypeMedication())
                .addValue(":ID_company", medication);
        namedParameterJdbcTemplate.update(SQL, namedParameters);
    }

    public void deleteMedication(int medicationId){
        String SQL="DELETE FROM Medication WHERE id_medication = :medicationId";
        SqlParameterSource namedParameters = new MapSqlParameterSource(":medicationId", medicationId);
        namedParameterJdbcTemplate.update(SQL, namedParameters);
    }

    public List<Medication> getMedications(List<Filter> filters) {
        StringBuilder SQL=new StringBuilder("SELECT * FROM Medication");
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        SQL= FilterHelper.determineAndAppendWhereClause(SQL, filters, namedParameters, tableAndColumnOptions);
        return namedParameterJdbcTemplate.query(SQL.toString(),namedParameters, BeanPropertyRowMapper.newInstance(Medication.class));
    }
}
