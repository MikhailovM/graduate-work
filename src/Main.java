import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import ru.graduate_work.scene.MainScene;
import ru.graduate_work.shape.Reamer;
import ru.graduate_work.shape.Surface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mike on 13.11.2016.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Kinematic ru.graduate_work.shape.Surface");
        GridPane root = new GridPane();

        // Построение поверхности
        Pane paneSurface = new Pane();
        paneSurface.setPrefSize(600, 400);
        List<Path> pathList = new ArrayList<>();
        int R = 3;
        MainScene mainScene = new MainScene(-R, R, -R, R, 600, 400);
        mainScene.setResolution(600, 400);
        Surface surface = new Surface(mainScene);
        mainScene.DrawShape(pathList, surface.pairLists);

        paneSurface.getChildren().addAll(pathList);

        // Построение развертки
        Pane paneReamer = new Pane();
        paneReamer.setPrefSize(600, 400);
        pathList.clear();
        Reamer reamer = new Reamer(mainScene, surface);
        mainScene.DrawShape(pathList, reamer.pairLists);

        paneReamer.getChildren().addAll(pathList);

        root.add(paneSurface, 0, 0);
        root.add(paneReamer, 1, 0);


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
