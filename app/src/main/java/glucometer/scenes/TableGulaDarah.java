package glucometer.scenes;

import java.sql.SQLException;

import glucometer.dataBase.DbGulaDarah;
import glucometer.models.GulaDarah;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TableGulaDarah extends Scene {
    private static ObservableList<GulaDarah> gulaDarahList = FXCollections.observableArrayList();

    public TableGulaDarah(Stage stage) {
        super(new VBox(), 480, 480);
        DbGulaDarah daoGulaDarah = new DbGulaDarah();

        try {
            gulaDarahList.addAll(daoGulaDarah.getAll());
            System.out.println(gulaDarahList.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));

        Label titleLabel = new Label("Gula Darah");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Button tambahButton = new Button("Tambah");
        tambahButton.setOnAction(event -> {
            SceneGulaDarah scGulaDarah = new SceneGulaDarah(stage, gulaDarahList);
            stage.setScene(scGulaDarah);
        });

        TableView<GulaDarah> tableGulaDarah = new TableView<>();
        TableColumn<GulaDarah, Integer> coloumn1 = new TableColumn<>("Konsentrasi Gula Darah");
        TableColumn<GulaDarah, String> coloumn2 = new TableColumn<>("Waktu");
        TableColumn<GulaDarah, String> coloumn3 = new TableColumn<>("Catatan");
        TableColumn<GulaDarah, String> coloumn4 = new TableColumn<>("Tanggal");

        coloumn1.setCellValueFactory(new PropertyValueFactory<>("gulaDarah"));
        coloumn2.setCellValueFactory(new PropertyValueFactory<>("waktu"));
        coloumn3.setCellValueFactory(new PropertyValueFactory<>("catatan"));
        coloumn4.setCellValueFactory(new PropertyValueFactory<>("tanggal"));

        tableGulaDarah.getColumns().addAll(coloumn1, coloumn2, coloumn3, coloumn4);
        tableGulaDarah.setItems(gulaDarahList);

        TextField tfGulaDarah = new TextField();
        tfGulaDarah.setPromptText("Konsentrasi Gula Darah");
        TextField tfWaktu = new TextField();
        tfWaktu.setPromptText("Waktu");
        TextField tfCatatan = new TextField();
        tfCatatan.setPromptText("Catatan");
        TextField tfTanggal = new TextField();
        tfTanggal.setPromptText("Tanggal");

        Button hapusButton = new Button("Hapus");
        hapusButton.setOnAction(event -> {
            GulaDarah selectedGulaDarah = tableGulaDarah.getSelectionModel().getSelectedItem();
            if (selectedGulaDarah != null) {
                gulaDarahList.remove(selectedGulaDarah);
                daoGulaDarah.deleteData(selectedGulaDarah);
            }
        });

        Button kembaliButton = new Button("Kembali");
        kembaliButton.setOnAction(v -> {
            MainScene mainScene = new MainScene(stage);
            mainScene.show();
        });

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(tambahButton, hapusButton, kembaliButton);

        root.getChildren().addAll(titleLabel, buttonBox, tableGulaDarah);

        setRoot(root);
    }
}
