public class RatNum {
    private int denominator;
    private int numerator;

    public RatNum() {
        this.numerator = 0;
        this.denominator = 1;
    }

    public RatNum(int a) {
        this.numerator = a;
        this.denominator = 1;
    }

    public RatNum(int a, int b) {
        if (b == 0) {
            throw new NumberFormatException("cant devide with zero");
        }
        int gcd = gcd(a, b);
        if (b < 0) { // make the denominator the sign carrying number
            a *= -1;
            b *= -1;
        }

        this.numerator = a / gcd;
        this.denominator = b / gcd;
    }

    public RatNum(RatNum r) {
        this.numerator = r.getNumerator();
        this.denominator = r.getDenominator();
    }
    
    public int getDenominator() {
        return this.denominator;
    }

    public int getNumerator() {
        return this.numerator;
    }

    public static int gcd(int m, int n) {
        m = Math.abs(m);
        n = Math.abs(n);
        if (n == 0 && m == 0) {
            throw new IllegalArgumentException("Both of the enetered numbers cant be zero");
        }
        while (m != 0 && n != 0) {
            if (m > n) {
                m = m % n;
            } else {
                n = n % m;
            }
        }
        return n + m; // because one of m and n will be zero, by adding them we get the non-zero value
    }

}
