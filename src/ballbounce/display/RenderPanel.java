package ballbounce.display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import ballbounce.engine.Engine;

@SuppressWarnings("serial")
public class RenderPanel extends JPanel {

	private static Graphics2D g2d;
	
	@Override
	public void paintComponent(Graphics g) {
		g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //enable antialiasing
		
		//clear viewport
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		//render entities
		Engine.render(g2d);
	}
	
}
