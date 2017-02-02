import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

import java.util.List;

/**
 * Created by Mike on 13.11.2016.
 */
public class Surface extends Path {

    private double L, R, B, T;
    private int W, H;
    private double DX, DY;
    private int N = 4;
    private int M = 1;

    public Matrix matrix;
    public Camera camera;

    public Double[][] P;
    public Double[][] SPC;
    public Double[][] RPC;
    public Double[][] BPC;

    private int toScreenX(double X){ return (int) (((X - L) * W) / (R - L)); }
    private int toScreenY(double Y) {
        return (int) (((T - Y) * H) / (T - B));
    }
    private double toWorldX(int X) {
        return L + (R - L) * ((double) X + 0.5) / W;
    }
    private double toWorldY(int Y) {
        return T - (T - B) * ((double) Y + 0.5) / H;
    }

    public Surface(Pane pane, double L, double R, double B, double T){
        W = (int) pane.getWidth();
        H = (int) pane.getHeight();

        this.L = L;
        this.R = R;
        this.B = B;
        this.T = T;

        matrix = new Matrix();
        camera = new Camera();
    }

    public void DrawSurface(List<Path> list){

        /*double t_0 = 0.;
        double t_n = 3 * Math.PI / 2.;
        double step_t = Math.PI / 9.;
        double step_y = 0.1;

        for(double y = 0.1; y >= 0.1; y -= step_y){

            for(;t_0 <= t_n; t_0 += step_t){

                Double[][] start_point_coord = {
                        {Math.sin(t_0)},
                        {y},
                        {Math.cos(t_0)},
                        {1.}
                };
                Double[][] right_point_coord = {
                        {Math.sin(t_0 + step_t)},
                        {y},
                        {Math.cos(t_0 + step_t)},
                        {1.}
                };
                Double[][] bottom_point_coord = {
                        {Math.sin(t_0)},
                        {y - step_y},
                        {Math.cos(t_0)},
                        {1.}
                };

                SPC = Camera.Multiply(camera.S_w_v, start_point_coord);
                RPC = Camera.Multiply(camera.S_w_v, right_point_coord);
                BPC = Camera.Multiply(camera.S_w_v, bottom_point_coord);

                boolean FLAG = (SPC[2][0] >= camera.D
                        && RPC[2][0] >= camera.D
                        && BPC[2][0] >= camera.D) ? false : true;

                if(FLAG){
                    double spX = SPC[0][0] / SPC[2][0];
                    double spY = SPC[1][0] / SPC[2][0];
                    double rpX = RPC[0][0] / RPC[2][0];
                    double rpY = RPC[1][0] / RPC[2][0];
                    double bpX = BPC[0][0] / BPC[2][0];
                    double bpY = BPC[1][0] / BPC[2][0];

                    Line(path, spX, spY, rpX, rpY);
                    Line(path, spX, spY, bpX, bpY);
                }
                else {
                    System.out.print("z_v < D - не выполняется!");
                    break;
                }

            }
        }*/


        for (int s = 0; s < matrix.surList.size(); s++) {

            Path path = new Path();

            P = Camera.Multiply(camera.S_w_v, matrix.surList.get(s));

            boolean FLAG = true;

            for (int i = 0; i < P[0].length; i++) {
                if (P[2][i] >= camera.D) {
                    FLAG = false;
                    break;
                }
            }


            if (FLAG) {
                P = Camera.Multiply(camera.S_w_p, matrix.surList.get(s));

                int prevX = toScreenX(P[0][0] / P[2][0]), prevY = toScreenY(P[1][0] / P[2][0]);
                int currX, currY;
                for (int i = 1; i < P[0].length; i++) {
                    currX = toScreenX(P[0][i] / P[2][i]);
                    currY = toScreenY(P[1][i] / P[2][i]);
                    Line(path, prevX, prevY, currX, currY);
                    prevX = currX;
                    prevY = currY;
                }
            } else {
                System.out.print("z_v < D - не выполняется!");
            }

            list.add(path);
        }
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
}
