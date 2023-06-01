// package glucometer.dataBase;

// import java.sql.SQLException;
// import glucometer.utils.DataBaseConfig;

// public class DbController extends DataBaseConfig {

//     public static void getDatabase() {
//         getConnection();
//         try {
//             // query = "SELECT nama, harga, stok FROM tb_produk ORDER BY ID DESC";
//             query = "SELECT nama, harga, stok FROM Statistik";

//             preparedStatement = conn.prepareStatement(query);
//             resultSet = preparedStatement.executeQuery();

//             while(resultSet.next()) {
//                 System.out.println(
//                     String.format("%s - Rp.%d - Stok %d", resultSet.getString("nama"), resultSet.getInt("harga"), resultSet.getInt("stok"))
//                 );
//             }
//             preparedStatement.close();
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }

//     public static Produk getProdukByNama(String nama) {
//         Produk produk = null;
//         connection();
//         query = "SELECT * FROM tb_produk WHERE nama=?";
//         try {
//             preparedStatement = connection.prepareStatement(query);
//             preparedStatement.setString(1, nama);
//             resultSet = preparedStatement.executeQuery();
//             while (resultSet.next()) {
//                 produk = new Produk(resultSet.getInt("id"), resultSet.getString("nama"), resultSet.getLong("harga"), resultSet.getInt("stok"));
//             }
//             preparedStatement.close();
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//         return produk;
//     }

//     public static boolean insertData(String nama, long harga, int stok) {
//         connection();
//         query = "INSERT INTO tb_produk (nama, harga, stok) VALUES (?, ?, ?)";
//         try {
//             preparedStatement = connection.prepareStatement(query);
//             preparedStatement.setString(1, nama);
//             preparedStatement.setLong(2, harga);
//             preparedStatement.setInt(3, stok);
//             preparedStatement.executeUpdate();
//             preparedStatement.close();
//             return true;
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//         return false;
//     }

//     public static void updateNama(int id, String nama) {
//         connection();
//         query = "UPDATE tb_produk SET nama=? WHERE id=?";
//         try {
//             preparedStatement = connection.prepareStatement(query);
//             preparedStatement.setString(1, nama);
//             preparedStatement.setInt(2, id);
//             preparedStatement.executeUpdate();
//             preparedStatement.close();
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }

//     public static void updateHarga(int id, long harga) {
//         connection();
//         query = "UPDATE tb_produk SET harga=? WHERE id=?";
//         try {
//             preparedStatement = connection.prepareStatement(query);
//             preparedStatement.setLong(1, harga);
//             preparedStatement.setInt(2, id);
//             preparedStatement.executeUpdate();
//             preparedStatement.close();
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }

//     public static void updateStok(int id, int stok) {
//         connection();
//         query = "UPDATE tb_produk SET stok=? WHERE id=?";
//         try {
//             preparedStatement = connection.prepareStatement(query);
//             preparedStatement.setInt(1, stok);
//             preparedStatement.setInt(2, id);
//             preparedStatement.executeUpdate();
//             preparedStatement.close();
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }

//     public static boolean deleteData(String nama) {
//         connection();
//         query = "DELETE FROM tb_produk WHERE nama=?";
//         try {
//             preparedStatement = connection.prepareStatement(query);
//             preparedStatement.setString(1, nama);
//             int affectedRowDelete = preparedStatement.executeUpdate();
//             if (affectedRowDelete > 0) {
//                 return true;
//             }
//             preparedStatement.close();
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//         return false;
//     }
// }