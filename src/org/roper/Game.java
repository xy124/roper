package org.roper;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Game extends Frame implements IGameObject, Runnable  {

	
	
	@Override
	public void init() {
		
		setSize(800,800);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent we) {
			    System.exit(0);
			  }
			});
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void quit() {
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game game = new Game();
		game.init();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	/**Doppelpufferung**/
	// Definition zweier Instanzvariablen für die Doppelpufferung im Kopf des Programmes
	private Image dbImage;
	private Graphics dbg;
	/** Update - Methode, Realisierung der Doppelpufferung zur Reduzierung des Bildschirmflackerns */
	public void update (Graphics g)	{	      
	      // Bildschirm im Hintergrund löschen
	      dbg.setColor (getBackground ());
	      dbg.fillRect (0, 0, this.getSize().width, this.getSize().height);

	      // Auf gelöschten Hintergrund Vordergrund zeichnen
	      dbg.setColor (getForeground());
	      paint (dbg);

	      // Nun fertig gezeichnetes Bild Offscreen auf dem richtigen Bildschirm anzeigen
	      g.drawImage (dbImage, 0, 0, this);
	}

	public void paint (Graphics g) {		
		
		g.drawString("Punkte: ", 100, 100);
		g.drawString("Zeit: "+" von 2250", 0, 20);
		//g.drawString("HZ: "+flyingbirds, 0, 50);
	}
	
}
