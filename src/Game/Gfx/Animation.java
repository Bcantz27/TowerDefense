package Game.Gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Animation 
{
	private String path;
	private int tileSize;
	private BufferedImage[] frame;
	
	private int currentAnimationIndex;
	private long lastInterationTime;
	private int animationDelay;
	
	private boolean playing;
	private boolean loop;
	
	public Animation(String path, int tileSize,int delay)
	{
		BufferedImage image = null;
		this.tileSize = tileSize;
		currentAnimationIndex = 0;
		animationDelay = delay;
		lastInterationTime = System.currentTimeMillis();
		playing = false;
		loop = false;
		
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
	
	public boolean getLooping()
	{
		return loop;
	}
	
	public BufferedImage getCurrentImage()
	{
		return frame[currentAnimationIndex];
	}
	
	public void setPlaying(boolean playing)
	{
		this.playing = playing;
	}
	
	public void setLooping(boolean looping)
	{
		this.loop = looping;
	}
	
	/* END getters and setters */
	
	public void play(boolean loop)
	{
		this.loop = loop;
		
		if(playing)
		{
			if(this.loop)
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
