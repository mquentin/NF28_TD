package td2;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class DefinitionPane extends JSplitPane implements PropertyChangeListener {
	//JFrame mFrame;
	protected static JList mList = new JList();
	protected static JTextArea mText = new JTextArea();
	protected AppModel appModel;
	
	public DefinitionPane(final AppModel appModel) {
		super(JSplitPane.HORIZONTAL_SPLIT,mList, mText);
		this.setOneTouchExpandable(true);
		this.setDividerLocation(150);

		//Provide minimum sizes for the two components in the split pane
		Dimension minimumSize = new Dimension(100, 50);
		mList.setMinimumSize(minimumSize);
		mText.setMinimumSize(minimumSize);
		
		
		//this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		this.appModel=appModel;
				
		//Create the word list
		mList.setModel(appModel.getLwm());
		mList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		mList.setLayoutOrientation(JList.VERTICAL);
		mList.setVisibleRowCount(-1);
		
		mList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				System.out.println("ListSelection");
				int index=mList.getSelectedIndex();
				ListWordModel<Definition> lwm=appModel.getLwm();
				mText.setText(((Definition) lwm.get(index)).getDef());
			};
		});
		
		this.setSize(200,400);
		EtchedBorder edge = new EtchedBorder(EtchedBorder.RAISED);
		mList.setBorder(edge);
		mText.setText("Definition");
		mText.setBorder(edge);
		mText.setLineWrap(true);
		
		this.appModel.addListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		System.out.println(arg0);
		
	}

}