/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectopacman;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Victor Gil 24.571.102 Daniel Hernandez 24.426.451
 */
public class Moneda extends JPanel {

    private int x;
    private int y;
    //Para controlar si el Pacman se lo comio
    private boolean comida;
    private int ban;
    private Rectangle rmoneda;
    private Panel_Lienzo lienzo;

    public Moneda(int x, int y, Panel_Lienzo lienzo) {
        super();
        setOpaque(false);
        this.x = x;
        this.y = y;
        this.lienzo = lienzo;
        lienzo.add(this);
        ban = 0;
        comida = false;
        setBounds(x, y, 18, 18);
        setBackground(Color.BLACK);
        rmoneda = new Rectangle(x, y, 18, 18);

        mostrar();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.ORANGE);
        g.fillOval(5, 5, 8, 8);

    }

    public void eliminar() {
        this.lienzo.remove(this);
    }

    public void mostrar() {

        if (comida) {
            if (ban == 0) {
                lienzo.setMostrarScore(lienzo.getMostrarScore() + 1);
                lienzo.setContadorM(lienzo.getContadorM() + 1);
                this.lienzo.remove(this);
                ban = 1;
            }
        }
    }

    public Rectangle getRmoneda() {
        return rmoneda;
    }

    public boolean isComida() {
        return comida;
    }

    public void setComida(boolean comida) {
        this.comida = comida;
    }

}
