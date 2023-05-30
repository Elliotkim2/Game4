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
	BufferedImage up1,up2, down1, down2,left1,left2,right1,right2;
	String direction;
	int actionLockcounter;
	public Monster(int x, int y) {
		super(x, y);
		GameFrame gf;
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
		up1=ImageIO.read(new File("Mu1.png"));
		up2=ImageIO.read(new File("Mu2.png"));
		down1=ImageIO.read(new File("Md1.png"));
		down2=ImageIO.read(new File("Md2.png"));
		
		left1=ImageIO.read(new File("Ml1.png"));
		left2=ImageIO.read(new File("Ml2.png"));
		right1=ImageIO.read(new File("Mr1.png"));
		right2=ImageIO.read(new File("Mr2.png"));
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}public void setAction() {
	actionLockcounter++;
	if(actionLockcounter==120) {
		Random random= new Random();
		int i= random.nextInt(100)+1;
		if(i<=25) {
			direction="up";
			
		}
		if(i>25&&i<=50) {
			direction="down";
		}
		if(i>50&&i<=75)
			direction="left";
		if(i>75&&i<=100) {
			direction="right"; 
		}
		actionLockcounter=0;
	}
}
}
