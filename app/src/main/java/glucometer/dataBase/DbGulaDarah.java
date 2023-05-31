package glucometer.dataBase;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import glucometer.models.GulaDarah;
import glucometer.utils.DataBaseConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

//     public List<GulaDarah> getAll() throws SQLException {
//         try {
//             List<GulaDarah> listGulaDarah = new ArrayList<>();
//             stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery("SELECT * FROM gulaDarah");
//             while (rs.next()) {
//                 int gulaDarah = rs.getInt("Gula Darah");
//                 String catatan = rs.getString("Catatan");
//                 listGulaDarah.add(new GulaDarah(gulaDarah,, catatan));
//             }
//             return listGulaDarah;
//         } catch (SQLException e) {
//             throw new SQLException();
//         }
//     }

    // public void syncData(List<GulaDarah> listGulaDarah) {
    //     try {
    //         stmt.executeUpdate("DELETE from gulaDarah");
    //         stmt = conn.createStatement();
    //         for (GulaDarah obat : listGulaDarah) {
    //             String sql = String.format("""
    //                     INSERT INTO obats(gulaDarah, catatan)
    //                     VALUES('%d', '%s');
    //                     """,
    //                     obat.getGulaDarah(),
    //                     obat.getCatatan());
    //             stmt.executeUpdate(sql);
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }
// }



public class DbGulaDarah {
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS gula_darah (id INTEGER PRIMARY KEY AUTOINCREMENT, gula_darah INTEGER, waktu TEXT, catatan TEXT)";
    private static final String INSERT_QUERY = "INSERT INTO gula_darah (gula_darah, waktu, catatan) VALUES (?, ?, ?)";
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
                ResultSet rs = stmt.executeQuery("SELECT * FROM gula_darah")) {
            while (rs.next()) {
                int gulaDarah = rs.getInt("gula_darah");
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
            for (GulaDarah obat : listGulaDarah) {
                String sql = String.format("""
                        INSERT INTO obats(gulaDarah, catatan)
                        VALUES('%d', '%s');
                        """,
                        obat.getGulaDarah(),
                        obat.getCatatan());
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
}
}
