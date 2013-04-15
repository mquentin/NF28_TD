package nf28.td1;

import java.util.TimerTask;

public class ImageTimerTask extends TimerTask {

	private Console mConsole;
	
	public ImageTimerTask(Console c){
		super();
		mConsole = c;
	}
	
	@Override
	public void run() {
		System.out.println("ImageTimerTask run");
		mConsole.time();
	}

}
