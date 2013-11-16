package Game.Gui;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import Game.Entity.Tower;
import Game.World.Level;

public class Shop implements MouseMotionListener
{
	public static Tower selectedTower;
	private static int mouseXPos = 0;
	private static int mouseYPos = 0;
	
	public static void buyTowerRequest(Tower t)
	{
		selectedTower = t;
	}
	
	public static void render(Graphics g)
	{
		g.drawImage(Shop.selectedTower.getImage(), mouseXPos, mouseYPos, Level.tileSize, Level.tileSize, null);
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(selectedTower != null)
		{
			mouseXPos = arg0.getXOnScreen();
			mouseYPos = arg0.getYOnScreen();
		}
	}
	
	
	
}
