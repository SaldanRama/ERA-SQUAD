package glucometer.scenes;

import java.sql.SQLException;

import glucometer.dataBase.DbObat;
import glucometer.models.Obat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TableObat extends Scene {
    private static ObservableList<Obat> obatList = FXCollections.observableArrayList();

    public TableObat(Stage stage) {
        super(new BorderPane(), 480, 480);
        DbObat daoObat = new DbObat();
        
        // Menghapus data yang sudah ada di dalam obatList
        obatList.clear();

        try {
            obatList.addAll(daoObat.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        Label titleLabel = new Label("Obat");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        BorderPane.setAlignment(titleLabel, Pos.CENTER);

        TableView<Obat> tableObat = new TableView<>();
        TableColumn<Obat, String> column1 = new TableColumn<>("Nama Obat");
        TableColumn<Obat, Integer> column2 = new TableColumn<>("Dosis");
        TableColumn<Obat, String> column3 = new TableColumn<>("Bentuk");
        TableColumn<Obat, String> column4 = new TableColumn<>("Catatan");
        TableColumn<Obat, String> column5 = new TableColumn<>("Tanggal");

        column1.setCellValueFactory(new PropertyValueFactory<>("namaObat"));
        column2.setCellValueFactory(new PropertyValueFactory<>("dosis"));
        column3.setCellValueFactory(new PropertyValueFactory<>("bentuk"));
        column4.setCellValueFactory(new PropertyValueFactory<>("catatan"));
        column5.setCellValueFactory(new PropertyValueFactory<>("tanggal"));

        tableObat.getColumns().addAll(column1, column2, column3, column4, column5);
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

        Button tambahButton = new Button("Tambah");
        Image tambahImage = new Image("F:/New folder (3)/ERA-SQUAD/app/src/main/resources/images/add.png");
        ImageView tambahImageView = new ImageView(tambahImage);
        tambahImageView.setFitWidth(16); 
        tambahImageView.setFitHeight(16); 
        tambahButton.setGraphic(tambahImageView);
        tambahButton.setOnAction(event -> {
            SceneObat scObat = new SceneObat(stage, obatList);
            stage.setScene(scObat);
        });

        Button kembaliButton = new Button("Kembali");
        Image kembaliImage = new Image("F:/New folder (3)/ERA-SQUAD/app/src/main/resources/images/left.png");
        ImageView kembaliImageView = new ImageView(kembaliImage);
        kembaliImageView.setFitWidth(16); // Atur lebar gambar
        kembaliImageView.setFitHeight(16); // Atur tinggi gambar
        kembaliButton.setGraphic(kembaliImageView);
        kembaliButton.setOnAction(v -> {
            MainScene mainScene = new MainScene(stage);
            mainScene.show();
        });

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(tambahButton, kembaliButton);

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(tableObat, buttonBox);
        vbox.setPadding(new Insets(10));

        root.setTop(titleLabel);
        root.setCenter(vbox);
        BorderPane.setAlignment(vbox, Pos.CENTER);

        setRoot(root);
    }
}
