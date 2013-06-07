package org.roper;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Game extends Frame implements Runnable, MouseListener  {

	
	Player player; //todo clean this stuff up... make it private, user getters and setters...
	
	private Image dbImage[];
	private Graphics dbg[];
	
	World world;
	
	private List<IGameObject> children;
	
	public Game() {
		children = new ArrayList<IGameObject>();
		world = new World();
		
		dbImage = new Image[2];
		dbg = new Graphics[2];
		
	}
	
	boolean started;

	
	private int currentBuffer;


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
		

		player = new Player();
		player.init(this);		
		addKeyListener(player);		
		children.add(player);
		addMouseListener(this);

		currentBuffer = 0;
		
		
		if (dbImage[0] == null) 
			dbImage[0] = createImage (this.getSize().width, this.getSize().height);
		if (dbImage[1] == null) 
			dbImage[1] = createImage (this.getSize().width, this.getSize().height);

			
		dbg[0] = dbImage[0].getGraphics ();
		dbg[1] = dbImage[1].getGraphics ();

		// Schaffen eines neuen Threads, in dem das Spiel läuft	
		Thread th = new Thread(this);

		// Starten des Threads
		started = true;
		th.start();
		

	
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
	
	
	
	
	private void update() {
		StatusBar.reset();
		
		Graphics g = dbg[currentBuffer];
		resetDbg(g);
		
		
		g.drawImage(((Sprite) world.getBackground()).getImg(), 0, 0, this);		
		
		g.drawString("state: " + StatusBar.str, 100, 100);

		
		

		for (IGameObject go: children) {
			go.update(g);
		}

		//sleep...
		try {
			// Stoppen des Threads für in Klammern angegebene Millisekunden
			Thread.sleep (20);
		} catch (InterruptedException ex) {}	            

		flip();
		repaint();

	}
	private void resetDbg(Graphics g) {
		// Bildschirm im Hintergrund löschen
		g.setColor (getBackground ());
		g.fillRect (0, 0, this.getSize().width, this.getSize().height);

		// Auf gelöschten Hintergrund Vordergrund zeichnen
		g.setColor (getForeground());
		
	}





	

	/** Update - Methode, Realisierung der Doppelpufferung zur Reduzierung des Bildschirmflackerns */
	@Override
	public void update (Graphics g)	{	      
	      

		// Nun fertig gezeichnetes Bild Offscreen auf dem richtigen Bildschirm anzeigen
		g.drawImage (dbImage[not(currentBuffer)], 0, 0, this);
	}
	
	/**
	 * 
	 * @param cb
	 * @return returns (cb == 0 ? 1 : 0)
	 */
	private int not(int cb) {		
		return (cb == 0 ? 1 : 0);
	}
	
	/**
	 * swaps current buffer
	 */
	private void flip() {
		currentBuffer = not(currentBuffer);
	}




	public static void main(String[] args) {
		//startingpoint...
		Game game = new Game();
		game.init();
		
	}



	@Override
	public void mouseClicked(MouseEvent evt) {
		System.out.print(""+evt.getX()+"; "+evt.getY());
		System.out.println(""+world.getBackground().isSolid(new Vec(evt.getX(), evt.getY())));
		
	}



	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	public void addChild(IGameObject it) {
		children.add(it);
		
	}



	public void removeChild(IGameObject it) {
		children.remove(it);
		
	}



	public World getWorld() {
		return world;
	}
	
}
