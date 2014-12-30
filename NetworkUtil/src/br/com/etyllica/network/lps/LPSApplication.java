package br.com.etyllica.network.lps;

import java.awt.Color;
import java.util.List;

import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.graphics.SVGColor;
import br.com.etyllica.network.lps.model.Interaction;
import br.com.etyllica.network.lps.model.Net;
import br.com.etyllica.network.lps.network.WifiNetwork;

public class LPSApplication extends Application {

	private Net a;
	private Net b;
	private Net c;
	
	private Interaction ab;
	private Interaction ac;
	private Interaction bc;
	
	private float px;
	private float py;
	
	public LPSApplication(List<WifiNetwork> networks){
		super(640,480);
		
		a = new Net(networks.get(0));
		b = new Net(networks.get(1));
		c = new Net(networks.get(2));
		
	}
	
	@Override
	public void load() {
		// TODO Auto-generated method stub
		
		a.setX(200);
		a.setY(120);
		a.setColor(Color.CYAN);
		
		b.setX(240);
		b.setY(170);
		b.setColor(Color.ORANGE);
		
		c.setX(120);
		c.setY(190);
		c.setColor(SVGColor.SEA_GREEN);
		
		ab = new Interaction(a, b);
		ac = new Interaction(a, c);
		bc = new Interaction(b, c);
		
		px = (ab.getIx()+ac.getIx()+bc.getIx())/3;
		py = (ab.getIy()+ac.getIy()+bc.getIy())/3;
		
		loading = 100;
	}
	
	float offset = 0.10f;
	
	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		
		if(event.isKeyDown(KeyEvent.TSK_1)){
			a.setRange(a.getRange()+offset);
			System.out.println("a range: "+a.getRange());
		}
		if(event.isKeyDown(KeyEvent.TSK_2)){
			a.setRange(a.getRange()-offset);
			System.out.println("a range: "+a.getRange());
		}
		
		if(event.isKeyDown(KeyEvent.TSK_3)){
			b.setRange(b.getRange()+offset);
			System.out.println("b range: "+b.getRange());
		}
		if(event.isKeyDown(KeyEvent.TSK_4)){
			b.setRange(b.getRange()-offset);
			System.out.println("b range: "+b.getRange());
		}
		
		if(event.isKeyDown(KeyEvent.TSK_5)){
			c.setRange(c.getRange()+offset);
			System.out.println("c range: "+c.getRange());
		}
		if(event.isKeyDown(KeyEvent.TSK_6)){
			c.setRange(c.getRange()-offset);
			System.out.println("c range: "+c.getRange());
		}
		
		
		return GUIEvent.NONE;		
	}

	@Override
	public void draw(Graphic g) {

		a.draw(g);
		b.draw(g);
		c.draw(g);
		
		ab.draw(g);
		ac.draw(g);
		bc.draw(g);
		
		g.setColor(Color.BLACK);
		g.fillCircle((int)px, (int)py, 6);
		g.setColor(Color.YELLOW);
		g.fillCircle((int)px, (int)py, 4);
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}
	
}
