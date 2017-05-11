import com.sun.javafx.runtime.VersionInfo;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import ru.graduate_work.geometrics.shapes.Conus;
import ru.graduate_work.geometrics.shapes.Cylinder;
import ru.graduate_work.geometrics.shapes.MainShape;
import ru.graduate_work.geometrics.shapes.Sphere;
import ru.graduate_work.scene.MainScene;
import ru.graduate_work.geometrics.Reamer;
import ru.graduate_work.geometrics.Surface;

import javax.swing.*;
import java.awt.*;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mike on 13.11.2016.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Поверхность");
        GridPane root = new GridPane();

        // Построение поверхности
        Pane paneSurface = new Pane();
        paneSurface.setPrefSize(600, 400);
        int R = 3;
        MainScene mainScene = new MainScene(-R, R, -R, R, 600, 400);
        mainScene.setResolution(600, 400);
//        MainShape shape = new Conus(mainScene);
//        MainShape shape = new Cylinder(mainScene);
        MainShape shape = new Sphere(mainScene);
        Surface surface = new Surface(mainScene, shape);
        paneSurface.getChildren().addAll(surface.pathList);

        root.add(paneSurface, 0, 0);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Построение развертки
        /*Stage primaryStageReamer = new Stage();
        primaryStageReamer.setTitle("Развертка");
        GridPane rootReamer = new GridPane();
        Pane paneReamer = new Pane();
        paneReamer.setPrefSize(600, 400);
        Reamer reamer = new Reamer(mainScene, shape);
        paneReamer.getChildren().addAll(surface.pathMateric);
        root.add(paneReamer, 1, 0);
        rootReamer.add(paneReamer, 0, 0);
        Scene sceneReamer = new Scene(rootReamer);
        primaryStageReamer.setScene(sceneReamer);
        primaryStageReamer.show();*/


    }

    public static void main(String[] args) {
        launch(args);
    }
}
