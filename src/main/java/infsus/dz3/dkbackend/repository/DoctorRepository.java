package infsus.dz3.dkbackend.repository;

import infsus.dz3.dkbackend.model.Doctor;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class DoctorRepository {
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Doctor getDoctor(int doctorId){
        String SQL="SELECT * FROM doctor NATURAL JOIN healthcareuser WHERE id_doctor = :doctorId";
        SqlParameterSource namedParameters = new MapSqlParameterSource("doctorId", doctorId);
        return namedParameterJdbcTemplate.queryForObject(SQL,namedParameters, BeanPropertyRowMapper.newInstance(Doctor.class));
    }
}
