package org.roper;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Game extends Frame implements IGameObject, Runnable  {

	
	Player player; //todo clean this stuff up... make it private, user getters and setters...
	
	private Image dbImage;
	private Graphics dbg;
	
	World world;
	
	private List<IGameObject> children;
	
	public Game() {
		children = new ArrayList<IGameObject>();
		world = new World();
		
	}
	
	boolean started;
	@Override
	public void init() {		
		started = false;
		setSize(640, 480);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent we) {
			    System.exit(0);
			  }
			});
		world.init();
		

		player = new Player(world);
		player.init();		
		player.setParent(this);
		addKeyListener(player);		
		children.add(player);

		if (dbImage == null) {
			dbImage = createImage (this.getSize().width, this.getSize().height);

			dbg = dbImage.getGraphics ();
		}

		// Schaffen eines neuen Threads, in dem das Spiel läuft	
		Thread th = new Thread(this);

		// Starten des Threads
		started = true;
		th.start();
		

	
	}

	
	
	@Override
	public void update() {
		
		for (IGameObject go: children) {
			go.update();
		}
		
		
		// Neuzeichnen des Applets
        repaint();
         

        //sleep...
        try {
              // Stoppen des Threads für in Klammern angegebene Millisekunden
              Thread.sleep (20);
        } catch (InterruptedException ex) {}	            

       
       

	}

	@Override
	public void quit() {
		// TODO Auto-generated method stub

	}

	
	

	@Override
	public void run() {
		// Erniedrigen der ThreadPriority
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);	      

		 while (started) {	    	  	
	          update();
	    } 
		 
		// Zurücksetzen der ThreadPriority auf Maximalwert
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
	}
	
	/**Doppelpufferung**/
	// Definition zweier Instanzvariablen für die Doppelpufferung im Kopf des Programmes
	
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
		g.drawImage(((Sprite) world.getBackground()).getImg(), 0, 0, this);
		
		for (Sprite i: world.sprites) {
			
			g.drawImage(i.img, (int) i.pos.x, (int) i.pos.y, this);			
		}
	}
	
	
	public static void main(String[] args) {
		//startingpoint...
		Game game = new Game();
		game.init();
		
	}
	
}
