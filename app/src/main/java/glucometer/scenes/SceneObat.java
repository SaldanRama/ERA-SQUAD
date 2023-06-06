package glucometer.scenes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import glucometer.abstract_db.AbstractDbObat;
import glucometer.models.Obat;
import glucometer.table.TableObat;
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

        Button tambahButton = new Button("Tambah");
        Image tambahImage = new Image("D:/SEMESTER 2/PRAKTIKUM/PROJECT_AKHIR_OOP/ERA-SQUAD/app/src/main/resources/images/add.png");
        ImageView tambahImageView = new ImageView(tambahImage);
        tambahImageView.setFitWidth(16); 
        tambahImageView.setFitHeight(16); 
        tambahButton.setGraphic(tambahImageView);
        tambahButton.setOnAction(event -> {
            String namaObat = obatTextField.getText();
            int dosis = Integer.parseInt(obatTextField2.getText());
            String catatan = catatanTextField.getText();
            LocalDate tanggal = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String tanggalString = tanggal.format(formatter);
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

            Obat obatObj = new Obat(namaObat, dosis, bentuk, catatan, tanggalString);
            obatList.add(obatObj);

            AbstractDbObat dbObat = new AbstractDbObat();
            dbObat.addData(obatObj);

            obatTextField.clear();
            obatTextField2.clear();
            mgCheckBox.setSelected(false);
            unitCheckBox.setSelected(false);
            mLCheckBox.setSelected(false);
            tabletCheckBox.setSelected(false);
            catatanTextField.clear();
        });

        Button kembaliButton = new Button("Kembali");
        Image kembaliImage = new Image("D:/SEMESTER 2/PRAKTIKUM/PROJECT_AKHIR_OOP/ERA-SQUAD/app/src/main/resources/images/left.png");
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
                buttonBox);

        setRoot(root);
    }
}
