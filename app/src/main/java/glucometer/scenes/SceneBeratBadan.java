package glucometer.scenes;

import glucometer.dataBase.DbBeratBadan;
import glucometer.dataBase.DbGulaDarah;
import glucometer.models.BeratBadan;
import glucometer.models.GulaDarah;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SceneBeratBadan extends Scene {
    private Stage stage;
    private static ObservableList<BeratBadan> beratBadanList;

   
    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public static ObservableList<BeratBadan> getBeratBadanList() {
        return beratBadanList;
    }

    public static void setBeratBadanList(ObservableList<BeratBadan> beratBadanList) {
        SceneBeratBadan.beratBadanList = beratBadanList;
    }

    public SceneBeratBadan(Stage stage, ObservableList<BeratBadan> beratBadanList) {
        super(new VBox(), 480, 480);
        this.stage = stage;
        this.beratBadanList = beratBadanList;

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

        Button tambahButton = new Button("Tambah");
        tambahButton.setOnAction(event -> {
            int beratBadan = Integer.parseInt(beratBadanTextField.getText());
            String catatan = catatanTextField.getText();

            BeratBadan beratBadanObj = new BeratBadan(beratBadan, catatan);
            beratBadanList.add(beratBadanObj);

            // Simpan ke database (TO DO LIST 1)
            DbBeratBadan dbBeratBadan = new DbBeratBadan();
            dbBeratBadan.addData(beratBadanObj);

            // Clear input fields
            beratBadanTextField.clear();
            catatanTextField.clear();
        });

        Button kembaliButton = new Button("Kembali");
        kembaliButton.setOnAction(v -> {
            MainScene mainScene = new MainScene(stage);
            mainScene.show();
        });

        root.getChildren().addAll(titleLabel, beratBadanTextField, catatanTextField, tambahButton, kembaliButton);

        setRoot(root);
    }
}
