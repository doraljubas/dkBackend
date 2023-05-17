package infsus.dz3.dkbackend.repository;

import infsus.dz3.dkbackend.model.Facility;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor

public class FacilityRepository {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Facility getFacility(long facilityId){
        String SQL="SELECT * FROM facility WHERE id_facility = :facilityId";
        SqlParameterSource namedParameters = new MapSqlParameterSource("facilityId", facilityId);
        return namedParameterJdbcTemplate.queryForObject(SQL,namedParameters, BeanPropertyRowMapper.newInstance(Facility.class));
    }
}
