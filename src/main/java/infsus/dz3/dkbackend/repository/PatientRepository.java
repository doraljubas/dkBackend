package infsus.dz3.dkbackend.repository;

import infsus.dz3.dkbackend.model.Patient;
import infsus.dz3.dkbackend.utils.filters.FilterHelper;
import infsus.dz3.dkbackend.utils.filters.domain.Filter;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class PatientRepository {
    private static final Map<String, Pair<String, String>> tableAndColumnOptions=Map.ofEntries(
            Map.entry("idDoctor", Pair.of("patient", "id_doctor")),
            Map.entry("name", Pair.of("healthcareuser", "name")),
            Map.entry("surname", Pair.of("healthcareuser", "surname")),
            Map.entry("mbo", Pair.of("healthcareuser", "mbo"))
    );

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Patient> getPatients( List<Filter> filters){

        StringBuilder SQL=new StringBuilder("SELECT * FROM patient NATURAL JOIN healthcareuser ");
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        SQL= FilterHelper.determineAndAppendWhereClause(SQL, filters, namedParameters, tableAndColumnOptions);
        return namedParameterJdbcTemplate.query(SQL.toString(),namedParameters, BeanPropertyRowMapper.newInstance(Patient.class));
    }

    public Patient getPatient(long patientId){
        String SQL="SELECT * FROM patient NATURAL JOIN healthcareuser JOIN doctor ON doctor.id_doctor=patient.id_doctor WHERE id_patient = :patientId";
        SqlParameterSource namedParameters = new MapSqlParameterSource("patientId", patientId);
        return namedParameterJdbcTemplate.queryForObject(SQL,namedParameters, BeanPropertyRowMapper.newInstance(Patient.class));
    }



}
