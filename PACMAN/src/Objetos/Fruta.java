package Objetos;

import Pacman.Jogo;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Guilherme Delmondes
 */
public class Fruta extends Objetos {

    public Fruta(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
    }
    public void render(Graphics g) {
        g.drawImage(Jogo.spritesheet.getSprite(62, 32, 16, 16), this.getX(), this.getY(), null);
    }
}
