package nf28.td1;

import java.util.Observable;
import java.util.Observer;
import java.util.Timer;

import javax.swing.ImageIcon;

public class Console extends Observable {
	
	
	private static final String IMAGE_DEFAULT = "img/image";
	
	
	private Timer mTimer;
	private int count = 0;
	
	public Console(){
		super();
	}
	
	public Timer getTimer(){
		System.out.println("Console getTimer");
		if(mTimer == null){
			mTimer = new Timer();
		}
		return mTimer;
	}
	
	public void start(int intervalle){
		System.out.println("Console start");
		ImageTimerTask task = new ImageTimerTask(this);
		getTimer().schedule(task, 0, intervalle);
	}
	
	public void stop(){
		System.out.println("Console stop");
		getTimer().cancel();
		mTimer=null;
	}
	
	@Override
	public synchronized void addObserver(Observer o) {
		System.out.println("Console Observer added");
		super.addObserver(o);
	}
	
	@Override
	public void notifyObservers(Object arg) {
		System.out.println("Console notifyObservers");
		super.notifyObservers(arg);
	}
	
	public void time(){
		System.out.println("Console time");
		ImageIcon newImage = new ImageIcon(IMAGE_DEFAULT+String.valueOf(count)+".jpg");
		setChanged();
		notifyObservers(newImage);
		count++;
		count=count%8;
		
	}
	
	
}
