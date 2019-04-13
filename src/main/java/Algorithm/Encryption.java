package Algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.StringJoiner;

public class Encryption {
    private BigNumber p = new BigNumber("11");
    private BigNumber q = new BigNumber("17");
    private BigNumber e = new BigNumber("7");
    private BigNumber pq =  p.subtract( new BigNumber("1")).multiply((q.subtract(new BigNumber("1"))));

    public String encrypt(String message) {
        StringJoiner cryptedMessage = new StringJoiner(" ");
        for (int i = 0; i < message.length(); i++) {
            String letterToSend = Integer.toString(message.charAt(i));
            BigNumber M = new BigNumber(letterToSend);
            BigNumber previousM = new BigNumber(letterToSend);
            for (BigNumber exponent = new BigNumber("0"); exponent.compareTo(e.subtract(new BigNumber("1"))) == -1; exponent = exponent.add(new BigNumber("1"))) {
                M = M.multiply(previousM);
            }
            cryptedMessage.add(M.getMod(this.getPublicKey()).toString());
        }

        return cryptedMessage.toString();
    }

    public void setP(BigNumber p) {
        this.p = p;
        this.pq = p.subtract( new BigNumber("1")).multiply((q.subtract(new BigNumber("1"))));
    }

    public void setQ(BigNumber q) {
        this.q = q;
        this.pq = p.subtract( new BigNumber("1")).multiply((q.subtract(new BigNumber("1"))));
    }
    public void setE(BigNumber e) {
        this.e = e;
    }
    public BigNumber getE() {
        return this.e;
    }
    public BigNumber getPublicKey() {
        return p.multiply(q);
    }

    public String decrypt(String message, BigNumber pk, BigNumber ePassed) {
        BigNumber d = modInverseFast(ePassed, this.pq);
        StringJoiner cryptedMessage = new StringJoiner(" ");
        String[] splitedByNumbers = message.split(" ");
        for (int i = 0; i < splitedByNumbers.length; i++) {
            BigNumber C = new BigNumber(splitedByNumbers[i]);
            cryptedMessage.add(modular_pow(C, d, pk).toString());
        }

        return cryptedMessage.toString();
    }

    private BigNumber modInverse(BigNumber a, BigNumber m)
    {
        a = a.getMod(m);
        for (BigNumber x = new BigNumber(a.toString()); x.compareTo(m) == -1; x = x.add(new BigNumber("1"))) {
            if ((a.multiply(x)).getMod(m).compareTo(new BigNumber("1")) == 0)
                return x;
        }
        return new BigNumber("1");
    }

    public static BigNumber modInverseFast(BigNumber a, BigNumber m)
    {
        BigNumber m0 = new BigNumber(m.toString());
        BigNumber y = new BigNumber("0");
        BigNumber x = new BigNumber("1");

        if (m.compareTo(new BigNumber("1")) == 0)
            return new BigNumber("0");
        while (a.compareTo(new BigNumber("1")) == 1 && m.compareTo(new BigNumber("0")) != 0)
        {
            // q is quotient
            // System.out.println("Start division od" + a.toString() + " / " + m.toString() );
            BigNumber q = a.divide(m);
            // System.out.println("End division");

            BigNumber t = new BigNumber(m.toString());


            // System.out.println("Get mod");
            m = a.getMod(m);
            // System.out.println("End get mod");
            a = new BigNumber(t.toString());
            t = new BigNumber(y.toString());

            // System.out.println(x.toString() + " - " + q.toString() + " * " + y.toString() + " = " + x.subtract(q.multiply(y)));
            // System.out.println(q.toString() + " * " + y.toString() + " = " + q.multiply(y));
            // System.out.println(x.toString() + " - " + q.toString() + " = " + x.subtract(q));
            // Update x and y
            // System.out.println("Multiplying");
            y = x.subtract(q.multiply(y));
            // System.out.println("End Multiplying");
            x = new BigNumber(t.toString());
        }
        // System.out.println("End of loop");

        // Make x positive
        if (x.compareTo(new BigNumber("0")) == -1)
            x = x.add(m0);

        return x;
    }

    static int modInverse(int a, int m)
    {
        int m0 = m;
        int y = 0, x = 1;

        if (m == 1)
            return 0;

        while (a > 1)
        {
            // q is quotient
            int q = a / m;

            int t = m;

            // m is remainder now, process
            // same as Euclid's algo
            m = a % m;
            a = t;
            t = y;

            // Update x and y
            int z = x-q*y;
            int mult = q*y;
            int rem = x-q;
            // System.out.println(x + " - " + q + " * " + y + " = " + z);
            // System.out.println(q + " * " + y + " = " + mult);
            // System.out.println(x + " - " + q + " = " + rem);
            y = x - q * y;
            x = t;
        }

        // Make x positive
        if (x < 0)
            x += m0;

        return x;
    }

    BigNumber modular_pow(BigNumber base, BigNumber exponent, BigNumber modulus) {
        BigNumber c = new BigNumber("1");
        for (BigNumber e = new BigNumber("0") ; e.compareTo(exponent) == -1 ; e = e.add(new BigNumber("1"))) {
            // System.out.println(exponent.toString() + " to " + e.toString());
            c = c.multiply(base).getMod(modulus);
        }
        return c;
    }
}
