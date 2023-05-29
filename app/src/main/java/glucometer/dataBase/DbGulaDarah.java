package glucometer.dataBase;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import glucometer.models.GulaDarah;
import glucometer.utils.DataBaseConfig;

public class DbGulaDarah {
    private Connection conn;
    private Statement stmt;

    public DbGulaDarah() {
        conn = DataBaseConfig.getConnection();
        setupTable();
    }

    private void setupTable() {
        try {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getTables(null, null, "gulaDarah", null);
            if (!rs.next()) {
                stmt = conn.createStatement();
                String sql = "CREATE TABLE gulaDarah " +
                        "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        " konsentrasiGula INTEGER NOT NULL, " +
                        " catatan TEXT NOT NULL)";
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<GulaDarah> getAll() throws SQLException {
        try {
            List<GulaDarah> listGulaDarah = new ArrayList<>();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM gulaDarah");
            while (rs.next()) {
                int gulaDarah = rs.getInt("Gula Darah");
                String catatan = rs.getString("Catatan");
                listGulaDarah.add(new GulaDarah(gulaDarah, catatan));
            }
            return listGulaDarah;
        } catch (SQLException e) {
            throw new SQLException();
        }
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

