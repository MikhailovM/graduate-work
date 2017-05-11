package ru.graduate_work.geometrics;

/**
 * Created by mikhyumikhaylov on 03.02.2017.
 */
public class Point {

    public double u1;
    public double u2;
    public double u3;
    public final double u4 = 1.;

    public Point(double u1, double u2, double u3) {
        this.u1 = u1;
        this.u2 = u2;
        this.u3 = u3;
    }

    public double getU1() {
        return u1;
    }

    public void setU1(double u1) {
        this.u1 = u1;
    }

    public double getU2() {
        return u2;
    }

    public void setU2(double u2) {
        this.u2 = u2;
    }

    public double getU3() {
        return u3;
    }

    public void setU3(double u3) {
        this.u3 = u3;
    }

    public double getU4() {
        return u4;
    }
}
