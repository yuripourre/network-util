package br.com.etyllica.network.lps.model;

import java.awt.Color;

import br.com.etyllica.core.Drawable;
import br.com.etyllica.core.graphics.Graphic;

public class Interaction implements Drawable {
	
	private Net a;
	
	private Net b;
	
	private float ix;
	private float iy;
	
	private float strongness;
	private float aStrongness;
	private float bStrongness;
	
	public Interaction(Net a, Net b){
		super();
		
		this.a = a;
		this.b = b;
		
		strongness = a.getRange()+b.getRange();
		
		aStrongness = (float)(a.getRange()/strongness);
		bStrongness = (float)(b.getRange()/strongness);
		
		ix = a.getX()*aStrongness+b.getX()*bStrongness;
		iy = a.getY()*aStrongness+b.getY()*bStrongness;
		
	}

	public Net getA() {
		return a;
	}

	public void setA(Net a) {
		this.a = a;
	}

	public Net getB() {
		return b;
	}

	public void setB(Net b) {
		this.b = b;
	}

	public float getIx() {
		return ix;
	}

	public void setIx(float ix) {
		this.ix = ix;
	}

	public float getIy() {
		return iy;
	}

	public void setIy(float iy) {
		this.iy = iy;
	}
	
	public void draw(Graphic g){
		g.setColor(Color.BLACK);
		
		//AB Distance
		g.drawLine(a.getX(), a.getY(), b.getX(), b.getY());
		
		g.fillCircle((int)ix, (int)iy, 7);
		g.setColor(Color.RED);
		g.fillCircle((int)ix, (int)iy, 5);
	}
	
}
