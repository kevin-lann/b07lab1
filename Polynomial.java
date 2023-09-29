import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.Math;
import java.util.Scanner;
import java.util.Arrays;

class Polynomial {
	public double [] coeffs;
	public int [] exps;
	
	public Polynomial () {
		coeffs = new double[] {0};
		exps = new int[] {0};
	}
	
	// helper method for initialization
	private int countZeroes(double[] arr) {
		if(Arrays.equals(arr, new double[] {0})) return 0;
		int count = 0;
		for(int i = 0; i < arr.length; i++) 
			if(arr[i] == 0) count += 1;
		return count;
	}

    public Polynomial (double [] arr) {
    	coeffs = new double[arr.length - countZeroes(arr)];
		exps = new int[arr.length - countZeroes(arr)];
        int i = 0, j = 0;
        while(i < arr.length) {
        	if(arr[i] != 0) {
        		coeffs[j] = arr[i];
        		exps[j] = i;
        		j++;
        	}
        	i++;
        }
    }
    
    public Polynomial (File f) {
    	Scanner in = null;
    	try {
			in = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	
    	// Parse each term
    	String parts[] = in.nextLine().replace("-", "+-").split("\\+");
    	coeffs = new double[parts.length];
    	exps = new int[parts.length];
    	
    	for(int i = 0; i < parts.length; i++) {
    		if(parts[i].indexOf('x') == -1) { // constant term
    			coeffs[0] = Double.parseDouble(parts[i]);
    			exps[0] = 0;
    		}
    		else {	// exponent part is greater than 0
    			coeffs[i] = Double.parseDouble(parts[i].substring(0, parts[i].indexOf('x')));
    			exps[i] = Integer.parseInt(parts[i].substring(parts[i].indexOf('x') + 1));
    		}
    	}
    	
    	in.close();
    }
    
    public void saveToFile(String s) throws FileNotFoundException {
    	File f = new File(s);
    	PrintStream ps = new PrintStream(s);
    	
    	for(int i = 0; i < coeffs.length; i++) {
    		ps.print(coeffs[i]);
    		if(exps[i] > 0)
    			ps.print("x" + exps[i]);
    		if(coeffs[i] > 0 && i != coeffs.length - 1) 
    			ps.print("+");
    	}
    	
    	ps.close();
    }
    
    @Override
    public String toString() {
    	String s= "";
    	for(int i = 0; i < coeffs.length;i++)
    		s = s + coeffs[i] + " ";
    	s = s + "/ ";
    	for(int i = 0; i < exps.length;i++)
    		s = s + exps[i] + " ";
    	return s;
    }

    public Polynomial add(Polynomial p) {
    	int a = this.exps[this.exps.length-1];
    	int b = p.exps[p.exps.length-1];
    	// set len of res array to len of largest summand
        double res[] = new double[1 + (a > b ? a : b)];
        
        // add in values from both polynomials
        for(int i = 0; i<this.exps.length; i++)
        	res[this.exps[i]] += this.coeffs[i];
        for(int i = 0; i<p.exps.length; i++)
        	res[p.exps[i]] += p.coeffs[i];
        
        return new Polynomial(res);
    }
    
    public Polynomial multiply(Polynomial p) {
    	int a = this.exps[this.exps.length-1];
    	int b = p.exps[p.exps.length-1];
    	// set len of res array to largest possible exp
        double res[] = new double[a + b + 1];
        
        for(int i = 0; i < this.exps.length; i++) {
        	for(int j = 0; j < p.exps.length; j++) {
        		res[this.exps[i] + p.exps[j]] += this.coeffs[i] * p.coeffs[j];
        	}
        }
        return new Polynomial(res);
    }

    public double evaluate(double x) {
        double res = 0;
        for (int i = 0; i < this.exps.length; i++) {
            res += coeffs[i] * Math.pow(x, exps[i]);
        }
        return res;
    }

    public boolean hasRoot(double x) {
        double res = this.evaluate(x);
        return  res > -0.001 && res < 0.001 ;
    }

}