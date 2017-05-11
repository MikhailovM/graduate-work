package ru.graduate_work.scene;

import ru.graduate_work.geometrics.Point;

import java.util.List;

/**
 * Created by Mike on 13.11.2016.
 */
public class Matrix {

    public Double[][] matrix_vertices;  // Координаты точек, мировые координаты
    public Integer[][] matrix_edges;     // Карта граней
    public Integer[][] matrix_verges;   // Карта вершин

    private int N = 4;
    private int M;


    public void Transfer(double x, double y, double z){
        Double[][] T = {
                {1., 0., 0., x},
                {0., 1., 0., y},
                {0., 0., 1., z},
                {0., 0., 0., 1.}
        };
        Multiply(T);
    }

    public void RotateX(double phi){
        Double[][] R = {
                {1., 0., 0., 0.},
                {0., Math.cos(phi), -Math.sin(phi), 0.},
                {0., Math.sin(phi), Math.cos(phi), 0.},
                {0., 0., 0., 1.}
        };
        Multiply(R);
    }

    public void RotateY(double phi){
        Double[][] R = {
                {Math.cos(phi), 0., Math.sin(phi), 0.},
                {0., 1., 0., 0.},
                {-Math.sin(phi), 0., Math.cos(phi), 0.},
                {0., 0., 0., 1.}
        };
        Multiply(R);
    }

    public void RotateZ(double phi){
        Double[][] R = {
                {Math.cos(phi), -Math.sin(phi), 0., 0.},
                {Math.sin(phi), Math.cos(phi), 0., 0.},
                {0., 0., 1., 0.},
                {0., 0., 0., 1.}
        };
        Multiply(R);
    }

    public void Scaling(double x, double y, double z){
        Double[][] S = {
                {x, 0., 0., 0.},
                {0., y, 0., 0.},
                {0., 0., z, 0.},
                {0., 0., 0., 1.}
        };
        Multiply(S);
    }

    public void MirrorYZ(){
        Double[][] M = {
                {-1., 0., 0., 0.},
                {0., 1., 0., 0.},
                {0., 0., 1., 0.},
                {0., 0., 0., 1.}
        };
        Multiply(M);
    }

    public void MirrorZX(){
        Double[][] M = {
                {1., 0., 0., 0.},
                {0., -1., 0., 0.},
                {0., 0., 1., 0.},
                {0., 0., 0., 1.}
        };
        Multiply(M);
    }

    public void MirrorXY(){
        Double[][] M = {
                {1., 0., 0., 0.},
                {0., 1., 0., 0.},
                {0., 0., -1., 0.},
                {0., 0., 0., 1.}
        };
        Multiply(M);
    }

    public void MirrorX(){
        Double[][] M = {
                {1., 0., 0., 0.},
                {0., -1., 0., 0.},
                {0., 0., -1., 0.},
                {0., 0., 0., 1.}
        };
        Multiply(M);
    }

    public void MirrorY(){
        Double[][] M = {
                {-1., 0., 0., 0.},
                {0., 1., 0., 0.},
                {0., 0., -1., 0.},
                {0., 0., 0., 1.}
        };
        Multiply(M);
    }

    public void MirrorZ(){
        Double[][] M = {
                {-1., 0., 0., 0.},
                {0., -1., 0., 0.},
                {0., 0., 1., 0.},
                {0., 0., 0., 1.}
        };
        Multiply(M);
    }

    public void MirrorO(){
        Double[][] M = {
                {-1., 0., 0., 0.},
                {0., -1., 0., 0.},
                {0., 0., -1., 0.},
                {0., 0., 0., 1.}
        };
        Multiply(M);
    }

    private void Multiply(Double[][] matrix1){
        Double[][] res = new Double[4][N];
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < N; j++) {
                res[i][j] = 0.;
                for (int k = 0; k < 4; k++)
                    res[i][j] += matrix1[i][k] * matrix_vertices[k][j];
            }
        matrix_vertices = res;
    }

    public static Double[][] convertListToArray(List<Point> points){
        int N = 4;
        int M = points.size();
        Double[][] matrix = new Double[N][M];

        for(int j = 0; j < M; j++){
            Point point = points.get(j);
            matrix[0][j] = point.getU1();
            matrix[1][j] = point.getU2();
            matrix[2][j] = point.getU3();
            matrix[3][j] = point.getU4();
        }
        return matrix;
    }
}
