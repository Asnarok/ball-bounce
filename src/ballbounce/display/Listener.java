package ballbounce.display;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import ballbounce.engine.Engine;
import ballbounce.engine.entity.Ball;
import ballbounce.engine.entity.Segment;
import ballbounce.math.Vector2f;

public class Listener implements MouseListener, KeyListener{
	
	//keys settings
	public static int addBall = KeyEvent.VK_B, addLine = KeyEvent.VK_L, clear = KeyEvent.VK_C,
					  addAccelerator = KeyEvent.VK_A, toggleAddLines = KeyEvent.VK_M;
	
	//states of adding entities
	public static final int STATE_NOTHING = 0, STATE_ADDING_BALL_POS = 1, STATE_ADDING_BALL_SPEED = 2,
			STATE_ADDING_LINE_POINT1 = 3, STATE_ADDING_LINE_POINT2 = 4,
			STATE_ADDING_ACCELERATOR_POINT1 = 5, STATE_ADDING_ACCELERATOR_POINT2 = 6;
	
	//factor of balls speed
	public static final float speedFactor = 0.025f;
	
	/*
	 * 0: nothing
	 * 1: adding ball position, 2: adding ball speed
	 * 3: adding line first point, 4: adding line second point 
	 */
	//buffers
	private static Vector2f v1, v2;
	
	//adding state
	public static int state = 0;
	
	// if line adding mode is toggled
	public static boolean linesToggled = false;

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//initiate every 
		if(e.getKeyCode() == addBall) {
			state = STATE_ADDING_BALL_POS;
		}else if(e.getKeyCode() == addLine) {
			state = STATE_ADDING_LINE_POINT1;
		}else if(e.getKeyCode() == clear) {
			Engine.clear();
			if(state == STATE_ADDING_LINE_POINT2) state = STATE_ADDING_LINE_POINT1;
			else if(state == STATE_ADDING_ACCELERATOR_POINT2) state = STATE_ADDING_ACCELERATOR_POINT1;
		}else if(e.getKeyCode() == addAccelerator) {
			state = STATE_ADDING_ACCELERATOR_POINT1;
		}else if(e.getKeyCode() == toggleAddLines) {
			linesToggled = !linesToggled;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	private static int xOffset = -2, yOffset = -30;
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if(state == STATE_ADDING_BALL_POS) { //first buffer to add ball
			v1 = new Vector2f(e.getX()+xOffset, e.getY()+yOffset);
			state = STATE_ADDING_BALL_SPEED; //set to the next state
		}else if(state == STATE_ADDING_BALL_SPEED) { //2nd state to add ball
			v2 = new Vector2f(e.getX()+xOffset, e.getY()+yOffset).sub(v1).mult(speedFactor);
			Engine.addBall(new Ball(v1, v2)); //add the ball using the buffers
			state = 0; //reset state
		}else if(state == STATE_ADDING_LINE_POINT1) { //adding segments first point
			v1 = new Vector2f(e.getX()+xOffset, e.getY()+yOffset);
			state = STATE_ADDING_LINE_POINT2; //next state
		}else if(state == STATE_ADDING_LINE_POINT2) { //adding segments 2nd point
			v2 = new Vector2f(e.getX()+xOffset, e.getY()+yOffset);
			Engine.addLine(new Segment(v1, v2, Segment.TYPE_NORMAL)); //add the segments using the buffers
			
			if(!linesToggled)state = 0;
			else {
				state = STATE_ADDING_LINE_POINT2; //to add segments continuously
				v1 = v2;
			}
		}else if(state == STATE_ADDING_ACCELERATOR_POINT1) { //same as segments but for accelerators
			v1 = new Vector2f(e.getX()+xOffset, e.getY()+yOffset);
			state = STATE_ADDING_ACCELERATOR_POINT2;
		}else if(state == STATE_ADDING_ACCELERATOR_POINT2) {
			v2 = new Vector2f(e.getX()+xOffset, e.getY()+yOffset);
			Engine.addLine(new Segment(v1, v2, Segment.TYPE_ACCELERATOR));
			if(!linesToggled)state = 0;
			else state = STATE_ADDING_ACCELERATOR_POINT1;
		}
	}


}
