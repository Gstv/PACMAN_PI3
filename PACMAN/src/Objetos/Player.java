package Objetos;

import Mapa.Camera;
import Mapa.Mapa;
import Mapa.Parede;
import java.awt.Graphics;
import Pacman.Jogo;
import java.awt.image.BufferedImage;

/**
 *
 * @author guilherme.mdelmondes
 */
public class Player extends Objetos {

    private int x;
    private int y;
    private double velocidadeX = 0.6, velocidadeY = 0.6;
    private int vidas = 3;
    private int score = 0;
    private boolean pediu;

    public Player(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
    }

    public int getX() {
        return x;
    }

    public double getVelocidadeX() {
        return velocidadeX;
    }

    public void setVelocidadeX(double velocidadeX) {
        this.velocidadeX = velocidadeX;
    }

    public double getVelocidadeY() {
        return velocidadeY;
    }

    public void setVelocidadeY(double velocidadeY) {
        this.velocidadeY = velocidadeY;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public void moverDir() {
        System.out.println(Mapa.colide((int) (x + velocidadeX), (int) (y + velocidadeY)));
        this.setVelocidadeX(1.0);
        this.setVelocidadeY(0);

    }

    public void moverEsq() {
        System.out.println(Mapa.colide((int) (x + velocidadeX), (int) (y + velocidadeY)));
        this.setVelocidadeX(-1.0);
        this.setVelocidadeY(0);

    }

    public void moverCima() {
        System.out.println(Mapa.colide((int) (x + velocidadeX), (int) (y + velocidadeY)));
        this.setVelocidadeX(0);
        this.setVelocidadeY(-1.0);

    }

    public void moverBaixo() {
        System.out.println(Mapa.colide((int) (x + velocidadeX), (int) (y + velocidadeY)));
        this.setVelocidadeX(0);
        this.setVelocidadeY(1.0);

    }

    //Acrescentar aqui, logica para colisao com a pilula de poder e frutas
    public void coletaItem() {
        for (int i = 0; i < Jogo.getObjJogo().size(); i++) {
            Objetos atual = Jogo.getObjJogo().get(i);
            if (atual instanceof Pontos) {
                if (Objetos.isColliding(this, atual)) {
                    this.score += 10;
                    Jogo.getObjJogo().remove(atual);
                }
            }

            if (atual instanceof Pilula) {
                if (Objetos.isColliding(this, atual)) {
                    this.score += 10;
                    System.out.println("Conceder o bônus");
                    Jogo.getObjJogo().remove(atual);
                }
            }

            if (atual instanceof Fruta) {
                if (Objetos.isColliding(this, atual)) {
                    this.score += 100;
                    Jogo.getObjJogo().remove(atual);
                }
            }
        }
    }

    @Override
    public void tick() {
        if (!Mapa.colide((int) (x + velocidadeX), (int) (y + velocidadeY))) {
            this.x += velocidadeX;
            this.y += velocidadeY;
       }

        coletaItem();
        //Verifica margens
        if (x > Jogo.getLargura() - 18) {
            this.x = 0;
        } else if (this.x < 0) {
            x = Jogo.getLargura() - 18;
        }

        Camera.x = this.getX() - (Jogo.getLargura() / 2);
        Camera.y = this.getY() - (Jogo.getAltura() / 2);
    }

    public void render(Graphics g) {
        g.drawImage(Jogo.spritesheet.getSprite(112, 0, 16, 16), this.getX() - Camera.x, this.getY() - Camera.y, null);
    }
}
