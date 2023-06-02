package glucometer.scenes;

import glucometer.dataBase.DbObat;
import glucometer.models.Obat;
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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class SceneObat extends Scene {
    public SceneObat(Stage stage, ObservableList<Obat> obatList) {
        super(new VBox(), 480, 480);

        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));

        Label titleLabel = new Label("Obat-Obatan");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField obatTextField = new TextField();
        obatTextField.setPromptText("Nama Obat");

        TextField obatTextField2 = new TextField();
        obatTextField2.setPromptText("Dosis");

        CheckBox mgCheckBox = new CheckBox("mg");
        CheckBox unitCheckBox = new CheckBox("unit");
        CheckBox mLCheckBox = new CheckBox("mL");
        CheckBox tabletCheckBox = new CheckBox("tablet");

        HBox checkBoxBox = new HBox();
        checkBoxBox.setSpacing(10);
        checkBoxBox.getChildren().addAll(mgCheckBox, unitCheckBox, mLCheckBox, tabletCheckBox);

        TextField catatanTextField = new TextField();
        catatanTextField.setPromptText("Tambah Catatan Disini");

        TextField tanggalTextField = new TextField();
        tanggalTextField.setPromptText("Tanggal");

        Button tambahButton = new Button("Tambah");
        Image tambahImage = new Image("F:/New folder (3)/ERA-SQUAD/app/src/main/resources/images/add.png");
        ImageView tambahImageView = new ImageView(tambahImage);
        tambahImageView.setFitWidth(16); 
        tambahImageView.setFitHeight(16); 
        tambahButton.setGraphic(tambahImageView);
        tambahButton.setOnAction(event -> {
            String namaObat = obatTextField.getText();
            int dosis = Integer.parseInt(obatTextField2.getText());
            String catatan = catatanTextField.getText();
            String tanggal = tanggalTextField.getText();
            String bentuk = "";

            if (mgCheckBox.isSelected()) {
                bentuk += "mg, ";
            }
            if (unitCheckBox.isSelected()) {
                bentuk += "unit, ";
            }
            if (mLCheckBox.isSelected()) {
                bentuk += "mL, ";
            }
            if (tabletCheckBox.isSelected()) {
                bentuk += "tablet, ";
            }
            if (!bentuk.isEmpty()) {
                bentuk = bentuk.substring(0, bentuk.length() - 2);
            }

            Obat obatObj = new Obat(namaObat, dosis, bentuk, catatan, tanggal);
            obatList.add(obatObj);

            DbObat dbObat = new DbObat();
            dbObat.addData(obatObj);

            obatTextField.clear();
            obatTextField2.clear();
            mgCheckBox.setSelected(false);
            unitCheckBox.setSelected(false);
            mLCheckBox.setSelected(false);
            tabletCheckBox.setSelected(false);
            catatanTextField.clear();
            tanggalTextField.clear();
        });

        Button kembaliButton = new Button("Kembali");
        Image kembaliImage = new Image("F:/New folder (3)/ERA-SQUAD/app/src/main/resources/images/left.png");
        ImageView kembaliImageView = new ImageView(kembaliImage);
        kembaliImageView.setFitWidth(16); // Atur lebar gambar
        kembaliImageView.setFitHeight(16); // Atur tinggi gambar
        kembaliButton.setGraphic(kembaliImageView);
        kembaliButton.setOnAction(event -> {
            stage.setScene(new TableObat(stage));
        });

        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        buttonBox.getChildren().addAll(tambahButton, kembaliButton);

        root.getChildren().addAll(titleLabel, obatTextField, obatTextField2, checkBoxBox, catatanTextField,
                tanggalTextField, buttonBox);

        setRoot(root);
    }
}
