package proyectopacman;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Victor Gil 24.571.102 Daniel Hernandez 24.426.451
 */
public class Bloque extends JPanel {

    private int x1, y1, x2, y2;
    private Panel_Lienzo lienzo;
    private Rectangle r;

    public Bloque(int x1, int y1, int x2, int y2, Panel_Lienzo lienzo) {
        super();
        setBackground(Color.blue);
        this.lienzo = lienzo;
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        setBounds(this.x1, this.y1, this.x2 - this.x1, this.y2 - this.y1);
        r = new Rectangle(this.x1, this.y1, this.x2 - this.x1, this.y2 - this.y1);
        this.lienzo.add(this);

    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public Panel_Lienzo getLienzo() {
        return lienzo;
    }

    public void setLienzo(Panel_Lienzo lienzo) {
        this.lienzo = lienzo;
    }

    public Rectangle getR() {
        return r;
    }

    public void setR(Rectangle r) {
        this.r = r;
    }

}
