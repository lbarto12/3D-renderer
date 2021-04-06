package com.renderer;

import com.renderer.shape.Point3D;
import com.renderer.shape.Polygon3D;
import com.renderer.shape.Shape3D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Renderer3D extends JPanel {

    public static int WIDTH = 800, HEIGHT = 500;

    private JFrame window;
    public Renderer3D(JFrame window){
        this.window = window;
        this.init();
    }

    private void init(){
        this.setBackground(Color.black);

        this.window.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

                Renderer3D.WIDTH = window.getWidth();
                Renderer3D.HEIGHT = window.getHeight();
            }
        });

        int s = 100;
        var p1 = new Point3D(s / 2.d, -s / 2.d, -s / 2.d);
        var p2 = new Point3D(s / 2.d, s / 2.d, -s / 2.d);
        var p3 = new Point3D(s / 2.d, s / 2.d, s / 2.d);
        var p4 = new Point3D(s / 2.d, -s / 2.d, s / 2.d);
        var p5 = new Point3D(-s / 2.d, -s / 2.d, -s / 2.d);
        var p6 = new Point3D(-s / 2.d, s / 2.d, -s / 2.d);
        var p7 = new Point3D(-s / 2.d, s / 2.d, s / 2.d);
        var p8 = new Point3D(-s / 2.d, -s / 2.d, s / 2.d);

        var pp1 = new Point3D(s / 2.d, -s / 2.d, -s / 2.d);
        var pp2 = new Point3D(s / 2.d, s / 2.d, -s / 2.d);
        var pp3 = new Point3D(s / 2.d, s / 2.d, s / 2.d);
        var pp4 = new Point3D(s / 2.d, -s / 2.d, s / 2.d);
        var pp5 = new Point3D(-s / 2.d, -s / 2.d, -s / 2.d);
        var pp6 = new Point3D(-s / 2.d, s / 2.d, -s / 2.d);
        var pp7 = new Point3D(-s / 2.d, s / 2.d, s / 2.d);
        var pp8 = new Point3D(-s / 2.d, -s / 2.d, s / 2.d);


        var ppp1 = new Point3D(s / 2.d, -s / 2.d, -s / 2.d);
        var ppp2 = new Point3D(s / 2.d, s / 2.d, -s / 2.d);
        var ppp3 = new Point3D(s / 2.d, s / 2.d, s / 2.d);
        var ppp4 = new Point3D(s / 2.d, -s / 2.d, s / 2.d);
        var ppp5 = new Point3D(-s / 2.d, -s / 2.d, -s / 2.d);
        var ppp6 = new Point3D(-s / 2.d, s / 2.d, -s / 2.d);
        var ppp7 = new Point3D(-s / 2.d, s / 2.d, s / 2.d);
        var ppp8 = new Point3D(-s / 2.d, -s / 2.d, s / 2.d);

        this.shapes.add(
                new Shape3D(
                        new Polygon3D(p1, p2, p3, p4).setColor(Color.CYAN),
                        new Polygon3D(p5, p6, p7, p8).setColor(Color.GREEN),
                        new Polygon3D(p1, p5, p6, p2).setColor(Color.BLUE),
                        new Polygon3D(p1, p5, p8, p4).setColor(Color.yellow),
                        new Polygon3D(p2, p6, p7, p3).setColor(Color.MAGENTA),
                        new Polygon3D(p4, p3, p7, p8).setColor(Color.RED)
                )
        );

        this.shapes.get(0).move(0, 0, -100);


        this.shapes.add(
                new Shape3D(
                        new Polygon3D(pp1, pp2, pp3, pp4).setColor(Color.CYAN),
                        new Polygon3D(pp5, pp6, pp7, pp8).setColor(Color.GREEN),
                        new Polygon3D(pp1, pp5, pp6, pp2).setColor(Color.BLUE),
                        new Polygon3D(pp1, pp5, pp8, pp4).setColor(Color.yellow),
                        new Polygon3D(pp2, pp6, pp7, pp3).setColor(Color.MAGENTA),
                        new Polygon3D(pp4, pp3, pp7, pp8).setColor(Color.RED)
                )
        );



        this.shapes.add(
                new Shape3D(
                        new Polygon3D(ppp1, ppp2, ppp3, ppp4).setColor(Color.CYAN),
                        new Polygon3D(ppp5, ppp6, ppp7, ppp8).setColor(Color.GREEN),
                        new Polygon3D(ppp1, ppp5, ppp6, ppp2).setColor(Color.BLUE),
                        new Polygon3D(ppp1, ppp5, ppp8, ppp4).setColor(Color.yellow),
                        new Polygon3D(ppp2, ppp6, ppp7, ppp3).setColor(Color.MAGENTA),
                        new Polygon3D(ppp4, ppp3, ppp7, ppp8).setColor(Color.RED)
                )
        );
        this.shapes.get(2).move(0, 0, 100);




        this.createRenderThread();
    }

    private ArrayList<Shape3D> shapes = new ArrayList<>();

    public void addShapes(Shape3D... shapes){
        this.shapes.addAll(Arrays.asList(shapes));
    }

    private void createRenderThread(){
        new Thread(() ->{
            while (true){


                this.run();

                this.repaint();


                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (var i : this.determineShapeOrder()){
            i.draw(g);
        }
    }

    private ArrayList<Polygon3D> determineShapeOrder(){
        var polys = new ArrayList<Polygon3D>();

        for (var i : this.shapes)
            polys.addAll(i.polygons);

        Collections.sort(polys, new Comparator<Polygon3D>() {
            public int compare(Polygon3D o1, Polygon3D o2) {
                double result = o2.getDepth() - o1.getDepth();
                if (result < 0) return 1;
                else if (result > 0) return -1;
                else return 0;
            }
        });

        return polys;
    }


    private void run(){
        //this.shapes.get(0).move(0, 0, -1);

        this.shapes.get(0).rotate(0.1, 0.1, 0.1);
        this.shapes.get(1).rotate(-0.25, -0.25, -0.25);
        this.shapes.get(2).rotate(-0.1, -.5, -.5);
    }
}
