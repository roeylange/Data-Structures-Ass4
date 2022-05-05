import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BacktrackingBTree<T extends Comparable<T>> extends BTree<T> {

    public BacktrackingBTree(int order) {
        super(order);
    }
	
	//You are to implement the function Backtrack.
	public void Backtrack() {
		
	    IntegrityStatement.signature(); // Reminder!
	    if(!MotherList.isEmpty()) {
	    	T curVal=(T)MotherList.removeLast();	//this is the inserted value
	    	Node<T> curNode=this.getNode(curVal);	//we find the node it's stored at
			curNode.removeKey(curVal);		//we delete the key from the node as requested
			if(curNode!=root)				//if the node isn't the root
				curNode=curNode.parent;		//we keep going onto curNode's parent
			if(this.root.getNumberOfKeys()==0&&this.root.getNumberOfChildren()==0)	//if the tree is empty we put null inside
				this.root=null;
			while(true) {	///backtrack loop for splits
				curVal=(T)MotherList.removeLast();	//we advance onto the next value
	    		if(curVal==null)					//if it equals null, we're dont with this backtrack
	    			return;
	    		int index=curNode.indexOf(curVal);		//we get the index of the value
	    		while(curNode.removeKey(curVal)==null) {			//first we remove the value from the node
					curNode=curNode.parent;
					index=curNode.indexOf(curVal);		//we get the index of the value
					}
				Node<T> secondhalf=curNode.removeChild(index+1);	//we take both halves of the original node
				Node<T> firsthalf=curNode.removeChild(index);
    			firsthalf.addKey(curVal);			//we add the med value first
    			for(int i=0;i<secondhalf.numOfKeys;i++) {		//we add all the second-half's keys
    				firsthalf.addKey(secondhalf.getKey(i));
    			}
    			for(int i=0;i<secondhalf.getNumberOfChildren();i++) {		//we add all the second-half's keys
    				firsthalf.addChild(secondhalf.getChild(i));
    			}
    			curNode.addChild(firsthalf);	//we insert this node, as it is the original node now
    			if(this.root.getNumberOfKeys()==0&&this.root.getNumberOfChildren()!=0) {	//if we've reached the root
	    				this.root=firsthalf;		//we make the new root our new made node
	    					}
	    					if(curNode!=root)
	    						curNode=curNode.parent;
	    				}
	    			}
	    		}
	
	
	//Change the list returned to a list of integers answering the requirements
	public static List<Integer> BTreeBacktrackingCounterExample(){
	    IntegrityStatement.signature(); // Reminder!
	    List<Integer> madmoni=new LinkedList<Integer>();
	    madmoni.add(1);
	    madmoni.add(2);
	    madmoni.add(3);
	    madmoni.add(4);
	    return madmoni;
	}
}