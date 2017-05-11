package ru.graduate_work.scene;

import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Pair;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by mikhyumikhaylov on 03.02.2017.
 */
public class MainScene extends Path{

    private double L, R, B, T;
    private int W, H;
    private double DX, DY;

    public Camera camera;

    public int toScreenX(double X){ return (int) (((X - L) * W) / (R - L)); }
    public int toScreenY(double Y) {
        return (int) (((T - Y) * H) / (T - B));
    }
    public double toWorldX(int X) {
        return L + (R - L) * ((double) X + 0.5) / W;
    }
    public double toWorldY(int Y) {
        return T - (T - B) * ((double) Y + 0.5) / H;
    }

    public MainScene(double L, double R, double B, double T, double width, double height){
        W = (int) width;
        H = (int) height;

        this.L = L;
        this.R = R;
        this.B = B;
        this.T = T;

        camera = new Camera();
    }

    public void DrawShape(List<Path> pathList, List<List<Pair>> pairLists){
        for(int i = 0; i < pairLists.size() - 1; i++){
            boolean f = (i + 1 < pairLists.size() - 1) ? false : true;
            Path path = getPath(pairLists.get(i), pairLists.get(i + 1), f);
            pathList.add(path);
        }
    }

    public Path drawMateric(List<List<Pair>> pairLists){
        Path path = new Path();
        path.setStroke(Color.RED);
        path.setStrokeWidth(3);
        Pair[][] points = listToArray(pairLists);
        int left = 20;
        int right = 30;
        int height = 3;

        for(int i = left; i < right; i++){
            Line(path,
                    (int) points[i][left].getKey(), (int) points[i][left].getValue(),
                    (int) points[i + 1][left].getKey(), (int) points[i + 1][left].getValue());
        }

        for (int i = left + height; i > left; i--){
            Line(path,
                    (int) points[right][i].getKey(), (int) points[right][i].getValue(),
                    (int) points[right][i - 1].getKey(), (int) points[right][i - 1].getValue());
        }

        for(int i = left; i < right; i++){
            Line(path,
                    (int) points[i][left + height].getKey(), (int) points[i][left + height].getValue(),
                    (int) points[i + 1][left + height].getKey(), (int) points[i + 1][left + height].getValue());
        }

        for (int i = left + height; i > left; i--){
            Line(path,
                    (int) points[left][i].getKey(), (int) points[left][i].getValue(),
                    (int) points[left][i - 1].getKey(), (int) points[left][i - 1].getValue());
        }

        return path;
    }

    private Path getPath(List<Pair> currList, List<Pair> nextList, boolean f){
        Path path = new Path();
        for(int i = 0; i < currList.size() - 1; i++){
            int x1 = (int) currList.get(i).getKey(), y1 = (int) currList.get(i).getValue(); // Нулевая точка
            int x2 = (int) currList.get(i + 1).getKey(), y2 = (int) currList.get(i + 1).getValue(); // Точка правее нулевой
            int x3 = (int) nextList.get(i).getKey(), y3 = (int) nextList.get(i).getValue(); // Точка лежащая под нулевой
            int x4 = (int) nextList.get(i + 1).getKey(), y4 = (int) nextList.get(i + 1).getValue(); // Точка лежащая под нулевой правее
            Line(path, x1, y1, x3, y3);
            Line(path, x1, y1, x2, y2);
            if(!(i + 1 < currList.size() - 1)){
                Line(path, x2, y2, x4, y4);
            }
            if (f){
                Line(path, x3, y3, x4, y4);
            }
        }
        return path;
    }

    public void Line(Path path, int x0, int y0, int x1, int y1){
        path.getElements().add(new MoveTo(x0, y0));
        path.getElements().add(new LineTo(x1, y1));
    }


    public void Clear(Path path){
        path.getElements().clear();
    }

    public void setResolution(double width, double height){

        W = (int) width;
        H = (int) height;
        DX = (R - L) / W;
        R = DX * W / 2;
        L = - R;
    }

    public void Zoom(double zoom){
        if(zoom > 0.){
            camera.S_w_p[2][2] += 0.5;
        }else {
            camera.S_w_p[2][2] -= 0.5;
        }
    }

    private Pair[][] listToArray(List<List<Pair>> pairLists){
        Pair[][] arr = new Pair[pairLists.size()][pairLists.get(0).size()];
        for(int i = 0; i < pairLists.size(); i++){
            for(int j = 0; j < pairLists.get(0).size(); j++){
                arr[i][j] = pairLists.get(i).get(j);
            }
        }
        return arr;
    }
}
