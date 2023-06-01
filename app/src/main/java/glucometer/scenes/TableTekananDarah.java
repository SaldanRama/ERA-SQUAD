package glucometer.scenes;

import java.sql.SQLException;

import glucometer.dataBase.DbGulaDarah;
import glucometer.dataBase.DbTekananDarah;
import glucometer.models.GulaDarah;
import glucometer.models.TekananDarah;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TableTekananDarah extends Scene {
    private static ObservableList<TekananDarah> tekananDarahList = FXCollections.observableArrayList();
    private VBox rightSide = new VBox();


    public TableTekananDarah(Stage stage) {
        super(new VBox(), 480, 480);
        DbTekananDarah daoTekananDarah = new DbTekananDarah();
        try {
            tekananDarahList.addAll(daoTekananDarah.getAll());
            System.out.println(tekananDarahList.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Membuat tampilan scene
        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));

        Label titleLabel = new Label("Tekanan Darah");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Button tambahButton = new Button("Tambah");
        tambahButton.setOnAction(event -> {
            SceneTekananDarah scTekananDarah = new SceneTekananDarah(stage, tekananDarahList);
            stage.setScene(scTekananDarah);
        });


        TableView<TekananDarah> tableTekananDarah = new TableView<>();
        // MembuAT Table Coloumn
        TableColumn<TekananDarah, Integer> coloumn1 = new TableColumn<>("Tekanan Sistolik");
        TableColumn<TekananDarah, Integer> coloumn2 = new TableColumn<>("Tekanan Diastolik");
        TableColumn<TekananDarah, String> coloumn3 = new TableColumn<>("Tangan");
        TableColumn<TekananDarah, String> coloumn4 = new TableColumn<>("Catatan");


        // Pasangkan
        coloumn1.setCellValueFactory(new PropertyValueFactory<>("tekananSistolik"));
        coloumn2.setCellValueFactory(new PropertyValueFactory<>("tekananDiastolik"));
        coloumn3.setCellValueFactory(new PropertyValueFactory<>("tangan"));
        coloumn4.setCellValueFactory(new PropertyValueFactory<>("catatan"));


        // tambah colum ke table
        tableTekananDarah.getColumns().addAll(coloumn1, coloumn2, coloumn3, coloumn4);

        // Kasi nilai
        tableTekananDarah.setItems(tekananDarahList);

        TextField tfSistolik = new TextField();
        tfSistolik.setPromptText("Tekanan Sistolik");
        TextField tfDiastolik = new TextField();
        tfDiastolik.setPromptText("Tekanan Diastolik");
        TextField tfTangan = new TextField();
        tfTangan.setPromptText("Tangan");
        TextField tfCatatan = new TextField();
        tfCatatan.setPromptText("Catatan");

        Button btnAdd = new Button("Tambah");
        btnAdd.setOnAction(v -> {
            tekananDarahList.add(
                    new TekananDarah(Integer.parseInt(tfSistolik.getText()), Integer.parseInt(tfDiastolik.getText()), tfTangan.getText(), tfCatatan.getText()));
            daoTekananDarah.syncData(tekananDarahList);
        });

        Button kembaliButton = new Button("Kembali");
        kembaliButton.setOnAction(v -> {
            MainScene mainScene = new MainScene(stage);
            mainScene.show();
        });

        root.getChildren().addAll(titleLabel, tambahButton, kembaliButton, tableTekananDarah);

        setRoot(root);
    }
}
