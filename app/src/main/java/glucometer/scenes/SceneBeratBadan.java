package glucometer.scenes;

import glucometer.dataBase.DbBeratBadan;
import glucometer.models.BeratBadan;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class SceneBeratBadan extends Scene {
    public SceneBeratBadan(Stage stage, ObservableList<BeratBadan> beratBadanList) {
        super(new VBox(), 480, 480);

        // Membuat tampilan scene
        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));

        Label titleLabel = new Label("Berat Badan");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField beratBadanTextField = new TextField();
        beratBadanTextField.setPromptText("Berat Badan");
        TextField catatanTextField = new TextField();
        catatanTextField.setPromptText("Tambah Catatan Disini");
        TextField tanggalTextField = new TextField();
        tanggalTextField.setPromptText("Tanggal");

        Button tambahButton = new Button("Tambah");
        Image tambahImage = new Image("D:/SEMESTER 2/PRAKTIKUM/PROJECT_AKHIR_OOP/ERA-SQUAD/app/src/main/resources/images/add.png");
        ImageView tambahImageView = new ImageView(tambahImage);
        tambahImageView.setFitWidth(16); 
        tambahImageView.setFitHeight(16); 
        tambahButton.setGraphic(tambahImageView);
        tambahButton.setOnAction(event -> {
            int beratBadan = Integer.parseInt(beratBadanTextField.getText());
            String catatan = catatanTextField.getText();
            String tanggal = tanggalTextField.getText();

            BeratBadan beratBadanObj = new BeratBadan(beratBadan, catatan, tanggal);
            beratBadanList.add(beratBadanObj);

            // Simpan ke database (TO DO LIST 1)
            DbBeratBadan dbBeratBadan = new DbBeratBadan();
            dbBeratBadan.addData(beratBadanObj);

            // Clear input fields
            beratBadanTextField.clear();
            catatanTextField.clear();
            tanggalTextField.clear();
        });

        Button kembaliButton = new Button("Kembali");
        Image kembaliImage = new Image("D:/SEMESTER 2/PRAKTIKUM/PROJECT_AKHIR_OOP/ERA-SQUAD/app/src/main/resources/images/left.png");
        ImageView kembaliImageView = new ImageView(kembaliImage);
        kembaliImageView.setFitWidth(16); // Atur lebar gambar
        kembaliImageView.setFitHeight(16); // Atur tinggi gambar
        kembaliButton.setGraphic(kembaliImageView);
        kembaliButton.setOnAction(event -> {
            stage.setScene(new TableBeratBadan(stage));
        });

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(tambahButton, kembaliButton);

        root.getChildren().addAll(titleLabel, beratBadanTextField, catatanTextField, tanggalTextField, buttonBox);

        setRoot(root);
    }
}
