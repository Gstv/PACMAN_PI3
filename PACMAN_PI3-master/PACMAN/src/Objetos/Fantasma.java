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
public class Fantasma extends Objetos {

    private double speed = 0.6;


    public Fantasma(int x, int y, int width, int height) {
        super(x, y, width, height);
        
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(x,y, 16,16);
    }

}
