import java.awt.Graphics2D;

public class EnvironmentManager {
GameFrame gf;
Lighting lighting;
public EnvironmentManager(GameFrame gf) {
	this.gf=gf;
	
}
public void setup()
{
	lighting= new Lighting(gf,350);
	}
public void draw(Graphics2D g2) {
	lighting.draw(g2);
}
}

