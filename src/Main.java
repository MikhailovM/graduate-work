import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

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
        Path path = new Path();
        Surface surface = new Surface(pane, -2, 2, -2, 2);
        surface.setResolution(pane.getPrefWidth(), pane.getPrefHeight());



        surface.DrawSurface(path);
        pane.getChildren().addAll(path);

        root.add(pane, 0, 0);


        Scene scene = new Scene(root);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                surface.Clear(path);
                if(event.getText().equals("p")){
                    surface.Zoom(1);
                }
                if(event.getText().equals("m")){
                    surface.Zoom(-1);
                }
                surface.DrawSurface(path);
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
