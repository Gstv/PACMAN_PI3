package Objetos;

import Mapa.Camera;
import Mapa.No;
import Mapa.Vector2i;
import Pacman.Jogo;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.List;


/**
 *
 * @author Guilherme Delmondes
 */
public class Objetos {

    public static BufferedImage FANTASMA_EN = Jogo.spritesheet.getSprite(7*16,16,16,16);
    public static BufferedImage PILULA_SPRITE = Jogo.spritesheet.getSprite(128,0,16,16);
    public static BufferedImage PONTOS_SPRITE = Jogo.spritesheet.getSprite(128,16,16,16);
    public static BufferedImage FRUTA_SPRITE = Jogo.spritesheet.getSprite(16,32,16,16);
    
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    public int depth;
    protected List<No> path;
    private int maskX, maskY, maskW, maskH;
    private BufferedImage sprite;

    public Objetos(int x, int y, int width, int height, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = sprite;
 
        this.maskX = 0;
        this.maskY = 0;
        this.maskW = width;
        this.maskH = height;
    }
    
    public static Comparator<Objetos> nodeSorter = new Comparator<Objetos>() {
        @Override
        public int compare(Objetos n0,Objetos n1) {
            if(n1.depth < n0.depth)
                return +1;
            if(n1.depth > n0.depth)
                return -1;
            return 0;
        }
    };
    
    public void setMask(int maskX, int maskY, int maskW, int maskH) {
        this.maskX = maskX;
        this.maskY = maskY;
        this.maskW = maskW;
        this.maskH = maskH;
    }

    public static boolean isColliding(Objetos e1, Objetos e2) {
        Rectangle e1Mask = new Rectangle(e1.getX() + e1.maskX, e1.getY() + e1.maskY, e1.maskW, e1.maskH);
        Rectangle e2Mask = new Rectangle(e2.getX() + e2.maskX, e2.getY() + e2.maskY, e2.maskW, e2.maskH);
        return e1Mask.intersects(e2Mask);
    }

    public void tick() {}
    
    public void followPath(List<No> path) {
        if(path != null) {
            if(path.size() > 0) {
                Vector2i target = path.get(path.size() - 1).tile;
                //xprev = x;
                //yprev = y;
                if(x < target.x * 16) {
                    x++;
                }else if(x > target.x * 16) {
                    x--;
                }

                if(y < target.y * 16) {
                    y++;
                }else if(y > target.y * 16) {
                    y--;
                }

                if(x == target.x * 16 && y == target.y * 16) {
                    path.remove(path.size() - 1);
                }
            }
        }
    }
    
    public void render(Graphics g) {
        g.drawImage(sprite, this.getX() - Camera.x,this.getY() - Camera.y, null);
    }

    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public int getX() {
        return (int) x;
    }
    
    public int getY() {
        return (int) y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
