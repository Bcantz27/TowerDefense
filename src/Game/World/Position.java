package Game.World;

public class Position 
{
	private int x = 0;
	private int y = 0;
	
	public Position()
	{
		x = 0;
		y = 0;
	}
	
	public Position(int newX, int newY)
	{
		x = newX;
		y = newY;
	}
	
	/* START Setters and Getters */
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setPosition(int newX, int newY)
	{
		x = newX;
		y = newY;
	}
	
	/* END Setters and Getters */
	
	public void shiftPosition(int shiftX, int shiftY)
	{
		x += shiftX;
		y += shiftY;
	}
	
	public boolean compare(Position pos)
	{
		if(pos.getX() == x && pos.getX() == y)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
