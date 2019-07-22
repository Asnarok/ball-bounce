package ballbounce.display;

import javax.swing.JFrame;

import ballbounce.multithreading.UpdateThread;

@SuppressWarnings("serial")
public class Display extends JFrame{

	private RenderPanel renderPanel;
	
	public UpdateThread updateThread;
	
	private Listener listener;
	
	public Display() {
		this.setTitle("Ball Bounce :-D");
		this.setSize(1280, 720);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		renderPanel = new RenderPanel();
		this.setContentPane(renderPanel);
		
		listener = new Listener();
		
		this.addMouseListener(listener);
		this.addKeyListener(listener);
		
		this.setVisible(true);
		
		updateThread = new UpdateThread(this);
		
		updateThread.start();
	}
	
	public void update() {
		renderPanel.repaint();
	}
	
}
