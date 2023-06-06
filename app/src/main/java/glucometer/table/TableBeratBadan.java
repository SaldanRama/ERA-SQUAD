package glucometer.table;

import java.sql.SQLException;
import glucometer.abstract_db.AbstractDbBeratBadan;
import glucometer.models.BeratBadan;
import glucometer.scenes.MainScene;
import glucometer.scenes.SceneBeratBadan;
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


public class TableBeratBadan extends Scene {
    private static ObservableList<BeratBadan> beratBadanList = FXCollections.observableArrayList();

    public TableBeratBadan(Stage stage) {
        super(new BorderPane(), 480, 480);
        AbstractDbBeratBadan daoBeratBadan = new AbstractDbBeratBadan();

        // Menghapus data yang sudah ada di dalam beratBadanList
        beratBadanList.clear();

        try {
            beratBadanList.addAll(daoBeratBadan.getAll());
            System.out.println(beratBadanList.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        Label titleLabel = new Label("Berat Badan");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        BorderPane.setAlignment(titleLabel, Pos.CENTER);

        Button tambahButton = new Button("Tambah");
        Image tambahImage = new Image("D:/SEMESTER 2/PRAKTIKUM/PROJECT_AKHIR_OOP/ERA-SQUAD/app/src/main/resources/images/add.png");
        ImageView tambahImageView = new ImageView(tambahImage);
        tambahImageView.setFitWidth(16); 
        tambahImageView.setFitHeight(16); 
        tambahButton.setGraphic(tambahImageView);
        tambahButton.setOnAction(event -> {
            SceneBeratBadan scBeratBadan = new SceneBeratBadan(stage, beratBadanList);
            stage.setScene(scBeratBadan);
        });

        TableView<BeratBadan> tableBeratBadan = new TableView<>();
        TableColumn<BeratBadan, Integer> column1 = new TableColumn<>("Berat Badan");
        TableColumn<BeratBadan, String> column2 = new TableColumn<>("Catatan");
        TableColumn<BeratBadan, String> column3 = new TableColumn<>("Tanggal");

        column1.setCellValueFactory(new PropertyValueFactory<>("beratBadan"));
        column2.setCellValueFactory(new PropertyValueFactory<>("catatan"));
        column3.setCellValueFactory(new PropertyValueFactory<>("tanggal"));

        column1.prefWidthProperty().bind(tableBeratBadan.widthProperty().multiply(0.3));
        column2.prefWidthProperty().bind(tableBeratBadan.widthProperty().multiply(0.35));
        column3.prefWidthProperty().bind(tableBeratBadan.widthProperty().multiply(0.35));

        tableBeratBadan.getColumns().addAll(column1, column2, column3); //untuk tampung tabel
        tableBeratBadan.setItems(beratBadanList);

        TextField tfBeratBadan = new TextField();
        tfBeratBadan.setPromptText("Berat Badan");
        TextField tfCatatan = new TextField();
        tfCatatan.setPromptText("Catatan");
       
        Button kembaliButton = new Button("Kembali");
        Image kembaliImage = new Image("D:/SEMESTER 2/PRAKTIKUM/PROJECT_AKHIR_OOP/ERA-SQUAD/app/src/main/resources/images/left.png");
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
        buttonBox.getChildren().add(kembaliButton);

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(tambahButton, tableBeratBadan, buttonBox);

        root.setTop(titleLabel);
        root.setCenter(vbox);

        setRoot(root);
    }
}
