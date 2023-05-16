package infsus.dz3.dkbackend.repository;

import infsus.dz3.dkbackend.model.Patient;
import infsus.dz3.dkbackend.model.Prescription;
import infsus.dz3.dkbackend.model.Report;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class DoctorRepository {
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Patient> getPatients(int doctorId){
        String SQL="SELECT * FROM patient NATURAL JOIN healthcareuser WHERE id_doctor = :doctorId";
        SqlParameterSource namedParameters = new MapSqlParameterSource("doctorId", doctorId);
        return namedParameterJdbcTemplate.query(SQL,namedParameters, BeanPropertyRowMapper.newInstance(Patient.class));
    }

    public List<Report> getPatientReports(int patientId){
        String SQL="SELECT * FROM patient NATURAL JOIN report WHERE id_patient = :patientId";
        SqlParameterSource namedParameters = new MapSqlParameterSource("patientId", patientId);
        return namedParameterJdbcTemplate.query(SQL,namedParameters, BeanPropertyRowMapper.newInstance(Report.class));
    }

    public List<Prescription> getPrescriptions(int reportId){
        String SQL="SELECT * FROM report NATURAL JOIN prescription NATURAL JOIN medication WHERE id_report = :reportId";
        SqlParameterSource namedParameters = new MapSqlParameterSource("reportId", reportId);
        return namedParameterJdbcTemplate.query(SQL,namedParameters, BeanPropertyRowMapper.newInstance(Prescription.class));
    }

}
