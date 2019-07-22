package ballbounce.engine;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import ballbounce.engine.entity.Ball;
import ballbounce.engine.entity.Segment;
import ballbounce.math.Vector2f;

public class Engine {
	
	// --- ENTITY STORAGE ---
	
	private static List<Ball> balls = new ArrayList<Ball>(); 
	private static List<Segment> lines = new ArrayList<Segment>();
	
	
	private static final float ACCELERATOR_FACTOR = 1.2f; //acceleration factor to apply to a ball's speed when hitting accelerator
	
	//private static final float MIN_IMPACT = 2;
	
	/**
	 * updates world, -> apply speed to position and detect collisions
	 */
	
	public static void update() {
		//has to be in synchronized statements as several threads can access entity storage
		synchronized(balls) {
			synchronized(lines) {
				for(Ball b : balls) {
					for(Segment l : lines) {
						collide(b, l); //test collisions for every ball and line
					}
					b.update(); //apply speed to ball position
				}
			}
		}
		
	}
	/**
	 * 
	 * @param l the Segment to add to the entity storage
	 */
	public static void addLine(Segment l) {
		synchronized(lines) {
			lines.add(l);
		}
	}
	
	/**
	 * Deletes every entity
	 */
	public static void clear() {
		synchronized(lines) {
			synchronized(balls) {
				lines.clear();
				balls.clear();
			}
		}
	}
	
	/**
	 * Renders every entity
	 * 
	 * @param g the Graphics2D object of the viewport
	 */
	public static void render(Graphics2D g) {
		synchronized(balls) {
			synchronized(lines) {
				for(Ball b : balls) {
					b.render(g);
				}
				
				for(Segment l : lines) {
					l.render(g);
				}
			}
		}
	}
	
	/**
	 * 
	 * @param b the Ball to add to the entity storage
	 */
	public static void addBall(Ball b) {
		synchronized(balls) {
			balls.add(b);
		}
	}
	
	/**
	 * Collision algorithm
	 * @param b the Ball object
	 * @param l the Segment object
	 */
	public static void collide(Ball b, Segment l) {
		
		Vector2f LtoB = b.getPos().sub(l.getPoint1()); //Vector (Line to Ball)
		
		float d = l.getVector().dot(l.getVector()); //scalar square of segment's vector
		
		float distance = 100; //default initialization
		
		Vector2f normal = null; //default initialization
		
		if(d > 0) { 
			
			// --- DOT PRODUCT ---
			// -> find the orthogonal projection vector
			
			float dp = LtoB.dot(l.getVector()); 
			float multiplier = dp / d;
			
			
			Vector2f projected = l.getVector().mult(multiplier).add(l.getPoint1()); //the orthogonal projection is turned into a point on the segment
			
			Vector2f projectionToMid = l.getMiddle().sub(projected); //orthogonal projection's point to segment's middle
			
			normal = b.getPos().sub(projected); //Vector
			
			// if the ball would hit the segment
			//then set distance to the distance between ball and segment
			if(projectionToMid.length() <= l.getVector().length()/2)distance = normal.length();
			
		}
		
		
		// in case of a collision
		if(distance <= Ball.RADIUS) {
			float nn = normal.dot(normal); //normal dot square 
			float vn = normal.dot(b.getSpeed()); //normal dot direction
			
			/*float normalisedVN = normal.normalise().dot(b.getSpeed().normalise());
			
			
			if(normalisedVN < MIN_IMPACT && normalisedVN > 0)vn = MIN_IMPACT + 1; //minimum impact angles
			else if(normalisedVN > -MIN_IMPACT && normalisedVN < 0) vn = -MIN_IMPACT - 1; //same
			 */
			
			float oldLength = b.getSpeed().length();
			
			b.setSpeed(b.getSpeed().sub(normal.mult(2.0f * (vn / nn)))); //apply reflexion
			
			b.setSpeed(b.getSpeed().mult(oldLength/b.getSpeed().length())); //control unwanted acceleration
			
			//if the line is an accelerator, then accelerate the ball
			if(l.getType() == Segment.TYPE_ACCELERATOR && b.getSpeed().length() <= 15)b.setSpeed(b.getSpeed().mult(ACCELERATOR_FACTOR));
		}
		
	}

}
