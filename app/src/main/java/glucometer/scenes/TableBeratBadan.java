package glucometer.scenes;

import glucometer.dataBase.DbGulaDarah;
import glucometer.models.BeratBadan;
import glucometer.models.GulaDarah;
import glucometer.models.Obat;
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

public class TableBeratBadan extends Scene {
    private Stage stage;
    private static ObservableList<BeratBadan> beratBadanList;

    public static ObservableList<BeratBadan> getBeratBadanList() {
        return beratBadanList;
    }

    public static void setBeratBadanList(ObservableList<BeratBadan> beratBadanList) {
        TableBeratBadan.beratBadanList = beratBadanList;
    }

    public TableBeratBadan(Stage stage, ObservableList<BeratBadan> beratBadanList) {
        super(new VBox(), 480, 480);
        this.stage = stage;
        this.beratBadanList = beratBadanList;

        // Membuat tampilan scene
        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));

        Label titleLabel = new Label("Berat Badan");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

       

        Button tambahButton = new Button("Tambah");
        tambahButton.setOnAction(event -> {
            SceneBeratBadan scBeratBadan = new SceneBeratBadan(stage, SceneBeratBadan.getBeratBadanList());
            stage.setScene(scBeratBadan);
        });

        Button kembaliButton = new Button("Kembali");
        kembaliButton.setOnAction(v -> {
            MainScene mainScene = new MainScene(stage);
            mainScene.show();
        });

        root.getChildren().addAll(titleLabel, tambahButton, kembaliButton);

        setRoot(root);
    }
}
