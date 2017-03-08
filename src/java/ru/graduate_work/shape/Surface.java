package ru.graduate_work.shape;

import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Pair;
import ru.graduate_work.scene.Camera;
import ru.graduate_work.scene.MainScene;
import ru.graduate_work.scene.Matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mike on 13.11.2016.
 */
public class Surface extends Path {

    public List<List<Point>> pointLists = new ArrayList<>();
    public List<List<Pair>> pairLists = new ArrayList<>();
    private MainScene mainScene;
    private final double t_0 = 0.;
    private final double t_n = 2 * Math.PI;
    private final double step = Math.PI / 18.;
    private Double[][] P;

    public Surface(MainScene mainScene){
        this.mainScene = mainScene;
        init();
        getAllPoints(pairLists);
    }

    private void init(){

        // Поверхность круглого прямого цилиндра
        /*for (double y = - 1; y <= 1; y += 0.1) {
            List<Point> points = new ArrayList<>();
            for (double t = t_0; t <= t_n; t += step) {
                points.add(new Point(Math.sin(t), y, Math.cos(t)));
            }
            pointLists.add(points);
        }*/


        // Поверхность круглого прямого конуса
        for(double t = t_0; t <= t_n; t += step){
            List<Point> points = new ArrayList<>();
            points.add(new Point(0, 0, 0.1));
            for(double y = 0.5, a = 0.1; y <= 2; y += 0.5, a += 0.1 ){

                if (y > 1) points.add(new Point(a * Math.sin(t), y, a * Math.cos(t)));
                else points.add(new Point(- a * Math.sin(t), y, - a * Math.cos(t)));
            }
            pointLists.add(points);
        }
    }


    public void getAllPoints(List<List<Pair>> list){


        for (int s = 0; s < pointLists.size(); s++) {
//        for (int s = 0; s < matrix.surList.size(); s++) {

//            Path path = new Path();
            List<Pair> pairList = new ArrayList<>();

//            P = Camera.Multiply(camera.S_w_v, matrix.surList.get(s));

            Double[][] matr = Matrix.convertListToArray(pointLists.get(s));

            P = Camera.Multiply(mainScene.camera.S_w_v, matr);
            boolean FLAG = true;

            for (int i = 0; i < P[0].length; i++) {
                if (P[2][i] >= mainScene.camera.D) {
                    FLAG = false;
                    break;
                }
            }

            if (FLAG) {
//                P = Camera.Multiply(camera.S_w_p, matrix.surList.get(s));

                P = Camera.Multiply(mainScene.camera.S_w_p, matr);

                int prevX = mainScene.toScreenX(P[0][0] / P[2][0]), prevY = mainScene.toScreenY(P[1][0] / P[2][0]);
                int currX, currY;
                for (int i = 1; i < P[0].length; i++) {
                    currX = mainScene.toScreenX(P[0][i] / P[2][i]);
                    currY = mainScene.toScreenY(P[1][i] / P[2][i]);
                    pairList.add(new Pair<Integer, Integer>(prevX, prevY));
                    pairList.add(new Pair<Integer, Integer>(currX, currY));
//                    Line(path, prevX, prevY, currX, currY);
                    prevX = currX;
                    prevY = currY;
                }
            } else {
                System.out.print("z_v < D - не выполняется!");
            }

            list.add(pairList);
        }
    }

}
