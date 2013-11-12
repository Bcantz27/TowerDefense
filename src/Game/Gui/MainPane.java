package Game.Gui;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import Game.Application;
import Game.World.Level;

public class MainPane extends JPanel implements ActionListener
{
	private static MainPane instance;
	private JLayeredPane layeredPane;
	private JButton startWave = new JButton("Start Wave");
	private JButton shop = new JButton("Shop");
	
	public MainPane(Canvas canvas)
	{
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		instance = this;
		
		startWave.addActionListener(this);
		shop.addActionListener(this);
		
		layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(Application.WIDTH*Application.SCALE, Application.HEIGHT*Application.SCALE));
        layeredPane.setLayout(new FlowLayout());
        
        layeredPane.add(canvas, new Integer(0));
        layeredPane.add(startWave, new Integer(1), 0);
        layeredPane.add(shop, new Integer(1), 0);
        
        add(layeredPane);
	}
	
	/* START Setters and getters */
	
	public MainPane getInstance()
	{
		if(instance == null) instance = this;
		return instance;
	}
	
	/* END Setters and getters */
	
	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		if(ae.getSource().equals(startWave))
		{
			Level.startNextWave();
		}
		else if(ae.getSource().equals(shop))
		{
			
		}
		
		
	}

}
