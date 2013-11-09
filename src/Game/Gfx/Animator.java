package Game.Gfx;

public class Animator 
{
	private Animation[] animations;
	private int loadedOfAnimations;
	private Animation currentAnimation;
	
	public Animator(int numberOfAnimations)
	{
		animations = new Animation[numberOfAnimations];
		loadedOfAnimations = 0;
	}
	
	/* START setters and getters */
	
	public Animation getCurrentAnimation()
	{
		if(currentAnimation == null)
		{
			currentAnimation = animations[0];
		}
		
		return currentAnimation;
	}
	
	/* END setters and getters */
	
	public void loadAnimation(String name, String path, int delay)
	{
		Animation ani = new Animation(name,path,16,delay);
		

		if(!hasAnimation(name))
		{
			animations[loadedOfAnimations] = ani;
			loadedOfAnimations++;
		}
	}
	
	public void playAnimation(String name)
	{	
		if(animating()) stopAllAnimations();
		
		for(int i = 0; i < loadedOfAnimations; i++)
		{
			if(animations[i].getName() == name)
			{
				animations[i].play(false);
				return;
			}
		}
		
		System.out.println("Invalid Animation: " + name);
		
	}
	
	public void playAnimation(String name, boolean loop)
	{	
		if(animating()) stopAllAnimations();
		
		for(int i = 0; i < loadedOfAnimations; i++)
		{
			if(animations[i].getName() == name)
			{
				animations[i].play(loop);
				currentAnimation = animations[i];
				return;
			}
		}
		
		System.out.println("Invalid Animation: " + name);
		
	}
	
	public boolean hasAnimation(String name)
	{
		boolean has = false;
		
		for(int i = 0; i < loadedOfAnimations; i++)
		{
			if(animations[i].getName() == name)
			{
				has = true;
			}
		}
		
		return has;
	}
	
	public boolean animating()
	{
		boolean has = false;
		
		for(int i = 0; i < loadedOfAnimations; i++)
		{
			if(animations[i].getPlaying())
			{
				has = true;
			}
		}
		
		return has;
	}
	
	public void stopAllAnimations()
	{
		for(int i = 0; i < loadedOfAnimations; i++)
		{
			animations[i].end();
		}
		
		currentAnimation = null;
	}
}
