/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import Mapa.Mapa;
import java.awt.Color;
import java.awt.Graphics;
import Pacman.Jogo;

/**
 *
 * @author guilherme.mdelmondes
 */
public class Player extends Objetos {

    private int x;
    private int y;
    private boolean right, up, left, down, moved;
    private double speed = 1.4;

    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillOval(x, y, 16, 16);
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

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }
    
    public void setDown(boolean down) {
        this.down = down;
    }
    
/*
    public void coletaItem() {
        for (int i = 0; i < Jogo.getEntities().size(); i++) {
            Objetos atual = Jogo.getEntities().get(i);
            if (atual instanceof Pontos) {
                if (Objetos.isColliding(this, atual)) {
                    // System.out.println("AMMO: "+ammo);
                    Jogo.getEntities().remove(atual);
                }
            } else if (atual instanceof Pilula) {
                if (Objetos.isColliding(this, atual)) {
                    // System.out.println("AMMO: "+ammo);
                    Jogo.getEntities().remove(atual);
                }
            } else if (atual instanceof Fruta) {
                if (Objetos.isColliding(this, atual)) {
                    // System.out.println("AMMO: "+ammo);
                    Jogo.getEntities().remove(atual);
                }
            }
        }
    }
*/
    @Override
    public void tick() {
        if (right && Mapa.isFree((int) (x + speed), this.getY())) {
            moved = true;
            left = false;
            up = false;
            down = false;
            this.x += speed;
        } else if (left && Mapa.isFree((int) (x - speed), this.getY())) {
            moved = true;
            right = false;
            up = false;
            down = false;
            this.x -= speed;
        }
        else if (up && Mapa.isFree(this.getX(), (int) (y - speed))) {
            moved = true;
            right = false;
            left = false;
            down = false;
            this.y -= speed;
        }  else if (down && Mapa.isFree(this.getX(), (int) (y + speed))) {
            moved = true;
            right = false;
            left = false;
            up = false;
            this.y += speed;
        }
        //coletaItem();
        //Verifica margens
        if (x > Jogo.getLargura() - 18) {
            this.x = 0;
        } else if (this.x < 0) {
            x = Jogo.getLargura();
        }
    }
    

}
