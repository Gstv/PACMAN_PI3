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
public class Bloco {

    protected int x, y;

    public Bloco(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(x, y,16,16);
    }
}
