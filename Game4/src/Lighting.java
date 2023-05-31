import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Lighting {
GameFrame gf;
BufferedImage darknessFilter;
public boolean hit = false; 
private Color color[]= new Color[5];
private float fraction[]= new float[5];
private int centerX, centerY, circleSize; 
Rectangle darkness = new Rectangle(0, 0, 800, 600);
public Lighting(GameFrame gf, int circleSize) {
	this.circleSize = circleSize; 
	this.gf = gf;  
	
	darknessFilter= new BufferedImage(gf.screenWidth,gf.screenHeight,BufferedImage.TYPE_INT_ARGB);
	Graphics2D g2 = (Graphics2D)darknessFilter.getGraphics();
	//Area screenArea= new Area(new Rectangle2D.Double(0,0, gf.screenWidth,gf.screenHeight));
	centerX= gf.player.x+gf.tileSize/2;
	centerY= gf.player.y+gf.tileSize/2;
	/*double x= centerX-(circleSize/2);
	double y= centerY-(circleSize/2);
	Shape circleShape = new Ellipse2D.Double(x,y,circleSize,circleSize);
	Area lightArea= new Area(circleShape);
	
	screenArea.subtract(lightArea);*/
	
	
	color[0]= new Color(0,0,0,0f);
	color[1]= new Color(0,0,0,0.25f);
	color[2]= new Color(0,0,0,0.5f);
	color[3]= new Color(0,0,0,0.75f);
	color[4]= new Color(0,0,0,0.98f);
	
	fraction[0]=0f;
	fraction[1]=.25f;
	fraction[2]=.5f;
	fraction[3]=.75f;
	fraction[4]=1f;
	
	
	
}
public void draw(Graphics2D g2) {
	if(hit) {
		RadialGradientPaint gPaint = new RadialGradientPaint(centerX,centerY,(circleSize/2),fraction,color);
		g2.setPaint(gPaint);
		g2.fillRect(0, 0,800, 600);
		//g2.fill(lightArea);
		
		
		//g2.fill(screenArea);
		g2.dispose();
		
	}
	g2.setColor(new Color(0, 0, 0, 255));
	g2.fill(darkness); 
	g2.drawImage(darknessFilter,0,0,800,600,null);
	
	
	
}


}