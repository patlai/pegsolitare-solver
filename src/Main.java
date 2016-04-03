//[@] = black = false
//[ ] = white = true
import java.lang.reflect.Array;
import java.util.*;
import javax.swing.*;

public class Main {
    final static int[][] triplets = {
            //4 outside borders:
            {0,1,2},
            {12,19,26},
            {6,13,20},
            {30,31,32},

            //8  other borders:
            {2,5,10},
            {10,11,12},
            {24,25,26},
            {24,29,32},
            {22,27,30},
            {20,21,22},
            {6,7,8},
            {0,3,8},

            //4 inner edges:
            {3,4,5},
            {11,18,25},
            {27,28,29},
            {7,14,21},

            {7,8,9},
            {8,9,10},
            {9,10,11},


            {13,14,15},
            {14,15,16},
            {15,16,17},
            {16,17,18},
            {17,18,19},


            {21,22,23},
            {22,23,24},
            {23,24,25},


            {5,10,17},
            {10,17,24},
            {17,24,29},


            {1,4,9},
            {4,9,16},
            {9,16,23},
            {16,23,28},
            {23,28,31},


            {3,8,15},
            {8,15,22},
            {15,22,27},




    };
    public static void main(String[] args){
        boolean[] c = {
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                false,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
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
        Node<boolean[]> bb = new Node(b);
        //System.out.println(findLoop(cc,bb));
       // makeBoolean(cc,s, 0);
        Node<boolean[]> q = new Node<boolean[]>(c);
        ArrayList<Node<boolean[]>> qq = new ArrayList<>();
        qq.add(q);
        solve(qq,0);
        HiRiQ one = new HiRiQ((byte) 0);
        HiRiQ two = new HiRiQ((byte) 0);
        HiRiQ three = new HiRiQ((byte) 0);
        HiRiQ four = new HiRiQ((byte) 0);
        HiRiQ five = new HiRiQ((byte) 0);
        HiRiQ six = new HiRiQ((byte) 0);
        Node<HiRiQ> ONE = new Node<>(one);
    }


    public static ArrayList<Node<boolean[]>> makeBoolean (Node<boolean[]> b , ArrayList<String> s, int index){
        // ArrayList<boolean[]> qq  = new ArrayList<>();
        int k = 0;
        HiRiQ tmp2 = new HiRiQ((byte)0);
        tmp2.store(b.getContent());
        if(tmp2.IsSolved()){
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
                        //throw(new NullPointerException());
                        while(true) {
                            return b.getChildren();
                        }
                    } else {
                        for (int i = 0; i < b.getChildren().size(); i++) {
                            if(findLoop(b, b.getParent())){
                                System.out.println("du ma");
                                return b.getChildren();
                            } else {
                                makeBoolean(b.getChildren().get(i), s, i);
                            }
                        }
                    }
                }
            }

        }
        return b.getChildren();
    }



    public static boolean findLoop(Node<boolean[]> b, Node<boolean[]> parent){
        try{
            if(Arrays.equals(b.getContent(),parent.getContent())) {
                return true;
            } else{
                return findLoop(b, parent.getParent());
            }
        }catch (NullPointerException e){
            return false;
        }
    }

    public static void solve(ArrayList<Node<boolean[]>>b, int depth){
        ArrayList<String> s = new ArrayList<>();
        boolean containsSolved = false;
        int i;
        for(i = 0; i<b.size(); i++){
            HiRiQ tmp = new HiRiQ((byte) 0);
            tmp.store(b.get(i).getContent());
            if(tmp.IsSolved()){
                containsSolved = true;
                tmp.print();
                break;
            }
        }
        if(containsSolved){
            s = getStrings(b.get(i),s);
            for(int f = 0; f< s.size(); f++){
                System.out.println(s.get(f));
            }
            System.exit(0);
        } else {
            for(int k = 0; k<b.size(); k++) {
                for (int j = 0; j < 38; j++) {
                    boolean[] b1 = new boolean[33];
                    System.arraycopy(b.get(k).getContent(), 0, b1, 0, b.get(k).getContent().length);
                    bSub(b1, triplets[j]);

                    if (!Arrays.equals(b1, b.get(k).getContent())) {
                        b.get(k).addChild(b1);
                        HiRiQ q = new HiRiQ((byte)0);
                        q.store(b1);
                        System.out.println("depth: " + depth + "  child: " + k);
                        q.print();
                        if(depth == 26 && unsolvable(b1)){
                            System.exit(0);
                        }
//                        if(findLoop(b.get(k).getChildren().get(0),b.get(k))){
//                            b.get(k).getChildren().remove(0);
//                            System.out.println("found loop");
//                        }
                    }
                }
                  solve(b.get(k).getChildren(),depth+1);
            }

        }
        return;
    }

    public static boolean search(ArrayList<Node<boolean[]>> b, int depth) {
        boolean containsSolved = false;
        int i;
        for (i = 0; i < b.size(); i++) {
            HiRiQ tmp = new HiRiQ((byte) 0);
            tmp.store(b.get(i).getContent());
            if (tmp.IsSolved()) {
                containsSolved = true;
                tmp.print();
                break;
            }
        }
        if (containsSolved) {
            ArrayList<String> s = new ArrayList<>();
            s = getStrings(b.get(i), s);
            for (int f = 0; f < s.size(); f++) {
                System.out.println(s.get(f));
            }
            System.exit(0);
            return containsSolved;
        } else {
            solve(b, depth + 1);
        }
        return false;
    }

    public static ArrayList<String> getStrings(Node<boolean[]> b, ArrayList<String> s){
        ArrayList<Integer> indexes = new ArrayList<>();
        for(int i = 0; i<33; i++){
            if(b.getContent()[i] != b.getParent().getContent()[i]){
                indexes.add(i);
            }
        }
        String tmp = indexes.get(0) + " W " + indexes.get(2);
        //System.out.println(tmp);
        s.add(tmp);
        if(b.getParent() != null){
            try {
                getStrings(b.getParent(), s);
            }
            catch(NullPointerException e){
                Collections.reverse(s);
                return s;
            }
        }
        return s;

    }


    public static boolean unsolvable(boolean[] b){
        boolean x = true;
        for(int i = 0; i < triplets.length; i++){
            if(((b[triplets[i][0]] ^ b[triplets[i][2]] ) && b[triplets[i][1]])){
                x = false;
               break;
            }

        }
        return x;
    }

    //bSub = [@][@][ ] -> [ ][ ][@]
    //wSub = [ ][ ][@] -> [@][@][ ]

    public static boolean[] wSub(boolean[] b, int[] x){
        if((b[x[0]] && b[x[2]]) || (!b[x[0]] && !b[x[2]])){
            return b;
        } else if((b[x[0]] || b[x[2]]) && !b[x[1]]) {
            //System.out.println("{"+x[0]+" W "+x[2]+"}");
            b[x[0]] = !b[x[0]];
            b[x[1]] = !b[x[1]];
            b[x[2]] = !b[x[2]];
            return b;
        } else {
            return b;
        }
    }

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
