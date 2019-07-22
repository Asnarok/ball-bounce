package ballbounce.math;

public class Vector2f {

	public float x, y;
	
	/**
	 * Warning ! -> this can be used in vector appliances but also in points appliances
	 * 
	 * @param x x coordinate 
	 * @param y y coordinate
	 */
	public Vector2f(float x, float y) {
		setX(x);
		setY(y);
	}
	/**
	 * 
	 * @return a normalised vector (x and y are mapped between -1 and 1, but proportions are maintained) 
	 */
	public Vector2f normalise() {
		Vector2f v = new Vector2f(x, y);
		if(Math.abs(x) > Math.abs(y))v = v.mult(1/Math.abs(x));
		else v = v.mult(1/Math.abs(y));
		
		return v;
	}
	/**
	 * 
	 * @param toAdd Vector2f object to add
	 * @return the addition of the current vector and toAdd
	 */
	public Vector2f add(Vector2f toAdd) {
		return new Vector2f(x+toAdd.getX(), y+toAdd.getY());
	}
	/**
	 * 
	 * @param toSub Vector2f object to substract
	 * @return the substraction of the current vector and toSub
	 */
	public Vector2f sub(Vector2f toSub) {
		return new Vector2f(x-toSub.getX(), y-toSub.getY());
	}
	
	/**
	 * 
	 * @return the length of the current vector
	 */
	public float length() {
		return (float)(Math.sqrt(x*x+y*y));
	}
	/**
	 * 
	 * @param factor the factor to apply
	 * @return the multiplication of the vector by factor
	 */
	public Vector2f mult(float factor) {
		return new Vector2f(x*factor, y*factor);
	}
	
	
	/**
	 * 
	 * @param vec Vector2f to use in dot product
	 * @return the dot product of vec and the current vector
	 */
	public float dot(Vector2f vec) {
		return x*vec.getX() + y*vec.getY();
	}
	
	/**
	 * 
	 * @return the opposit normal vector
	 */
	public Vector2f oppositNormal() {
		return new Vector2f(-y/x, 1);
	}
	
	/**
	 * 
	 * @return the normal vector
	 */
	public Vector2f normal() {
		return new Vector2f(y/x, -1);
	}
	

	/**
	 * @return the x
	 */
	public float getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public float getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(float y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "["+x+", "+y+"]";
	}
	
}
