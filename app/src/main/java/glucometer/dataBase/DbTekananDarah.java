package glucometer.dataBase;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import glucometer.config.DataBaseConfig;
import glucometer.models.TekananDarah;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public abstract class DbTekananDarah {
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS tekananDarah (id INTEGER PRIMARY KEY AUTOINCREMENT, tekananSistolik INTEGER, tekananDiastolik INTEGER, tangan TEXT, catatan TEXT, tanggal TEXT)";
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

    public abstract void addData(TekananDarah tekananDarah);

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
                String tanggal = rs.getString("tanggal");

                TekananDarah tekananDarahObj = new TekananDarah(tekananSistolik, tekananDiastolik, tangan, catatan, tanggal);
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
                        INSERT INTO tekananDarah(tekananSistolik, tekananDistolik, tangan, catatan, tanggal)
                        VALUES('%d', '%s');
                        """,
                        tekanan.getTekananSistolik(),
                        tekanan.getTekananDiastolik(),
                        tekanan.getTangan(),
                        tekanan.getCatatan());
                        tekanan.getTanggal();
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
}
}
