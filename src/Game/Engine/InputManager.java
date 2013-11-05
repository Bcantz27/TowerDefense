package Game.Engine;

public class InputManager 
{
	public static void handleInput(String string)
	{
		//InteractionPanel.sendMessage("Input: "+string);
		
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
}
