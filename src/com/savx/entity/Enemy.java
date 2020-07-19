package com.savx.entity;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy {

	public int width, height;
	public double x, y;
	public boolean right, left;
	
	public Enemy(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.width = 100;
		this.height = 20;
	}
	
	public void tick()
	{
		if (right)
		{
			x += 3;
		}
		else if (left)
		{
			x -= 3;
		}
		
		if (x + width > Game.widthWindow)
		{
			x = Game.widthWindow - width;
		}
		else if (x < 0)
		{
			x = 0;
		}
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, width, height);
	}
}
