package ru.graduate_work.geometrics.shapes;

import javafx.util.Pair;
import ru.graduate_work.geometrics.Point;
import ru.graduate_work.scene.MainScene;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mike on 06.05.2017.
 */
public class Cylinder extends MainShape {

    private final double t_0 = 0;
    private final double tau_0 = 0;
    private final double tau_n = 2 * Math.PI;
    private final double step = Math.PI / 18.;
    private final double h = 5;
    private final double b = 2;
    private final double t_n = h / b;

    public Cylinder(MainScene mainScene) {
        super(mainScene);
    }

    public void init() {

        for (double t = t_0; t <= t_n; t += 0.1) {
            List<Point> points = new ArrayList<>();
            for (double tau = tau_0; tau <= tau_n; tau += step) {
                points.add(new Point(Math.sin(tau), t, Math.cos(tau)));
            }
            super.pointLists.add(points);
        }

    }

    public void buildReamer() {

        super.pairLists.clear();
        for(int i = 0; i < super.pointLists.size(); i++){
            List<Pair> pairList = new ArrayList<>();
            phi_0 = calcPhi(super.pointLists.get(i).get(0));
            for (int j = 0; j < super.pointLists.get(i).size(); j++){
                Point p = super.pointLists.get(i).get(j);
                double phi = calcPhi(p);
                double r = calcRadius(p);
                double x = (phi - phi_0) * r; // Координата x развертки точки p
                double y = p.getU2(); // Координата y развертки точки p
                int scrX = mainScene.toScreenX(x), scrY = mainScene.toScreenY(y);
                pairList.add(new Pair<Integer, Integer>(scrX, scrY));
            }
            super.pairLists.add(pairList);
        }

    }
}
