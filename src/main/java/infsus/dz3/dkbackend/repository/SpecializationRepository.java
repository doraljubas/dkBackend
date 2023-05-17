package infsus.dz3.dkbackend.repository;


import infsus.dz3.dkbackend.model.Specialization;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class SpecializationRepository {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Specialization getSpecialization(long specializationId){
        String SQL="SELECT * FROM specialization WHERE id_specialization = :specializationId";
        SqlParameterSource namedParameters = new MapSqlParameterSource("specializationId", specializationId);
        return namedParameterJdbcTemplate.queryForObject(SQL,namedParameters, BeanPropertyRowMapper.newInstance(Specialization.class));
    }
}
