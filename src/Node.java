/**
 * Created by patricklai on 2016-03-28.
 */
import java.util.ArrayList;
public class Node<T> {
    private ArrayList<Node<T>> children = new ArrayList<>();
    private Node<T> parent = null;
    private T content = null;

    public Node(T content) {
        this.content = content;
    }

    public Node(T content, Node<T> parent) {
        this.content = content;
        this.parent = parent;
    }

    public ArrayList<Node<T>> getChildren() {
        return children;
    }
    public Node<T> getParent(){
        return parent;
    }

    public void setParent(Node<T> parent) {
        //parent.addChild2(this);
        this.parent = parent;
    }

    public void addChild(T content) {
        Node<T> child = new Node<T>(content);
        child.setParent(this);
        this.children.add(child);
    }

    public void addChild2(Node<T> child) {
        child.setParent(this);
        this.children.add(child);
    }

    public T getContent() {
        return this.content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public boolean isRoot() {
        return (this.parent == null);
    }

    public boolean isLeaf() {
        if(this.children.size() == 0)
            return true;
        else
            return false;
    }

    public void removeParent() {
        this.parent = null;
    }
}