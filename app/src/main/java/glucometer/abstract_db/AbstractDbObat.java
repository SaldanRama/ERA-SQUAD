package glucometer.abstract_db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import glucometer.config.DataBaseConfig;
import glucometer.dataBase.DbObat;
import glucometer.models.Obat;

public class AbstractDbObat extends DbObat {
    private static final String INSERT_QUERY = "INSERT INTO obat (namaObat, dosis, bentuk, catatan, tanggal) VALUES (?, ?, ?, ?, ?)";

    @Override
    public void addData(Obat obat) {
        try (Connection conn = DataBaseConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(INSERT_QUERY)) {
            stmt.setString(1, obat.getNamaObat());
            stmt.setInt(2, obat.getDosis());
            stmt.setString(3, obat.getBentuk());
            stmt.setString(4, obat.getCatatan());
            stmt.setString(5, obat.getTanggal());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
}
