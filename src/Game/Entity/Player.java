package Game.Entity;

public class Player extends Entity 
{
	private static int STARTING_LIVES = 100;
	private static int STARTING_GOLD = 500;
	
	private int lives;
	private int gold;
	private int wave;
	
	public Player(String newName) 
	{
		super(newName, 0, 0, 0);
		lives = STARTING_LIVES;
		gold = STARTING_GOLD;
		wave = 1;
	}
	
	/* START setters and getters */
	
	public int getLivesLeft()
	{
		return lives;
	}	
	
	public int getGold()
	{
		return gold;
	}
	
	public int getWave()
	{
		return wave;
	}
	
	public void setLives(int lives)
	{
		this.lives = lives;
	}
	
	public void setGold(int gold)
	{
		this.gold = gold;
	}
	
	public void setWave(int wave)
	{
		this.wave = wave;
	}
	
	/* END setters and getters */
	
	public void tick()
	{
		
	}
}
