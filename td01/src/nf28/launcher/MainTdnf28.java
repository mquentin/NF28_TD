package nf28.launcher;

import nf28.td1.Console;
import nf28.td1.ConsoleView;
import nf28.td1.ImageView;

public class MainTdnf28 {

	public static void main(String[] args) {
		td1();
	}
	
	public static void td1(){
		Console c = new Console();
		ImageView mImageView = new ImageView("Images");
		ConsoleView mConsoleView = new ConsoleView("Console");
		
		
		c.addObserver(mImageView);
		mConsoleView.addModel(c);
	}
}
