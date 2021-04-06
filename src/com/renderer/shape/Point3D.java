package com.renderer.shape;

import com.renderer.Renderer3D;
import com.renderer.util.Vector2;

import java.awt.*;

public class Point3D {
    public double x, y, z;
    public static double scale = 1.d;

    public Point3D(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point asPoint2D(){
        var scaled = this.scale();

        return new Point(
                (int)(Renderer3D.WIDTH / 2 + scaled.x),
                (int)(Renderer3D.HEIGHT / 2 + scaled.y)
        );
    }

    private Vector2<Double> scale(){
        var x = this.y;
        var y = this.x;
        var z = this.z * scale;

        var d = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        var t = Math.atan2(y, x);
        var dp2 = 15 - z;
        var local = Math.abs(1400 / (dp2 + 1400));

        d *= local;

        return new Vector2<>(
                d * Math.cos(t),
                d * Math.sin(t)
        );
    }

    public void move(double x, double y, double z){
        this.x += x;
        this.y += y;
        this.z += z;
    }

    public void rotate(double x, double y, double z){
        this.rotateX(x);
        this.rotateY(y);
        this.rotateZ(z);
    }

    private void rotateX(double degrees){
        double r = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
        double t = Math.atan2(this.y, this.x);
        t += 2 * Math.PI / 360 * degrees;
        this.x = r * Math.cos(t);
        this.y = r * Math.sin(t);
    }

    private void rotateY(double degrees){
        double radius = Math.sqrt(Math.pow(this.z, 2) + Math.pow(this.y, 2));
        double t = Math.atan2(this.z, this.y);
        t += 2 * Math.PI / 360 * degrees;
        this.z = radius * Math.sin(t);
        this.y = radius * Math.cos(t);
    }

    private void rotateZ(double degrees){
        double radius = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.z, 2));
        double t = Math.atan2(this.x, this.z);
        t += 2 * Math.PI / 360 * degrees;
        this.z = radius * Math.cos(t);
        this.x = radius * Math.sin(t);
    }
}
