import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mike on 13.11.2016.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Kinematic Surface");
        GridPane root = new GridPane();

        Pane pane = new Pane();
        pane.setPrefSize(600, 400);
        List<Path> pathList = new ArrayList<>();
        int R = 3;
        Surface surface = new Surface(pane, -R, R, -R, R);
        surface.setResolution(pane.getPrefWidth(), pane.getPrefHeight());



        surface.DrawSurface(pathList);
        pane.getChildren().addAll(pathList);

        root.add(pane, 0, 0);


        Scene scene = new Scene(root);

        /*scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                surface.Clear(path);
                if(event.getText().equals("p")){
                    surface.Zoom(1);
                }
                if(event.getText().equals("m")){
                    surface.Zoom(-1);
                }
                surface.getAllPoints(path);
            }
        });*/
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
