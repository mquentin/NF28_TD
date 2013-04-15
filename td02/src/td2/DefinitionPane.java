package td2;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class DefinitionPane extends JPanel implements PropertyChangeListener {
	protected JList mList = new JList();
	protected JLabel mText = new JLabel();
	protected AppModel appModel;
	
	public DefinitionPane(final AppModel appModel) {
		super();
		
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
		this.add(mList);
		mText.setText("Definition");
		this.add(mText);
		
		this.appModel.addListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		System.out.println(arg0);
		
	}

}