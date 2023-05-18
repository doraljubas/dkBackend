package infsus.dz3.dkbackend.repository;

import infsus.dz3.dkbackend.model.Patient;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@AllArgsConstructor
public class PatientRepository {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Patient> getPatients(long doctorId){
        String SQL="SELECT * FROM patient NATURAL JOIN healthcareuser WHERE id_doctor = :doctorId";
        SqlParameterSource namedParameters = new MapSqlParameterSource("doctorId", doctorId);
        return namedParameterJdbcTemplate.query(SQL,namedParameters, BeanPropertyRowMapper.newInstance(Patient.class));
    }

    public Patient getPatient(long patientId){
        String SQL="SELECT * FROM patient NATURAL JOIN healthcareuser JOIN doctor ON doctor.id_doctor=patient.id_doctor WHERE id_patient = :patientId";
        SqlParameterSource namedParameters = new MapSqlParameterSource("patientId", patientId);
        return namedParameterJdbcTemplate.queryForObject(SQL,namedParameters, BeanPropertyRowMapper.newInstance(Patient.class));
    }



}
