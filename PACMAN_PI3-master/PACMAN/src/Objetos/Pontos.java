/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Guilherme Delmondes
 */
public class Pontos extends Objetos {

    public Pontos(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    
    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x+6, y+6,4,4);
    }

}
