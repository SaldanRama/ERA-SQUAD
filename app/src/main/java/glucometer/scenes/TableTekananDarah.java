package glucometer.scenes;

import glucometer.dataBase.DbGulaDarah;
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

public class TableTekananDarah extends Scene {
    private Stage stage;
    private static ObservableList<TekananDarah> tekananDarahList;

    public static ObservableList<TekananDarah> getTekananDarahList() {
        return tekananDarahList;
    }

    public static void setTekananDarahList(ObservableList<TekananDarah> tekananDarahList) {
        TableTekananDarah.tekananDarahList = tekananDarahList;
    }

    public TableTekananDarah(Stage stage, ObservableList<TekananDarah> tekananDarahList) {
        super(new VBox(), 480, 480);
        this.stage = stage;
        this.tekananDarahList = tekananDarahList;

        // Membuat tampilan scene
        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));

        Label titleLabel = new Label("Tekanan Darah");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

       

        Button tambahButton = new Button("Tambah");
        tambahButton.setOnAction(event -> {
            SceneTekananDarah scTekananDarah = new SceneTekananDarah(stage, SceneTekananDarah.getTekananDarahList());
            stage.setScene(scTekananDarah);
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
