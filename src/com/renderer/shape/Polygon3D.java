package com.renderer.shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Polygon3D {
    public final ArrayList<Point3D> points;
    private Color color = Color.WHITE;

    public Polygon3D(){
        this.points = new ArrayList<>();
    }

    public Polygon3D(Point3D... points){
        this.points = new ArrayList<>(Arrays.asList(points));
    }

    public Polygon3D setColor(Color color){
        this.color = color;
        return this;
    }

    public double getDepth(){
        double sum = 0.d;
        for (var i : this.points)
            sum += i.z;
        return sum / this.points.size();
    }

    public void rotate(double x, double y, double z){
        for (var i : this.points)
            i.rotate(x, y, z);
    }

    public void move(double x, double y, double z){
        for (var i : this.points)
            i.move(x, y, z);
    }

    public void draw(Graphics g){
        var temp = new Polygon();
        for (var i : this.points){
            var point = i.asPoint2D();
            temp.addPoint(point.x, point.y);
        }
        g.setColor(this.color);
        g.fillPolygon(temp);
    }


}
