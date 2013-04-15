package nf28.td1;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class ImageView extends JFrame implements Observer {

	private ImageIcon mImageIcon;
	private JLabel mJLabel;
	
	
	public ImageView(String title) {
		super();
		
		//title
		this.setTitle(title);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mJLabel = new JLabel();
		//size
		Dimension d = new Dimension(200, 200);
		setPreferredSize(d);

		//show
		this.pack();
		setVisible(true);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("ImageView update");
		if(arg1 instanceof ImageIcon){
			System.out.println("Image updated");
			this.remove(mJLabel);
			mJLabel = new JLabel((ImageIcon) arg1);
			this.add(mJLabel);
			//setVisible(false);
			//setVisible(true);
			SwingUtilities.updateComponentTreeUI(this);
		}				
	}
	
	
	

}
