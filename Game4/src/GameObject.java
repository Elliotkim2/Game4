import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GameObject implements ScrollObject{
	private int objectX, objectY;
	private static double scrollConst = .95; 
	private BufferedImage objectImg;
	private String fileName; 
	private Rectangle hitBox; 
	private int height,length;
	
	public GameObject(int objectX, int objectY, String fileName) {
		super();
		this.objectX = objectX;
		this.objectY = objectY;
		this.fileName = fileName; 
		
		try {
			objectImg = ImageIO.read(new File(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		hitBox = new Rectangle(objectX, objectY, objectImg.getWidth(), objectImg.getHeight()); 

	}
	
	
	public boolean isTouchingPlayer(Player p) {
		 if (
				    hitBox.x < p.hitBox.x + p.hitBox.width &&
				    hitBox.x + hitBox.width > p.hitBox.x &&
				    hitBox.y < p.hitBox.y + p.hitBox.height &&
				    hitBox.height + hitBox.y > p.hitBox.y
				  ) {
				    // Collision detected!
				   return true;
				  } 
		 else if(  objectX < p.x + p.playerWidth &&
				    objectX + objectImg.getWidth() > p.x &&
				    objectY < p.y + p.playerHeight &&
				    objectImg.getHeight() + objectY > p.y)
			 return true;
				 else {
				    // No collision
				    return false;
				  }
				}
	public boolean letGo(Player p) {
		if(isTouchingPlayer(p)&&p.direction=="right")
		{
			if(!p.direction.equals("right")) {
				return true;
			}
			
		}
		else 	if(isTouchingPlayer(p)&&p.direction=="left")
		{
			if(!p.direction.equals("left")) {
				return true;
			}
			
		}
		else 	if(isTouchingPlayer(p)&&p.direction=="up")
		{
			if(!p.direction.equals("up")) {
				return true;
			}
			
		}
		else if(isTouchingPlayer(p)&&p.direction=="down")
		{
			if(!p.direction.equals("down")) {
				return true;
			}
			
		}
		
		return false;
	}
	/*	
		if(hitBox.intersects(p.hitBox)) {
			if(p.direction.equals("left")) {
				
				//p.velx = 0;
				//p.x += 2; 
				return true; 
			}
			else if (p.direction.equals("right")){
				//p.velx = 0;
				//p.x += -2; 
				return true; 
			}
			else {
				//p.x = p.x; 
			}
		}
		return false; */
		
	
	
	public void drawObject(Graphics2D g2d) {
		g2d.drawImage(objectImg, objectX, objectY, null); 
		g2d.draw(hitBox);
	}
	public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	}  

	@Override
	
	public void update(Player p) {
		if(isTouchingPlayer(p)) {
			if(letGo(p)) {
				objectX += -(p.getXVel());
				objectY += -(p.getYVel()); 
				hitBox = new Rectangle(objectX, objectY, objectImg.getWidth(), objectImg.getHeight());
			}else {
			objectX += (p.getXVel());
			objectY += (p.getYVel());
			hitBox = new Rectangle(objectX, objectY, objectImg.getWidth(), objectImg.getHeight());
			}	
		}
		else {
			objectX += -(p.getXVel());
			objectY += -(p.getYVel()); 
			hitBox = new Rectangle(objectX, objectY, objectImg.getWidth(), objectImg.getHeight());
		}
		
		
		
		
	}

	public int getObjectX() {
		return objectX;
	}
	

	public void setObjectX(int objectX) {
		this.objectX = objectX;
	}

	public int getObjectY() {
		return objectY;
	}

	public void setObjectY(int objectY) {
		this.objectY = objectY;
	}

	public BufferedImage getObjectImg() {
		return objectImg;
	}

	public void setObjectImg(BufferedImage objectImg) {
		this.objectImg = objectImg;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	} 
	
	
	
}