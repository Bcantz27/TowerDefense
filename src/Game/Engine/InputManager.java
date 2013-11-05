package Game.Engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputManager implements KeyListener
{
	public void handleCommand(String string)
	{		
		String[] args = new String[10];
		int counter = 0;
		
		for(int i = 0; i < string.length(); i++)
		{
			if(string.toCharArray()[i] != ' ')
			{
				if(args[counter] == null)
				{
					args[counter] = new String();
				}
				
				args[counter] = args[counter] + string.toCharArray()[i];
			}
			else
			{
				counter++;
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
