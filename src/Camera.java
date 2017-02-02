/**
 * Created by Mike on 13.11.2016.
 */
public class Camera {

    private int SIZE = 3;
    public Double[][] S_w_v;           // Матрица перехода от мировых к видовым
    public Double[][] S_v_p;           // Матрица перехода от видовых к проекционным
    public Double[][] S_w_p;           // Матрица перехода от мировых к проекционным
    public double D = 2.;

    public Camera() {

        Double[] O_v = {0., 0., 0.};    // Координаты центра экрана
        Double[] T = {0., 1., 0.};      // Вверх камеры
        Double[] N = {0., 0., -1.};     // Направление камеры

        Double[] k_v = new Double[SIZE];
        Double[] i_v = new Double[SIZE];

        Double N_lenght = Math.sqrt(Math.pow(N[0], 2) + Math.pow(N[1], 2) + Math.pow(N[2], 2));

        Double[] TN = new Double[]{                 // Скалярное произведение T на N
                T[1] * N[2] - T[2] * N[1],
                T[2] * N[0] - T[0] * N[2],
                T[0] * N[1] - T[1] * N[0]
        };

        Double TN_lenght = Math.sqrt(Math.pow(TN[0], 2) + Math.pow(TN[1], 2) + Math.pow(TN[2], 2));

        for (int i = 0; i < SIZE; i++){
            k_v[i] = N[i] / N_lenght;
            i_v[i] = TN[i] / TN_lenght;
        }

        Double[] j_v = new Double[]{
                k_v[1] * i_v[2] - i_v[1] * k_v[2],
                k_v[2] * i_v[0] - i_v[2] * k_v[0],
                k_v[0] * i_v[1] - i_v[0] * k_v[1]
        };

        S_w_v = new Double[][]{
                {i_v[0], i_v[1], i_v[2], -(O_v[0] * i_v[0] + O_v[1] * i_v[1] + O_v[2] * i_v[2])},
                {j_v[0], j_v[1], j_v[2], -(O_v[0] * j_v[0] + O_v[1] * j_v[1] + O_v[2] * j_v[2])},
                {k_v[0], k_v[1], k_v[2], -(O_v[0] * k_v[0] + O_v[1] * k_v[1] + O_v[2] * k_v[2])},
                {0., 0., 0., 1.}
        };

        S_v_p = new Double[][]{
                {1., 0., 0., 0.},
                {0., 1., 0., 0.},
                {0., 0., -1. / D, 1.}
        };

        S_w_p = Multiply(S_v_p, S_w_v);

    }

    public static Double[][] Multiply(Double[][] matrix1, Double[][] matrix2){
        Double[][] res = new Double[matrix1.length][matrix2[0].length];
        for(int i = 0; i < matrix1.length; i++)
            for(int j = 0; j < matrix2[0].length; j++) {
                res[i][j] = 0.;
                for (int k = 0; k < matrix1[0].length; k++)
                    res[i][j] += matrix1[i][k] * matrix2[k][j];
            }
        return res;
    } // Функция умножения матриц



}
