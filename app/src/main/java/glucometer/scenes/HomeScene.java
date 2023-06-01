package glucometer.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class HomeScene {
    private Stage stage;

    public HomeScene(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        StackPane spLayout = new StackPane();
        Scene scene = new Scene(spLayout, 480, 480);
        scene.getStylesheets().add(getClass().getResource("/styles/home_style.css").toExternalForm());

        // Setting background
        ImageView ivBackground = new ImageView("/images/bg.png");
        ivBackground.setFitWidth(scene.getWidth());
        ivBackground.setFitHeight(scene.getHeight());
        spLayout.getChildren().add(ivBackground);

        // Text Title
        Text tLeft = new Text("Gluco");
        tLeft.getStyleClass().add("title-text-left");
        Text tRight = new Text("Meter");
        tRight.getStyleClass().add("title-text-right");
        TextFlow tfTitle = new TextFlow(tLeft, tRight);
        HBox hbox = new HBox(5,tLeft,tRight);
        hbox.setAlignment(Pos.CENTER);

        // Top Logo
        ImageView ivLogo = new ImageView("/images/logo.png");
        ivLogo.setFitWidth(85); 
        ivLogo.setFitHeight(85);


        // Text Desc
        Label lblDesc = new Label(
                "Catatlah kadar gula darah Anda setiap hari untuk melacak perubahan dan mengelola diabetes dengan lebih baik. Pengetahuan yang baik akan membantu Anda mengelola diabetes dengan lebih efektif.");
        lblDesc.getStyleClass().add("desc-text");
        lblDesc.setWrapText(true);
        lblDesc.setMaxWidth(355);
        lblDesc.setTextAlignment(TextAlignment.JUSTIFY);


        // Button Explore
        Region space = new Region();
        space.setPrefHeight(12);
        Button btnExplore = new Button("Mulai");
        btnExplore.getStyleClass().add("btn-explore");

        // VBOX layout
        VBox vLayout = new VBox(hbox, ivLogo, tfTitle, lblDesc, space, btnExplore);
        vLayout.setSpacing(8);
        spLayout.getChildren().add(vLayout);
        vLayout.setPadding(new Insets(45));
        vLayout.setAlignment(Pos.CENTER);

        // actions
        btnExplore.setOnAction(v -> {
            MainScene mainScene = new MainScene(stage);
            mainScene.show();
          
        });

        stage.setScene(scene);
        stage.show();
        
    }
}

