package org.roper;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Game extends Frame implements IGameObject, Runnable  {

	private Image dbImage;
	private Graphics dbg;
	
	Image worldimg;
	
	int tim = 0;
	boolean started;
	@Override
	public void init() {
		try {
		    worldimg = ImageIO.read(new File("share/bild.jpg"));
		} catch (IOException e) {
		}
		
		
		started = false;
		setSize(640,480);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent we) {
			    System.exit(0);
			  }
			});
		started = true;
		
		// Erniedrigen der ThreadPriority
	      Thread.currentThread().setPriority(Thread.MIN_PRIORITY);	      
	      // Solange true ist läuft der Thread weiter
	  
	      
	    
		if (dbImage == null) {
	    	dbImage = createImage (this.getSize().width, this.getSize().height);
	    	
	    	dbg = dbImage.getGraphics ();
	    }
	    
		// Schaffen eines neuen Threads, in dem das Spiel läuft	
		Thread th = new Thread(this);
			
		// Starten des Threads
		th.start();
		

	
	}

	
	
	@Override
	public void update() {
		
		//TODO call update of members.,...
		// Neuzeichnen des Applets
        repaint();
  
        

        try {
              // Stoppen des Threads für in Klammern angegebene Millisekunden
              Thread.sleep (20);
        } catch (InterruptedException ex){}	            

        // Zurücksetzen der ThreadPriority auf Maximalwert
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        tim++;
        if(tim>2250){
        	started = false;
        	
        }

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
		 while (started) {	    	  	
	          update();
	    } 
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
		g.drawString("Zeit: "+" von 2250", 0, 20);
		g.drawImage(worldimg, 0, 0, worldimg.getHeight(this), worldimg.getWidth(this), this);
	}
	
}
