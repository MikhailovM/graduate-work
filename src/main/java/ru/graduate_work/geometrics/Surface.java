package ru.graduate_work.geometrics;

import javafx.scene.shape.Path;
import javafx.util.Pair;
import ru.graduate_work.geometrics.shapes.MainShape;
import ru.graduate_work.geometrics.shapes.Sphere;
import ru.graduate_work.scene.Camera;
import ru.graduate_work.scene.MainScene;
import ru.graduate_work.scene.Matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mike on 13.11.2016.
 */
public class Surface extends Path {

    public List<Path> pathList = new ArrayList<>();

    public Surface(MainScene scene, MainShape shape) {
        scene.DrawShape(pathList, shape.pairLists);
    }

}
