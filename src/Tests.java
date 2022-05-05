
import java.util.*;



public class Tests extends BacktrackingAVL{

    private static Tests t = new Tests();
    private static Tests t1 = new Tests();
    private static Tests t2 = new Tests();

    public static void main(String[] args){
        testAVLTreeBacktracking();
        testAVLTreeContinuesBacktracking();
        testBTreeBacktracking();
    }

    public static void testAVLTreeBacktracking(){
        System.out.println("test AVL Tree Backtracking:");
        boolean passed = true;
        for(int j = 0;j < 1000 & passed;j++) {
            int m = (int) (Math.random() * 90);
            for (int i = 0; i < m & passed; i++) {
                t = new Tests();
                t2 = new Tests();
                t1 = new Tests();
                passed &= AVLTreeBacktracking();
            }
        }
        if (passed) System.out.println("Passed all tests");

    }




    public static void testBTreeBacktracking(){
        System.out.println("test BTree Backtracking:");
        boolean passed = true;
        for(int i = 0;i < 300;i++) {
            passed &= BTreeBacktracking();
        }
        if (passed) System.out.println("Passed all tests");
    }

    public static boolean BTreeBacktracking() {
        //if(true)
            //throw new UnsupportedOperationException("You should add this constructors to the Backtracking BTree class, delete this afterwards");
        /*
    public BacktrackingBTree(int order) {
        super(order);
    }
    public BacktrackingBTree() {
        super();
    }*/
        boolean allSucc = true;

        BacktrackingBTree<Integer> tree = new BacktrackingBTree(((int) (Math.random() * 19) + 2));

        //INSERT HERE HOW MANY ITEMS YOU WANT TO BE INSERTED
        int num = (int) (Math.random() *1000);
        //************************//
        String[] trees = new String[num];
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            int toAdd = (int) (Math.random() * num * 10000);
            while (l.contains(toAdd)) {
                toAdd = (int) (Math.random() * num * 10000);
            }
            l.add(toAdd);
            trees[i] = tree.toString();
            tree.insert(toAdd);
        }
        for (int i = num; i > 0 && allSucc; ) {
            String treeBefore = tree.toString();
            tree.Backtrack();
            if (!tree.toString().equals(trees[--i])) {
                System.out.println("Failed in tree when backtracking key value: " + l.get(i) + "\n tree before: \n" + treeBefore + "\n origin tree: \n" + trees[i + 1]);
                System.out.println("expected tree: \n" + trees[i]);
                System.out.println();
                System.out.println("actual tree: \n" + tree);
                allSucc = false;
            }

        }
        return allSucc;
    }
    public static boolean AVLTreeBacktracking(){
        int rrr = 500;
        BacktrackingAVL[] trees = {t, t1, t2};
        Boolean passed = true;
        LinkedList<Integer> list = new LinkedList<>();
        int re = (int)(Math.random()*90)+45;
        for(int i = 0;i < re; i++){
            int ran = (int)(Math.random()*rrr);
            while (list.contains(ran)){
                ran = (int)(Math.random()*rrr);
            }
            if(list.contains(ran)) System.out.println(true);
            list.add(ran);
            insert(trees, ran);
        }
        trees = new BacktrackingAVL[]{t,t1};
        List<Integer> list1 = new ArrayList<>();
        int r = (int)(Math.random()*45);
        for(int i = 0;i < r; i++){
            int ran = (int)(Math.random()*rrr);
            while (list.contains(ran) || list1.contains(ran)){
                ran = (int)(Math.random()*rrr);
            }
            list1.add(ran);
            insert(trees, ran);
        }

        for(int i = 0;i<r;i++){
            t.Backtrack();
        }

        passed &= AVLTreeEquals(t2,t);
        for (Integer i : list1){
            t.insert(i);
        }
        passed &= AVLTreeEquals(t1,t);
        return passed;
    }

    public static void testAVLTreeContinuesBacktracking(){
        System.out.println("test AVL Tree Backtracking:");
        boolean passed = true;
        for (int i = 0; i< 100 & passed;i++){
            passed &= BacktrackingAVL();
        }
        if(passed) System.out.println("Passed All tests");
    }
    public static boolean BacktrackingAVL(){
        int rrr = 500;
        t = new Tests();
        Boolean passed = true;
        LinkedList<Integer> list = new LinkedList<>();
        int re = (int)(Math.random()*140);
        String[] trees = new String[re];
        for(int i = 0;i < re; i++){
            int ran = (int)(Math.random()*rrr);
            while (list.contains(ran)){
                ran = (int)(Math.random()*rrr);
            }
            list.add(ran);
            trees[i] = toString(t);
            t.insert(ran);
        }
        for (int i = re; i > 0 & passed;){
            t.Backtrack();
            passed &= toString(t).equals(trees[--i]);
            if(!passed) System.out.println("Failed in continues Backtracking");
        }
        return passed;
    }


    public static String toString(Tests t){
        Iterator<Node> it1 = t.getInorderIteratorNode();
        Iterator<Node> it2 = t.getPreorderIteratorNode();
        String res = "";
        while (it1.hasNext() && it2.hasNext()){
            Node t1 = it1.next();
            Node t2 = it2.next();
            res+=t1.value+""+t1.height+""+NodeToString(t1.parent)+""+NodeToString(t1.left)+""+NodeToString(t1.right)
            +""+t2.value+""+t2.height+""+NodeToString(t2.parent)+""+NodeToString(t2.left)+""+NodeToString(t2.right);
        }
        return res;
    }
    public static boolean AVLTreeEquals(Tests t1, Tests t2){
        if(!t1.equals(t2)){
            System.out.println("Expected Tree: ");
            t1.printTree();
            System.out.println("Actual Tree: ");
            t2.printTree();
        }
        return t1.equals(t2);
    }
    public static void insert(BacktrackingAVL[] trees, Integer value){
        for (BacktrackingAVL tree : trees)
            tree.insert(value);
    }

    public boolean equals(Object obj){
        Tests b = (Tests) obj;
        Iterator<Node> it1 = getInorderIteratorNode();
        Iterator<Node> it2 = b.getInorderIteratorNode();
        Iterator<Node> it3 = getPreorderIteratorNode();
        Iterator<Node> it4 = b.getPreorderIteratorNode();
        while (it1.hasNext() && it2.hasNext()){
            Node t1 = it1.next();
            Node t2 = it2.next();
            Node t3 = it3.next();
            Node t4 = it4.next();
            if((t1.value != t2.value) || (t3.value != t4.value) || (t1.height != t2.height) || (t3.height != t4.height)) {
                System.out.println("trees aren't match");
                System.out.println("Expected Key(InOrder): " + t1.value + " Expected Height(InOrder): " + t1.height);
                System.out.println("Actual Key(InOrder): " + t2.value + " Actual Height(InOrder): " + t2.height);
                System.out.println("Expected Key(PreOrder): " + t3.value + " Expected Height(PreOrder): " + t3.height);
                System.out.println("Actual Key(PreOrder): " + t4.value + " Actual Height(PreOrder): " + t4.height);
                return false;
            }
            if(!isPointersTheSame(t1,t2,"InOrder:")||
            !isPointersTheSame(t3,t4,"PreOrder:")){
                return false;
            }
        }
        if(it1.hasNext() || it2.hasNext() || it3.hasNext() || it4.hasNext()){
            System.out.println("one tree is bigger than the other");
            return false;
        }
        return true;
    }
    private static String NodeToString(Node n){
        return (n == null) ? "null" : "" + n.value;
    }

    private boolean isPointersTheSame(Node n1, Node n2, String order){
        if(!(NodeToString(n1.parent).equals(NodeToString(n2.parent))) ||
                !(NodeToString(n1.right).equals(NodeToString(n2.right)))
                || !(NodeToString(n1.left).equals(NodeToString(n2.left))) ){
            System.out.println(order);
            System.out.println("Expected Parent: " + NodeToString(n1.parent) + " Actual Parent: " + NodeToString(n2.parent));
            System.out.println("Expected Left: " + NodeToString(n1.left) + " Actual Left: " + NodeToString(n2.left));
            System.out.println("Expected Right: " + NodeToString(n1.right) + " Actual Right: " + NodeToString(n2.right));
            return false;
        }
        return true;
    }
    private abstract class BaseBSTIterator implements Iterator<Node> {
        private List<Node> values;
        private int index;
        public BaseBSTIterator(Node root) {
            values = new ArrayList<>();
            addValues(root);

            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < values.size();
        }

        @Override
        public Node next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return values.get(index++);
        }

        protected void addNode(Node node) {
            values.add(node);
        }

        abstract protected void addValues(Node node);
    }

    public class InorderIterator extends Tests.BaseBSTIterator {
        public InorderIterator(Node root) {
            super(root);
        }

        @Override
        protected void addValues(Node node) {
            if (node != null) {
                addValues(node.left);
                addNode(node);
                addValues(node.right);
            }
        }

    }

    public class PreorderIterator extends Tests.BaseBSTIterator {

        public PreorderIterator(Node root) {
            super(root);
        }

        @Override
        protected void addValues(AVLTree.Node node) {
            if (node != null) {
                addNode(node);
                addValues(node.left);
                addValues(node.right);
            }
        }
    }
    public Iterator<Node> getInorderIteratorNode() {
        return new Tests.InorderIterator(this.root);
    }
    public Iterator<Node> getPreorderIteratorNode() {
        return new Tests.PreorderIterator(this.root);
    }


}
