package Game.Gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.World.Tile;

public class SpriteSheet
{
	
	public String path;
	public int width;
	public int height;
	public int tileSize;
	
	public int[] pixels;
	public Tile[][] tiles;
	
	public SpriteSheet(String path, int tileSize)
	{
		this.tileSize = tileSize;
		
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(SpriteSheet.class.getResourceAsStream(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(image == null)
		{
			return;
		}
		
		this.path = path;
		this.width = image.getWidth();
		this.height = image.getHeight();
		tiles = new Tile[height/tileSize][width/tileSize];
		
		pixels = image.getRGB(0, 0, width, height, null, 0, width);
		
		for(int i = 0; i < pixels.length; i ++)
		{
			pixels[i] = (pixels[i] & 0xff)/64;
		}
		
		loadSpriteSheet(image);
	}
	
	private void loadSpriteSheet(BufferedImage image)
	{	
		for(int i = 0; i < height/tileSize;i++)
		{
			for(int j = 0; j < width/tileSize ; j++)
			{
				tiles[i][j] = new Tile(i,image.getSubimage(i*tileSize, j*tileSize, tileSize, tileSize));
			}
		}
	}
}
