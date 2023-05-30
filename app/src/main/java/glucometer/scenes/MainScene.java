package glucometer.scenes;

import javafx.scene.text.Font;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import glucometer.models.BeratBadan;
import glucometer.models.GulaDarah;
import glucometer.models.Obat;
import glucometer.models.TekananDarah;
import java.sql.SQLException;
import glucometer.dataBase.DbGulaDarah;

public class MainScene {
    private Stage stage;

    public MainScene(Stage stage) {
        this.stage = stage;
    }

    // SCENE 1
    public void show() {

        // BUTTON
                      //Gula Darah
        Image img1 = new Image(getClass().getClassLoader().getResourceAsStream("images/Gula_Darah.png"));
        ImageView imgView1 = new ImageView(img1);
        imgView1.setFitWidth(100);
        imgView1.setFitHeight(100);
        Label title1 = new Label("");
        VBox tombol1 = new VBox(imgView1, title1);
        tombol1.setPadding(new Insets(10));
        tombol1.setOnMouseClicked(v -> {
            SceneGulaDarah scGulaDarah = new SceneGulaDarah(stage);
            // scGulaDarah.show2();
        });


        //Tekanan Darah
        Image img2 = new Image(getClass().getClassLoader().getResourceAsStream("images/Tekanan_Darah.png"));
        ImageView imgView2 = new ImageView(img2);
        imgView2.setFitWidth(100);
        imgView2.setFitHeight(100);
        Label title2 = new Label("");
        VBox tombol2 = new VBox(imgView2, title2);
        tombol2.setPadding(new Insets(10));
        // tombol2.setStyle("-fx-background-color: red");
        tombol2.setOnMouseClicked(v -> {
            // isi
        });


        //Obat-obatan
        Image img3 = new Image(getClass().getClassLoader().getResourceAsStream("images/Obat_Obatan.png"));
        ImageView imgView3 = new ImageView(img3);
        imgView3.setFitWidth(100);
        imgView3.setFitHeight(100);
        Label title3 = new Label("");
        VBox tombol3 = new VBox(imgView3, title3);
        tombol3.setPadding(new Insets(10));
        tombol3.setOnMouseClicked(v -> {
            // isi
        });


        //Berat Badan
        Image img4 = new Image(getClass().getClassLoader().getResourceAsStream("images/Berat_Badan.png"));
        ImageView imgView4 = new ImageView(img4);
        imgView4.setFitWidth(100);
        imgView4.setFitHeight(100);
        Label title4 = new Label("");
        VBox tombol4 = new VBox(imgView4, title4);
        tombol4.setPadding(new Insets(10));
        // tombol4.setStyle("-fx-background-color: red");
        tombol4.setOnMouseClicked(v -> {
            // isi
        });

        VBox sectionLeft = new VBox(tombol1, tombol2);
        sectionLeft.setSpacing(20);
        sectionLeft.setAlignment(Pos.CENTER);
        sectionLeft.setPrefWidth(80);
        sectionLeft.setId("section-right");

        VBox sectionRight = new VBox(tombol3, tombol4);
        sectionRight.setSpacing(20);
        sectionRight.setAlignment(Pos.CENTER);
        sectionRight.setPrefWidth(80);
        sectionRight.setId("section-right");

        HBox rootNode = new HBox(sectionLeft, sectionRight);
        rootNode.setStyle("-fx-background-color: white");
        rootNode.setSpacing(50);
        rootNode.setAlignment(Pos.CENTER);

        Text newTitle = new Text("DashBoard");
        newTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-font-family: League Spartan;");
        StackPane spTitle = new StackPane(newTitle);
        spTitle.setPrefHeight(70);
        spTitle.setStyle("-fx-background-color: #9ED0F3;");


        VBox content = new VBox(spTitle, rootNode);
        content.setSpacing(10);
        content.setAlignment(Pos.TOP_CENTER);

        // SECTION RIGHT
        // VBox sectionRight = new VBox(title, button1, button2, button3, button4,
        // button5);
        // sectionRight.setSpacing(8);
        // sectionRight.setAlignment(Pos.CENTER);
        // sectionRight.setPrefWidth(320);
        // sectionRight.setId("section-right");

        // button1.setOnAction(v -> {
        // changeMenu(2);
        // });

        // VBox rootNode1 = new VBox(sectionRight);
        // rootNode1.setAlignment(Pos.CENTER);
        // rootNode1.getStyleClass().add("rootNode");

        Scene scene = new Scene(content, 720, 480);

        // atur css
        scene.getStylesheets().add(getClass().getResource("/styles/main_style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    // SCENE 2
    // private Scene getScene2() {
    // rightSide.getChildren().clear();

    // ObservableList<GulaDarah> gulaDarah = FXCollections.observableArrayList();

    // // ambil data dari database (TO DO LIST 1)
    // DbGulaDarah dbGulaDarah = new DbGulaDarah();
    // try {
    // gulaDarah.addAll(dbGulaDarah.getAll());
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }

    // // membuat tabel view
    // TableView<GulaDarah> tableGula = new TableView<>();

    // // membuat table coloumn
    // TableColumn<GulaDarah, Integer> coloumn1 = new TableColumn<>("Konsentrasi
    // Gula");
    // TableColumn<GulaDarah, String> coloumn2 = new TableColumn<>("Catatan");

    // // Pasangkan
    // coloumn1.setCellValueFactory(new PropertyValueFactory<>("gulaDarah"));
    // coloumn2.setCellValueFactory(new PropertyValueFactory<>("catatan"));

    // coloumn1.setPrefWidth((rightSide.getWidth() - 60) / 2);
    // coloumn2.setPrefWidth((rightSide.getWidth() - 60) / 2);

    // // tambah colum ke table
    // tableGula.getColumns().addAll(coloumn1, coloumn2);

    // // kasi nilai
    // tableGula.setItems(gulaDarah);

    // TextField tfGula = new TextField();
    // tfGula.setPromptText("Konsentrasi Gula");
    // TextField tfCatatan = new TextField();
    // tfCatatan.setPromptText("Catatan");
    // HBox hBox = new HBox(tfGula, tfCatatan);

    // Button btnAdd = new Button("Tambah");
    // btnAdd.setOnAction(v -> {
    // gulaDarah.add(new GulaDarah(Integer.parseInt(tfGula.getText()),
    // tfCatatan.getText()));
    // dbGulaDarah.syncData(gulaDarah);
    // });

    // rightSide.getChildren().addAll(tableGula, hBox, btnAdd);
    // return new Scene(rightSide);
    // }

    // private void changeMenu(int indexMenu) {
    // switch (indexMenu) {
    // case 1:
    // stage.setScene(getScene1());
    // stage.show();
    // break;
    // case 2:
    // stage.setScene(getScene2());
    // stage.show();
    // break;
    // default:
    // break;
    // }
    // }

    // private VBox generateRightSide(double width, double height) {
    // VBox vBoxLayout = new VBox();
    // vBoxLayout.setPrefSize(width, height);
    // vBoxLayout.setMaxSize(width, height);
    // vBoxLayout.setPadding(new Insets(24));
    // return vBoxLayout;
    // }

    // private VBox generateLeftSide(double width, double height) {
    // // Left Side (MENU)
    // VBox vboxMenu = new VBox();
    // vboxMenu.setPrefSize(width, height);
    // vboxMenu.setMaxSize(width, height);
    // vboxMenu.getStyleClass().add("vbox-menu");
    // return vboxMenu;
    // }

}