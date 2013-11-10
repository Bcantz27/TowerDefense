package Game.World;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import Game.Application;
import Game.Entity.Enemy;
import Game.Entity.Player;
import Game.Entity.Tower;
import Game.Entity.Enemy.PremadeEnemies;
import Game.Entity.Tower.PremadeTowers;
import Game.Generators.LevelGenerator;
import Game.Gfx.SpriteSheet;
import Game.Gfx.Tile;

public class Level 
{
	public static final int tileSize = 32;
	
	private static Player player;
	public static List<Tower> towers = new ArrayList<Tower>();
	public static List<Enemy> enemies = new ArrayList<Enemy>();
	public static Position enemySpawnPoint;
	public static Tile[][] tiles;
	public static Wave[] waves;
	
	public Level(String name)
	{
		player = new Player(name);
		enemySpawnPoint = new Position(0,0);
		tiles = new Tile[(Application.WIDTH*Application.SCALE)/tileSize][((Application.HEIGHT*Application.SCALE)/tileSize) + 1];
		LevelGenerator.generateLevel();
	}
	
	/* START setters and getters */
	
	public static Player getPlayer()
	{
		return player;
	}
	
	/* END setters and getters */
	
	public static void tick()
	{
		player.tick();
		
		for(Tower t : towers)
		{
			t.tick();
		}
		
		for(Enemy e : enemies)
		{
			e.tick();
		}
	}
	
	public static void render(Graphics g)
	{
		for(int i = 0; i < tiles.length; i++)//COLS
		{
			for(int j = 0; j < tiles[0].length; j++)//ROWS
			{
				g.drawImage(tiles[i][j].getImage(), i*tileSize, j*tileSize, tileSize, tileSize, null);
			}
		}
		
		for(Enemy e : enemies)
		{
			e.render(g);
		}
		
		for(Tower t : towers)
		{
			t.render(g);
		}
	}
	
	public static void spawnEnemy(int id, float x, float y)
	{
		Enemy e;
		
		e = PremadeEnemies.values()[id].getEnemy();
		e.setPosition(new Position(x,y));
		
		addEnemy(e);
	}
	
	public static void spawnTower(int id, float x, float y)
	{
		Tower tower;

		tower = PremadeTowers.values()[id].getTower();
		tower.setPosition(new Position(x,y));
		
		addTower(tower);;
	}
	
	public static Tile getTileAtPos(float x, float y)
	{
		if(x < 0) x = 0;
		if(y < 0) y = 0;
		if(x > Application.WIDTH*Application.SCALE) x = Application.WIDTH*Application.SCALE;
		if(y > Application.HEIGHT*Application.SCALE) y = Application.HEIGHT*Application.SCALE;
		
		
		System.out.println("X: " + (int)x/tileSize + " Y:" + (int)y/tileSize);
		System.out.println("Type: " + tiles[(int) (x/tileSize)][(int) (y/tileSize)].getType().toString());
		return tiles[(int) (x/tileSize)][(int) (y/tileSize)];
	}
	
	public static void addTower(Tower t)
	{
		towers.add(t);
	}
	
	public static void addEnemy(Enemy e)
	{
		enemies.add(e);
	}
	
	public static void removeTower(Tower t)
	{
		towers.remove(t);
	}
	
	public static void removeEnemy(Enemy e)
	{
		enemies.remove(e);
	}
}
