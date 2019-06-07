package Mapa;

import Pacman.Jogo;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Bloco {
	
    public static BufferedImage TILE_FLOOR = Jogo.spritesheet.getSprite(144,16,16,16);
    public static BufferedImage TILE_WALL = Jogo.spritesheet.getSprite(144,0,16,16);

    private BufferedImage sprite;
    private int x,y;

    public Bloco(int x, int y, BufferedImage sprite){
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    //Renderiza todos os tiles do mapa
    public void render(Graphics g){
        g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
    }

}
