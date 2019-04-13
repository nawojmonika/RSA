package Algorithm;

import com.sun.corba.se.impl.logging.OMGSystemException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BigNumberTest {

    @Test
    void add() {
        BigNumber bn1 = new BigNumber("400");
        BigNumber bn2 = new BigNumber("500");

        Assert.assertEquals("900", bn1.add(bn2).toString());
    }

    @Test
    void multiply() {
    }

    @Test
    void subtract() {


    }

    @Test
    void divide() {
    }

    @Test
    void gcd() {
            System.out.println(new BigNumber(Integer.toString(17)).gcd(new BigNumber("1219387143609325096783875126955856337631934037112052758452417855221503908900")));
    }
}