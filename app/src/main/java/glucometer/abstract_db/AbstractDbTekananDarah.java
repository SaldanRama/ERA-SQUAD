package glucometer.abstract_db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import glucometer.config.DataBaseConfig;
import glucometer.dataBase.DbTekananDarah;
import glucometer.models.TekananDarah;

public class AbstractDbTekananDarah extends DbTekananDarah{
    private static final String INSERT_QUERY = "INSERT INTO tekananDarah (tekananSistolik, tekananDiastolik, tangan, catatan, tanggal) VALUES (?, ?, ?, ?, ?)";

    @Override
    public void addData(TekananDarah tekananDarah) {
        try (Connection conn = DataBaseConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(INSERT_QUERY)) {
            stmt.setInt(1, tekananDarah.getTekananSistolik());
            stmt.setInt(2, tekananDarah.getTekananDiastolik());
            stmt.setString(3, tekananDarah.getTangan());
            stmt.setString(4, tekananDarah.getCatatan());
            stmt.setString(5, tekananDarah.getTanggal());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
