package infsus.dz3.dkbackend.repository;

import infsus.dz3.dkbackend.model.Patient;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.List;

public class PatientRepository {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Patient> getPatients(int doctorId){
        String SQL="SELECT * FROM patient NATURAL JOIN healthcareuser WHERE id_doctor = :doctorId";
        SqlParameterSource namedParameters = new MapSqlParameterSource("doctorId", doctorId);
        return namedParameterJdbcTemplate.query(SQL,namedParameters, BeanPropertyRowMapper.newInstance(Patient.class));
    }
}
