package proyectopacman;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.concurrent.*;
import java.util.*;

/**
 *
 * @author Victor Gil 24.571.102 Daniel Hernandez 24.426.451
 */
public class Panel_Lienzo extends JPanel {

    private ImageIcon r4 = new ImageIcon(getClass().getResource("/imagenes/r4.png"));
    private ExecutorService thread_pool;
    //Para el llamado de las clases del juego
    private ArrayList<Fantasma> fantasmas;
    private ArrayList<Bloque> bloques;
    private ArrayList<Moneda> monedas;
    private Pacman pacma1;
    private Score score;
    //Para las monedas y el puntaje
    private int contadorM;
    private int mostrarScore;
    //Para indicar si se está jugando o no
    private boolean enjuego;
    //Para la velocidad por nivel de los fantasmas
    private int velocidadN;
    //Botones de acción
    private JButton finalizar_btn;
    private JButton pausa_btn;
    private boolean ban;

    public Panel_Lienzo() {
        super();
        setLayout(null);
        fantasmas = new ArrayList<>();
        setBackground(Color.BLACK);
        Bloques();
        CargarMonedas();
        enjuego = false;
        contadorM = 0;
        mostrarScore = 0;
        velocidadN = 0;
        finalizar_btn = new JButton();
        pausa_btn = new JButton();
        Panel_Lienzo.this.add(pausa_btn);
        pausa_btn.setBounds(301, 370, 93, 23);
        pausa_btn.setText("Pausa");
        Panel_Lienzo.this.add(finalizar_btn);
        finalizar_btn.setBounds(400, 370, 93, 23);
        finalizar_btn.setText("Finalizar");
        thread_pool = Executors.newCachedThreadPool();
        pacma1 = new Pacman(Panel_Lienzo.this);
        thread_pool.execute(pacma1);
        ban = false;
        addMouseListener(new Oyente());
        Pausa();
        Finalizar();
    }

    /**
     * Crea y Fija los bloques del tablero
     *
     */
    private void Bloques() {
        Bloque b1 = new Bloque(60, 60, 90, 180, this);
        Bloque b2 = new Bloque(90, 150, 150, 180, this);
        Bloque b3 = new Bloque(60, 210, 90, 330, this);
        Bloque b4 = new Bloque(90, 210, 150, 240, this);
        Bloque b5 = new Bloque(270, 180, 300, 330, this);
        Bloque b6 = new Bloque(210, 240, 360, 270, this);
        Bloque b7 = new Bloque(480, 60, 510, 180, this);
        Bloque b8 = new Bloque(420, 150, 480, 180, this);
        Bloque b9 = new Bloque(480, 210, 510, 330, this);
        Bloque b10 = new Bloque(420, 210, 480, 240, this);
        Bloque b11 = new Bloque(210, 90, 240, 150, this);
        Bloque b12 = new Bloque(330, 90, 360, 150, this);
        Bloque b13 = new Bloque(210, 120, 360, 150, this);

        bloques = new ArrayList<>();
        bloques.add(b1);
        bloques.add(b2);
        bloques.add(b3);
        bloques.add(b4);
        bloques.add(b5);
        bloques.add(b6);
        bloques.add(b7);
        bloques.add(b8);
        bloques.add(b9);
        bloques.add(b10);
        bloques.add(b11);
        bloques.add(b12);
        bloques.add(b13);
    }

    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs); //To change body of generated methods, choose Tools | Templates.
        Graphics2D g2d = (Graphics2D) grphcs;
        if (!enjuego) {
            MensajeInicial(g2d);
        }
    }

    /**
     * Despliega el mensaje para comenzar el juego
     *
     */
    private void MensajeInicial(Graphics2D g2d) {

        g2d.setColor(new Color(0, 32, 48));
        g2d.fillRect(100, 366, 190, 29);
        g2d.setColor(Color.white);
        g2d.drawRect(100, 366, 190, 29);

        String s = "Click para Comenzar";
        Font small = new Font("Helvetica", Font.BOLD, 15);
        FontMetrics metr = this.getFontMetrics(small);

        g2d.setColor(Color.white);
        g2d.setFont(small);
        g2d.drawString(s, (269 - metr.stringWidth(s)), 386);
    }

    /**
     * Carga las monedas en el tablero
     *
     */
    public void CargarMonedas() {
        int a = 6;
        int b = 6;
        monedas = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 19; j++) {

                monedas.add(new Moneda(a, b, this));
                a = a + 30;

            }
            a = 6;
            b = b + 30;
        }

    }

    /**
     * Remueve un conjunto de fantasmas y crea otro
     *
     */
    public void NuevosFantasmas() {
        for (int i = 0; i < fantasmas.size(); i++) {
            fantasmas.get(i).setDetenido(true);
            remove(fantasmas.get(i));
            repaint();

        }

        if (enjuego) {

            fantasmas = new ArrayList<>();

            for (int i = 0; i < 2; i++) {
                for (int j = 1; j < 5; j++) {
                    Fantasma fan_aux = new Fantasma(j, velocidadN, Panel_Lienzo.this);
                    thread_pool.execute(fan_aux);
                    fantasmas.add(fan_aux);

                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "GAME OVER", "PACMAN", JOptionPane.DEFAULT_OPTION, r4);
            score.Remover();
            repaint();
        }
    }

    /**
     * Pausar o Reanudar el Juego
     *
     */
    private void Pausa() {

        pausa_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (enjuego) {
                    ban = !ban;
                    if (ban) {
                        System.out.println("PRUEBAAAA2222222222222");
                        pausa_btn.setText("Reanudar");
                        pacma1.setEnPausa(true);

                        for (int i = 0; i < fantasmas.size(); i++) {
                            fantasmas.get(i).setEnPausa(true);

                        }
                    } else {
                        System.out.println("PRUEBAAAA33333333333");
                        pausa_btn.setText("Pausa");
                        pacma1.setEnPausa(false);

                        pacma1.requestFocusInWindow();
                        for (int i = 0; i < fantasmas.size(); i++) {
                            fantasmas.get(i).setEnPausa(false);

                        }
                    }
                }
            }

        });

    }

    /**
     * Finaliza el Juego
     *
     */
    private void Finalizar() {
        finalizar_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (enjuego) {
                    enjuego = false;
                    NuevosFantasmas();
                    pacma1.Inicial();
                    pacma1.requestFocusInWindow();

                }
            }

        });

    }

    /**
     * Comienza el Juego con un click en pantalla si enjuego es falso, si no no
     * hace nada.
     *
     */
    private class Oyente extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent me) {
            super.mouseClicked(me); //To change body of generated methods, choose Tools | Templates.
            if (!enjuego) {
                for (int i = 0; i < monedas.size(); i++) {
                    monedas.get(i).eliminar();
                }
                CargarMonedas();
                pacma1.setVida(3);
                pacma1.setEnPausa(false);
                enjuego = true;
                pausa_btn.setText("Pausa");
                ban = false;
                contadorM = 0;
                mostrarScore = 0;
                velocidadN = 0;
                score = new Score(Panel_Lienzo.this);
                NuevosFantasmas();

            }
        }

    }

    public ArrayList<Bloque> getBloques() {
        return bloques;
    }

    public void setBloques(ArrayList<Bloque> bloques) {
        this.bloques = bloques;
    }

    public ArrayList<Moneda> getMonedas() {
        return monedas;
    }

    public Pacman getPacma1() {
        return pacma1;
    }

    public void setPacma1(Pacman pacma1) {
        this.pacma1 = pacma1;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public ArrayList<Fantasma> getFantasmas() {
        return fantasmas;
    }

    public void setFantasmas(ArrayList<Fantasma> fantasmas) {
        this.fantasmas = fantasmas;
    }

    public int getContadorM() {
        return contadorM;
    }

    public void setContadorM(int contadorM) {
        this.contadorM = contadorM;
    }

    public boolean isEnjuego() {
        return enjuego;
    }

    public void setEnjuego(boolean enjuego) {
        this.enjuego = enjuego;
    }

    public int getMostrarScore() {
        return mostrarScore;
    }

    public void setMostrarScore(int mostrarScore) {
        this.mostrarScore = mostrarScore;
    }

    public int getVelocidadN() {
        return velocidadN;
    }

    public void setVelocidadN(int velocidadN) {
        this.velocidadN = velocidadN;
    }

}
