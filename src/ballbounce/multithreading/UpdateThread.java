package ballbounce.multithreading;

import ballbounce.display.Display;
import ballbounce.engine.Engine;

public class UpdateThread implements Runnable {

	private Display target;
	
	public UpdateThread(Display toAttach) {
		target = toAttach;
	}

	
	/**
	 * starts the frame rate thread
	 */
	public void start() {
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		
		// --- TIMER SYSTEM ---
		
		long millis = System.currentTimeMillis();
		
		long delay = 1000/240; //1000/FPS_COUNT which also interfers on update rate 
		
		long delta;
		
		while(true) {
			
			delta = System.currentTimeMillis()-millis;
			
			if(delta >= delay) {				
				Engine.update();
				target.update();
				
				millis = System.currentTimeMillis(); //reset
			}else {
				try {
					Thread.sleep(delay-delta); //wait till the next refresh to relieve CPU
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}

}
