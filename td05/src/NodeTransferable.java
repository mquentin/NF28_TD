import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.tree.DefaultMutableTreeNode;


public class NodeTransferable implements Transferable {
	protected DefaultMutableTreeNode node;
	public static final DataFlavor nodeFlavor = new DataFlavor(
			DataFlavor.javaJVMLocalObjectMimeType,"ContactNode");

	public NodeTransferable(DefaultMutableTreeNode node){
		this.node = node;
	}
	
	@Override
	public Object getTransferData(DataFlavor flavor)
			throws UnsupportedFlavorException, IOException {
		if(flavor==nodeFlavor)
			return node;
		if(flavor==DataFlavor.stringFlavor)
			return ((Contact)node.getUserObject()).getNom();
		return null;
	}
	
	public static DataFlavor getNodeFlavor() {
		return	nodeFlavor;}

	public boolean isDataFlavorSupported(DataFlavor arg0) {
			return Arrays.asList(getTransferDataFlavors()).contains(arg0);
	}

	public DataFlavor[] getTransferDataFlavors() {
		DataFlavor[] result = {nodeFlavor, DataFlavor.stringFlavor};
		return result;
	}

}
