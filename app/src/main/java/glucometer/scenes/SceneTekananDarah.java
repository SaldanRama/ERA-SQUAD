package glucometer.scenes;

import glucometer.dataBase.DbTekananDarah;
import glucometer.models.TekananDarah;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class SceneTekananDarah extends Scene {
    public SceneTekananDarah(Stage stage, ObservableList<TekananDarah> tekananDarahList) {
        super(new VBox(), 480, 480);


        // Membuat tampilan scene
        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));

        Label titleLabel = new Label("Tekanan Darah");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField tekananDarahTextField = new TextField();
        tekananDarahTextField.setPromptText("Tekanan Sistolik");
        TextField tekananDarahTextField2 = new TextField();
        tekananDarahTextField2.setPromptText("Tekanan Diastolik");

        CheckBox rightArmCheckBox = new CheckBox("Right Arm");
        CheckBox leftArmCheckBox = new CheckBox("Left Arm");
        
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
            int tekananSistolik = Integer.parseInt(tekananDarahTextField.getText());
            int tekananDiastolik = Integer.parseInt(tekananDarahTextField2.getText());
            String catatan = catatanTextField.getText();
            String tanggal = tanggalTextField.getText();
            String tangan = "";

            if (rightArmCheckBox.isSelected()) {
                tangan += "Right Arm";
            }
            if (leftArmCheckBox.isSelected()) {
                tangan += "Left Arm";
            }
            if (!tangan.isEmpty()) {
                tangan = tangan.substring(0, tangan.length() - 2);
            }

            TekananDarah tekananDarahObj = new TekananDarah(tekananSistolik, tekananDiastolik, tangan, catatan, tanggal);
            tekananDarahList.add(tekananDarahObj);

            // Simpan ke database (TO DO LIST 1)
            DbTekananDarah dbTekananDarah = new DbTekananDarah();
            dbTekananDarah.addData(tekananDarahObj);

            // Clear input fields
            tekananDarahTextField.clear();
            tekananDarahTextField2.clear();
            rightArmCheckBox.setSelected(false);
            leftArmCheckBox.setSelected(false);
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
            stage.setScene(new TableTekananDarah(stage));
        });
        

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(tambahButton, kembaliButton);

        root.getChildren().addAll(titleLabel, tekananDarahTextField, tekananDarahTextField2, rightArmCheckBox, leftArmCheckBox,
            catatanTextField, tanggalTextField, buttonBox);

        setRoot(root);
    }
}
