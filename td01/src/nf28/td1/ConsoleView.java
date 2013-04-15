package nf28.td1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ConsoleView extends JFrame {
	
	private static final int DEFAULT_TIME = 1100;
	
	private JButton mStart;
	private JButton mStop;
	//private JTextField mText;
	private JSlider mSlider = new JSlider(JSlider.HORIZONTAL,200,2000,DEFAULT_TIME);
	private int mSliderValue=DEFAULT_TIME;
	private Console mConsole;
	
	public ConsoleView(String title){
		super();
		
		//set tile
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Start
		mStart = new JButton("Start", null);
		mStart.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("start clicked");
				mConsole.start(mSliderValue);
				
			}
		});
		
		//Stop
		mStop = new JButton("Stop", null);
		mStop.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("stop clicked");
				mConsole.stop();
				
			}
		});
		
		/*mText = new JTextField(String.valueOf(DEFAULT_TIME));
		mText.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("intervale changed : " + mText.getText());
				
			}
		});*/
		
		mSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				JSlider source = (JSlider)arg0.getSource();
				System.out.println("intervale changed : " + source.getValue());
				mSliderValue=Integer.valueOf(source.getValue());
			}
		});
		
		mSlider.setMajorTickSpacing(500); 
		mSlider.setMinorTickSpacing(500); 
		mSlider.setPaintTicks(true); 
		mSlider.setPaintLabels(true); 
		
		//frame design
		JPanel components = new JPanel();
		Box all = Box.createVerticalBox();
		all.add(mSlider);
		all.add(mStart);
		all.add(mStop);
		
		this.add(all, BorderLayout.CENTER);
		
		this.setSize(200, 200);
		
		this.pack();
		setVisible(true);		
		
		
	}
	
	public void addModel(Console c){
		mConsole = c;
	}
	

}
