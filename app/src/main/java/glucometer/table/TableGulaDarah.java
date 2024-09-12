package glucometer.table;

import java.sql.SQLException;

import glucometer.abstract_db.AbstractDbGulaDarah;
import glucometer.models.GulaDarah;
import glucometer.scenes.MainScene;
import glucometer.scenes.SceneGulaDarah;
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

public class TableGulaDarah extends Scene {
    private static ObservableList<GulaDarah> gulaDarahList = FXCollections.observableArrayList();

    public TableGulaDarah(Stage stage) {
        super(new BorderPane(), 480, 480);
        AbstractDbGulaDarah daoGulaDarah = new AbstractDbGulaDarah();

        // Menghapus data yang sudah ada di dalam gulaDarahList
        gulaDarahList.clear();

        try {
            gulaDarahList.addAll(daoGulaDarah.getAll());
            System.out.println(gulaDarahList.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        Label titleLabel = new Label("Gula Darah");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        BorderPane.setAlignment(titleLabel, Pos.CENTER);

        Button tambahButton = new Button("Tambah");
        Image tambahImage = new Image("D:/SEMESTER 2/PRAKTIKUM/PROJECT_AKHIR_OOP/ERA-SQUAD/app/src/main/resources/images/add.png");
        ImageView tambahImageView = new ImageView(tambahImage);
        tambahImageView.setFitWidth(16); 
        tambahImageView.setFitHeight(16); 
        tambahButton.setGraphic(tambahImageView);
        tambahButton.setOnAction(event -> {
            SceneGulaDarah scGulaDarah = new SceneGulaDarah(stage, gulaDarahList);
            stage.setScene(scGulaDarah);
        });

        TableView<GulaDarah> tableGulaDarah = new TableView<>();
        TableColumn<GulaDarah, Integer> column1 = new TableColumn<>("Konsentrasi Gula Darah");
        TableColumn<GulaDarah, String> column2 = new TableColumn<>("Waktu");
        TableColumn<GulaDarah, String> column3 = new TableColumn<>("Catatan");
        TableColumn<GulaDarah, String> column4 = new TableColumn<>("Tanggal");

        column1.setCellValueFactory(new PropertyValueFactory<>("gulaDarah"));
        column2.setCellValueFactory(new PropertyValueFactory<>("waktu"));
        column3.setCellValueFactory(new PropertyValueFactory<>("catatan"));
        column4.setCellValueFactory(new PropertyValueFactory<>("tanggal"));

        column1.prefWidthProperty().bind(tableGulaDarah.widthProperty().multiply(0.25));
        column2.prefWidthProperty().bind(tableGulaDarah.widthProperty().multiply(0.25));
        column3.prefWidthProperty().bind(tableGulaDarah.widthProperty().multiply(0.25));
        column4.prefWidthProperty().bind(tableGulaDarah.widthProperty().multiply(0.25));

        tableGulaDarah.getColumns().addAll(column1, column2, column3, column4);
        tableGulaDarah.setItems(gulaDarahList);

        TextField tfGulaDarah = new TextField();
        tfGulaDarah.setPromptText("Konsentrasi Gula Darah");
        TextField tfWaktu = new TextField();
        tfWaktu.setPromptText("Waktu");
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
        vbox.getChildren().addAll(tambahButton, tableGulaDarah, buttonBox);

        root.setTop(titleLabel);
        root.setCenter(vbox);

        setRoot(root);
    }
}
