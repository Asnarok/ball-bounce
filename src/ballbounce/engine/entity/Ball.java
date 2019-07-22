package ballbounce.engine.entity;

import java.awt.Color;
import java.awt.Graphics2D;

import ballbounce.math.Vector2f;

public class Ball {

	private Vector2f pos, speed;
	
	public static final int RADIUS = 7;
	
	public Ball(Vector2f pos, Vector2f speed) {
		setPos(pos);
		setSpeed(speed);
	}

	/**
	 * @return the pos
	 */
	public Vector2f getPos() {
		return pos;
	}

	/**
	 * @param pos the pos to set
	 */
	public void setPos(Vector2f pos) {
		this.pos = pos;
	}

	/**
	 * @return the speed
	 */
	public Vector2f getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(Vector2f speed) {
		this.speed = speed;
	}

	/**
	 * apply speed to position
	 */
	public void update() {
		pos = pos.add(speed);
	}
	/**
	 * 
	 * Renders the ball
	 * 
	 * @param g the Graphics2D object of the viewport
	 */
	public void render(Graphics2D g) {
		g.setColor(Color.cyan);
		g.fillOval((int)pos.getX()-RADIUS, (int)pos.getY()-RADIUS, RADIUS*2, RADIUS*2);
	}
}
