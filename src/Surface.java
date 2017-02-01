import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

/**
 * Created by Mike on 13.11.2016.
 */
public class Surface extends Path {

    private double L, R, B, T;
    private int W, H;
    private double DX, DY;

    public Matrix matrix;
    public Camera camera;

    public Double[][] P;

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

    public void DrawSurface(Path path){

        P = Camera.Multiply(camera.S_w_v, matrix.matrix_vertices);

        boolean FLAG = true;

        for(int i = 0; i < P[0].length; i++){
            if(P[2][i] >= camera.D){
                FLAG = false;
                break;
            }
        }


        if(FLAG){
            P = Camera.Multiply(camera.S_w_p, matrix.matrix_vertices);

            double prevX = P[0][0] / P[2][0], prevY = P[1][0] / P[2][0];
            double currX, currY;
            for(int i = 1; i < P.length; i++){
                currX = P[0][i] / P[2][i];
                currY = P[1][i] / P[2][i];
                Line(path, prevX, prevY, currX, currY);
                prevX = currX;
                prevY = currY;
            }
        } else {
            System.out.print("z_v < D - не выполняется!");
        }
    }

    public void Line(Path path, double x0, double y0, double x1, double y1){
        path.getElements().add(new MoveTo(toScreenX(x0), toScreenY(y0)));
        path.getElements().add(new LineTo(toScreenX(x1), toScreenY(y1)));
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
