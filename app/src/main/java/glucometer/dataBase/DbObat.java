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
import glucometer.models.Obat;
import glucometer.models.TekananDarah;
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


public class DbObat {
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS obat (id INTEGER PRIMARY KEY AUTOINCREMENT, namaObat TEXT, dosis TEXT, bentuk TEXT, catatan TEXT)";
    private static final String INSERT_QUERY = "INSERT INTO obat (namaObat, dosis, bentuk, catatan) VALUES (?, ?, ?)";
    private Statement stmt;
    private Connection conn;

    public DbObat() {
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        try (Connection conn = DataBaseConfig.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(CREATE_TABLE_QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addData(Obat obat) {
        try (Connection conn = DataBaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(INSERT_QUERY)) {
            stmt.setString(1, obat.getNamaObat());
            stmt.setInt(2, obat.getDosis());
            stmt.setString(3, obat.getBentuk());
            stmt.setString(4, obat.getCatatan());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Obat> getAll() throws SQLException {
        ObservableList<Obat> obatList = FXCollections.observableArrayList();

        try (Connection conn = DataBaseConfig.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM obat")) {
            while (rs.next()) {
                String namaObat = rs.getString("namaObat");
                int dosis = rs.getInt("dosis");
                String bentuk = rs.getString("bentuk");
                String catatan = rs.getString("catatan");

                Obat obatObj = new Obat(namaObat, dosis, bentuk, catatan);
                obatList.add(obatObj);
            }
        } catch (SQLException e) {
            throw new SQLException();
        }

        return obatList;
    }

    public void syncData(List<Obat> listObat) {
        try {
            stmt.executeUpdate("DELETE from obat");
            stmt = conn.createStatement();
            for (Obat obat : listObat) {
                String sql = String.format("""
                        INSERT INTO tekananDarah(namaObat, dosis, bentuk, catatan)
                        VALUES('%d', '%s');
                        """,
                        obat.getNamaObat(),
                        obat.getDosis(),
                        obat.getBentuk(),
                        obat.getCatatan());
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
}
}
