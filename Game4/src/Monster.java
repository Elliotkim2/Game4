import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import javax.imageio.ImageIO;

public class Monster extends Entity{
	BufferedImage m1,m2, gameOver;
	String direction;
	GameFrame gf;
	Player p;
	int count=0;
	boolean kill=false;
	boolean dead;
	int speed; 
	int monsterX, monsterY;
	int actionLockcounter;
	int spriteCounter=0, spriteNum=1;
	public Monster(int x, int y, GameFrame gf,Player p) {
		super(x, y);
		monsterX=x;
		monsterY=y;
		this.gf=gf;
		this.p= p;
		String name="De";
		int speed =10;
		direction="down";
		int maxLife=100;
		actionLockcounter=0;
		getImage();
		// TODO Auto-generated constructor stub
	}
public void getImage() {
	try {
		m1=ImageIO.read(new File("m1.png"));
		m2=ImageIO.read(new File("m2.png"));
		gameOver=ImageIO.read(new File("gameOver.png"));
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}public void draw(Graphics2D g2d) {
	count++;
	set();
	BufferedImage image=null;
	if(spriteNum==1) {
		image=m1;
	}else if(spriteNum==2)
	image = m2;
	
	g2d.drawImage(image, monsterX, monsterY, 48, 48, null);

}
public void set() {
	if(p.x+48>monsterX) {
		monsterX+=speed;
		}
	
	else if(p.x<x) {
		monsterX-=speed;
			}
	else if(p.y>y) {
		monsterY+=speed;
		}
	else if (p.y+48<y) {
		monsterY-=speed;
		}
	else monsterY-=speed;
if(kill==true) {
	
}else if (dead==true) {
	
	System.exit(0);
}}
public void update(Player p) {
	monsterX += -(p.getXVel());
	monsterY += -(p.getYVel()); 
	//hitBox = new Rectangle(objectX, objectY, objectImg.getWidth(), objectImg.getHeight()); 
//	set();
	monsterY+=speed;
	monsterX+=speed;
			spriteCounter++;
			if(spriteCounter>30) {
				if(spriteNum==1) {
					spriteNum=2;
				}else if (spriteNum==2)
			
					spriteNum=1;
				spriteCounter=0;
					
			}
	
	
}

}
