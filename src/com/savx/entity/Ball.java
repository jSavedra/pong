package com.savx.entity;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {

	public double x, y;
	public double  dx, dy;
	public double spd = 5.5;
	public int width, height;
	public int scorePlayer;
	public int scoreEnemy;
	public int roundsPlayer;
	public int roundsEnemy;
	public int angle1 = 90;
	public int angle2 = 90;
	
	public Ball(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.width = 7;
		this.height = 7;
		
		int angle = new Random().nextInt(90);
		if (angle < 45)
		{
			angle = angle1;
		}else {
			angle = angle2;
		}
		
		dx = Math.cos(Math.toRadians(angle));
		dy = Math.sin(Math.toRadians(angle));
	}
	
	public void tick()
	{	
		if (x + (dx * spd) + width >= Game.widthWindow)
		{
			dx *= -1;
		}
		
		else if (x + (dy * spd) < 0)
		{
			dx *= -1;
		}
		
		if (y >= Game.heightWindow)
		{
			scorePlayer++;
			Game.player.x = 190;
			Game.player.y = Game.heightWindow - 8;
			Game.enemy.x = 190;
			Game.enemy.y = - 12;
			x = 240;
			y = 150;
			dx = 0;
			dy = 1;
			
			if (scorePlayer == 5|| scoreEnemy == 5)
			{
				scorePlayer = 0;
				scoreEnemy = 0;
				roundsEnemy++;
				if (roundsEnemy == 3)
				{
					new Game();
					return;
				}
			}
		}
		else if (y < 0)
		{
			scoreEnemy++;
			Game.enemy.x = 190;
			Game.enemy.y = - 12;
			Game.player.x = 190;
			Game.player.y = Game.heightWindow - 8;
			x = 240;
			y = 150;
			dx = 0;
			dy = 1;
			
			if (scorePlayer == 5 || scoreEnemy == 5)
			{
				scorePlayer = 0;
				scoreEnemy = 0;
				roundsPlayer++;
				if (roundsPlayer == 3)
				{
					new Game();
					return;
				}
			}
		}

		//Colisão dos Players.

		
		Rectangle bounds = new Rectangle ((int)(x + (dx * spd)), (int)(y + (dy * spd)), width, height);
		Rectangle boundsPlayer = new Rectangle ((int)Game.player.x, (int)Game.player.y, Game.player.width, Game.player.height);
		Rectangle boundsEnemy = new Rectangle ((int)Game.enemy.x, (int)Game.enemy.y, Game.enemy.width, Game.enemy.height);
		
		//Movimento caso colida com os Players.

		if (bounds.intersects(boundsPlayer))
		{
			int angle1 = new Random().nextInt(120) + 25;
			if (angle1 < 40)
			{
				angle1 = 120;
			}
			dx = Math.cos(Math.toRadians(angle1));
			dy = Math.sin(Math.toRadians(angle1));
			if (dy > 0)
			{
				dy *= -1;
			}
		}
		else if (bounds.intersects(boundsEnemy))
		{
			int angle2 = new Random().nextInt(120) + 25;
			if (angle2 < 40)
			{
				angle2 = 120;
			}
			dx = Math.cos(Math.toRadians(angle2));
			dy = Math.sin(Math.toRadians(angle2));
			if (dy < 0)
			{
				dy *= -1;
			}
		}

		//Velocidade

		x += dx  * spd;
		y += dy * spd;
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		
		//Pontuação Player.
		
		if (scorePlayer == 0)
		{ 
			g.drawString("0", 235, 120);
		}
		else if (scorePlayer == 1)
		{ 
			g.drawString("1", 235, 120);
		}
		else if (scorePlayer == 2)
		{ 
			g.drawString("2", 235, 120);
		}
		else if (scorePlayer == 3)
		{ 
			g.drawString("3", 235, 120);
		}
		else if (scorePlayer == 4)
		{ 
			g.drawString("4", 235, 120);
		}
		else if (scorePlayer == 5)
		{ 
			g.drawString("5", 235, 120);
		}
		
		//Placar Player2.
		
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		if (scoreEnemy == 0)
		{
			g.drawString("0", 236, 200);
		}
		else if (scoreEnemy == 1)
		{
			g.drawString("1", 236, 200);
		}
		else if (scoreEnemy == 2)
		{
			g.drawString("2", 236, 200);
		}
		else if (scoreEnemy == 3)
		{
			g.drawString("3", 236, 200);
		}
		else if (scoreEnemy == 4)
		{
			g.drawString("4", 236, 200);
		}
		else if (scoreEnemy == 5)
		{
			g.drawString("5", 236, 200);
		}
		
		//Cor Da Bola. 
		
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, width, height);
		
		//Linhas Lado Direito.
		
		g.setColor(Color.white);
		g.fillRect(234, 149, 20, 2);
		g.setColor(Color.white);
		g.fillRect(264, 149, 20, 2);
		g.setColor(Color.white);
		g.fillRect(294, 149, 20, 2);
		g.setColor(Color.white);
		g.fillRect(324, 149, 20, 2);
		g.setColor(Color.white);
		g.fillRect(354, 149, 20, 2);
		g.setColor(Color.white);
		g.fillRect(384, 149, 20, 2);
		g.setColor(Color.white);
		g.fillRect(414, 149, 20, 2);
		g.setColor(Color.white);
		g.fillRect(444, 149, 20, 2);
		g.setColor(Color.white);
		g.fillRect(474, 149, 20, 2);
		
		//Linhas Lado Esquerdo.
		
		g.fillRect(204, 149, 20, 2);
		g.setColor(Color.white);
		g.fillRect(174, 149, 20, 2);
		g.setColor(Color.white);
		g.fillRect(144, 149, 20, 2);
		g.setColor(Color.white);
		g.fillRect(114, 149, 20, 2);
		g.setColor(Color.white);
		g.fillRect(84, 149, 20, 2);
		g.setColor(Color.white);
		g.fillRect(54, 149, 20, 2);
		g.setColor(Color.white);
		g.fillRect(24, 149, 20, 2);
		g.setColor(Color.white);
		g.fillRect(-4, 149, 20, 2);
		
		//Rounds.
		
		g.setFont(new Font("Arial", Font.BOLD, 10));
		g.drawString("Rounds:", 10, 250);
		
		if (roundsPlayer == 0)
		{
			g.drawString("0", 55, 250);
		}
		else if (roundsPlayer == 1)
		{
			g.drawString("1", 55, 250);
		}
		else if (roundsPlayer == 2)
		{
			g.drawString("2", 55, 250);
		}
		else if (roundsPlayer == 3)
		{
			g.drawString("3", 55, 250);
		}
		
		g.setFont(new Font("Arial", Font.BOLD, 10));
		g.drawString("Rounds:", 10, 80);
		
		if (roundsEnemy == 0)
		{
			g.drawString("0", 55, 80);
		}
		else if (roundsEnemy == 1)
		{
			g.drawString("1", 55, 80);
		}
		else if (roundsEnemy == 2)
		{
			g.drawString("2", 55, 80);
		}
		else if (roundsEnemy == 3)
		{
			g.drawString("3", 55, 80);
		}
	}
}







