//[@] = black = false = no peg
//[ ] = white = true = yes peg
import java.lang.reflect.Array;
import java.util.*;
import javax.swing.*;

public class Main {
    final static int[][] triplets = {
            //4 outside borders: (0 - 3)
            {0,1,2},
            {12,19,26},
            {6,13,20},
            {30,31,32},

            //inner borders:
            {7,8,9},
            {3,8,15},
            {5,10,17},
            {9,10,11},
            {23,24,25},
            {17,24,29},
            {15,22,27},
            {21,22,23},

            //8  other borders: (4 - 11)
            {0,3,8},
            {2,5,10},
            {10,11,12},
            {24,25,26},
            {24,29,32},
            {22,27,30},
            {20,21,22},
            {6,7,8},




            //4 inner edges: (11 - 14)
            {3,4,5},
            {11,18,25},
            {27,28,29},
            {7,14,21},

            //center cross:
            {9,16,23},
            {15,16,17},

            //final 4:
            {14,15,16},
            {16,17,18},
            {4,9,16},
            {16,23,28},

            //outer cross:
            {13,14,15},
            {17,18,19},
            {1,4,9},
            {23,28,31},

            //inner square border:
            {8,9,10},
            {10,17,24},
            {22,23,24},
            {8,15,22},
    };
    final static int[][] triplets2 = {
            //outer cross:
            {13,14,15},
            {17,18,19},
            {1,4,9},
            {23,28,31},
            //center cross:
            {9,16,23},
            {15,16,17},

            //8  other borders: (4 - 11)
            {0,3,8},
            {2,5,10},
            {10,11,12},
            {24,25,26},
            {24,29,32},
            {22,27,30},
            {20,21,22},
            {6,7,8},

            {7,8,9},
            {3,8,15},
            {5,10,17},
            {9,10,11},
            {23,24,25},
            {17,24,29},
            {15,22,27},
            {21,22,23},


            //4 inner edges: (11 - 14)
            {3,4,5},
            {11,18,25},
            {27,28,29},
            {7,14,21},


            //final 4:
            {14,15,16},
            {16,17,18},
            {4,9,16},
            {16,23,28},



            //inner square border:
            {8,9,10},
            {10,17,24},
            {22,23,24},
            {8,15,22},

            //4 outside borders: (0 - 3)
            {0,1,2},
            {12,19,26},
            {6,13,20},
            {30,31,32},
    };

    public static void main(String[] args){
        HiRiQ test = new HiRiQ((byte) 3);
        boolean[] c = new boolean[33];
        c = test.load(c);
        boolean[] D = {
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
                false,
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
                false,
                true,
                true,
                false,
                false,
                false,
                false,
                false
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

        Node<boolean[]> q = new Node<boolean[]>(c);
        ArrayList<Node<boolean[]>> qq = new ArrayList<>();
        qq.add(q);
        solve(qq,0);
        HiRiQ one = new HiRiQ((byte) 0);
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
        int k;
        for(k = 0; k<b.size(); k++) {
            for (int j = 0; j < triplets.length; j++) {
                boolean[] b1 = new boolean[33];
                System.arraycopy(b.get(k).getContent(), 0, b1, 0, b.get(k).getContent().length);
                bSub(b1, triplets[j]);
                //successful bSub:
                if (!Arrays.equals(b1, b.get(k).getContent())) {
                    b.get(k).addChild(b1);
                    HiRiQ q = new HiRiQ((byte)0);
                    q.store(b1);
                    //System.out.println("depth: " + depth + "  child: " + k);
                    //q.print();
//                    if(unsolvable(b1)){
//                        for(int l = 0; l < triplets.length; l++) {
//                            wSub(b1, triplets[l]);
//                            boolean[] b2 = new boolean[33];
//                            System.arraycopy(b1, 0, b2, 0, b1.length);
//                            if(!Arrays.equals(b1,b2)){
//                                break;
//                            }
//                        }
//                    }
//                    if(depth == 26 && unsolvable(b1)){
//                        System.exit(0);
//                    }

                }
            }
//            if(b.get(k).getChildren().isEmpty()){
//
//                for(int j = 0; j<triplets2.length; j++) {
//                    wSub(b.get(k).getContent(), triplets2[j]);
//                }
//            }
            search(b.get(k).getChildren(), depth);
        }
        HiRiQ ff = new HiRiQ((byte) 0);
        //ff.store(b.get(k).getChildren().get);


        //return;
    }

    public static void search(ArrayList<Node<boolean[]>> b, int depth) {
        boolean containsSolved = false;
//        if(b.isEmpty()){
//
//            for(int j = 0; j<triplets.length; j++) {
//                wSub(b.get(0).getContent(), triplets[j]);
//            }
//        }
        int i;
        for (i = 0; i < b.size(); i++) {
            HiRiQ tmp = new HiRiQ((byte) 0);
            tmp.store(b.get(i).getContent());
            if (tmp.IsSolved()) {
                containsSolved = true;
                //tmp.print();
                break;
            }
        }
        if (containsSolved) {
            ArrayList<String> s = new ArrayList<>();
            s = getStrings(b.get(i), s);
            for (int f = 0; f < s.size(); f++) {
                System.out.println(s.get(f));
            }
            HiRiQ solved = new HiRiQ((byte) 0);
            solved.store(b.get(i).getContent());
            solved.print();
            System.exit(0);
        } else {
            solve(b, depth + 1);
        }
    }


    public static boolean noChildren(ArrayList<Node<boolean[]>> b){
        boolean chld;
        try{
            chld = false;
        } catch (ArrayIndexOutOfBoundsException e){
            chld = true;
        }
//        for(int i = 0; i<b.size(); i++){
//            if(b.get(i).getChildren().get(0) == null){
//                chld = true;
//                break;
//            }
//        }
        return chld;
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
