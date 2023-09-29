package proyectopacman;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author Victor Gil 24.571.102 Daniel Hernandez 24.426.451
 */
public class Pacman extends JPanel implements Runnable {

    private Panel_Lienzo lienzo1;
    //Para la accion de moverse y los eventos
    private int reqdx, reqdy, viewdx, viewdy;
    //posicion y tamano
    private int x, y, dx, dy;
    //Para el juego
    private boolean enjuego;
    //Para la velocidad de movimiento y el efecto de comer
    private int velocidad;
    private int imagen;
    private final int retraso = 3;
    private int contador;
    private int sentido;
    //Rectangulo del pacman
    private Rectangle rpacman;
    //Vidas del Pacman
    private int vida;
//para dibujar
    private ImageIcon pacman1 = new ImageIcon(getClass().getResource("/imagenes/pacman.gif"));
    private ImageIcon up1 = new ImageIcon(getClass().getResource("/imagenes/up1.gif"));
    private ImageIcon up2 = new ImageIcon(getClass().getResource("/imagenes/up2.gif"));
    private ImageIcon up3 = new ImageIcon(getClass().getResource("/imagenes/up3.gif"));
    private ImageIcon down3 = new ImageIcon(getClass().getResource("/imagenes/down3.gif"));
    private ImageIcon down2 = new ImageIcon(getClass().getResource("/imagenes/down2.gif"));
    private ImageIcon down1 = new ImageIcon(getClass().getResource("/imagenes/down1.gif"));
    private ImageIcon left1 = new ImageIcon(getClass().getResource("/imagenes/left1.gif"));
    private ImageIcon left2 = new ImageIcon(getClass().getResource("/imagenes/left2.gif"));
    private ImageIcon left3 = new ImageIcon(getClass().getResource("/imagenes/left3.gif"));
    private ImageIcon right1 = new ImageIcon(getClass().getResource("/imagenes/right1.gif"));
    private ImageIcon right2 = new ImageIcon(getClass().getResource("/imagenes/right2.gif"));
    private ImageIcon right3 = new ImageIcon(getClass().getResource("/imagenes/right3.gif"));
    private boolean detenido;
    private boolean enPausa;
    private long time_sleep;

    public Pacman(Panel_Lienzo lienzo1) {
        super();
        x = 270;
        y = 150;
        rpacman = new Rectangle(x, y, 30, 30);
        this.lienzo1 = lienzo1;
        dx = 0;
        dy = 0;
        setOpaque(false); // componente transparente
        setBounds(this.x, this.y, 30, 30);
        lienzo1.add(this);
        reqdx = 0;
        reqdy = 0;
        viewdx = -1;
        viewdy = 0;
        detenido = false;
        enPausa = false;
        time_sleep = 19;
        imagen = 0;
        velocidad = 5;
        sentido = 1;
        contador = 2;
        vida = 3;
        addKeyListener(new Oyente());
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs); //To change body of generated methods, choose Tools | Templates.

        if (viewdx == -1) {
            drawPacnanLeft(grphcs);
        } else if (viewdx == 1) {
            drawPacmanRight(grphcs);
        } else if (viewdy == -1) {
            drawPacmanUp(grphcs);
        } else if (viewdy == 1) {
            drawPacmanDown(grphcs);
        }

    }

    private void drawPacmanUp(Graphics grphcs) {

        switch (imagen) {
            case 1:
                grphcs.drawImage(up1.getImage(), 0, 0, getWidth(), getHeight(), this);
                break;
            case 2:
                grphcs.drawImage(up2.getImage(), 0, 0, getWidth(), getHeight(), this);
                break;
            case 3:
                grphcs.drawImage(up3.getImage(), 0, 0, getWidth(), getHeight(), this);
                break;
            default:
                grphcs.drawImage(pacman1.getImage(), 0, 0, getWidth(), getHeight(), this);
                break;
        }
    }

    private void drawPacmanDown(Graphics grphcs) {

        switch (imagen) {
            case 1:
                grphcs.drawImage(down1.getImage(), 0, 0, getWidth(), getHeight(), this);
                break;
            case 2:
                grphcs.drawImage(down2.getImage(), 0, 0, getWidth(), getHeight(), this);
                break;
            case 3:
                grphcs.drawImage(down3.getImage(), 0, 0, getWidth(), getHeight(), this);
                break;
            default:
                grphcs.drawImage(pacman1.getImage(), 0, 0, getWidth(), getHeight(), this);
                break;
        }

    }

    private void drawPacnanLeft(Graphics grphcs) {

        switch (imagen) {
            case 1:
                grphcs.drawImage(left1.getImage(), 0, 0, getWidth(), getHeight(), this);
                break;
            case 2:
                grphcs.drawImage(left2.getImage(), 0, 0, getWidth(), getHeight(), this);
                break;
            case 3:
                grphcs.drawImage(left3.getImage(), 0, 0, getWidth(), getHeight(), this);
                break;
            default:
                grphcs.drawImage(pacman1.getImage(), 0, 0, getWidth(), getHeight(), this);
                break;
        }
    }

    private void drawPacmanRight(Graphics grphcs) {

        switch (imagen) {
            case 1:
                grphcs.drawImage(right1.getImage(), 0, 0, getWidth(), getHeight(), this);
                break;
            case 2:
                grphcs.drawImage(right2.getImage(), 0, 0, getWidth(), getHeight(), this);
                break;
            case 3:
                grphcs.drawImage(right3.getImage(), 0, 0, getWidth(), getHeight(), this);
                break;
            default:
                grphcs.drawImage(pacman1.getImage(), 0, 0, getWidth(), getHeight(), this);
                break;
        }
    }

    /**
     * Realiza el movimiento de boca del pacman
     *
     */
    private void Animacion() {

        contador--;
        if (contador == 0) {
            contador = retraso;
            imagen = imagen + sentido;
            if (imagen == 3 || imagen == 0) {
                sentido = -sentido;
            }
        }
    }

    /**
     * Controla todo el movimiento y eventos del pacman por si muere o choca con
     * un bloque
     *
     */
    private void movePacman() {
        //Si está en los limites de la cuadricula, mueve, si no, retiene la entrada de mousecliced y espera
        if (((x + 30) % 30) == 0 && ((y + 30) % 30) == 0) {
            if ((reqdx == -1 && reqdy == 0) || (reqdx == 1 && reqdy == 0)
                    || (reqdx == 0 && reqdy == -1) || (reqdx == 0 && reqdy == 1)) {
                dx = reqdx;
                dy = reqdy;
                if (dx == 1) {
                    viewdx = 1;
                    viewdy = 0;
                } else if (dx == -1) {
                    viewdx = -1;
                    viewdy = 0;
                } else if (dy == 1) {
                    viewdx = 0;
                    viewdy = 1;
                } else if (dy == -1) {
                    viewdx = 0;
                    viewdy = -1;
                }

            }
        }

        x = x + velocidad * dx;
        y = y + velocidad * dy;
        //Reajustes para que no se salga de los limites
        //Reajusten der
        if ((x + 30) > 570) {
            x = x - velocidad * dx;
            dx = 0;
            dy = 0;

        }
        //Reajuste izq
        if (x < 0) {
            x = x - velocidad * dx;
            dx = 0;
            dy = 0;
        }
        //ajuste abajo
        if (y + 30 > 360) {
            y = y - velocidad * dy;
            dx = 0;
            dy = 0;
        }
        //Reajuste arriba
        if (y < 0) {
            y = y - velocidad * dy;
            dx = 0;
            dy = 0;
        }

        //Reajuste Bloques
        rpacman = new Rectangle(x, y, 30, 30);
        for (int i = 0; i < lienzo1.getBloques().size(); i++) {
            if (rpacman.intersects(lienzo1.getBloques().get(i).getR())) {
                x = x - velocidad * dx;
                y = y - velocidad * dy;
                dx = 0;
                dy = 0;
            }

        }
        //Comer Monedas
        for (int i = 0; i < lienzo1.getMonedas().size(); i++) {
            if (rpacman.intersects(lienzo1.getMonedas().get(i).getRmoneda())) {
                lienzo1.getMonedas().get(i).setComida(true);
                lienzo1.getMonedas().get(i).mostrar();

            }
        }

        //Revisa Fanstasma
        for (int i = 0; i < lienzo1.getFantasmas().size(); i++) {

            if (rpacman.intersects(lienzo1.getFantasmas().get(i).getrFantasma())) {
                //Detiene el hilo de los fantasmas y reinicia el Pacman de lienzo si aun quedan vidas
                vida--;
                if (vida == 0) {
                    lienzo1.setEnjuego(false);
                }

                Inicial();
                lienzo1.NuevosFantasmas();
            }

        }
        setBounds(x, y, 30, 30);
    }

    /**
     * Reinicia al Pacman a su posición por defecto
     *
     */
    public void Inicial() {
        viewdx = -1;
        viewdy = 0;
        reqdx = 0;
        reqdy = 0;
        dx = 0;
        dy = 0;
        x = 270;
        y = 150;
        setBounds(this.x, this.y, 30, 30);
    }

    @Override
    public void run() {

        while (!detenido) {

            try {
                if (!enPausa) {
                    //operaciones
                    Animacion();
                    movePacman();
                    lienzo1.getScore().repaint();
                    repaint();
                    NuevoNivel();

                }
                //reposo
                Thread.sleep(time_sleep);
            } catch (Exception e) {
            }

        }

    }

    /**
     * Carga el nuevo nivel, aumenta la velocidad de ejecucion del hilo de los
     * Cada3 niveles se otorga una vida nueva
     */
    private void NuevoNivel() {
        if (lienzo1.getContadorM() == 187) {
            for (int i = 0; i < lienzo1.getMonedas().size(); i++) {
                lienzo1.getMonedas().get(i).remove(lienzo1);
            }
            lienzo1.CargarMonedas();
            lienzo1.setContadorM(-1);
            if (lienzo1.getMostrarScore() % 563 == 0) {
                vida++;
            }

            lienzo1.setVelocidadN(lienzo1.getVelocidadN() + 1);
            lienzo1.NuevosFantasmas();
            Inicial();

        }

    }

    private class Oyente extends KeyAdapter {

        public Oyente() {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (lienzo1.isEnjuego()) {
                if (key == KeyEvent.VK_LEFT) {
                    System.out.println("CO;OOOOOOO");
                    reqdx = -1;
                    reqdy = 0;
                } else if (key == KeyEvent.VK_RIGHT) {
                    reqdx = 1;
                    reqdy = 0;
                } else if (key == KeyEvent.VK_UP) {
                    reqdx = 0;
                    reqdy = -1;
                } else if (key == KeyEvent.VK_DOWN) {
                    reqdx = 0;
                    reqdy = 1;

                }
            }
        }

    }

    public Panel_Lienzo getLienzo1() {
        return lienzo1;
    }

    public void setLienzo1(Panel_Lienzo lienzo1) {
        this.lienzo1 = lienzo1;
    }

    public int getReqdx() {
        return reqdx;
    }

    public void setReqdx(int reqdx) {
        this.reqdx = reqdx;
    }

    public int getReqdy() {
        return reqdy;
    }

    public void setReqdy(int reqdy) {
        this.reqdy = reqdy;
    }

    public int getViewdx() {
        return viewdx;
    }

    public void setViewdx(int viewdx) {
        this.viewdx = viewdx;
    }

    public int getViewdy() {
        return viewdy;
    }

    public void setViewdy(int viewdy) {
        this.viewdy = viewdy;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public boolean isEnjuego() {
        return enjuego;
    }

    public void setEnjuego(boolean enjuego) {
        this.enjuego = enjuego;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public int getSentido() {
        return sentido;
    }

    public void setSentido(int sentido) {
        this.sentido = sentido;
    }

    public ImageIcon getPacman1() {
        return pacman1;
    }

    public void setPacman1(ImageIcon pacman1) {
        this.pacman1 = pacman1;
    }

    public ImageIcon getUp1() {
        return up1;
    }

    public void setUp1(ImageIcon up1) {
        this.up1 = up1;
    }

    public ImageIcon getUp2() {
        return up2;
    }

    public void setUp2(ImageIcon up2) {
        this.up2 = up2;
    }

    public ImageIcon getUp3() {
        return up3;
    }

    public void setUp3(ImageIcon up3) {
        this.up3 = up3;
    }

    public ImageIcon getDown3() {
        return down3;
    }

    public void setDown3(ImageIcon down3) {
        this.down3 = down3;
    }

    public ImageIcon getDown2() {
        return down2;
    }

    public void setDown2(ImageIcon down2) {
        this.down2 = down2;
    }

    public ImageIcon getDown1() {
        return down1;
    }

    public void setDown1(ImageIcon down1) {
        this.down1 = down1;
    }

    public ImageIcon getLeft1() {
        return left1;
    }

    public void setLeft1(ImageIcon left1) {
        this.left1 = left1;
    }

    public ImageIcon getLeft2() {
        return left2;
    }

    public void setLeft2(ImageIcon left2) {
        this.left2 = left2;
    }

    public ImageIcon getLeft3() {
        return left3;
    }

    public void setLeft3(ImageIcon left3) {
        this.left3 = left3;
    }

    public ImageIcon getRight1() {
        return right1;
    }

    public void setRight1(ImageIcon right1) {
        this.right1 = right1;
    }

    public ImageIcon getRight2() {
        return right2;
    }

    public void setRight2(ImageIcon right2) {
        this.right2 = right2;
    }

    public ImageIcon getRight3() {
        return right3;
    }

    public void setRight3(ImageIcon right3) {
        this.right3 = right3;
    }

    public boolean isDetenido() {
        return detenido;
    }

    public void setDetenido(boolean detenido) {
        this.detenido = detenido;
    }

    public boolean isEnPausa() {
        return enPausa;
    }

    public void setEnPausa(boolean enPausa) {
        this.enPausa = enPausa;
    }

    public long getTime_sleep() {
        return time_sleep;
    }

    public void setTime_sleep(long time_sleep) {
        this.time_sleep = time_sleep;
    }

    public Rectangle getRpacman() {
        return rpacman;
    }

    public void setRpacman(Rectangle rpacman) {
        this.rpacman = rpacman;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

}
