package glucometer.scenes;

import glucometer.table.TableBeratBadan;
import glucometer.table.TableGulaDarah;
import glucometer.table.TableObat;
import glucometer.table.TableTekananDarah;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainScene {
    private Stage stage;

    public MainScene(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        // Gula Darah
        Image img1 = new Image(getClass().getClassLoader().getResourceAsStream("images/Gula_Darah.png"));
        ImageView imgView1 = new ImageView(img1);
        imgView1.setFitWidth(100);
        imgView1.setFitHeight(100);
        Label title1 = new Label("");
        VBox tombol1 = new VBox(imgView1, title1);
        tombol1.getStyleClass().add("tombol1");
        tombol1.setPadding(new Insets(10));
        tombol1.setOnMouseClicked(v -> {
            TableGulaDarah tbGulaDarah = new TableGulaDarah(stage);
            stage.setScene(tbGulaDarah);
        });

        // Tekanan Darah
        Image img2 = new Image(getClass().getClassLoader().getResourceAsStream("images/Tekanan_Darah.png"));
        ImageView imgView2 = new ImageView(img2);
        imgView2.setFitWidth(100);
        imgView2.setFitHeight(100);
        Label title2 = new Label("");
        VBox tombol2 = new VBox(imgView2, title2);
        tombol2.getStyleClass().add("tombol2");
        tombol2.setPadding(new Insets(10));
        tombol2.setOnMouseClicked(v -> {
            TableTekananDarah tbTekananDarah = new TableTekananDarah(stage);
            stage.setScene(tbTekananDarah);
        });

        // Obat-obatan
        Image img3 = new Image(getClass().getClassLoader().getResourceAsStream("images/Obat_Obatan.png"));
        ImageView imgView3 = new ImageView(img3);
        imgView3.setFitWidth(100);
        imgView3.setFitHeight(100);
        Label title3 = new Label("");
        VBox tombol3 = new VBox(imgView3, title3);
        tombol3.getStyleClass().add("tombol3");
        tombol3.setPadding(new Insets(10));
        tombol3.setOnMouseClicked(v -> {
            TableObat tbObat = new TableObat(stage);
            stage.setScene(tbObat);
        });

        // Berat Badan
        Image img4 = new Image(getClass().getClassLoader().getResourceAsStream("images/Berat_Badan.png"));
        ImageView imgView4 = new ImageView(img4);
        imgView4.setFitWidth(100);
        imgView4.setFitHeight(100);
        Label title4 = new Label("");
        VBox tombol4 = new VBox(imgView4, title4);
        tombol4.getStyleClass().add("tombol4");
        tombol4.setPadding(new Insets(10));
        tombol4.setOnMouseClicked(v -> {
            TableBeratBadan tbBeratBadan = new TableBeratBadan(stage);
            stage.setScene(tbBeratBadan);
        });

        // Button Kembali
        Label titleKembali = new Label("Home");
        VBox kembaliButton = new VBox(titleKembali);
        ImageView imageView = new ImageView(
                "D:/SEMESTER 2/PRAKTIKUM/PROJECT_AKHIR_OOP/ERA-SQUAD/app/src/main/resources/images/icon_home.png"); // Ganti
                                                                                                                    // dengan
                                                                                                                    // path
                                                                                                                    // file
                                                                                                                    // gambar
                                                                                                                    // yang
                                                                                                                    // sesuai
        titleKembali.setGraphic(imageView);
        kembaliButton.setAlignment(Pos.CENTER);
        kembaliButton.setPadding(new Insets(0));
        VBox.setMargin(kembaliButton, new Insets(40, 300, 0, 300));
        kembaliButton.getStyleClass().add("custom-button");

        kembaliButton.setOnMouseClicked(v -> {
            HomeScene homeScene = new HomeScene(stage);
            homeScene.show();
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

        VBox content = new VBox(spTitle, rootNode, kembaliButton);
        content.setSpacing(0);
        content.setAlignment(Pos.TOP_CENTER);
        content.setStyle("-fx-background-color: white");

        Scene scene = new Scene(content, 480, 480);

        // atur css
        scene.getStylesheets().add(getClass().getResource("/styles/main_style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

}
