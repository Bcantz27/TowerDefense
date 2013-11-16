package Game.Gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import Game.Application;
import Game.Generators.LevelGenerator;
import Game.World.Level;

public class GamePanel extends JPanel
{	
	public static JLabel view;
	public static BufferedImage game;

	public GamePanel()
	{
		game = new BufferedImage(Application.getWidth(),Application.getHeight(),BufferedImage.TYPE_INT_RGB);
		view = new JLabel(new ImageIcon(game));
		Graphics g = game.getGraphics();
		
		this.setLayout(new BorderLayout());
		this.add(view, BorderLayout.CENTER);
		g.dispose();
	}
	
	public void render()
	{
		Graphics g = game.getGraphics();

	    //g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		Level.render(g);
		
		g.dispose();
		view.repaint();
	}

}
