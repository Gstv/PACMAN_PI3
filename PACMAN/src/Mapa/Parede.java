/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Guilherme Delmondes
 */
public class Parede extends Bloco {

    public Parede(int x, int y) {
        super(x, y);
    }
     public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x, y ,16,16);
    }
}
