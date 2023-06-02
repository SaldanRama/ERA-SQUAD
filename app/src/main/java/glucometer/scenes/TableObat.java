package glucometer.scenes;

import java.sql.SQLException;

import glucometer.dataBase.DbObat;
import glucometer.models.Obat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TableObat extends Scene {
    private static ObservableList<Obat> obatList = FXCollections.observableArrayList();
    // private VBox rightSide = new VBox();


    public TableObat(Stage stage) {
        super(new VBox(), 480, 480);
        DbObat daoObat = new DbObat();
        try {
            obatList.addAll(daoObat.getAll());
            System.out.println(obatList.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Membuat tampilan scene
        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));

        Label titleLabel = new Label("Obat");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Button tambahButton = new Button("Tambah");
        tambahButton.setOnAction(event -> {
            SceneObat scObat = new SceneObat(stage, obatList);
            stage.setScene(scObat);
        });

        TableView<Obat> tableObat = new TableView<>();
        // MembuAT Table Coloumn
        TableColumn<Obat, String> coloumn1 = new TableColumn<>("Nama Obat");
        TableColumn<Obat, Integer> coloumn2 = new TableColumn<>("Dosis");
        TableColumn<Obat, String> coloumn3 = new TableColumn<>("Bentuk");
        TableColumn<Obat, String> coloumn4 = new TableColumn<>("Catatan");
        TableColumn<Obat, String> coloumn5 = new TableColumn<>("Tanggal");

        // Pasangkan
        coloumn1.setCellValueFactory(new PropertyValueFactory<>("namaObat"));
        coloumn2.setCellValueFactory(new PropertyValueFactory<>("dosis"));
        coloumn3.setCellValueFactory(new PropertyValueFactory<>("bentuk"));
        coloumn4.setCellValueFactory(new PropertyValueFactory<>("catatan"));
        coloumn5.setCellValueFactory(new PropertyValueFactory<>("tanggal"));

        coloumn1.prefWidthProperty().bind(tableObat.widthProperty().multiply(0.2));
        coloumn2.prefWidthProperty().bind(tableObat.widthProperty().multiply(0.2));
        coloumn3.prefWidthProperty().bind(tableObat.widthProperty().multiply(0.2));
        coloumn4.prefWidthProperty().bind(tableObat.widthProperty().multiply(0.2));
        coloumn5.prefWidthProperty().bind(tableObat.widthProperty().multiply(0.2));

        // tambah colum ke table
        tableObat.getColumns().addAll(coloumn1, coloumn2, coloumn3, coloumn4, coloumn5);

        // Kasi nilai
        tableObat.setItems(obatList);

        TextField tfNamaObat = new TextField();
        tfNamaObat.setPromptText("Nama Obat");
        TextField tfDosis = new TextField();
        tfDosis.setPromptText("Dosis");
        TextField tfBentuk = new TextField();
        tfBentuk.setPromptText("Bentuk");
        TextField tfCatatan = new TextField();
        tfCatatan.setPromptText("Catatan");
        TextField tfTanggal = new TextField();
        tfTanggal.setPromptText("Tanggal");

        Button kembaliButton = new Button("Kembali");
        kembaliButton.setOnAction(v -> {
            MainScene mainScene = new MainScene(stage);
            mainScene.show();
        });

        root.getChildren().addAll(titleLabel, tambahButton, kembaliButton, tableObat);

        setRoot(root);
    }
}
