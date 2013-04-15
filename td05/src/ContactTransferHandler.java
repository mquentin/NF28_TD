import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;


public class ContactTransferHandler extends TransferHandler {
	protected AppView mAppView;
	
	
	public ContactTransferHandler(AppView mAppView) {
		super();
		this.mAppView = mAppView;
	}

	public int getSourceActions(JComponent c){
		return MOVE;
	}

	protected Transferable createTransferable(JComponent c){
		JTree tree = (JTree) c;
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		if (node.getUserObject() instanceof Contact)
			return new NodeTransferable(node);
		return null;
	}
	
	public  boolean canImport(TransferHandler.TransferSupport support) {		
		if(support.isDrop() && support.getTransferable().isDataFlavorSupported(NodeTransferable.nodeFlavor))
			return true;
		return false;
	}
	
	public boolean importData(TransferSupport support) {
		if (canImport(support)) {
			try{
				Transferable t = support.getTransferable();
				DefaultMutableTreeNode dmt = (DefaultMutableTreeNode)
				t.getTransferData(NodeTransferable.nodeFlavor);
				JTree.DropLocation dl = (JTree.DropLocation) support.getDropLocation();
				TreePath tp = dl.getPath();
				if(tp==null) {
					return false;
				}
				DefaultMutableTreeNode parent = (DefaultMutableTreeNode)tp.getLastPathComponent();
				if(parent.getUserObject() instanceof Contact) {
					return false;
				}
				JTree tree = (JTree)support.getComponent();
				DefaultTreeModel tm = (DefaultTreeModel) tree.getModel();
				parent.add(dmt);
				//tm.reload();
				mAppView.reload_tree();
				tree.expandPath(tp);
				return true;
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		return false;
	}
	

}
