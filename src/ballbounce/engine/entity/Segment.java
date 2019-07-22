package ballbounce.engine.entity;

import java.awt.Color;
import java.awt.Graphics2D;

import ballbounce.math.Vector2f;

public class Segment {

	private Vector2f point1, point2;
	private int type;
	
	public static final int TYPE_NORMAL = 0, TYPE_ACCELERATOR = 1;
	
	public Segment(Vector2f point1, Vector2f point2, int type) {
		setPoint1(point1);
		setPoint2(point2);
		setType(type);
	}

	/**
	 * @return the point1
	 */
	public Vector2f getPoint1() {
		return point1;
	}

	/**
	 * @param point1 the point1 to set
	 */
	public void setPoint1(Vector2f point1) {
		this.point1 = point1;
	}

	/**
	 * @return the point2
	 */
	public Vector2f getPoint2() {
		return point2;
	}
	
	public Vector2f getVector() {
		return point2.sub(point1);
	}

	/**
	 * @param point2 the point2 to set
	 */
	public void setPoint2(Vector2f point2) {
		this.point2 = point2;
	}
	/**
	 * Rendering
	 * @param g
	 */
	public void render(Graphics2D g) {
		if(type == TYPE_NORMAL)g.setColor(Color.gray);
		else if(type == TYPE_ACCELERATOR)g.setColor(Color.red);
		g.drawLine((int)point1.getX(), (int)point1.getY(), (int)point2.getX(), (int)point2.getY());
		
	}
	/**
	 * 
	 * @return the middle point
	 */
	public Vector2f getMiddle() {
		return new Vector2f((point1.getX()+point2.getX())/2, (point1.getY()+point2.getY())/2);
	}
	/**
	 * 
	 * @return the type of segment
	 */
	public int getType() {
		return type;
	}
	/**
	 * 
	 * @param type the type of segment to set
	 */
	public void setType(int type) {
		this.type = type;
	}
	
}
