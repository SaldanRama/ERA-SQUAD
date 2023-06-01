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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SceneObat extends Scene {
    // private static ObservableList<Obat> obatList;

    public SceneObat(Stage stage, ObservableList<Obat> obatList) {
        super(new VBox(), 480, 480);
     

        // Membuat tampilan scene
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
        
        TextField catatanTextField = new TextField();
        catatanTextField.setPromptText("Tambah Catatan Disini");
        TextField tanggalTextField = new TextField();
        tanggalTextField.setPromptText("Tanggal");

        Button tambahButton = new Button("Tambah");
        tambahButton.setOnAction(event -> {
            String namaObat = obatTextField.getText();
            int dosis = Integer.parseInt(obatTextField2.getText());
            String catatan = catatanTextField.getText();
            String tanggal = tanggalTextField.getText();
            String bentuk = "";

            if (mgCheckBox.isSelected()) {
                bentuk += "mg";
            }
            if (unitCheckBox.isSelected()) {
                bentuk += "unit";
            }
            if (mLCheckBox.isSelected()) {
                bentuk += "mL";
            }
            if (tabletCheckBox.isSelected()) {
                bentuk += "tablet";
            }
            if (!bentuk.isEmpty()) {
                bentuk = bentuk.substring(0, bentuk.length() - 2);
            }


            Obat obatObj = new Obat(namaObat, dosis, bentuk, catatan, tanggal);
            obatList.add(obatObj);

            // Simpan ke database (TO DO LIST 1)
            DbObat dboObat = new DbObat();
            dboObat.addData(obatObj);

            // Clear input fields
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
        kembaliButton.setOnAction(v -> {
            MainScene mainScene = new MainScene(stage);
            mainScene.show();
        });

        root.getChildren().addAll(titleLabel, obatTextField, obatTextField2, mgCheckBox, unitCheckBox, mLCheckBox,
            tabletCheckBox, catatanTextField, tanggalTextField, tambahButton, kembaliButton);

        setRoot(root);
    }
}
