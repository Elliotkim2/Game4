import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

public class TileManager {
GameFrame gf;
Tile[] tile;
int mapTileNum[][];
public TileManager(GameFrame gf) {
this.gf=gf;
tile =new Tile[10];
mapTileNum= new int[50][50];
getTileImage();
loadMap();
}
public void getTileImage() {
	
	try {
		tile[0]=new Tile();
		tile[0].image=ImageIO.read(new File("grass.png"));
		tile[1]=new Tile();
		tile[1].image=ImageIO.read(new File("water.png"));
		tile[2]=new Tile();
		tile[2].image=ImageIO.read(new File("darkG.png"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void loadMap() {
	try {
		InputStream is = getClass().getResourceAsStream("map.txt");
		BufferedReader br= new BufferedReader(new InputStreamReader(is));
		int col=0;
		int row=0;
		while(col<50&&row<50) {
			String line=br.readLine();
			while(col<50) {
				String numbers[] = line.split(" ");
				int num =Integer.parseInt(numbers[col]);
				mapTileNum[col][row]= num;
				col++;
			}
			if(col==50) {
				col=0;
				row++;
			}
		}
		br.close();
	}catch(Exception e) {
		
	}
}
public void draw(Graphics2D g2) {
	//System.out.println(gf.maxWorldCol);
	int col=0;
	int row=0;
	
	while(col<50&& row<50) {
		int tileNum=mapTileNum[col][row];
		int worldx=col*gf.tileSize;
		int worldy= row*gf.tileSize;
		//int screenx=worldx-gf.player.worldx+gf.player.screenx;
		//int screeny=worldy-gf.player.worldy+gf.player.screeny;
		
		
		g2.drawImage(tile[tileNum].image,worldx,worldy,gf.tileSize,gf.tileSize,null);
		col++;
		
		
		if(col==50) {
			col=0;
			
			row++;
		
		}
	}
}
}