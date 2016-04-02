import java.util.ArrayList;

/**
 * Created by patricklai on 2016-03-28.
 */
public class work {
    public static void main(String[] args){
        int j = 5;
        Node<Integer> n = new Node(j);
        n.addChild(3);
        n.addChild(4);
        n.addChild(2);

        for(int i = 0; i < n.getChildren().size(); i++){
           System.out.println( n.getChildren().get(i).getContent());
            System.out.println(n.getChildren().get(0).getParent().getContent());
        }

    }

    public static int returntest(){
        for(int i = 0; i < 10; i++){
            return 1;
        }
        return 1;
    }
}
