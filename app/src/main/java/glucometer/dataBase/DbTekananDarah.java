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


public class DbTekananDarah {
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS tekananDarah (id INTEGER PRIMARY KEY AUTOINCREMENT, tekananSistolik INTEGER, tekananDiastolik INTEGER, tangan TEXT, catatan TEXT)";
    private static final String INSERT_QUERY = "INSERT INTO tekananDarah (tekananSistolik, tekananDiastolik, tangan, catatan) VALUES (?, ?, ?)";
    private Statement stmt;
    private Connection conn;

    public DbTekananDarah() {
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        try (Connection conn = DataBaseConfig.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(CREATE_TABLE_QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addData(TekananDarah tekananDarah) {
        try (Connection conn = DataBaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(INSERT_QUERY)) {
            stmt.setInt(1, tekananDarah.getTekananSistolik());
            stmt.setInt(2, tekananDarah.getTekananDiastolik());
            stmt.setString(3, tekananDarah.getTangan());
            stmt.setString(4, tekananDarah.getCatatan());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<TekananDarah> getAll() throws SQLException {
        ObservableList<TekananDarah> tekananDarahList = FXCollections.observableArrayList();

        try (Connection conn = DataBaseConfig.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM tekananDarah")) {
            while (rs.next()) {
                int tekananSistolik = rs.getInt("tekananSistolik");
                int tekananDiastolik = rs.getInt("tekananDiastolik");
                String tangan = rs.getString("tangan");
                String catatan = rs.getString("catatan");

                TekananDarah tekananDarahObj = new TekananDarah(tekananSistolik, tekananDiastolik, tangan, catatan);
                tekananDarahList.add(tekananDarahObj);
            }
        } catch (SQLException e) {
            throw new SQLException();
        }

        return tekananDarahList;
    }

    public void syncData(List<TekananDarah> listTekananDarah) {
        try {
            stmt.executeUpdate("DELETE from tekananDarah");
            stmt = conn.createStatement();
            for (TekananDarah tekanan : listTekananDarah) {
                String sql = String.format("""
                        INSERT INTO tekananDarah(tekananSistolik, tekananDistolik, tangan, catatan)
                        VALUES('%d', '%s');
                        """,
                        tekanan.getTekananSistolik(),
                        tekanan.getTekananDiastolik(),
                        tekanan.getTangan(),
                        tekanan.getCatatan());
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
}
}
