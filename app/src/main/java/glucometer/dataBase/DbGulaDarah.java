package glucometer.dataBase;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import glucometer.models.GulaDarah;
import glucometer.utils.DataBaseConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class DbGulaDarah {
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS gulaDarah (id INTEGER PRIMARY KEY AUTOINCREMENT, gulaDarah INTEGER, waktu TEXT, catatan TEXT, tanggal TEXT)";
    private static final String INSERT_QUERY = "INSERT INTO gulaDarah (gulaDarah, waktu, catatan, tanggal) VALUES (?, ?, ?, ?)";
    private Statement stmt;
    private Connection conn;

    public DbGulaDarah() {
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        try (Connection conn = DataBaseConfig.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(CREATE_TABLE_QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    public ObservableList<GulaDarah> getAll() throws SQLException {
        ObservableList<GulaDarah> gulaDarahList = FXCollections.observableArrayList();

        try (Connection conn = DataBaseConfig.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM gulaDarah")) {
            while (rs.next()) {
                int gulaDarah = rs.getInt("gulaDarah");
                String waktu = rs.getString("waktu");
                String catatan = rs.getString("catatan");
                String tanggal = rs.getString("tanggal");

                GulaDarah gulaDarahObj = new GulaDarah(gulaDarah, waktu, catatan, tanggal);
                gulaDarahList.add(gulaDarahObj);
            }
        } catch (SQLException e) {
            throw new SQLException();
        }

        return gulaDarahList;
    }

    public void syncData(List<GulaDarah> listGulaDarah) {
        try {
            stmt.executeUpdate("DELETE from gulaDarah");
            stmt = conn.createStatement();
            for (GulaDarah gula : listGulaDarah) {
                String sql = String.format("""
                        INSERT INTO gulaDarah(gulaDarah, waktu, catatan, tanggal)
                        VALUES('%d', '%s');
                        """,
                        gula.getGulaDarah(),
                        gula.getWaktu(),
                        gula.getCatatan(),
                        gula.getTanggal());
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
}
}
