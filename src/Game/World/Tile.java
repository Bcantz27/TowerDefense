package Game.World;

import java.awt.image.BufferedImage;

public class Tile 
{
	private int id;
	private BufferedImage image;
	
	public Tile(int id, BufferedImage image)
	{
		this.id = id;
		this.image = image;
	}
	
	/* START setters and getters */
	
	public int getId()
	{
		return id;
	}
	
	public BufferedImage getImage()
	{
		return image;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public void setImage(BufferedImage image)
	{
		this.image = image;
	}
	
	/* END setters and getters */
	
	public static enum TileType
	{
		Block,
		Tower,
		Road,
		Spawn,
		End
	}
}
