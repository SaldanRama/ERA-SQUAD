package glucometer.abstract_db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import glucometer.config.DataBaseConfig;
import glucometer.dataBase.DbBeratBadan;
import glucometer.models.BeratBadan;

public class AbstractDbBeratBadan extends DbBeratBadan {
    private static final String INSERT_QUERY = "INSERT INTO beratBadan (beratBadan, catatan, tanggal) VALUES (?, ?, ?)";

    @Override
    public void addData(BeratBadan beratBadan) {
        try (Connection conn = DataBaseConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(INSERT_QUERY)) {
            stmt.setInt(1, beratBadan.getBeratBadan());
            stmt.setString(2, beratBadan.getCatatan());
            stmt.setString(3, beratBadan.getTanggal());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
