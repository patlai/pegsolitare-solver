import com.sun.tools.javac.util.List;

import java.util.ArrayList;


/**
 * Created by patricklai on 2016-03-28.
 */
public class Tree<HiRiQ> {
    private Node<HiRiQ> root;

    public Tree(HiRiQ rootData) {
        root =  new Node<HiRiQ>();
        root.data = rootData;
        root.children = new ArrayList<Node<HiRiQ>>();
    }

    public Node getRoot(){
        return root;
    }

    public static class Node<HiRiQ> {
         HiRiQ data;
         Node<HiRiQ> parent;
         ArrayList<Node<HiRiQ>> children;
    }
}
