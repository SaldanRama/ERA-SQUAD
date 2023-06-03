package glucometer.dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import glucometer.models.GulaDarah;
import glucometer.utils.DataBaseConfig;

public class AbstractDbGulaDarah extends DbGulaDarah{
    private static final String INSERT_QUERY = "INSERT INTO gulaDarah (gulaDarah, waktu, catatan, tanggal) VALUES (?, ?, ?, ?)";

    @Override
    public void addData(GulaDarah gulaDarah) {
        try (Connection conn = DataBaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(INSERT_QUERY)) {
            stmt.setInt(1, gulaDarah.getGulaDarah());
            stmt.setString(2, gulaDarah.getWaktu());
            stmt.setString(3, gulaDarah.getCatatan());
            stmt.setString(4, gulaDarah.getTanggal());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
}
