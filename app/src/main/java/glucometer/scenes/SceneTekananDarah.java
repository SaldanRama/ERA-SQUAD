package glucometer.scenes;

import glucometer.dataBase.DbGulaDarah;
import glucometer.dataBase.DbTekananDarah;
import glucometer.models.GulaDarah;
import glucometer.models.TekananDarah;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SceneTekananDarah extends Scene {
    private static ObservableList<TekananDarah> tekananDarahList;

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

        Button tambahButton = new Button("Tambah");
        tambahButton.setOnAction(event -> {
            int tekananSistolik = Integer.parseInt(tekananDarahTextField.getText());
            int tekananDiastolik = Integer.parseInt(tekananDarahTextField2.getText());
            String catatan = catatanTextField.getText();
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

            TekananDarah tekananDarahObj = new TekananDarah(tekananSistolik, tekananDiastolik, tangan, catatan);
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
        });

        Button kembaliButton = new Button("Kembali");
        kembaliButton.setOnAction(v -> {
            MainScene mainScene = new MainScene(stage);
            mainScene.show();
        });

        root.getChildren().addAll(titleLabel, tekananDarahTextField, tekananDarahTextField2, rightArmCheckBox, leftArmCheckBox,
            catatanTextField, tambahButton, kembaliButton);

        setRoot(root);
    }
}
