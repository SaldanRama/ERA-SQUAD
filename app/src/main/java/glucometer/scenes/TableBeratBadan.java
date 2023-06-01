package glucometer.scenes;

import java.sql.SQLException;

import glucometer.dataBase.DbBeratBadan;
import glucometer.dataBase.DbGulaDarah;
import glucometer.models.BeratBadan;
import glucometer.models.GulaDarah;
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

public class TableBeratBadan extends Scene {
    private static ObservableList<BeratBadan> beratBadanList = FXCollections.observableArrayList();
    // private VBox rightSide = new VBox();

    public TableBeratBadan(Stage stage) {
        super(new VBox(), 480, 480);
        DbBeratBadan daoBeratBadan = new DbBeratBadan();

        try {
            beratBadanList.addAll(daoBeratBadan.getAll());
            System.out.println(beratBadanList.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }


        // Membuat tampilan scene
        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));

        Label titleLabel = new Label("Berat Badan");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

    
        Button tambahButton = new Button("Tambah");
        tambahButton.setOnAction(event -> {
            SceneBeratBadan scBeratBadan = new SceneBeratBadan(stage, beratBadanList);
            stage.setScene(scBeratBadan);
        });

        // Membuat Tabel View
        TableView<BeratBadan> tableBeratBadan = new TableView<>();
        // MembuAT Table Coloumn
        TableColumn<BeratBadan, Integer> coloumn1 = new TableColumn<>("Berat Badan");
        TableColumn<BeratBadan, String> coloumn2 = new TableColumn<>("Catatan");
        TableColumn<BeratBadan, String> coloumn3 = new TableColumn<>("Tanggal");

        // Pasangkan
        coloumn1.setCellValueFactory(new PropertyValueFactory<>("beratBadan"));
        coloumn2.setCellValueFactory(new PropertyValueFactory<>("catatan"));
        coloumn3.setCellValueFactory(new PropertyValueFactory<>("tanggal"));


        // tambah colum ke table
        tableBeratBadan.getColumns().addAll(coloumn1, coloumn2, coloumn3);

        // Kasi nilai
        tableBeratBadan.setItems(beratBadanList);

        TextField tfBeratBadan = new TextField();
        tfBeratBadan.setPromptText("Berat Badan");
        TextField tfCatatan = new TextField();
        tfCatatan.setPromptText("Catatan");
        TextField tfTanggal = new TextField();
        tfTanggal.setPromptText("Tanggal");

        // Button btnAdd = new Button("Tambah");
        // btnAdd.setOnAction(v -> {
        //     beratBadanList.add(
        //             new BeratBadan(Integer.parseInt(tfBeratBadan.getText()), tfCatatan.getText()));
        //     daoBeratBadan.syncData(beratBadanList);
        // });

        Button kembaliButton = new Button("Kembali");
        kembaliButton.setOnAction(v -> {
            MainScene mainScene = new MainScene(stage);
            mainScene.show();
        });

        root.getChildren().addAll(titleLabel, tambahButton, kembaliButton, tableBeratBadan);

        setRoot(root);
    }
}
