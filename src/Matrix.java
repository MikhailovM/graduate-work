import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mike on 13.11.2016.
 */
public class Matrix {

    public Double[][] matrix_vertices;  // Координаты точек, мировые координаты
    public Integer[][] matrix_edges;     // Карта граней
    public Integer[][] matrix_verges;   // Карта вершин
    public List<Double[][]> surList = new ArrayList<>();
    private int N = 4;
    private int M;

    public  Matrix() {
        setMatrixVertices();
    }

    private void setMatrixVertices(){
        double t_0 = 0.;
        double t_n = 3 * Math.PI / 2.;
//        double t_n = 2 * Math.PI;
        double step = Math.PI / 18.;
        this.M = (int) ((t_n - t_0) / step);
//        matrix_vertices = new Double[N][M + 1];


        for (double y = - 1; y <= 1; y += 0.1) {
            int i = 0;
            Double[][] matrix = new Double[N][M + 1];
            for (double t = t_0; t <= t_n; t += step) {
                matrix[0][i] = Math.sin(t);
                matrix[1][i] = y;
                matrix[2][i] = Math.cos(t);
                matrix[3][i] = 1.;
                i++;
            }
            surList.add(matrix);
        }
    }

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
}
