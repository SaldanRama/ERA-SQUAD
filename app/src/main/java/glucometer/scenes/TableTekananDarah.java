package glucometer.scenes;

import java.sql.SQLException;

import glucometer.dataBase.DbTekananDarah;
import glucometer.models.TekananDarah;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TableTekananDarah extends Scene {
    private static ObservableList<TekananDarah> tekananDarahList = FXCollections.observableArrayList();

    public TableTekananDarah(Stage stage) {
        super(new VBox(), 480, 480);
        DbTekananDarah daoTekananDarah = new DbTekananDarah();

        // Menghapus data yang sudah ada di dalam tekananDarahList
        tekananDarahList.clear();

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
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-alignment: center;");
        titleLabel.setTextAlignment(TextAlignment.CENTER);

        Button tambahButton = new Button("Tambah");
        Image tambahImage = new Image("D:/SEMESTER 2/PRAKTIKUM/PROJECT_AKHIR_OOP/ERA-SQUAD/app/src/main/resources/images/add.png");
        ImageView tambahImageView = new ImageView(tambahImage);
        tambahImageView.setFitWidth(16);
        tambahImageView.setFitHeight(16);
        tambahButton.setGraphic(tambahImageView);
        tambahButton.setOnAction(event -> {
            SceneTekananDarah scTekananDarah = new SceneTekananDarah(stage, tekananDarahList);
            stage.setScene(scTekananDarah);
        });

        TableView<TekananDarah> tableTekananDarah = new TableView<>();
        // Membuat Table Column
        TableColumn<TekananDarah, Integer> column1 = new TableColumn<>("Tekanan Sistolik");
        TableColumn<TekananDarah, Integer> column2 = new TableColumn<>("Tekanan Diastolik");
        TableColumn<TekananDarah, String> column3 = new TableColumn<>("Tangan");
        TableColumn<TekananDarah, String> column4 = new TableColumn<>("Catatan");
        TableColumn<TekananDarah, String> column5 = new TableColumn<>("Tanggal");

        // Pasangkan
        column1.setCellValueFactory(new PropertyValueFactory<>("tekananSistolik"));
        column2.setCellValueFactory(new PropertyValueFactory<>("tekananDiastolik"));
        column3.setCellValueFactory(new PropertyValueFactory<>("tangan"));
        column4.setCellValueFactory(new PropertyValueFactory<>("catatan"));
        column5.setCellValueFactory(new PropertyValueFactory<>("tanggal"));

        column1.prefWidthProperty().bind(tableTekananDarah.widthProperty().multiply(0.2));
        column2.prefWidthProperty().bind(tableTekananDarah.widthProperty().multiply(0.2));
        column3.prefWidthProperty().bind(tableTekananDarah.widthProperty().multiply(0.2));
        column4.prefWidthProperty().bind(tableTekananDarah.widthProperty().multiply(0.2));
        column5.prefWidthProperty().bind(tableTekananDarah.widthProperty().multiply(0.2));

        // tambah column ke table
        tableTekananDarah.getColumns().addAll(column1, column2, column3, column4, column5);

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
        TextField tfTanggal = new TextField();
        tfTanggal.setPromptText("Tanggal");

        Button kembaliButton = new Button("Kembali");
        Image kembaliImage = new Image("D:/SEMESTER 2/PRAKTIKUM/PROJECT_AKHIR_OOP/ERA-SQUAD/app/src/main/resources/images/left.png");
        ImageView kembaliImageView = new ImageView(kembaliImage);
        kembaliImageView.setFitWidth(16);
        kembaliImageView.setFitHeight(16);
        kembaliButton.setGraphic(kembaliImageView);
        kembaliButton.setOnAction(v -> {
            MainScene mainScene = new MainScene(stage);
            mainScene.show();
        });

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(tambahButton, kembaliButton);

        root.getChildren().addAll(titleLabel, buttonBox, tableTekananDarah);

        setRoot(root);
    }
}
