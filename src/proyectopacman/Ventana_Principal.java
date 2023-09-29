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
 * @author Victor Gil
 */
public class Ventana_Principal extends JFrame {

    private final Panel_Lienzo p1;

    public Ventana_Principal() {
        super();
        setSize(575, 440);
        setTitle("PACMAN_VictorGil_DanielHernandez");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        Container contenedor = getContentPane();
        contenedor.setLayout(new BorderLayout());
        p1 = new Panel_Lienzo();
        contenedor.add(p1, BorderLayout.CENTER);

    }

}
