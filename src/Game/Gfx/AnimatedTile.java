package Game.Gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import Game.World.Tile;

public class AnimatedTile extends Tile
{
	public static List<AnimatedTile> animatedTiles = new ArrayList<AnimatedTile>();
	public static final AnimatedTile water =  new AnimatedTile("/AnimatedTiles/Water.png", 16, 1500);
	
	private String path;
	private int tileSize;
	private BufferedImage[] frame;
	
	private int currentAnimationIndex;
	private long lastInterationTime;
	private int animationDelay;
	
	public AnimatedTile(String path, int tileSize,int delay)
	{
		super(-1,null);
		
		BufferedImage image = null;
		this.tileSize = tileSize;
		currentAnimationIndex = 0;
		animationDelay = delay;
		lastInterationTime = System.currentTimeMillis();
		
		try {
			image = ImageIO.read(AnimatedTile.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(image == null)
		{
			return;
		}
		
		this.path = path;
		frame = new BufferedImage[image.getWidth()/tileSize];
		
		loadFrames(image);
		
		animatedTiles.add(this);
	}
	
	/* START setters and getters */
	
	public BufferedImage getImage()
	{
		return frame[currentAnimationIndex];
	}
	
	/* END setters and getters */
	
	private void loadFrames(BufferedImage image)
	{
		System.out.println("Frames: " + image.getWidth()/tileSize);
		for(int i = 0; i < image.getWidth()/tileSize;i++)
		{
			frame[i] = image.getSubimage(i*tileSize, 0, tileSize, tileSize);
		}
	}
	
	public void tick()
	{
		if((System.currentTimeMillis() - lastInterationTime) >= animationDelay)
		{
			lastInterationTime = System.currentTimeMillis();
			if(currentAnimationIndex < frame.length -1 )
			{
				currentAnimationIndex++;
			}
			else
			{
				currentAnimationIndex = 0;
			}
		}
	}
}
