//[@] = black = false
//[ ] = white = true
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;

public class Main {
    final static int[][] triplets = {
            {0,1,2},
            {3,4,5},

            {6,7,8},
            {7,8,9},
            {8,9,10},
            {9,10,11},
            {10,11,12},

            {13,14,15},
            {14,15,16},
            {15,16,17},
            {16,17,18},
            {17,18,19},

            {20,21,22},
            {21,22,23},
            {22,23,24},
            {23,24,25},
            {24,25,26},

            {27,28,29},
            {30,31,32},

            {12,19,26},
            {11,18,25},

            {2,5,10},
            {5,10,17},
            {10,17,24},
            {17,24,29},
            {24,29,32},

            {1,4,9},
            {4,9,16},
            {9,16,23},
            {16,23,28},
            {23,28,31},

            {0,3,8},
            {3,8,15},
            {8,15,22},
            {15,22,27},
            {22,27,30},

            {7,14,21},
            {6,13,20}
    };
    public static void main(String[] args){
        boolean[] c = {
                false,
                false,
                false,
                false,
                true,
                false,
                false,
                false,
                true,
                false,
                false,
                false,
                false,
                false,
                true,
                true,
                true,
                true,
                false,
                false,
                false,
                true,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
        };
        HiRiQ x = new HiRiQ((byte) 3);
        HiRiQ tt = new HiRiQ((byte) 0);
        tt.store(c);
        System.out.println(tt.config + "  " + tt.weight);
        tt.print();
        //ArrayList<HiRiQ> a = (makeTree(x,0));
        boolean[] b = new boolean[33];
        ArrayList<String> s = new ArrayList<>();
        Node<boolean[]> cc = new Node(c);
        makeBoolean(cc,s, 0);
    }


    public static ArrayList<Node<boolean[]>> makeBoolean (Node<boolean[]> b , ArrayList<String> s, int index){
       // ArrayList<boolean[]> qq  = new ArrayList<>();
        int k = 0;
        HiRiQ tmp2 = new HiRiQ((byte)0);
        tmp2.store(b.getContent());
        if(tmp2.IsSolved()){
            return b.getChildren();
        } else {
            outerloop:
            for (k = 0; k < 38; k++) {
                boolean[] b1 = new boolean[33];
                System.arraycopy(b.getContent(), 0, b1, 0, b.getContent().length);
                bSub(b1, triplets[k]);
                //if both boards are the same, do nothing
                if (Arrays.equals(b1, b.getContent())) {
                    //---do nothing---
                } else {
                    printIntArray(triplets[k]);
                    System.out.println(countFalse(b1) + " black squares");
                    b.addChild(b1);
                    s.add(k + " W " + (k + 2));
                    HiRiQ tmp = new HiRiQ((byte) 3);
                    tmp.store(b1);
                    tmp.print();
                    if (tmp.IsSolved()) {
                        System.out.println("Solved");
                        System.out.println("index: " + index);
                        getStrings(b.getChildren().get(0), s);
                        //return makeBoolean(b.getChildren().get(0), s, 0);
                        return b.getChildren();
                    } else {
                        for (int i = 0; i < b.getChildren().size(); i++) {
                            makeBoolean(b.getChildren().get(i), s, i);
                        }
                    }
                }
            }
            return b.getChildren();
        }

    }

    public static ArrayList<String> getStrings(Node<boolean[]> b, ArrayList<String> s){
        ArrayList<Integer> indexes = new ArrayList<>();
        for(int i = 0; i<33; i++){
            if(b.getContent()[i] != b.getParent().getContent()[i]){
                indexes.add(i);
            }
        }
        String tmp = indexes.get(0) + " W " + indexes.get(2);
        System.out.println(tmp);
        s.add(tmp);
        if(b.getParent() != null){
            try {
                getStrings(b.getParent(), s);
            }
            catch(NullPointerException e){
                return s;
            }
        }
        return s;

    }


    //bSub = [@][@][ ] -> [ ][ ][@]
    //wSub = [ ][ ][@] -> [@][@][ ]

    public static boolean[] bSub(boolean[] b, int[] x){

        if((b[x[0]] && b[x[2]]) || (!b[x[0]] && !b[x[2]])){
            return b;
        } else if((!b[x[0]] || !b[x[2]]) && b[x[1]]) {
            //System.out.println("{"+x[0]+" W "+x[2]+"}");
            b[x[0]] = !b[x[0]];
            b[x[1]] = !b[x[1]];
            b[x[2]] = !b[x[2]];
            return b;
        } else {
            return b;
        }
    }
    public static int countFalse(boolean[] b){
        int falseCount = 0;
        for(int i = 0; i < b.length; i++){
            if(!b[i]){
                falseCount++;
            }
        }
        return falseCount;
    }

    public static int countFalseMax(ArrayList<boolean[]> bb){
        int max = 0;
        int i;
        for(i = 0; i < bb.size(); i++){
            if(countFalse(bb.get(i)) > max){
                max = countFalse(bb.get(i));
            }
        }
        return i;
    }

    public static void printBooleanArray(boolean[] b){
        for(int i = 0; i < b.length; i++){
            System.out.println(b[i]);
        }
    }

    public static void printIntArray(int[] x){
        System.out.print("[");
        for(int i = 0; i < x.length; i++){
            System.out.print(x[i] + ",");
        }
        System.out.print("]\n");
    }

    public static HiRiQ bSub(HiRiQ q, int[] x){
        boolean[] b = new boolean[33];
        q.load(b);
        if((b[x[0]] && b[x[2]]) || (!b[x[0]] && !b[x[2]])){
            return q;
        } else if(!b[x[0]] || !b[x[2]]) {
            b[x[0]] = !b[x[0]];
            b[x[1]] = !b[x[1]];
            b[x[2]] = !b[x[2]];
            q.store(b);
            return q;
        } else {
            return q;
        }
    }
}
