package Game.World;

public class Position 
{
	private float x = 0;
	private float y = 0;
	
	public Position()
	{
		x = 0;
		y = 0;
	}
	
	public Position(float newX, float newY)
	{
		x = newX;
		y = newY;
	}
	
	/* START Setters and Getters */
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	public int getTileX()
	{
		return (int) (x/Level.tileSize);
	}
	
	public int getTileY()
	{
		return (int) (y/Level.tileSize);
	}
	
	public void setPosition(float newX, float newY)
	{
		x = newX;
		y = newY;
	}
	
	/* END Setters and Getters */
	
	public void shiftPosition(float shiftX, float shiftY)
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
