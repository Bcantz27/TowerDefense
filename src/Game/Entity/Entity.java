package Game.Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.World.Position;

public class Entity 
{
	protected String name;
	protected int id;
	protected Position position = new Position();
	private boolean alive;
	private BufferedImage image;
	
	public Entity()
	{
		position.setPosition(0, 0);
		name = "No Name";
		alive = true;
		id = 0;
		image = LoadImage(id);
	}
	
	public Entity(String newName, int newId, int newX, int newY)
	{
		position.setPosition(newX, newY);
		name = newName;
		alive = true;
		id = newId;
		//image = LoadImage(id);
	}
	
	/* START setters and getters */
	
	public int Id()
	{
		return id;
	}
	
	public String  Name()
	{
		return name;
	}
	
	public boolean isAlive()
	{
		return alive;
	}
	
	public Position getPosition()
	{
		return position;
	}
	public BufferedImage Image()
	{
		return image;
	}
	
	public void setName(String newName)
	{
		name = newName;
	}
	
	public void setId(int newId)
	{
		id = newId;
	}
	
	public void setAlive(boolean alive)
	{
		this.alive = alive;
	}
	
	public void setPosition(Position pos)
	{
		position = pos;
	}
	
	/* END setters and getters */
	
	public static BufferedImage LoadImage(int id)
	{
		BufferedImage newImage = null;
		
		switch(id)
		{
			case 0:
				try {
					newImage = ImageIO.read(new File("filename.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			break;
		}
		
		return newImage;
	}
}
