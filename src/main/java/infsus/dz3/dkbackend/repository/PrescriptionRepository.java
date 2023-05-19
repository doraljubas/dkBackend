package infsus.dz3.dkbackend.repository;

import infsus.dz3.dkbackend.model.Prescription;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@AllArgsConstructor
public class PrescriptionRepository {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Prescription> getPrescriptions(long reportId){
        String SQL="SELECT * FROM report NATURAL JOIN prescription NATURAL JOIN medication WHERE id_report = :reportId";
        SqlParameterSource namedParameters = new MapSqlParameterSource("reportId", reportId);
        return namedParameterJdbcTemplate.query(SQL,namedParameters, BeanPropertyRowMapper.newInstance(Prescription.class));
    }

    public void insertPrescription(Prescription prescription){
        String SQL=""" 
                INSERT INTO Prescription(date_of_expiry, instructions, renewing, ID_medication, ID_report)
                VALUES(:date_of_expiry, :instructions, :renewing, :ID_medication, :ID_report)
                """;
        SqlParameterSource namedParameters = new MapSqlParameterSource("date_of_expiry", prescription.getDateOfExpiry())
                .addValue("instructions", prescription.getInstructions())
                .addValue("renewing", prescription.isRenewing())
                .addValue("ID_medication", prescription.getIdMedication())
                .addValue("ID_report", prescription.getIdReport());
        namedParameterJdbcTemplate.update(SQL, namedParameters);
    }

    public void deletePrescription(long prescriptionId){
        String SQL="DELETE FROM Prescription WHERE id_prescription = :prescriptionId";
        SqlParameterSource namedParameters = new MapSqlParameterSource("prescriptionId", prescriptionId);
        namedParameterJdbcTemplate.update(SQL, namedParameters);
    }

}
