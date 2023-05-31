import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Player extends Entity{
	BufferedImage attackU,attackD,attackL,attackR; 
	BufferedImage up1,up2, up3, up4, down1, down2, down3, down4, left1,left2,left3,left4,right1,right2,right3,right4;
	int velx=0, vely=0;
	public Rectangle hitBox; 
	public int playerWidth=48, playerHeight = 48; 
	int spriteCounter, spriteNum;
	String direction=""; 
	public boolean hidden = false; 
	public Player(int x, int y) {
		super(x, y);
		setPlayerImg();
		direction="down";
		spriteNum=1;
		hitBox = new Rectangle(x, y, playerWidth, playerHeight); 
		update();
		// TODO Auto-generated constructor stub
	}
	public void set() {
		
	}
	
	public void update(){
		//y+=vely;
		//x+=velx;
		spriteCounter++;
		if(spriteCounter>30) {
			if(spriteNum==1) {
				spriteNum=2;
			}else if (spriteNum==2)
				spriteNum=3;
			else if(spriteNum==3)
				spriteNum=4;
			else if(spriteNum==4)
				spriteNum=1;
			spriteCounter=0;
				
		}
	}
	public void draw(Graphics2D g2d){
		BufferedImage image=null;
	
		switch(direction) {
		case "up":
			if(spriteNum==1) {
				image=up1;
			}else if(spriteNum==2)
			image = up2;
			else if(spriteNum==3)
				image=up3;
			else if(spriteNum==4)
				image=up4;
				
			break;
		case "down":
			if(spriteNum==1) {
			image=down1;}
			else if(spriteNum==2) {
				image = down2;}
			else if(spriteNum==3) {
				image=down3;}
			else if(spriteNum==4) {
				image=down4;}
			break;
		case "left":
			if(spriteNum==1) {
				image =left1;}
			if(spriteNum==2) {
				image = left2;}
			if(spriteNum==3) {
				image=left3;}
			if(spriteNum==4) {
				image=left4;}
			
			break;
		case "right":
			if(spriteNum==1) {
				image= right1;}
			if(spriteNum==2) {
				image = right2;}
			if(spriteNum==3) {
				image=right3;}
			if(spriteNum==4) {
				image=right4;}
			
			
			break;
		}
		if(vely==0&&velx==0&&direction.equals("up"))
			image=up1;
		else if(vely==0&&velx==0&&direction.equals("down"))
			image=down1;
		else if(vely==0&&velx==0&&direction.equals("right"))
			image=right1;
		else if(vely==0&&velx==0&&direction.equals("left"))
			image=left1;
		//g2d.setColor(Color.red);
		
		g2d.drawImage(image, x, y, 48, 48, null);
		//g2d.draw(hitBox);
	}
public void setPlayerImg(){

	try {
		 up1=ImageIO.read(new File("up1.png"));
		 up2=ImageIO.read(new File("up2.png"));
		 up3=ImageIO.read(new File("up3.png"));
		 up4=ImageIO.read(new File("up4.png"));
		 down1=ImageIO.read(new File("down1.png"));
		 down2=ImageIO.read(new File("down2.png"));
		 down3=ImageIO.read(new File("down3.png"));
		 down4=ImageIO.read(new File("down4.png"));
		 right1=ImageIO.read(new File("right1.png"));
		 right2=ImageIO.read(new File("right2.png"));
		 right3=ImageIO.read(new File("right3.png"));
		 right4=ImageIO.read(new File("right4.png"));
		 left1=ImageIO.read(new File("left1.png"));
		 left2=ImageIO.read(new File("left2.png"));
		 left3=ImageIO.read(new File("left3.png"));
		 left4=ImageIO.read(new File("left4.png"));
	}catch(IOException e) {
		e.printStackTrace();
	}
	
}

public int getXVel() {
	return velx;
}

public int getYVel() {
	return vely;
}

public void keyPressed(KeyEvent e) {
	int key=e.getKeyCode();
	if(key==KeyEvent.VK_W) {
		vely=-2;
		direction="up";
	}else if (key==KeyEvent.VK_S) {
		vely=2;
		direction="down";
	}else if (key==KeyEvent.VK_A) {
		velx=-2;
		direction="left";
	}else if(key==KeyEvent.VK_D) {
		velx=2;
		direction="right";
	}
}
public void keyReleased(KeyEvent e) {
	int key=e.getKeyCode();
	if(key==KeyEvent.VK_W) {
		vely=0;
		//direction="sup";
	}else if (key==KeyEvent.VK_S) {
		vely=0;
		//direction="sdown";
	}else if (key==KeyEvent.VK_A) {
		velx=0;
		//direction="sleft";
		
	}else if(key==KeyEvent.VK_D) {
		velx=0;
		//direction="sright";
	}

}

	


}