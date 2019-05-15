/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pacman;

import Objetos.Objetos;
import Objetos.Player;
import Objetos.Fantasma;
import Graficos.Spritesheet;
import Mapa.Mapa;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

/**
 *
 * @author Guilherme Delmondes
 */
public class Jogo extends Canvas implements Runnable, KeyListener {

    public static JFrame frame;
    private Thread thread;
    private boolean isRunning = true;
    public static final int largura = 52 * 16;
    public static final int altura = 31 * 16;
    public static final int SCALE = 1;

    private int CUR_LEVEL = 1, MAX_LEVEL = 2;

    private BufferedImage image;

    public static List<Objetos> entities;
    public static List<Fantasma> enemies;
    public static Spritesheet spritesheet;

    public static Mapa world;

    public static Player player;

    public static Random rand;

    public Jogo() {
        rand = new Random();
        addKeyListener(this);
        setPreferredSize(new Dimension(largura * SCALE, altura * SCALE));
        initFrame();
        //Inicializa objetos
        image = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
        entities = new ArrayList<>();
        enemies = new ArrayList<>();
        spritesheet = new Spritesheet("/res/spritesheet.png");
        player = new Player(0, 0, 16, 16);
        entities.add(player);
        world = new Mapa("/res/level1.png");
    }

    public static void main(String[] args) {
        Jogo game = new Jogo();
        game.start();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int frames = 0;
        double timer = System.currentTimeMillis();
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                tick();
                render();
                frames++;
                delta--;
            }

            if (System.currentTimeMillis() - timer >= 1000) {
                System.out.println("FPS " + frames);
                frames = 0;
                timer += 1000;
            }
        }
        stop();
    }

    public void tick() {
        for (int i = 0; i < entities.size(); i++) {
            Objetos e = entities.get(i);
            e.tick();
        }

    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = image.getGraphics();
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, largura, altura);

        world.render(g);
        for (int i = 0; i < entities.size(); i++) {
            Objetos e = entities.get(i);
            e.render(g);
        }
     
        g.dispose();
        g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, largura * SCALE, altura * SCALE, null);
        bs.show();

    }

    public synchronized void start() {
        thread = new Thread(this);
        isRunning = true;
        thread.start();
    }

    public synchronized void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void initFrame() {
        frame = new JFrame("PACMAN");
        frame.add(this);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //Sem uso
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
             player.setRight(true);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.setLeft(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
             player.setUp(true);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
             player.setDown(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //faça algo
    }

    public static int getLargura() {
        return largura;
    }

    public static int getAltura() {
        return altura;
    }

    public static List<Objetos> getEntities() {
        return entities;
    }

    public static List<Fantasma> getEnemies() {
        return enemies;
    }
    
    

}
