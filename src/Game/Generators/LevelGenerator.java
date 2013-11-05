package Game.Generators;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import Game.Gfx.SpriteSheet;
import Game.World.Level;

public class LevelGenerator 
{
	public static final int flowerTileChance = 10;			//Higher the number the less chance
	
	public static SpriteSheet blockSpriteSheet = new SpriteSheet("/SpriteSheet.png", 16);
	
	public static BufferedImage rotate(BufferedImage img, int rotation) 
    {
		int w = img.getWidth();  
		int h = img.getHeight();  
		BufferedImage newImage = new BufferedImage(w, h, img.getType());
	    Graphics2D g2 = newImage.createGraphics();
	    g2.rotate(Math.toRadians(rotation), w/2, h/2);  
	    g2.drawImage(img,null,0,0);
		return newImage;  
    }
	
	public static void generateLevel()
	{
		Random generator = new Random();
		int randomNumber = generator.nextInt(100);
		
		for(int i = 0; i < Level.tiles.length; i++)					//Fill the level with grass and such
		{
			for(int j = 0; j < Level.tiles[0].length; j++)
			{	
				if(randomNumber%flowerTileChance == 0)
				{
					Level.tiles[i][j] = blockSpriteSheet.tiles[1][0];
				}
				else
				{
					Level.tiles[i][j] = blockSpriteSheet.tiles[0][0];
				}
				
				randomNumber = generator.nextInt(100);
			}
		}
		
		createRoads();
	}
	
	private static void createRoads()
	{
		boolean endRoadPlaced = false;
		Random generator = new Random();
		int startingRoadIndex = generator.nextInt(Level.tiles[0].length - 2) + 1;
		
		int currentX = 0;
		int currentY = startingRoadIndex;
		int direction = 1; // 0 = north 1 = east 2 = south 3 = west
		
		boolean canTurn = true;
		int counterSinceLastTurn = 0;
		int turnRandomizer = generator.nextInt(5) + 3;
		
		Level.tiles[0][startingRoadIndex] = blockSpriteSheet.tiles[2][0];
		
		while(!endRoadPlaced)
		{
			if(currentX > 2 && canTurn)
			{
				canTurn = false;
				
				switch(direction)
				{
					case 0:
						Level.tiles[currentX][currentY] = blockSpriteSheet.tiles[6][0];
						currentX++;
						direction = 1;
						break;
					case 1:
						if(currentY <=5)
						{
							Level.tiles[currentX][currentY] = blockSpriteSheet.tiles[3][0];
							currentY++;
							direction = 2;
						}
						else
						{
							Level.tiles[currentX][currentY] = blockSpriteSheet.tiles[5][0];
							currentY--;
							direction = 0;
						}
						break;
					case 2:
						Level.tiles[currentX][currentY] = blockSpriteSheet.tiles[7][0];
						currentX++;
						direction = 1;
						break;
				}
			}
			else
			{
				switch(direction)
				{
					case 0:
						Level.tiles[currentX][currentY] = blockSpriteSheet.tiles[4][0];
						currentY--;
						break;
					case 1:
						Level.tiles[currentX][currentY] = blockSpriteSheet.tiles[2][0];
						currentX++;
						break;
					case 2:
						Level.tiles[currentX][currentY] = blockSpriteSheet.tiles[4][0];
						currentY++;
						break;
				}
			}
			
			if(!canTurn)
			{
				counterSinceLastTurn++;

				if(counterSinceLastTurn > turnRandomizer)
				{
					System.out.println("Can Turn");
					counterSinceLastTurn = 0;
					canTurn = true;
				}
				
				turnRandomizer = generator.nextInt(5) + 3;
			}
			
			if(currentX >= Level.tiles.length)
			{
				endRoadPlaced = true;
			}
		}
	}
}
