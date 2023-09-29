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
public class Score extends JPanel {

    private int x;
    private int y;
    private final Font fuente = new Font("Helvetica", Font.BOLD, 17);
    private Panel_Lienzo lienzo;

    public Score(Panel_Lienzo lienzo) {
        super();
        x = 0;
        y = 365;
        this.lienzo = lienzo;
        lienzo.add(this);
        setBounds(x, y, 300, 35);
        setBackground(Color.BLACK);

    }

    public void Remover() {
        lienzo.remove(this);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs); //To change body of generated methods, choose Tools | Templates.
        Graphics2D g2d = (Graphics2D) grphcs;
        DibujarScore(g2d);
    }

    /**
     * Panel que muestra el Score y las vidas como dibujos
     *
     */
    private void DibujarScore(Graphics2D grphcs) {
        String s;
        grphcs.setFont(fuente);
        grphcs.setColor(Color.RED);
        s = "Score: " + lienzo.getMostrarScore();
        grphcs.drawString(s, 25, 25);
        for (int i = 0; i < lienzo.getPacma1().getVida(); i++) {
            grphcs.drawImage(lienzo.getPacma1().getLeft1().getImage(), i * 30 + 128, 0, 30, 30, this);
        }
    }

}
