import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

import org.xml.sax.SAXException;



public class XMLView extends JFrame {
	JMenuBar mMenuBar=new JMenuBar();
	JMenu mMenu=new JMenu("Fichier");
	JMenuItem mFichier = new JMenuItem("Ouvrir");
	JPanel mPanel1 = new JPanel();
	JPanel mPanel2 = new JPanel();
	JTree mTree;
	JTextArea mArea=new JTextArea("coucou");
	ContactFacility mContactFacility=new ContactFacility();
	
	DefaultMutableTreeNode root = new DefaultMutableTreeNode("Racine");

	class FichierListener implements ActionListener {
		JFrame mParent;
		FichierListener(JFrame f){
			mParent=f;
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("\nOuverture de fichier");
			final JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(mParent);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = fc.getSelectedFile();
	            //This is where a real application would open the file.
	            System.out.append("Opening: " + file.getName() + ".\n");
	            try {
	            	ContactTreeModel mTreeModel=mContactFacility.parse(file.getAbsolutePath());
//	            	mTree=new JTree(mTreeModel.getRoot());
//	            	DefaultMutableTreeNode page = new DefaultMutableTreeNode("Page2");
////	            	mTree.add(page);
	            	root.add((MutableTreeNode) mTreeModel.getRoot());
	            	mArea.append(mTreeModel.toXML());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (SAXException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        } else {
	        	System.out.append("Open command cancelled by user.\n");
	        }
		}
	}

	
	public XMLView() {
		super();
		this.setSize(700, 600);
		this.setLayout(new BorderLayout());  
		
		mPanel1.setLayout(new FlowLayout(FlowLayout.LEFT)); 
		mPanel2.setLayout(new FlowLayout(FlowLayout.LEFT)); 
		
		Border border = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		  
		mFichier.addActionListener(new FichierListener(this));
		mMenu.add(mFichier);
		mMenuBar.add(mMenu);
		
		
		mTree=new JTree(root);
		  
		mPanel1.add(mTree);
		mPanel1.setBorder(border);
		mPanel1.setPreferredSize(new Dimension(300, 300));
		getContentPane().add(mPanel1, BorderLayout.WEST);
		
		mPanel2.add(mArea);
		mPanel2.setBorder(border);
		this.add(mPanel2, BorderLayout.CENTER); 
		
		this.setJMenuBar(mMenuBar);
		
		this.setVisible(true);
	}
	
	
}
