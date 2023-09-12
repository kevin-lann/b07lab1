import java.lang.Math;

class Polynomial {
	public double [] coeffs;
	
	public Polynomial () {
		coeffs = new double[] {0};
	}

    public Polynomial (double [] arr) {
        coeffs = arr;
    }

    public Polynomial add(Polynomial p) {
        Polynomial res;
        // check which polynomial is higher degree
        if (this.coeffs.length > p.coeffs.length) {
            res = new Polynomial(this.coeffs);
            for (int i = 0; i < p.coeffs.length; i++) {
                res.coeffs[i] += p.coeffs[i];
            }
        }
        else {
            res = new Polynomial(p.coeffs);
            for (int i = 0; i < this.coeffs.length; i++) {
                res.coeffs[i] += this.coeffs[i];
            }
        }

        return res;
    }

    public double evaluate(double x) {
        double res = 0;
        for (int i = 0; i < this.coeffs.length; i++) {
            res += coeffs[i] * Math.pow(x, i);
        }
        return res;
    }

    public boolean hasRoot(double x) {
        double res = this.evaluate(x);
        return  res > -0.001 && res < 0.001 ;
    }

}