/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

import java.awt.image.BufferedImage;

/**
 *
 * @author Guilherme Delmondes
 */
public class Parede extends Bloco {

    public Parede(int x, int y, BufferedImage sprite) {
        super(x, y, sprite);
    }
/*
    public static boolean colide(Player pacman) {
        if (this.getX() >= pacman.getX()
                && this.getX() <= pacman.getX() + 16
                && this.getY() >= pacman.getY()
                && this.getY() <= pacman.getY() + 16) {
            return true;
        }
        return false;
    }
    */
}

