import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GameFrame extends JPanel implements ActionListener, Runnable{
	Timer mainTimer;
	TileManager tileM= new TileManager(this);
	GameObject[] objects = new GameObject[15];
	Monster[] monsters= new Monster[1];
	
	final int originalTile =16;
	final int scale=3;
	final int tileSize= originalTile*scale;
	final int maxScreenCol=16;
	final int maxScreenRow=12;
	final int screenWidth= tileSize*maxScreenCol;
	final int screenHeight= tileSize* maxScreenRow;
	SecureRandom random = new SecureRandom(); 
	Thread gameThread;
	Player player;
	public Boolean[] hiddens; 
	public final int maxWorCol=50;
	public final int maxWorRow=50;
	public final int worldWid=tileSize*maxWorCol;
	public final int worldHei = tileSize*maxWorRow;
	BufferedImage treeImg;
	EnvironmentManager eManager= new EnvironmentManager(this);
	public Entity monster[] = new Entity[20];
	public GameFrame() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		
		for(int i = 0; i < objects.length; i++) {
			objects[i] = new GameObject(random.nextInt(screenWidth*2), random.nextInt(screenHeight*2), "tree.png"); 
		}
		
		setFocusable(true);
		
		hiddens = new Boolean[objects.length]; 
		player = new Player(screenWidth/2-tileSize/2,screenHeight/2-tileSize/2);
		monsters[0]= new Monster(500,500,this,player);
		addKeyListener(new KeyAdapt(player));
		eManager.setup();
		addMouseListener(new clicker());
 		mainTimer = new Timer(10,this);
 		mainTimer.start();
	}
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		tileM.draw(g2d);
		
		
		//g2d.drawImage(testImg, testX, testY, null);
		player.draw(g2d);
		for(int i=0; i<objects.length; i++) {
			objects[i].drawObject(g2d);
		}
		monsters[0].draw(g2d);
	//	eManager.draw(g2d);
		
		
	}
	
	public void update() {
		player.update();
		
		System.out.println(player.direction);
		for(int i=0; i<objects.length; i++) {
			objects[i].update(player);
			if(objects[i].hitBox.contains(player.hitBox)) {
				hiddens[i] = true;  
			}else {
				hiddens[i] = false; 
			}
			
			if(eManager.getLighting().hit) {
				player.hidden = false; 
			}else {
				if(Arrays.asList(hiddens).contains(true)){
					player.hidden = true; 
				}else {
					player.hidden = false; 
				}
			}
			
			//System.out.println(Arrays.asList(hiddens)); 
			//System.out.println(player.hidden);
			//objects[i].isTouchingPlayer(player);
		}
		System.out.println(monsters[0]!=null);
		if(monsters[0]!=null)
			monsters[0].update(player);
			System.out.println(monsters[0].monsterX);
		
		
		
	//	player.draw();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//player.update();
		update();
		repaint();
		
	}
	public void startGameThread() {
		gameThread= new Thread(this);
		gameThread.start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		double drawInterval = 1000000000/60;
		double nextDraw= System.nanoTime()+drawInterval;
		
		double delta=0;
		long lastTime=System.nanoTime();
		long currentTime;
		while (gameThread!=null) {
		
			update();
			repaint();
			
			try {
				double remaining= nextDraw-System.nanoTime();
				remaining= remaining/1000000;
				if(remaining<0) {
					remaining=0;
				}
				Thread.sleep((long)remaining);
				nextDraw+=drawInterval;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private class clicker extends MouseAdapter{
		public void mousePressed(MouseEvent m) {
			System.out.println("clicked");
			eManager.getLighting().hit = true; 
			
			
			//eManager.getLighting().hit = false;
		}
		public void mouseReleased(MouseEvent m) {
			eManager.getLighting().hit = false; 
		}
	}


}