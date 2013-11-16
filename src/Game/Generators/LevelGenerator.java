package Game.Generators;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import Game.Application;
import Game.Entity.Enemy;
import Game.Entity.Tower;
import Game.Gfx.AnimatedTile;
import Game.Gfx.SpriteSheet;
import Game.Gfx.Tile.TileType;
import Game.World.Level;
import Game.World.Position;

public class LevelGenerator 
{
	public static final int flowerTileChance = 10;			//Higher the number the less chance
	public static final int maxRoadLength = 6;			
	public static final int minRoadLength = 3;		
	
	public static SpriteSheet blockSpriteSheet = new SpriteSheet("/SpriteSheet.png", 16);
	private static Direction dir;
	
	public static void generateLevel()
	{
		Level.clearLevel();
		createBaseLayer();
		createRoads();
	}
	
	private static void createBaseLayer()
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
				else if(randomNumber%15 == 0)
				{
					Level.tiles[i][j] = AnimatedTile.bush;
				}
				else
				{
					Level.tiles[i][j] = blockSpriteSheet.tiles[0][0];
				}
				
				randomNumber = generator.nextInt(100);
			}
		}
	}
	
	private static void createRoads()
	{
		boolean endRoadPlaced = false;
		Random generator = new Random();
		int startingRoadYIndex = Level.tiles[0].length/2;
		
		int currentX = 0;
		int currentY = startingRoadYIndex;
		
		boolean shouldTurn = true;
		int counterSinceLastTurn = 0;
		int roadLengthRandomizer = 2;
		int directionChooser = generator.nextInt(2);
		
		dir = Direction.East;
		
		Level.tiles[0][startingRoadYIndex] = blockSpriteSheet.tiles[2][0];
		Level.tiles[0][startingRoadYIndex].setType(TileType.Road);
		Level.enemySpawnPoint = new Position(0,startingRoadYIndex*Level.tileSize);
		
		while(!endRoadPlaced)
		{		
			if(currentX >= Application.getWidth()/Level.tileSize - 1)
			{
				currentX = Application.getWidth()/Level.tileSize - 1;
				Level.tiles[currentX][currentY] = blockSpriteSheet.tiles[2][0];
				Level.tiles[currentX][currentY].setType(TileType.Road);
				endRoadPlaced = true;
				break;
			}
			else if(currentX < 0)
			{
				currentX = 0;
			}
			
			if(currentY >= Application.getHeight()/Level.tileSize - 3)
			{
				currentY = Application.getHeight()/Level.tileSize - 3;
			}
			else if(currentY < 0)
			{
				currentY = 0;
			}
			
			if(dir == Direction.North)
			{
				if(counterSinceLastTurn < roadLengthRandomizer)
				{
					Level.tiles[currentX][currentY] = blockSpriteSheet.tiles[4][0];
					Level.tiles[currentX][currentY].setType(TileType.Road);
					currentY--;
					counterSinceLastTurn++;
					if(currentY <= 0)
					{
						Level.tiles[currentX][currentY] = blockSpriteSheet.tiles[6][0];
						Level.tiles[currentX][currentY].setType(TileType.Road);
						currentX++;
						counterSinceLastTurn = 0;
						roadLengthRandomizer = generator.nextInt(maxRoadLength) + minRoadLength;
						dir = Direction.East;
					}
				}
				else
				{
					Level.tiles[currentX][currentY] = blockSpriteSheet.tiles[6][0];
					Level.tiles[currentX][currentY].setType(TileType.Road);
					currentX++;
					counterSinceLastTurn = 0;
					roadLengthRandomizer = generator.nextInt(maxRoadLength) + minRoadLength;
					dir = Direction.East;
				}
			}
			else if(dir == Direction.East)
			{
				if(counterSinceLastTurn < roadLengthRandomizer)
				{
					Level.tiles[currentX][currentY] = blockSpriteSheet.tiles[2][0];
					Level.tiles[currentX][currentY].setType(TileType.Road);
					currentX++;
					counterSinceLastTurn++;
				}
				else
				{
					counterSinceLastTurn = 0;
					roadLengthRandomizer = generator.nextInt(maxRoadLength) + minRoadLength;
					if(currentY >= Application.getHeight()/Level.tileSize - 3)
					{
						dir = Direction.North;
						Level.tiles[currentX][currentY] = blockSpriteSheet.tiles[5][0];
						Level.tiles[currentX][currentY].setType(TileType.Road);
						currentY--;
					}
					else if(currentY <= 0)
					{
						dir = Direction.South;
						Level.tiles[currentX][currentY] = blockSpriteSheet.tiles[3][0];
						Level.tiles[currentX][currentY].setType(TileType.Road);
						currentY++;
					}
					else
					{
						if(directionChooser == 1)	// North
						{
							directionChooser = generator.nextInt(2);
							dir = Direction.North;
							Level.tiles[currentX][currentY] = blockSpriteSheet.tiles[5][0];
							Level.tiles[currentX][currentY].setType(TileType.Road);
							currentY--;
						}
						else						//South
						{
							directionChooser = generator.nextInt(2);
							dir = Direction.South;
							Level.tiles[currentX][currentY] = blockSpriteSheet.tiles[3][0];
							Level.tiles[currentX][currentY].setType(TileType.Road);
							currentY++;
						}
					}
				}
			}
			else if(dir == Direction.South)
			{
				if(counterSinceLastTurn < roadLengthRandomizer)
				{
					Level.tiles[currentX][currentY] = blockSpriteSheet.tiles[4][0];
					Level.tiles[currentX][currentY].setType(TileType.Road);
					currentY++;
					counterSinceLastTurn++;
					if(currentY >= Application.getHeight()/Level.tileSize - 3)
					{
						Level.tiles[currentX][currentY] = blockSpriteSheet.tiles[7][0];
						Level.tiles[currentX][currentY].setType(TileType.Road);
						currentX++;
						counterSinceLastTurn = 0;
						roadLengthRandomizer = generator.nextInt(maxRoadLength) + minRoadLength;
						dir = Direction.East;
					}
				}
				else
				{
					Level.tiles[currentX][currentY] = blockSpriteSheet.tiles[7][0];
					Level.tiles[currentX][currentY].setType(TileType.Road);
					currentX++;
					counterSinceLastTurn = 0;
					roadLengthRandomizer = generator.nextInt(maxRoadLength) + minRoadLength;
					dir = Direction.East;
				}
			}
		}
	}
	
	private static enum Direction
	{
		North,
		South,
		East,
		West;
	}
}
