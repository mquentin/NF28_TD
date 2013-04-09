import java.util.Collections;
import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;


public class ContactTreeModel extends DefaultTreeModel{
	ContactHandler handler;
	public ContactTreeModel(TreeNode node){
		super(node);
	}
	
	public String toXML(){
		StringBuffer xml = new StringBuffer("<"+root.toString()+">");
		if(!root.isLeaf()){
			for(int i=0;i<root.getChildCount();i++){
				DefaultMutableTreeNode tn = (DefaultMutableTreeNode) root.getChildAt(i);
				
				if (tn.getUserObject() instanceof Contact){
					xml.append("\n" + ((Contact)tn.getUserObject()).infoToString());
				}
					
				else{
					String value= tn.toString();
					xml.append("\n<" + value + ">");
					
					for(int j=0;j<tn.getChildCount();j++){
						if (((DefaultMutableTreeNode) tn.getChildAt(j)).getUserObject() instanceof Contact){
							xml.append("\n" + ((Contact)((DefaultMutableTreeNode) tn.getChildAt(j)).getUserObject()).infoToString());
						}
						
						else xml.append("\n"+tn.getChildAt(j).toString());
					}
					
					xml.append("\n</" + value + ">");
				}
				
			}
			
		}
		xml.append("\n</" + root.toString() + ">");
		
		return xml.toString();	
	}
}