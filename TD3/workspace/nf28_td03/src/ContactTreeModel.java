import java.util.Collections;
import java.util.Enumeration;

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
				TreeNode tn = root.getChildAt(i);
				xml.append("\n1<" + tn.toString() + ">");
				
				for(int j=0;j<tn.getChildCount();j++){
					
					try{
						Contact c=(Contact)tn.getChildAt(j);
						xml.append("\n2<" + c.infoToString() + ">");
					}catch(Exception e){
						xml.append("\n2 Pb cast "+tn.getChildAt(j).toString());
					}
					
					
				}
				
				xml.append("\n1</" + tn.toString() + ">");
			}
			
		}
		xml.append("\n</" + root.toString() + ">");
		
		return xml.toString();	
	}
	protected String getChildXML(TreeNode node){
		StringBuffer output = new StringBuffer();
		for (Enumeration<TreeNode> e = node.children(); e.hasMoreElements();){
//			if(!((TreeNode) e).isLeaf())
		}
		       
			//System.out.println(e.nextElement());
		return output.toString();
		
	}

}