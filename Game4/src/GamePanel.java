import java.awt.color.*;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
final int originalTile =16;
final int scale=3;
final int tileSize= originalTile*scale;
final int maxScreenCol=16;
final int maxScreenRow=12;
final int screenWidth= tileSize*maxScreenCol;
final int screenHeight= tileSize* maxScreenRow;


public GamePanel() {
	this.setPreferredSize(new Dimension(screenWidth, screenHeight));
	this.setBackground(Color.black);
	this.setDoubleBuffered(true);
}
}
