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
				String value= tn.toString();
								
				if(value.contains("<contact>")) xml.append("\n" + value);
				else xml.append("\n<" + value + ">");
				
				for(int j=0;j<tn.getChildCount();j++){
					xml.append("\n"+tn.getChildAt(j).toString());
				}
				
				if(!value.contains("<contact>")) xml.append("\n</" + value + ">");
				
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