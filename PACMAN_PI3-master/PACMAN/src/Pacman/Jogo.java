/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pacman;

import Objetos.Objetos;
import Objetos.Player;
import Objetos.Fantasma;
import Mapa.Mapa;
import Objetos.Fruta;
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
    
    private static final int largura = 21 * 16;
    private static final int altura = 27 * 16 + 32;
    private static final int SCALE = 1; //Manter essa variavel, ela eh poderosissima para mudar o tamanho do game
   
    private BufferedImage image;

    private static List<Objetos> objJogo;
    private static List<Fantasma> fantasmas;

    private static Mapa mapa;
    private static Player pacman;
    private static Fruta fruit;

    private static Random rand;

    public Jogo() {
        rand = new Random();
        addKeyListener(this);
        setPreferredSize(new Dimension(largura * SCALE, altura * SCALE));
        initFrame();
        
        //Inicializa objetos
        image = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
        objJogo = new ArrayList<>();
        fantasmas = new ArrayList<>();
        pacman = new Player(0, 0, 16, 16);
        //fruit = new Fruta(209, 241, 14, 14);
        
        objJogo.add(pacman);
        //objJogo.add(fruit);
        
        mapa = new Mapa("/res/level1.png");
    }

    public static void main(String[] args) {
        Jogo game = new Jogo();
        game.start();
    }

    @Override
    public void run() {
        //Game loop nao colocar mais nada aqui, toda logica deve ser implementadata no metodo tick
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
        for (int i = 0; i < objJogo.size(); i++) {
            Objetos e = objJogo.get(i);
            e.tick();
        }
        
        //Randomiza o tempo de -aparecimento- da fruta caso ela já não esteja na lista de obj 
        if(rand.nextInt(2000) == 1 && !objJogo.contains(fruit)){
            fruit = new Fruta(209, 241, 14, 14);
            objJogo.add(fruit);
        }
        //Randomiza o tempo de -desaparecimento- da fruta caso ela esteja na lista de obj
        if(rand.nextInt(2000) == 1 && objJogo.contains(fruit)){
            objJogo.remove(fruit);
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
        
        //Renderiza o mapa
        mapa.render(g);
        
        //Desenha objetos herdados da classe objeto, pontos, pacman, fantasma e etc
        for (int i = 0; i < objJogo.size(); i++) {
            Objetos e = objJogo.get(i);
            e.render(g);
        }

        //Desenha UI(Score e vidas)
        g.setColor(Color.white);
        g.drawString("SCORE: " + pacman.getScore(), 4, altura - 12);
        g.drawString("LIVES: " + pacman.getVidas(), largura - 52, altura - 12);

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
            //Direita
            //if(Mapa.colideEsqDir((int)(pacman.getX() + pacman.getVelocidadeX()))){
                pacman.setVelocidadeX(2.0);
                pacman.setVelocidadeY(0);
            //}
              
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            //Esquerda
            if(Mapa.colideEsqDir((int)(pacman.getX() + pacman.getVelocidadeX()))){
                pacman.setVelocidadeX(-2.0);
                pacman.setVelocidadeY(0);
            }
          
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            //Cima
            pacman.setVelocidadeX(0);
            pacman.setVelocidadeY(-2.0);

        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            //Baixo
            pacman.setVelocidadeX(0);
            pacman.setVelocidadeY(2.0);
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

    public static List<Objetos> getObjJogo() {
        return objJogo;
    }

    public static List<Fantasma> getFantasmas() {
        return fantasmas;
    }

    public static Player getPacman() {
        return pacman;
    }

}
