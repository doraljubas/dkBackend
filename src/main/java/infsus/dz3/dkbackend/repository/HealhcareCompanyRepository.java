package infsus.dz3.dkbackend.repository;

import infsus.dz3.dkbackend.model.HealthcareCompany;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class HealhcareCompanyRepository {
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public HealthcareCompany getHealhcareCompany(long id){
        String SQL="SELECT * FROM HealthcareCompany WHERE id_company = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.queryForObject(SQL, namedParameters, BeanPropertyRowMapper.newInstance(HealthcareCompany.class));
    }
}
