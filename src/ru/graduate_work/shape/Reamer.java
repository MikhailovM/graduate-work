package ru.graduate_work.shape;

import javafx.scene.shape.Path;
import javafx.util.Pair;
import ru.graduate_work.scene.MainScene;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mikhyumikhaylov on 03.02.2017.
 */
public class Reamer extends Path {

    //TODO Создать класс, в который перенести все методы построения проекции
    //TODO В классе Reamer должны быть только методы построения развертки

    private double phi_0 = 0;
    private MainScene mainScene;
    public List<List<Point>> pointLists = new ArrayList<>();
    public List<List<Pair>> pairLists = new ArrayList<>();

    public Reamer(MainScene mainScene, Surface surface){
        this.mainScene = mainScene;
        pointLists = surface.pointLists;
        getAllPoints(pairLists);
    }

    public void getAllPoints(List<List<Pair>> list) {



        for(int i = 0; i < pointLists.size(); i++){
            List<Pair> pairList = new ArrayList<>();
            phi_0 = calcPhi(pointLists.get(i).get(0));
            for (int j = 0; j < pointLists.get(i).size(); j++){
                Point p = pointLists.get(i).get(j);
                double phi = calcPhi(p);
                double r = calcRadius(p);
                double x = (phi - phi_0) * r; // Координата x развертки точки p
                double y = p.getU2(); // Координата y развертки точки p
                int scrX = mainScene.toScreenX(x), scrY = mainScene.toScreenY(y);
                pairList.add(new Pair<Integer, Integer>(scrX, scrY));
            }
            list.add(pairList);
        }

    }

    private double calcRadius(Point p){
        return Math.sqrt(Math.pow(p.getU1(),2) + Math.pow(p.getU3(),2));
    }

    private double calcPhi(Point p){
        double res = 0;
        if(p.getU1() >= 0){
            res = Math.acos(p.getU3() / calcRadius(p));
        }else {
            res = 2 * Math.PI - Math.acos(p.getU3() / calcRadius(p));
        }
        return res;
    }


}
