import java.util.LinkedList;
import java.util.List;

public class BacktrackingAVL extends AVLTree {

	//You are to implement the function Backtrack.
    public void Backtrack() {
        IntegrityStatement.signature(); // Do not remove this line
        if(MotherList.isEmpty())		//if the list is empty, we send the call back
        	return;
        Node delete=(AVLTree.Node) MotherList.removeLast();	//we take the last item out of the list- it is the inserted node
        Node par =delete.parent;
        Node insertedNode=(AVLTree.Node) MotherList.removeLast();	//we remove the second item from the list. if it is null, no rotation was made, else:
        if(insertedNode==null) {	//there was only 1 deletion, no rotations
            if(delete==root)		//if it is the root, we put null because the tree is now empty
            	root=null;
            else if(delete.parent.right==delete)	//else we delete the node according to it's location
            	delete.parent.right=null;
            else											//else we delete the node according to it's location
            	delete.parent.left=null;
        	while(par!=null) {		//done with editing the tree, now we need to update the heights from par to the root
        		int oldHeight=par.height;
        		par.height=Math.max(getNodeHeight(par.left),getNodeHeight(par.right))+1;		//we adjust from the node to the root
        		if(oldHeight==par.height)
        			return;
        		par=par.parent;
        	}


        }
        else { 		//there WERE rotations
        	while(insertedNode!=null) {	//first, we undo the rotations
        		String str=(String) MotherList.removeLast();	//this will be the indicator of what rotation was made (left/right)
        		if(str=="left")
        			undoLR(insertedNode);	//we undo the rotation using our helper function
        		else
        			undoRR(insertedNode);	//we undo the rotation using our helper function
        		insertedNode=(AVLTree.Node) MotherList.removeLast();	//we check the next node- if it's not null there was a DOUBLE ROTATION
        	}	//done rotating, now we make the deletion
        	par=delete.parent;	//for updating heights
            if(delete.parent.right==delete)		//else we delete the node according to it's location
            	delete.parent.right=null;
            else											//else we delete the node according to it's location
            	delete.parent.left=null;

        	while(par!=null) {		//done with editing the tree, now we need to update the heights from par to the root
        		int oldHeight=par.height;
        		par.height=Math.max(getNodeHeight(par.left),getNodeHeight(par.right))+1;		//we adjust from the node to the root
        		if(oldHeight==par.height)
        			return;
        		par=par.parent;
        	}
        }
    }
    private void undoLR(Node node) {		//helper function for backtracking a left rotation
    	Node right=node.right;
    	Node par=node.parent;
    	node.parent=par.parent;		//according to what was taught in lecture, we un-rotate the tree
    	if(par.parent==null)		//we put the node in where it originally was (first parents)
    		this.root=node;
    	else if(par.parent.value>node.value)
    		par.parent.left=node;
    	else
    		par.parent.right=node;
    	node.right=par;		//we re-arrange the nodes
    	par.parent=node;
    	par.left=right;
    	if(right!=null)
    		right.parent=par;
    	if(getNodeHeight(node.left)>getNodeHeight(node.right))		//we re-arrange the heights
    		node.height=getNodeHeight(node.left)+1;
    	else
    		node.height=getNodeHeight(node.right)+1;
    	if(getNodeHeight(par.left)>getNodeHeight(par.right))
    		par.height=getNodeHeight(par.left)+1;
    	else
    		par.height=getNodeHeight(par.right)+1;
    }
    private void undoRR(Node node) {	//helper function for backtracking a right rotation
    	Node left=node.left;
    	Node par=node.parent;
    	node.parent=par.parent;		//according to what was taught in lecture, we un-rotate the tree
    	if(par.parent==null)		//we put the node in where it originally was (first parents)
    		this.root=node;
    	else if(par.parent.value>node.value)
    		par.parent.left=node;
    	else
    		par.parent.right=node;
    	node.left=par;			//we re-arrange the nodes
    	par.parent=node;
    	par.right=left;
    	if(left!=null)
    		left.parent=par;
    	if(getNodeHeight(node.left)>getNodeHeight(node.right))	//we re-arrange the heights
    		node.height=getNodeHeight(node.left)+1;
    	else
    		node.height=getNodeHeight(node.right)+1;
    	if(getNodeHeight(par.left)>getNodeHeight(par.right))
    		par.height=getNodeHeight(par.left)+1;
    	else
    		par.height=getNodeHeight(par.right)+1;
    }

    
    //Change the list returned to a list of integers answering the requirements
    public static List<Integer> AVLTreeBacktrackingCounterExample(){
        IntegrityStatement.signature(); // Do not remove this line
	    List<Integer> madmoni=new LinkedList<Integer>();
	    madmoni.add(1);
	    madmoni.add(2);
	    madmoni.add(3);
	    return madmoni;
    }
    
}