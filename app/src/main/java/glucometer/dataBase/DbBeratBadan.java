package glucometer.dataBase;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import glucometer.models.BeratBadan;
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


public class DbBeratBadan {
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS beratBadan (id INTEGER PRIMARY KEY AUTOINCREMENT, beratBadan INTEGER, catatan TEXT)";
    private static final String INSERT_QUERY = "INSERT INTO beratBadan (beratBadan, catatan) VALUES (?, ?, ?)";
    private Statement stmt;
    private Connection conn;

    public DbBeratBadan() {
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        try (Connection conn = DataBaseConfig.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(CREATE_TABLE_QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addData(BeratBadan beratBadan) {
        try (Connection conn = DataBaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(INSERT_QUERY)) {
            stmt.setInt(1, beratBadan.getBeratBadan());
            stmt.setString(2, beratBadan.getCatatan());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<BeratBadan> getAll() throws SQLException {
        ObservableList<BeratBadan> beratBadanList = FXCollections.observableArrayList();

        try (Connection conn = DataBaseConfig.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM beratBadan")) {
            while (rs.next()) {
                int beratBadan = rs.getInt("beratBadan");
                String catatan = rs.getString("catatan");

                BeratBadan beratBadanObj = new BeratBadan(beratBadan, catatan);
                beratBadanList.add(beratBadanObj);
            }
        } catch (SQLException e) {
            throw new SQLException();
        }

        return beratBadanList;
    }

    public void syncData(List<BeratBadan> listBeratBadan) {
        try {
            stmt.executeUpdate("DELETE from beratBadan");
            stmt = conn.createStatement();
            for (BeratBadan beratBadan : listBeratBadan) {
                String sql = String.format("""
                        INSERT INTO beratBadan(beratBadan, catatan)
                        VALUES('%d', '%s');
                        """,
                        beratBadan.getBeratBadan(),
                        beratBadan.getCatatan());
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
}
}
