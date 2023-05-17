package infsus.dz3.dkbackend.repository;

import infsus.dz3.dkbackend.model.Report;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor

public class ReportRepository {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Report> getReports(long patientId){
        String SQL="SELECT * FROM patient NATURAL JOIN report WHERE id_patient = :patientId and id_doctor = 5";
        SqlParameterSource namedParameters = new MapSqlParameterSource("patientId", patientId);
        return namedParameterJdbcTemplate.query(SQL,namedParameters, BeanPropertyRowMapper.newInstance(Report.class));
    }

    public int insertReport(Report report){
        String SQL=""" 
        INSERT INTO Report(date_report, diagnosis, recomendation, anamnesis, ID_patient, ID_doctor)
        VALUES(:date_report, :diagnosis, :recomendation, :anamnesis, :ID_patient, :ID_doctor)
        """;
        SqlParameterSource namedParameters = new MapSqlParameterSource(":date_report", report.getDateReport())
                .addValue(":diagnosis", report.getDiagnosis())
                .addValue(":recomendation", report.getRecomendation())
                .addValue(":anamnesis", report.getAnamnesis())
                .addValue(":ID_patient", report.getIdPatient())
                .addValue(":ID_doctor", report.getIdDoctor());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(SQL, namedParameters, keyHolder);
        return keyHolder.getKey().intValue();
    }

    public void deleteReport(long reportId){
        String SQL="DELETE FROM Report WHERE id_report = :reportId";
        SqlParameterSource namedParameters = new MapSqlParameterSource(":reportId", reportId);
        namedParameterJdbcTemplate.update(SQL, namedParameters);
    }
}
