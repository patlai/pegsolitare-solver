
public class tester {

    public static void main(String[] args) {
        boolean[] B=new boolean[33];
        HiRiQ W=new HiRiQ((byte) 0) ;
        W.print(); System.out.println(W.IsSolved());
        HiRiQ X=new HiRiQ((byte) 1) ;
        X.print(); System.out.println(X.IsSolved());
        HiRiQ Y=new HiRiQ((byte) 2) ;
        Y.print(); System.out.println(Y.IsSolved());
        HiRiQ Z=new HiRiQ((byte) 3) ;
        Z.print(); System.out.println(Z.IsSolved());
        B=Z.load(B);
        for (int i=0; i<33; i++){B[i]= !B[i];}
        Z.store(B);
        Z.print(); System.out.println(Z.IsSolved());
        boolean[] f = new boolean[33];
        for(int i  = 0; i < 33; i++){
            f[i] = true;
        }
        HiRiQ F = new HiRiQ((byte) 0);
        F.store(f);
        F.print();
    }
}