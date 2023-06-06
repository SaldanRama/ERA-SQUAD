package glucometer.dataBase;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import glucometer.config.DataBaseConfig;
import glucometer.models.Obat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public abstract class DbObat {
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS obat (id INTEGER PRIMARY KEY AUTOINCREMENT, namaObat TEXT, dosis INTEGER, bentuk TEXT, catatan TEXT, tanggal TEXT)";
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

    public abstract void addData(Obat obat);

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
                String tanggal = rs.getString("tanggal");

                Obat obatObj = new Obat(namaObat, dosis, bentuk, catatan, tanggal);
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
                        INSERT INTO obat(namaObat, dosis, bentuk, catatan, tanggal)
                        VALUES('%d', '%s');
                        """,
                        obat.getNamaObat(),
                        obat.getDosis(),
                        obat.getBentuk(),
                        obat.getCatatan(),
                        obat.getTanggal());
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
}
}
