package Game.Entity;

import Game.Gui.Gui;

public class Player extends Entity 
{
	public static final int STARTING_LIVES = 100;
	public static final int STARTING_GOLD = 500;
	
	private int lives;
	private int gold;
	
	public Player(String newName) 
	{
		super(newName, 0, 0, 0);
		lives = STARTING_LIVES;
		gold = STARTING_GOLD;
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
	
	public void setLives(int lives)
	{
		this.lives = lives;
		notifyLivesChanged();
	}
	
	public void setGold(int gold)
	{
		this.gold = gold;
		notifyGoldChanged();
	}
	
	/* END setters and getters */
	
	public void removeLive()
	{
		if(lives > 1)
		{
			lives--;
			System.out.println("Player lost a life.");
		}
		else
		{
			lives--;
			System.out.println("Player has Lost.");
			alive = false;
		}
		notifyLivesChanged();
	}
	
	public void tick()
	{

	}
	
	public void notifyGoldChanged()
	{
		Gui.goldAmountChanged();
	}
	
	public void notifyLivesChanged()
	{
		Gui.livesAmountChanged();
	}
	
	
}
