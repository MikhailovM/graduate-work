package ru.graduate_work.geometrics;

import javafx.scene.shape.Path;
import javafx.util.Pair;
import ru.graduate_work.geometrics.shapes.MainShape;
import ru.graduate_work.scene.MainScene;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mikhyumikhaylov on 03.02.2017.
 */
public class Reamer extends Path {

    public List<Path> pathList = new ArrayList<>();

    public Reamer(MainScene scene, MainShape shape){
        shape.buildReamer();
        scene.DrawShape(pathList, shape.pairLists);
    }

}
