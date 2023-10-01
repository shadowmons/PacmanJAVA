package proyectopacman;

import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 *
 * @author Victor Gil 24.571.102 Daniel Hernandez 24.426.451
 */
public class Fantasma extends JPanel implements Runnable {

    private int x, y;
    //cual de los fantasmas, rojo es el mas lento, azul el más rápido
    private int tipo;
    //para el movimiento
    private int movx, movy;
    private int velocidad;
    private Random r;
    //Para la direccion
    private int aleatorio;
    //Para la Intersección
    private Rectangle rFantasma;
    private Rectangle rFantasmax;
    private Rectangle rFantasmay;
    private ImageIcon a1 = new ImageIcon(getClass().getResource("/imagenes/a1.png"));
    private ImageIcon a2 = new ImageIcon(getClass().getResource("/imagenes/a2.png"));
    private ImageIcon a3 = new ImageIcon(getClass().getResource("/imagenes/a3.png"));
    private ImageIcon e1 = new ImageIcon(getClass().getResource("/imagenes/e1.png"));
    private ImageIcon e2 = new ImageIcon(getClass().getResource("/imagenes/e2.png"));
    private ImageIcon e3 = new ImageIcon(getClass().getResource("/imagenes/e3.png"));
    private ImageIcon a4 = new ImageIcon(getClass().getResource("/imagenes/a4.png"));
    private ImageIcon e4 = new ImageIcon(getClass().getResource("/imagenes/e4.png"));
    private ImageIcon r4 = new ImageIcon(getClass().getResource("/imagenes/r4.png"));
    private ImageIcon r1 = new ImageIcon(getClass().getResource("/imagenes/r1.png"));
    private ImageIcon r2 = new ImageIcon(getClass().getResource("/imagenes/r2.png"));
    private ImageIcon r3 = new ImageIcon(getClass().getResource("/imagenes/r3.png"));
    private ImageIcon t1 = new ImageIcon(getClass().getResource("/imagenes/t1.png"));
    private ImageIcon t2 = new ImageIcon(getClass().getResource("/imagenes/t2.png"));
    private ImageIcon t3 = new ImageIcon(getClass().getResource("/imagenes/t3.png"));
    private ImageIcon t4 = new ImageIcon(getClass().getResource("/imagenes/t4.png"));
    private Panel_Lienzo lienzo1;
    private boolean detenido;
    private boolean enPausa;
    private long time_sleep;

    public Fantasma(int tipo, int veloN, Panel_Lienzo lienzo1) {
        super();
        this.tipo = tipo;
        x = 270;
        y = 90;
        this.lienzo1 = lienzo1;
        setOpaque(false); // componente transparente
        setBounds(this.x, this.y, 30, 30);
        lienzo1.add(this);
        detenido = false;
        enPausa = false;
        time_sleep = 70 - 4 * veloN;
        if (time_sleep < 22) {
            time_sleep = 22;
        }
        if (tipo < 3) {
            velocidad = 1 + tipo;
        } else {
            velocidad = 2 + tipo;
        }
        r = new Random();
        //r maneja la probabilidad para un nuevo movimiento aleatorio con un 1 o un 2.
        aleatorio = (int) (r.nextDouble() + 1.9);
        switch (tipo) {
            case 3:
                movx = 1;
                movy = 0;
                break;
            case 4:
                movx = -1;
                movy = 0;
                break;
            default:
                movx = 0;
                movy = -1;
                break;
        }

    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs); //To change body of generated methods, choose Tools | Templates.

        if (tipo == 1) {
            fantasma1(grphcs);
        } else if (tipo == 2) {
            fantasma2(grphcs);
        } else if (tipo == 3) {
            fantasma3(grphcs);
        } else {
            fantasma4(grphcs);
        }

    }

    @Override
    public void run() {

        while (!detenido) {

            try {
                if (!enPausa) {
                    Movimiento();
                }
                Thread.sleep(time_sleep);
            } catch (Exception e) {
            }

        }

    }

    /**
     * Pinta al Fantasma rojo
     *
     */
    private void fantasma1(Graphics grphcs) {
        if (movx == -1) {
            grphcs.drawImage(r3.getImage(), 0, 0, getWidth(), getHeight(), this);
        } else if (movx == 1) {
            grphcs.drawImage(r4.getImage(), 0, 0, getWidth(), getHeight(), this);
        } else if (movy == -1) {
            grphcs.drawImage(r1.getImage(), 0, 0, getWidth(), getHeight(), this);

        } else if (movy == 1) {
            grphcs.drawImage(r2.getImage(), 0, 0, getWidth(), getHeight(), this);
        }

    }

    /**
     * Pinta al Fantasma azul
     *
     */
    private void fantasma2(Graphics grphcs) {
        if (movx == -1) {
            grphcs.drawImage(a3.getImage(), 0, 0, getWidth(), getHeight(), this);

        } else if (movx == 1) {
            grphcs.drawImage(a4.getImage(), 0, 0, getWidth(), getHeight(), this);
        } else if (movy == -1) {
            grphcs.drawImage(a1.getImage(), 0, 0, getWidth(), getHeight(), this);

        } else if (movy == 1) {
            grphcs.drawImage(a2.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }

    /**
     * Pinta al Fantasma rosa
     *
     */
    private void fantasma3(Graphics grphcs) {
        if (movx == -1) {
            grphcs.drawImage(e3.getImage(), 0, 0, getWidth(), getHeight(), this);

        } else if (movx == 1) {
            grphcs.drawImage(e4.getImage(), 0, 0, getWidth(), getHeight(), this);
        } else if (movy == -1) {
            grphcs.drawImage(e1.getImage(), 0, 0, getWidth(), getHeight(), this);

        } else if (movy == 1) {
            grphcs.drawImage(e2.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }

    /**
     * Pinta al Fantasma naranja
     *
     */
    private void fantasma4(Graphics grphcs) {
        if (movx == -1) {
            grphcs.drawImage(t3.getImage(), 0, 0, getWidth(), getHeight(), this);

        } else if (movx == 1) {
            grphcs.drawImage(t4.getImage(), 0, 0, getWidth(), getHeight(), this);
        } else if (movy == -1) {
            grphcs.drawImage(t1.getImage(), 0, 0, getWidth(), getHeight(), this);

        } else if (movy == 1) {
            grphcs.drawImage(t2.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }

    /**
     * Genera y controla el movimiento de los fantasmas
     *
     */
    private void Movimiento() {
        //Nuevo recorrido, Se mueve cuando esta dentro de la cuadricula
        if (((x + 30) % 30) == 0 && ((y + 30) % 30) == 0) {
            if (aleatorio == 1) {
                aleatorio = (int) (r.nextDouble() * 4);
                switch (aleatorio) {
                    case 1:
                        movx = 1;
                        movy = 0;
                        break;
                    case 2:
                        movx = -1;
                        movy = 0;
                        break;
                    case 3:
                        movx = 0;
                        movy = 1;
                        break;
                    default:
                        movx = 0;
                        movy = -1;
                        break;
                }
            }
        }

        x = x + velocidad * movx;
        y = y + velocidad * movy;

        //Reajuste derecha
        if ((x + 30) > lienzo1.getWidth()) {
            x = x - velocidad * movx;
            Reajustex();
            Reajustex2();

        }
        //Reajuste izq
        if (x < 0) {
            x = x - velocidad * movx;
            Reajustex();
            Reajustex2();

        }
        //ajuste abajo
        if (y + 30 > 360) {
            y = y - velocidad * movy;
            Reajustey();
            Reajustey2();
        }
        //Reajuste arriba
        if (y < 0) {
            y = y - velocidad * movy;
            Reajustey();
            Reajustey2();
        }
        //Reajuste Bloque
        rFantasma = new Rectangle(x, y, 30, 30);
        for (int i = 0; i < lienzo1.getBloques().size(); i++) {
            if (rFantasma.intersects(lienzo1.getBloques().get(i).getR())) {
                if (movx != 0) {
                    x = x - velocidad * movx;
                    Reajustex();
                    rFantasmax = new Rectangle(x, y, 30, 30);
                    for (int j = 0; j < lienzo1.getBloques().size(); j++) {
                        if (rFantasmax.intersects(lienzo1.getBloques().get(j).getR())) {
                            y = y - velocidad * movy;
                            if (movy == 1) {
                                movx = 0;
                                movy = -1;
                            } else {
                                movx = 0;
                                movy = 1;
                            }
                            y = y + velocidad * movy;
                        }

                    }
                } else if (movy != 0) {
                    y = y - velocidad * movy;
                    Reajustey();
                    rFantasmay = new Rectangle(x, y, 30, 30);
                    for (int j = 0; j < lienzo1.getBloques().size(); j++) {
                        if (rFantasmay.intersects(lienzo1.getBloques().get(j).getR())) {
                            x = x - velocidad * movx;
                            if (movx == 1) {
                                movx = -1;
                                movy = 0;
                            } else {
                                movx = 1;
                                movy = 0;
                            }
                            x = x + velocidad * movx;
                        }

                    }
                }
            }

        }
        rFantasma = new Rectangle(x, y, 30, 30);
        setBounds(this.x, this.y, 30, 30);
        aleatorio = (int) (r.nextDouble() + 1.7);

    }

    private void Reajustex() {
        aleatorio = (int) (r.nextDouble() + 1.5);
        if (aleatorio == 1) {
            movx = 0;
            movy = 1;
            y = y + velocidad * movy;
        } else {
            movx = 0;
            movy = -1;
            y = y + velocidad * movy;
        }

    }

    private void Reajustex2() {
        if (y + 30 > 360) {
            y = y - velocidad * movy;
            movx = 0;
            movy = -1;

        } else if (y < 0) {
            y = y - velocidad * movy;
            movx = 0;
            movy = 1;

        }

    }

    private void Reajustey() {
        aleatorio = (int) (r.nextDouble() + 1.5);
        if (aleatorio == 1) {
            movx = 1;
            movy = 0;
            x = x + velocidad * movx;
        } else {
            movx = -1;
            movy = 0;
            x = x + velocidad * movx;
        }

    }

    private void Reajustey2() {
        if (x + 30 > lienzo1.getWidth()) {
            x = x - velocidad * movx;
            movx = -1;
            movy = 0;

        } else if (x < 0) {
            x = x - velocidad * movx;
            movx = 1;
            movy = 0;

        }

        //Reajuste abajo
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getMovx() {
        return movx;
    }

    public void setMovx(int movx) {
        this.movx = movx;
    }

    public int getMovy() {
        return movy;
    }

    public void setMovy(int movy) {
        this.movy = movy;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getAleatorio() {
        return aleatorio;
    }

    public void setAleatorio(int aleatorio) {
        this.aleatorio = aleatorio;
    }

    public Random getR() {
        return r;
    }

    public void setR(Random r) {
        this.r = r;
    }

    public ImageIcon getA1() {
        return a1;
    }

    public void setA1(ImageIcon a1) {
        this.a1 = a1;
    }

    public ImageIcon getA2() {
        return a2;
    }

    public void setA2(ImageIcon a2) {
        this.a2 = a2;
    }

    public ImageIcon getA3() {
        return a3;
    }

    public void setA3(ImageIcon a3) {
        this.a3 = a3;
    }

    public ImageIcon getE1() {
        return e1;
    }

    public void setE1(ImageIcon e1) {
        this.e1 = e1;
    }

    public ImageIcon getE2() {
        return e2;
    }

    public void setE2(ImageIcon e2) {
        this.e2 = e2;
    }

    public ImageIcon getE3() {
        return e3;
    }

    public void setE3(ImageIcon e3) {
        this.e3 = e3;
    }

    public ImageIcon getA4() {
        return a4;
    }

    public void setA4(ImageIcon a4) {
        this.a4 = a4;
    }

    public ImageIcon getE4() {
        return e4;
    }

    public void setE4(ImageIcon e4) {
        this.e4 = e4;
    }

    public ImageIcon getR4() {
        return r4;
    }

    public void setR4(ImageIcon r4) {
        this.r4 = r4;
    }

    public ImageIcon getR1() {
        return r1;
    }

    public void setR1(ImageIcon r1) {
        this.r1 = r1;
    }

    public ImageIcon getR2() {
        return r2;
    }

    public void setR2(ImageIcon r2) {
        this.r2 = r2;
    }

    public ImageIcon getR3() {
        return r3;
    }

    public void setR3(ImageIcon r3) {
        this.r3 = r3;
    }

    public ImageIcon getT1() {
        return t1;
    }

    public void setT1(ImageIcon t1) {
        this.t1 = t1;
    }

    public ImageIcon getT2() {
        return t2;
    }

    public void setT2(ImageIcon t2) {
        this.t2 = t2;
    }

    public ImageIcon getT3() {
        return t3;
    }

    public void setT3(ImageIcon t3) {
        this.t3 = t3;
    }

    public ImageIcon getT4() {
        return t4;
    }

    public void setT4(ImageIcon t4) {
        this.t4 = t4;
    }

    public Panel_Lienzo getLienzo1() {
        return lienzo1;
    }

    public void setLienzo1(Panel_Lienzo lienzo1) {
        this.lienzo1 = lienzo1;
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

    public Rectangle getrFantasma() {
        return rFantasma;
    }

    public void setrFantasma(Rectangle rFantasma) {
        this.rFantasma = rFantasma;
    }

}
