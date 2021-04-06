import com.renderer.Renderer3D;

import javax.swing.*;
import java.awt.*;

public class Main {

    private JFrame window;

    public Main(){
        this.init();
    }

    private void init(){
        this.window = new JFrame();
        this.window.setTitle("3D Render");
        this.window.setSize(new Dimension(800, 500));
        this.window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.window.add(new Renderer3D(this.window));
        this.window.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
