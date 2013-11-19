package br.com.etyllica.network.lps.model;

import java.awt.Color;

import br.com.etyllica.core.Drawable;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.network.lps.network.WifiNetwork;

public class Net implements Drawable{
	
	private Color color;
	
	private int x;
	private int y;
	//private int z;
	
	private String name;
	
	private float range;
	
	public Net(WifiNetwork network) {
		super();
		this.name = network.getSSID();
		this.range = network.getQuality();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public float getRange() {
		return range;
	}

	public void setRange(float range) {
		this.range = range;
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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public void draw(Graphic g) {
		
		//int radius = 300-range*3;
		int radius = 100-(int)(100*range);
		
		g.setAlpha(50);
		g.setColor(color);
		g.fillCircle(x, y, radius);
		
		g.setAlpha(100);
		g.setColor(Color.BLACK);
		g.drawCircle(x, y, radius);
		
		g.setColor(Color.WHITE);
		g.drawStringShadow(x-radius/2, y-radius/2, radius, radius, name, Color.BLACK);
	}

}
