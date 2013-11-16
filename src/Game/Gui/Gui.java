package Game.Gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Game.Application;
import Game.Entity.Player;
import Game.Entity.Tower;
import Game.Generators.LevelGenerator;
import Game.Gfx.AnimatedTile;
import Game.World.Level;

public class Gui extends JPanel implements ActionListener 
{
	private static JButton startWave = new JButton("Start Wave");
	private static JButton newMap = new JButton("Generate New Map");
	private static JButton[] towersToBuy;
	private static JPanel topPanel = new JPanel();
	private static JPanel buttomPanel = new JPanel();
	private static JPanel shopPanel = new JPanel();
	private static JPanel towerPanel = new JPanel();
	private static JLabel goldAmount = new JLabel("" + Player.STARTING_GOLD);
	private static JLabel livesAmount = new JLabel("" + Player.STARTING_LIVES);
	
	public Gui()
	{
		towersToBuy = new JButton[Tower.PremadeTowers.values().length];
		
		BufferedImage gold = null;
		BufferedImage lives = null;
		try {
			gold = ImageIO.read(AnimatedTile.class.getResourceAsStream("/Icons/gold.png"));
			lives = ImageIO.read(AnimatedTile.class.getResourceAsStream("/Icons/lives.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		JLabel livesLabel = new JLabel(new ImageIcon(lives));
		JLabel goldLabel = new JLabel(new ImageIcon(gold));
		
		this.setLayout(new BorderLayout());
		buttomPanel.setLayout(new BorderLayout());
		shopPanel.setLayout(new GridLayout(3,3));
		towerPanel.setLayout(new BorderLayout());
		
		buttomPanel.setPreferredSize(new Dimension(Application.getWidth(), Application.getHeight()/2));
		topPanel.setPreferredSize(new Dimension(Application.getWidth(), Application.getHeight()/2));
		shopPanel.setPreferredSize(new Dimension(Application.getWidth()/6, 100));
		//shopPanel.setBounds(0, Application.getHeight()*(7/8), Application.getWidth()/3, Application.getHeight()/8);
		
		livesAmount.setForeground(Color.RED);
		goldAmount.setForeground(Color.YELLOW);
		
		topPanel.setOpaque(false);
		this.setOpaque(false);
		buttomPanel.setOpaque(false);
		shopPanel.setOpaque(false);
		towerPanel.setOpaque(false);
			
		startWave.addActionListener(this);
		newMap.addActionListener(this);
		
		setupTowersToBuy();
		
		buttomPanel.add(shopPanel, BorderLayout.WEST);
		buttomPanel.add(towerPanel, BorderLayout.EAST);
		
		topPanel.add(startWave);
		topPanel.add(newMap);
		topPanel.add(goldLabel);
		topPanel.add(goldAmount);
		topPanel.add(livesLabel);
		topPanel.add(livesAmount);
		
		this.add(topPanel, BorderLayout.NORTH);
		this.add(buttomPanel, BorderLayout.SOUTH);
	}
	
	public static void setupTowersToBuy()
	{
		for(int i = 0; i < towersToBuy.length; i++)
		{
			towersToBuy[i] = new JButton(Tower.getTowerById(i).Name());
			shopPanel.add(towersToBuy[i]);
		}
	}
	
	public static void goldAmountChanged()
	{
		goldAmount.setText("" + Level.getPlayer().getGold());
	}
	
	public static void livesAmountChanged()
	{
		livesAmount.setText("" + Level.getPlayer().getLivesLeft());
	}

	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		if(ae.getSource().equals(startWave))
		{
			Level.startNextWave();
		}
		else if(ae.getSource().equals(newMap))
		{
			LevelGenerator.generateLevel();
		}
		else if(ae.getSource().equals(towersToBuy[0]));
		{
			System.out.println("Derp");
			Shop.buyTowerRequest(Tower.getTowerById(0));
		}

		for(int i = 0; i < towersToBuy.length; i++)
		{
			if(ae.getSource().equals(towersToBuy[i]));
			{
				System.out.println("Derp");
				Shop.buyTowerRequest(Tower.getTowerById(i));
			}
		}
	}
}
