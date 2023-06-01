package glucometer.dataBase;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import glucometer.models.BeratBadan;
import glucometer.utils.DataBaseConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class DbBeratBadan {
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS beratBadan (id INTEGER PRIMARY KEY AUTOINCREMENT, beratBadan INTEGER, catatan TEXT, tanggal TEXT)";
    private static final String INSERT_QUERY = "INSERT INTO beratBadan (beratBadan, catatan, tanggal) VALUES (?, ?, ?)";
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
            stmt.setString(3, beratBadan.getTanggal());
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
                String tanggal = rs.getString("tanggal");

                BeratBadan beratBadanObj = new BeratBadan(beratBadan, catatan, tanggal);
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
                        INSERT INTO beratBadan(beratBadan, catatan, tanggal)
                        VALUES('%d', '%s');
                        """,
                        beratBadan.getBeratBadan(),
                        beratBadan.getCatatan());
                        beratBadan.getTanggal();
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
}
}
