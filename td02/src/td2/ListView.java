package td2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;

public class ListView extends JFrame implements ActionListener {
	protected JMenuBar menuBar;
	protected JMenu menu;
	protected JMenuItem menuItem;
	protected DefinitionPane pane;
	
	protected AppModel appModel;
	
	
	public ListView(String title, AppModel appModel){
		super();
		
		this.appModel=appModel;
		
		//set tile
		this.setTitle(title);
		this.setPreferredSize(new Dimension(400,400));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		//Create the menu bar.
		menuBar = new JMenuBar();

		//Build the first menu.
		menu = new JMenu("Fichier");
		menu.setMnemonic(KeyEvent.VK_A);
		menuBar.add(menu);

		//a group of JMenuItems
		menuItem = new JMenuItem("Choisir un fichier", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menu.add(menuItem);
		
		menuItem.addActionListener(this);
		
		this.setJMenuBar(menuBar);
		
		pane=new DefinitionPane(appModel);
		this.add(pane);

		this.pack();
		setVisible(true);		
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==menuItem){
			JFileChooser jfc = new JFileChooser();
			int retValue = jfc.showOpenDialog(this);
			if (retValue == JFileChooser.APPROVE_OPTION) {
	            File file = jfc.getSelectedFile();	            
	            System.out.println("Opening: " + file.getName() + ".");
	            appModel.openDictionnary(file);
	            
	        } else {
	        	System.out.println("Open command cancelled by user.");
	        }
		}
	}
}
