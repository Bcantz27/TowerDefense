package Game.Gfx;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Tile 
{
	private int id;
	private BufferedImage image;
	private TileType type;
	
	public Tile(int id, BufferedImage image)
	{
		this.id = id;
		this.image = image;
		type = TileType.Block;
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
	
	public TileType getType()
	{
		return type;
	}

	public void setId(int id)
	{
		this.id = id;
	}
	
	public void setImage(BufferedImage image)
	{
		this.image = image;
	}
	
	public void setType(TileType type)
	{
		this.type = type;
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
	
	/**
	 * Rotates an image. Actually rotates a new copy of the image.
	 * 
	 * @param img The image to be rotated
	 * @param angle The angle in degrees
	 * @return The rotated image
	 */
	public static BufferedImage rotate(BufferedImage img, double angle){
	    double sin = Math.abs(Math.sin(Math.toRadians(angle))), cos = Math.abs(Math.cos(Math.toRadians(angle)));
	    int w = img.getWidth(null), h = img.getHeight(null);
	    int neww = (int) Math.floor(w * cos + h * sin), newh = (int) Math.floor(h
	            * cos + w * sin);
	    BufferedImage bimg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g = bimg.createGraphics();
	    g.translate((neww - w) / 2, (newh - h) / 2);
	    g.rotate(Math.toRadians(angle), w / 2, h / 2);
	    g.drawRenderedImage(img, null);
	    g.dispose();
	    return bimg;
	}
}
