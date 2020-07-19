package com.savx.entity;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	public Thread thread;
	public BufferedImage img;
	public boolean isRunning;
	public static JFrame frame;
	public static final int widthWindow = 480;
	public static final int heightWindow = 320;
	public static final int scaleWindow = 2;
	
	//Jogadores.
	
	public static Player player;
	public static Enemy enemy;
	public static Ball ball;

	public static void main(String[] args)
	{
		Game game = new Game();
		frame = new JFrame("Pong");
		frame.add(game);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		game.start();
	}
	
	public Game()
	{
		setPreferredSize(new Dimension(widthWindow * scaleWindow, heightWindow * scaleWindow));
		player = new Player(190, heightWindow - 8);
		enemy = new Enemy(190, -12);
		ball = new Ball(240, 150);
		this.addKeyListener(this);
		img = new BufferedImage(widthWindow, heightWindow, BufferedImage.TYPE_INT_RGB);
	}

	public void tick()
	{
		player.tick();
		enemy.tick();
		ball.tick();
	}
	
	public void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = img.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, widthWindow, heightWindow);
		player.render(g);
		enemy.render(g);
		ball.render(g);
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(img, 0, 0, widthWindow * scaleWindow, heightWindow * scaleWindow, null);
		bs.show();
	}
	
	public synchronized void start()
	{
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop()
	{
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() 
	{
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		while(isRunning)
		{
			requestFocus();
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1)
			{
				tick();
				render();
				frames++;
				delta--;
			}
			if (System.currentTimeMillis() - timer >= 1000)
			{
				System.out.println("FPS: "+frames);
				frames = 0;
				timer += 1000;
			}
 		}
	  stop();
	}

	public void keyTyped(KeyEvent e)
	{
		
	}

	public void keyPressed(KeyEvent e) 
	{
		//Player.
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			player.right = true;
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			player.left = true;
		}
		
		//Enemy.
		
		if (e.getKeyCode() == KeyEvent.VK_D)
		{
			enemy.right = true;
		}
		else if (e.getKeyCode() == KeyEvent.VK_A)
		{
			enemy.left = true;
		}
	}

	public void keyReleased(KeyEvent e) 
	{
		//Player.
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			player.right = false;
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			player.left = false;
		}
		
		//Enemy.
		
		if (e.getKeyCode() == KeyEvent.VK_D)
		{
			enemy.right = false;
		}
		else if (e.getKeyCode() == KeyEvent.VK_A)
		{
			enemy.left = false;
		}
	}
}
