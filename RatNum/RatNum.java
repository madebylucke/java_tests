/**
 * This is Ratnum an implementation of ratinal numbers.
 */
public class RatNum {
    private int denominator;
    private int numerator;

    /**
     * constructor of RatNum with values 0/1
     */
    public RatNum() {
        this.numerator = 0;
        this.denominator = 1;
    }

    /**
     * constructor of RatNum with values a/1
     */
    public RatNum(int a) {
        this.numerator = a;
        this.denominator = 1;
    }

    /**
     * constructor of RatNum with values a/b
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

        this.numerator = a / gcd;
        this.denominator = b / gcd;
    }

    /**
     * constructor of RatNum with values mirrored from r
     */
    public RatNum(RatNum r) {
        this.numerator = r.getNumerator();
        this.denominator = r.getDenominator();
    }

    /**
     * constructor of RatNum from the stirng passed
     */
    public RatNum(String s) {
        this(parse(s));
    }

    /**
     * returns denominator
     */
    public int getDenominator() {
        return this.denominator;
    }

    /**
     * returns numerator
     */
    public int getNumerator() {
        return this.numerator;
    }

    /**
     * returns string representation of the ratinal number
     */
    public String toString() {
        return this.numerator + "/" + this.denominator;
    }

    /**
     * returns parsed string of the number
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
     * returns if object r is equal to the number
     */
    public boolean equals(Object r) {
        if (r == null || (r.getClass() != this.getClass())) {
            return false;
        }
        RatNum ratnum = (RatNum) r;
        return this.getDenominator() == ratnum.getDenominator() && this.getNumerator() == ratnum.getNumerator();
    }

    /**
     * returns boolean of the number less than r
     */
    public boolean lessThan(RatNum r) {
        return (float) this.getNumerator() / this.getDenominator() < (float) r.getNumerator() / r.getDenominator();
    }

    /**
     * returns addition of r to the number
     */
    public RatNum add(RatNum r) {
        return new RatNum(this.getNumerator() * r.getDenominator() + r.getNumerator() * this.getDenominator(),
                this.getDenominator() * r.getDenominator());
    }

    /**
     * returns subtraction of r to the number
     */
    public RatNum sub(RatNum r) {
        return new RatNum(this.getNumerator() * r.getDenominator() - r.getNumerator() * this.getDenominator(),
                this.getDenominator() * r.getDenominator());
    }

    /**
     * returns multiplication of r with the number
     */
    public RatNum mul(RatNum r) {
        return new RatNum(this.getNumerator() * r.getNumerator(), this.getDenominator() * r.getDenominator());
    }

    /**
     * returns division of the number with r
     */
    public RatNum div(RatNum r) {
        return new RatNum(this.getNumerator() * r.getDenominator(), this.getDenominator() * r.getNumerator());
    }

    /**
     * returns string representation of the number
     */
    public String toIntString() {
        return (Integer.toString(this.getNumerator() / this.getDenominator()));
    }

    /**
     * returns greatest comon divider of two integers, m and n
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
