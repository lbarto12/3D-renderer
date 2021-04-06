package com.renderer.shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Shape3D {

    public ArrayList<Polygon3D> polygons;

    public Shape3D(){}

    public Shape3D(Polygon3D... polygons){
        this.polygons = new ArrayList<>(Arrays.asList(polygons));
    }

    public void sortPolygon(){
        Collections.sort(this.polygons, new Comparator<Polygon3D>() {
            public int compare(Polygon3D o1, Polygon3D o2) {
                double result = o2.getDepth() - o1.getDepth();
                if (result < 0) return 1;
                else if (result > 0) return -1;
                else return 0;
            }
        });
    }

    public void rotate( double x, double y, double z){
        for (var i : this.polygons)
            i.rotate(x, y, z);
        this.sortPolygon();
    }

    public void move(double x, double y, double z){
        for (var i : this.polygons)
            i.move(x, y, z);
    }

    public void draw(Graphics g){
        for (var i : this.polygons)
            i.draw(g);
    }
}
