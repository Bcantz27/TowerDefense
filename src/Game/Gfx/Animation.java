package Game.Gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Animation 
{
	private String name;
	private String path;
	private int tileSize;
	private BufferedImage[] frame;
	
	private int currentAnimationIndex;
	private long lastInterationTime;
	private int animationDelay;
	
	private boolean playing;
	
	public Animation(String name, String path, int tileSize,int delay)
	{
		BufferedImage image = null;
		
		this.name = name;
		this.tileSize = tileSize;
		currentAnimationIndex = 0;
		animationDelay = delay;
		lastInterationTime = System.currentTimeMillis();
		playing = false;
		
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
	}
	
	/* START getters and setters */
	
	public boolean getPlaying()
	{
		return playing;
	}
	
	public String getName()
	{
		if(name == null)
		{
			name = "No Name";
		}
		return name;
	}
	
	public String getPath()
	{
		return path;
	}
	
	public BufferedImage getCurrentFrame()
	{
		if(frame[currentAnimationIndex] == null)
		{
			return frame[0];
		}
		else
		{
			return frame[currentAnimationIndex];
		}
	}
	
	public void setPlaying(boolean playing)
	{
		this.playing = playing;
	}
	
	/* END getters and setters */
	
	public void play(boolean loop)
	{
		playing = true;
		if(playing)
		{
			if(loop)
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
			else
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
						playing = false;
						currentAnimationIndex = 0;
					}
				}
			}
		}
	}
	
	public void end()
	{
		playing = false;
	}
	
	private void loadFrames(BufferedImage image)
	{
		for(int i = 0; i < image.getWidth()/tileSize;i++)
		{
			frame[i] = image.getSubimage(i*tileSize, 0, tileSize, tileSize);
		}
	}
}
