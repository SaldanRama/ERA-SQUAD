package glucometer.scenes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import glucometer.models.BeratBadan;
import glucometer.models.GulaDarah;
import glucometer.models.Obat;
import glucometer.models.TekananDarah;
import java.sql.SQLException;
import glucometer.dataBase.DbGulaDarah;


public class SceneGulaDarah {
    private Stage stage;
    private VBox rightSide;

    public SceneGulaDarah(Stage stage) {
        this.stage = stage;
    }

    public void show2() {
        HBox root = new HBox();
        Scene scene = new Scene(root, 640, 480);

        VBox leftSide = generateLeftSide(scene.getWidth() * .36, scene.getHeight());
        rightSide = generateRightSide(scene.getWidth() * .64, scene.getHeight());
        changeMenu(1);

        root.getChildren().addAll(leftSide, rightSide);

        scene.getStylesheets().add(getClass().getResource("/styles/main_style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }


    // SCENE 1
    public Scene getScene1() {
        // SCENE
        Text title = new Text("DashBoard");

        // BUTTON
        Button button1 = new Button("Gula Darah");
        Button button2 = new Button("Tekanan Darah");
        Button button3 = new Button("Obat-obatan");
        Button button4 = new Button("Berat Badan");
        Button button5 = new Button("Statistik");


        // SECTION RIGHT
        VBox sectionRight = new VBox(title, button1, button2, button3, button4, button5);
        sectionRight.setSpacing(8);
        sectionRight.setAlignment(Pos.CENTER);
        sectionRight.setPrefWidth(320);
        sectionRight.setId("section-right");

        button1.setOnAction(v -> {
            changeMenu(2);
        });

        VBox rootNode1 = new VBox(sectionRight);
        rootNode1.setAlignment(Pos.CENTER);
        rootNode1.getStyleClass().add("rootNode");

        Scene scene = new Scene(new StackPane(rootNode1), 500, 400);

        // atur css
        scene.getStylesheets().add(getClass().getResource("/styles/main_style.css").toExternalForm());
        return scene;
    }

    // SCENE 2
    public Scene getScene2() {
        rightSide.getChildren().clear();

        ObservableList<GulaDarah> gulaDarah = FXCollections.observableArrayList();

        // ambil data dari database (TO DO LIST 1)
        DbGulaDarah dbGulaDarah = new DbGulaDarah();
        try {
            gulaDarah.addAll(dbGulaDarah.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // membuat tabel view
        TableView<GulaDarah> tableGula = new TableView<>();

        // membuat table coloumn
        TableColumn<GulaDarah, Integer> coloumn1 = new TableColumn<>("Konsentrasi Gula");
        TableColumn<GulaDarah, String> coloumn2 = new TableColumn<>("Catatan");
        
        // Pasangkan
        coloumn1.setCellValueFactory(new PropertyValueFactory<>("gulaDarah"));
        coloumn2.setCellValueFactory(new PropertyValueFactory<>("catatan"));

        coloumn1.setPrefWidth((rightSide.getWidth() - 60) / 2);
        coloumn2.setPrefWidth((rightSide.getWidth() - 60) / 2);

        // tambah colum ke table
        tableGula.getColumns().addAll(coloumn1, coloumn2);

        // kasi nilai
        tableGula.setItems(gulaDarah);

        TextField tfGula = new TextField();
        tfGula.setPromptText("Konsentrasi Gula");
        TextField tfCatatan = new TextField();
        tfCatatan.setPromptText("Catatan");
        HBox hBox = new HBox(tfGula, tfCatatan);

        Button btnAdd = new Button("Tambah");
        btnAdd.setOnAction(v -> {
            gulaDarah.add(new GulaDarah(Integer.parseInt(tfGula.getText()), tfCatatan.getText()));
            dbGulaDarah.syncData(gulaDarah);
        });
        
        rightSide.getChildren().addAll(tableGula, hBox, btnAdd);
        return new Scene(rightSide);
    }

    private void changeMenu(int indexMenu) {
        switch (indexMenu) {
            case 1:
                stage.setScene(getScene1());
                stage.show();
                break;
            case 2:
                stage.setScene(getScene2());
                stage.show();
                break;
            default:
                break;
        }
    }

    private VBox generateRightSide(double width, double height) {
        VBox vBoxLayout = new VBox();
        vBoxLayout.setPrefSize(width, height);
        vBoxLayout.setMaxSize(width, height);
        vBoxLayout.setPadding(new Insets(24));
        return vBoxLayout;
    }

    private VBox generateLeftSide(double width, double height) {
        // Left Side (MENU)
        VBox vboxMenu = new VBox();
        vboxMenu.setPrefSize(width, height);
        vboxMenu.setMaxSize(width, height);
        vboxMenu.getStyleClass().add("vbox-menu");
        return vboxMenu;
    }

}