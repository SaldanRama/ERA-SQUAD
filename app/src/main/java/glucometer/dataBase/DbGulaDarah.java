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

<<<<<<< HEAD
=======

// public class DbGulaDarah {
//     private Connection conn;
//     private Statement stmt;

//     public DbGulaDarah() {
//         conn = DataBaseConfig.getConnection();
//         setupTable();
//     }

//     private void setupTable() {
//         try {
//             DatabaseMetaData meta = conn.getMetaData();
//             ResultSet rs = meta.getTables(null, null, "gulaDarah", null);
//             if (!rs.next()) {
//                 stmt = conn.createStatement();
//                 String sql = "CREATE TABLE gulaDarah " +
//                         "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                         " konsentrasiGula INTEGER NOT NULL, " +
//                         " catatan TEXT NOT NULL)";
//                 stmt.executeUpdate(sql);
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }

>>>>>>> c3644c56205a812715fa598d2b583cf597bd2a3d


public class DbGulaDarah {
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS gulaDarah (id INTEGER PRIMARY KEY AUTOINCREMENT, gulaDarah INTEGER, waktu TEXT, catatan TEXT)";
    private static final String INSERT_QUERY = "INSERT INTO gulaDarah (gulaDarah, waktu, catatan) VALUES (?, ?, ?)";
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

                GulaDarah gulaDarahObj = new GulaDarah(gulaDarah, waktu, catatan);
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
                        INSERT INTO gulaDarah(gulaDarah, catatan)
                        VALUES('%d', '%s');
                        """,
                        gula.getGulaDarah(),
                        gula.getCatatan());
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
}
}
