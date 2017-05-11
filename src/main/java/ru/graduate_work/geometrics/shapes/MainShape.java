package ru.graduate_work.geometrics.shapes;

import javafx.util.Pair;
import ru.graduate_work.geometrics.Point;
import ru.graduate_work.scene.Camera;
import ru.graduate_work.scene.MainScene;
import ru.graduate_work.scene.Matrix;

import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mike on 06.05.2017.
 */
public class MainShape implements MainShapeInterface{

    private Double[][] P;
    public double phi_0 = 0;
    public List<List<Point>> pointLists = new ArrayList<>();
    public List<List<Pair>> pairLists = new ArrayList<>();

    public MainScene mainScene;

    public MainShape(MainScene mainScene) {
        this.mainScene = mainScene;
        init();
        worldCoordToProjecCoord(pairLists);
    }

    @Override
    public void init(){
        System.out.println("Вызван метод init() класса MainShape");
    }

    @Override
    public void buildReamer() {
        System.out.println("Вызван метод buildReamer() класса MainShape");
    }

    private void worldCoordToProjecCoord(List<List<Pair>> list){

        int numOfLevel = 3;
        int numOfPoint = 3;
        for (int s = 0; s < pointLists.size(); s++) {
            List<Pair> pairList = new ArrayList<>();

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
                P = Camera.Multiply(mainScene.camera.S_w_p, matr);

                int prevX = mainScene.toScreenX(P[0][0] / P[2][0]), prevY = mainScene.toScreenY(P[1][0] / P[2][0]);
                int currX, currY;
                int j = 0;
                for (int i = 1; i < P[0].length; i++) {
                    currX = mainScene.toScreenX(P[0][i] / P[2][i]);
                    currY = mainScene.toScreenY(P[1][i] / P[2][i]);
                    pairList.add(new Pair<>(prevX, prevY));
                    pairList.add(new Pair<>(currX, currY));
                    prevX = currX;
                    prevY = currY;
                }
            } else {
                System.out.print("z_v < D - не выполняется!");
            }

            list.add(pairList);
        }
    }

    public double calcRadius(Point p){
        return Math.sqrt(Math.pow(p.getU1(),2) + Math.pow(p.getU3(),2));
    }

    public double calcPhi(Point p){
        double res = 0;
        if(p.getU1() >= 0){
            res = Math.acos(p.getU3() / calcRadius(p));
        }else {
            res = 2 * Math.PI - Math.acos(p.getU3() / calcRadius(p));
        }
        return res;
    }

    public double calcModul(Point p){
        return Math.sqrt(Math.pow(p.getU1(), 2) + Math.pow(p.getU2(), 2) + Math.pow(p.getU3(), 2));
    }

    public double calcPsy(Point p) {
        return (calcModul(p) != 0) ? Math.acos(p.getU2() / calcModul(p)) : 0;
    }
}
