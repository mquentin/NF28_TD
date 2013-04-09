import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

import org.xml.sax.SAXException;



public class AppView extends JFrame {
	ContactTreeModel mTreeModel;
	
	JMenuBar mMenuBar=new JMenuBar();
	JMenu mFichier=new JMenu("Fichier");
	JMenuItem mOuvrir = new JMenuItem("Ouvrir");
	JMenuItem mSauver = new JMenuItem("Sauver");
	JMenuItem mSauverSous = new JMenuItem("Sauver sous");
	JMenuItem mQuitter = new JMenuItem("Quitter");
	JMenu mEditer = new JMenu("Editer");
	JMenuItem mAjouter = new JMenuItem("Ajouter un contact");
	JMenuItem mVoirXML = new JMenuItem("Voir XML");
	
	JTabbedPane mOnglets=new JTabbedPane();
	JPanel mPanel1 = new JPanel();
	JPanel mPanel2 = new JPanel();
	BoxLayout mOngletContact = new BoxLayout(mPanel2, BoxLayout.PAGE_AXIS);
	
	JTextField inputNom = new JTextField("");
	JTextField inputMail = new JTextField("");
	
	ImageIcon inputImage = new ImageIcon("img3.jpg");
	JLabel inputLabelImage = new JLabel(inputImage);
	
	JButton buttonImage = new JButton("Image");
	JButton buttonSubmit = new JButton("Valider");
	
	JTree mTree;
	JTextArea mArea=new JTextArea("");
	ContactFacility mContactFacility=new ContactFacility();
	
	String mFileOpen;
	
	DefaultMutableTreeNode root = new DefaultMutableTreeNode("Default");

	class MenuListener implements ActionListener {
		JFrame mParent;
		MenuListener(JFrame f){
			mParent=f;
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("actionPerformed="+arg0.getSource());
			if(arg0.getSource() == mOuvrir){
				System.out.append("Ouvrir");
				final JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(mParent);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            System.out.append("Opening: " + file.getName() + ".\n");
		            try {
		            	if(file.getName().contains(".xml")){
		            		mTreeModel=mContactFacility.parse(file.getAbsolutePath());
			            	mTree.setModel(mTreeModel);
			            	mArea.setText(mTreeModel.toXML());
			            	mFileOpen=file.getAbsolutePath();
		            	}
		            	else System.out.append("The system needs XML file\n");
		            	
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
			
			else if(arg0.getSource() == mQuitter){
				System.out.append("\nQuitter");
				//processWindowEvent(new WindowEvent(mParent, WindowEvent.WINDOW_CLOSING));
				System.exit(0);
			}
			
			else if(arg0.getSource() == mSauver){
				System.out.append("\nSauver");
				try {
					FileWriter writer=new FileWriter(mFileOpen);
					writer.write(mArea.getText());
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else if(arg0.getSource() == mSauverSous){
				System.out.append("\nSauver Sous");
				final JFileChooser fc = new JFileChooser();
				int returnVal = fc.showSaveDialog(mParent);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            System.out.append("Saving as: " + file.getName() + ".\n");
		            try {
						FileWriter writer=new FileWriter(file.getAbsolutePath());
						writer.write(mArea.getText());
						writer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        } else {
		        	System.out.append("Save as command cancelled by user.\n");
		        }
				
				
				
			}else if(arg0.getSource() == mAjouter){
				System.out.append("\nAjout d'un contact : ");
				Contact contact=new Contact("anonyme","anonyme",null);
				DefaultMutableTreeNode node=new DefaultMutableTreeNode(contact);
				System.out.append("\nroot : "+mTreeModel.getRoot().toString());
				((DefaultMutableTreeNode) mTreeModel.getRoot()).add(node);
				mTreeModel.reload();
				//mTree.setModel(mTreeModel);
				System.out.append(contact.getNom()+"\n");
				mArea.setText(mTreeModel.toXML());
			}
			
			else if(arg0.getSource() == mVoirXML){
				System.out.append("\nVoir XML");
				mOnglets.setSelectedIndex(0);
			}
		}
	}
	
	class ButtonListener implements ActionListener {
		JFrame mParent;
		ButtonListener(JFrame f){
			mParent=f;
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("actionPerformed="+arg0.getSource());
			if(arg0.getSource() == buttonImage){
				System.out.append("Img");
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) mTree.getLastSelectedPathComponent();
				if(node==null) return;
				else if(node.getUserObject() instanceof String) return;
				else if(node.getUserObject() instanceof Contact){
					final JFileChooser fc = new JFileChooser();
					int returnVal = fc.showOpenDialog(mParent);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
			            File file = fc.getSelectedFile();
			            System.out.append("Opening: " + file.getName() + ".\n");
			            try {
			            	Contact c=(Contact)node.getUserObject();
			            	c.setIcon(file.getAbsolutePath());
							inputImage=new ImageIcon(c.icon);
							inputImage.getImage().flush();
							inputLabelImage.setIcon(inputImage);
			            	mTreeModel.reload();
			            	mArea.setText(mTreeModel.toXML());
						} catch (Exception e){}
			        } else {
			        	System.out.append("Open command cancelled by user.\n");
			        }
				}
			}
			else if(arg0.getSource() == buttonSubmit){
				System.out.append("submit");
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) mTree.getLastSelectedPathComponent();
				if(node==null) return;
				if(node.getUserObject() instanceof String) return;
				if(node.getUserObject() instanceof Contact){
					Contact c= (Contact)node.getUserObject();
					c.nom=inputNom.getText();
					c.mail=inputMail.getText();
					//inputImage.setImage(c.icon);
					mTreeModel.reload();
					mArea.setText(mTreeModel.toXML());
					
				}
			}
		}
	}
	
	public AppView() {
		super();
		this.setSize(700, 600);
		this.setLayout(new BorderLayout());  
		
		mPanel1.setLayout(new FlowLayout(FlowLayout.LEFT)); 
		mPanel2.setLayout(new FlowLayout(FlowLayout.LEFT)); 
		
		Border border = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		  
		mFichier.add(mOuvrir);
		mOuvrir.addActionListener(new MenuListener(this));

		mFichier.add(mSauver);
		mSauver.addActionListener(new MenuListener(this));
		mFichier.add(mSauverSous);
		mSauverSous.addActionListener(new MenuListener(this));
		mFichier.add(mQuitter);
		mQuitter.addActionListener(new MenuListener(this));
		mMenuBar.add(mFichier);
		
		//mFichier.addActionListener(new FichierListener(this));
		mEditer.add(mAjouter);
		mAjouter.addActionListener(new MenuListener(this));
		mEditer.add(mVoirXML);
		mVoirXML.addActionListener(new MenuListener(this));
		mMenuBar.add(mEditer);
		mVoirXML.addActionListener(new MenuListener(this));
		
		mTree=new JTree(root);
		mTree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent arg0) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) mTree.getLastSelectedPathComponent();
				if(node==null) return;
				else if(node.getUserObject() instanceof String){
					inputNom.setText("");
					inputMail.setText("");
					inputImage=new ImageIcon("");
					inputImage.getImage().flush();
					inputLabelImage.setIcon(inputImage);
				}
				else if(node.getUserObject() instanceof Contact){
					Contact c= (Contact)node.getUserObject();
					inputNom.setText(c.nom);
					inputMail.setText(c.mail);
					inputImage=new ImageIcon(c.getIcon());
					inputImage.getImage().flush();
					inputLabelImage.setIcon(inputImage);
				}
				
				//System.out.printl(arg0.getSource();
			}
		});
		  
		mPanel1.add(mTree);
		mPanel1.setBorder(border);
		mPanel1.setPreferredSize(new Dimension(300, 300));
		getContentPane().add(mPanel1, BorderLayout.WEST);
		
		mPanel2.setLayout(new BorderLayout());
		
		JPanel top = new JPanel();
		top.add(new JLabel("Nom"));
		inputNom.setPreferredSize(new Dimension(200,20));
		top.add(inputNom);
		mPanel2.add(top, BorderLayout.NORTH);
		
		JPanel center = new JPanel();
		center.add(new JLabel("Mail"));
		inputMail.setPreferredSize(new Dimension(200,20));
		center.add(inputMail);
		mPanel2.add(center, BorderLayout.CENTER);
		
		
		JPanel bottom = new JPanel();
		
		//bottom.setLayout(new BorderLayout, BorderLayout.SOUTH);
		bottom.add(inputLabelImage);
		buttonImage.addActionListener(new ButtonListener(this));
		bottom.add(buttonImage);
		

		buttonSubmit.addActionListener(new ButtonListener(this));
		bottom.add(buttonSubmit);
		mPanel2.add(bottom, BorderLayout.SOUTH);
		
		
		mOnglets.addTab("XML", null, mArea);
		mOnglets.addTab("Contact", null, mPanel2);
		mOnglets.setPreferredSize(new Dimension(350, 550));
		this.add(mOnglets, BorderLayout.CENTER); 
		
		this.setJMenuBar(mMenuBar);
		
		this.setVisible(true);
	}
	
	
}
