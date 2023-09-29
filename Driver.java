import java.io.File;
import java.io.FileNotFoundException;

public class Driver {
    public static void main(String [] args) throws FileNotFoundException {
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
        double [] c1 = {6,0,0,5};
        Polynomial p1 = new Polynomial(c1);
        double [] c2 = {0,-2,0,0,-9};
        Polynomial p2 = new Polynomial(c2);
        Polynomial s = p1.add(p2);
        System.out.println("s(0.1) = " + s.evaluate(0.1));
        if(s.hasRoot(1))
            System.out.println("1 is a root of s");
        else
            System.out.println("1 is not a root of s");
        
        double[] c3 = {1,1};
        double[] c4 = {2,1};
        Polynomial p3 = new Polynomial(c3);
        Polynomial p4 = new Polynomial(c4);
        Polynomial res = p3.multiply(p4);
        System.out.println(res);
        
        File f = new File("foo.txt");
        Polynomial p5 = new Polynomial(f);
        System.out.println(p5);
        
        double [] c6 = {1};
        Polynomial p6 = new Polynomial(c6);
        double [] c7 = {-1};
        Polynomial p7 = new Polynomial(c7);
        Polynomial t = p6.add(p7);
        t.saveToFile("goo.txt");
        
    }
}
