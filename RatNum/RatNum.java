import java.math.BigInteger;

/**
 * @author Lucas Ludwig Christiansson
 * @author Nils Unge Wickenberg Rangfast
 * This is Ratnum an implementation of ratinal numbers.
 */
public class RatNum {
    private BigInteger denominator;
    private BigInteger numerator;


    private RatNum(BigInteger n, BigInteger d) {
        this.numerator = n;
        this.denominator = d;
    }

    /**
     * constructor of RatNum with values 0/1 - Nils
     */
    public RatNum() {
        this(BigInteger.valueOf(0),BigInteger.valueOf(1));
    }

    /**
     * @param a value for numerator
     */
    public RatNum(int a) {
        this(BigInteger.valueOf(a),BigInteger.valueOf(1));
    }

    /**
     * @param a value for numerator
     * @param b value for denominator
     * @throws NumberFormatException when b is 0.
     */
    public RatNum(int a, int b) {
        if (b == 0) {
            throw new NumberFormatException("cant devide with zero");
        }
        int gcd = gcd(a, b);
        if (b < 0) { // make the denominator the sign carrying number
            a *= -1;
            b *= -1;
        }
        
        this.numerator = BigInteger.valueOf(a / gcd);
        this.denominator = BigInteger.valueOf(b / gcd);
    }

    /**
     * @param r rational number to be mirrored
     */
    public RatNum(RatNum r) {
        this(r.getNumerator(), r.getDenominator());
    }

    /**
     * @param s string representing rational number
     */
    public RatNum(String s) {
        this(parse(s));
    }

    /**
     * @return denominator
     */
    public int getDenominator() {
        return this.denominator.intValue();
    }

    /**
     * @return numerator
     */
    public int getNumerator() {
        return this.numerator.intValue();
    }

    /**
     * @return string representation of the rational number
     */
    public String toString() {
        return this.numerator + "/" + this.denominator;
    }

    /**
     * @param s the string to be parsed
     * @return the rational number from the string
     * @throws NumberFormatException Like six different reasons - Nils, han bryr sig inte
     */
    public static RatNum parse(String s) {
        if (!s.contains("/")) {
            return new RatNum(Integer.parseInt(s));
        }
        String[] parsedString = s.split("/");
        if (parsedString.length == 2) {
            return new RatNum(Integer.parseInt(parsedString[0]), Integer.parseInt(parsedString[1]));
        } else
            throw new NumberFormatException("Can only construct a Ratinal number form two integers");
    }

    /**
     * @param r Object to be compared
     * @return true/false
     */
    public boolean equals(Object r) {
        if (r == null || (r.getClass() != this.getClass())) {
            return false;
        }
        RatNum ratnum = (RatNum) r;
        return this.getDenominator() == ratnum.getDenominator() && this.getNumerator() == ratnum.getNumerator();
    }

    /**
     * @param r rational number
     * @return true/false
     */
    public boolean lessThan(RatNum r) {
        return (long) this.getNumerator() / this.getDenominator() < (long) r.getNumerator() / r.getDenominator();
    }

    /**
     * @param r rational number
     * @return a new rational number
     */
    public RatNum add(RatNum r) {
        BigInteger a = this.numerator
        .multiply(BigInteger.valueOf( r.getDenominator()))
        .add(this.denominator.multiply(BigInteger.valueOf( r.getNumerator())));
        
        BigInteger b = this.denominator
        .multiply(BigInteger.valueOf(r.getDenominator()));
        return new RatNum(a, b);
    }

    /**
     * @param r rational number
     * @return a new rational number
     */
    public RatNum sub(RatNum r) {
        return new RatNum(this.getNumerator() * r.getDenominator() - r.getNumerator() * this.getDenominator(),
                this.getDenominator() * r.getDenominator());
    }

    /**
     * @param r rational number
     * @return a new rational number
     */
    public RatNum mul(RatNum r) {
        return new RatNum(this.getNumerator() * r.getNumerator(), this.getDenominator() * r.getDenominator());
    }

    /**
     * @param r rational number
     * @return a new rational number
     */
    public RatNum div(RatNum r) {
        return new RatNum(this.getNumerator() * r.getDenominator(), this.getDenominator() * r.getNumerator());
    }

    /**
     * @return string from the rational number
     */
    public String toIntString() {
        return (Integer.toString(this.getNumerator() / this.getDenominator()));
    }

    /**
     * @param m a number
     * @param n a number
     * @return the greatest common divisor of two integers, m and n
     */
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
