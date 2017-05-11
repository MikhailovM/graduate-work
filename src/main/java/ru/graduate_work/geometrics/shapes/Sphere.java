package ru.graduate_work.geometrics.shapes;

import ru.graduate_work.geometrics.Point;
import ru.graduate_work.scene.MainScene;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mike on 07.05.2017.
 */
public class Sphere extends MainShape {

    private final double t_0 = 0;
    private final double tau_0 = 0;
    private final double tau_n = 2 * Math.PI;
    private final double step = Math.PI / 18.;
    private final double t_n = 2 * Math.PI;

    public Sphere(MainScene mainScene) {
        super(mainScene);
    }

    public void init() {

        for(double t = t_0; t <= t_n; t += step){
            List<Point> points = new ArrayList<>();
            for(double tau = tau_0; tau <= tau_n; tau += step){
                points.add(new Point(  Math.sin(tau) * Math.cos(t), Math.cos(tau) ,   - Math.sin(tau) * Math.sin(t)));
            }
            super.pointLists.add(points);
        }

    }

}
