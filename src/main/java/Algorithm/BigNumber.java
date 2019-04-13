package Algorithm;


import java.util.LinkedList;

public class BigNumber
{
    private LinkedList<Integer> numList;

    public BigNumber()
    {
        this.numList = new LinkedList<Integer>();
    }

    public BigNumber(String x)
    {
        this.numList = new LinkedList<>();
        boolean isNegative = false;
        if(x.startsWith("-"))
        {
            isNegative = true;
            x = x.substring(1);
        }
        while(x.startsWith("0") && x.length() > 1)
        {
            x = x.substring(1);
        }
        for(int i = 0; i < x.length(); i++)
        {
            numList.add(Character.getNumericValue(x.charAt(i)));
        }

        if(!isNegative)
        {
            numList.addFirst(0);
        }
        else
        {
            numList = tensComplement(numList);
            numList.addFirst(9);
        }

    }

    public BigNumber add(BigNumber bn)
    {
        LinkedList<Integer> addition = new LinkedList<>();
        StringBuilder s = new StringBuilder();
        LinkedList<Integer> x = new LinkedList<>();
        LinkedList<Integer> y = bn.toList();
        int carry = 0;

        if(numList.size() == y.size())
        {
            x = fill(numList, 1);
            y = fill(y, 1);
        }
        else if(y.size() >= numList.size())
        {
            x = fill(numList, y.size() - numList.size() + 1);
            y = fill(y, 1);
        }
        else
        {
            y = fill(y, numList.size() - y.size() + 1);
            x = fill(numList, 1);
        }


        for(int i = x.size() - 1; i >= 0; i--)
        {
            int temp = 0;

            temp = x.get(i) + y.get(i) + carry;

            if(temp > 9)
            {
                carry = 1;
                temp -= 10;
            }
            else
            {
                carry = 0;
            }
            addition.addFirst(temp);
        }

        if(addition.get(0) > 4)
        {
            addition = tensComplement(addition);
            for(int k = 1; k < addition.size(); k++)
            {
                s.append(addition.get(k));
            }
            s.insert(0, "-");
        }
        else
        {
            for(int k = 0; k < addition.size(); k++)
            {
                s.append(addition.get(k));
            }
        }

        return new BigNumber(s.toString());
    }

    private LinkedList<Integer> fill(LinkedList<Integer> num, int amount)
    {
        LinkedList<Integer> temp = new LinkedList<>();
        for(Integer item : num)
        {
            temp.add(item);
        }

        int fill = temp.get(0) < 5 ? 0 : 9;

        for(int i = 0; i < amount; i++)
        {
            temp.addFirst(fill);
        }

        return temp;
    }

    public BigNumber multiply(BigNumber y) {
        BigNumber product = new BigNumber("0");
        BigNumber in = this;
        BigNumber param = y;

        if (y.compareTo(product) == 0 || this.compareTo(product) == 0) {
            return new BigNumber("0");
        }

        if(in.numList.get(0) != 0)
            in = in.negate();
        if(param.numList.get(0) != 0)
            param = param.negate();

        int jc = 0;
        int ic = 0;
        for(int i = in.numList.size() - 1; i > 0; i--) {
            for(int j = param.numList.size() - 1; j > 0; j--) {
                if(in.numList.get(i) != 0 && param.numList.get(j) != 0) {
                    int tNum = in.numList.get(i) * param.numList.get(j);
                    String zeros = "";
                    for(int k = 0; k < jc + ic;k++)
                        zeros = zeros +  "0";
                    String temp = tNum + zeros;
                    BigNumber tempBN = new BigNumber(temp);
                    product = product.add(tempBN);
                }
                jc++;
            }
            jc = 0;
            ic++;
        }

        if(this.numList.get(0) == 0 && y.numList.get(0) != 0)
            return product.negate();
        if(this.numList.get(0) != 0 && y.numList.get(0) == 0)
            return product.negate();

        return product;
    }


    public BigNumber subtract(BigNumber y)
    {
        if (y.compareTo(new BigNumber("0")) == 0) {
            return this;
        } else if (this.compareTo(new BigNumber("0")) == 0) {
            return new BigNumber("-" + y.toString());
        }
        return (add(y.negate()));
    }

    public BigNumber getMod(BigNumber y){
        BigNumber rem = this;
        BigNumber div = y;

        while(rem.compareTo(div) >= 0) {
            String zeros = "";
            for(int k = 0; k < rem.numList.size() - div.numList.size() - 1; k++)
                zeros = zeros +  "0";
            String temp = 1 + zeros;
            BigNumber tempBN = new BigNumber(temp);
            rem = rem.subtract(div.multiply(tempBN));
        }
        return rem;
    }

    public BigNumber negate()
    {

        StringBuilder s = new StringBuilder();

        if(numList.get(0) == 0)
        {
            s.append("-");
            for(int i = 1; i < numList.size(); i++)
            {
                s.append(numList.get(i));
            }
        }
        else
        {
            LinkedList<Integer> temp = tensComplement(numList);
            for(int i = 1; i < temp.size(); i++)
            {
                s.append(temp.get(i));
            }
        }

        return new BigNumber(s.toString());
    }

    private LinkedList<Integer> tensComplement(LinkedList<Integer> num)
    {
        LinkedList<Integer> temp = new LinkedList<>();
        for(Integer item : num)
        {
            temp.add(item);
        }

        boolean trailingZero = true;

        for(int i = temp.size() - 1; i >= 0; i--)
        {
            if(temp.get(i) == 0)
            {
                if(!trailingZero)
                {
                    temp.set(i, 9);
                }
            }
            else
            {
                temp.set(i, (trailingZero ? 10 : 9) - temp.get(i));
                trailingZero = false;
            }
        }
        return temp;
    }

    public LinkedList<Integer> toList()
    {
        return this.numList;
    }

    public String toString()
    {
        StringBuilder s = new StringBuilder();
        LinkedList<Integer> temp = numList;

        if(numList.get(0) == 9)
        {
            temp = tensComplement(temp);
            s.append("-");
        }

        for(int i = 1; i < temp.size(); i++)
        {
            s.append(temp.get(i));
        }

        return s.toString();
    }

    public int compareTo(BigNumber y)
    {
        LinkedList<Integer> numY = y.toList();

        int xSign = sign(), ySign = y.sign();
        int xLen = numList.size(), yLen = numY.size();
        if(xSign != ySign)
        {
            if(xSign > ySign)
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
        else if(xLen != yLen)
        {
            if(xLen > yLen)
            {
                if(xSign == -1)
                {
                    return -1;
                }
                return 1;
            }
            else
            {
                if(ySign == -1)
                {
                    return 1;
                }
                return -1;
            }
        }
        else
        {
            for(int i = 1; i < xLen; i++)
            {
                if(numList.get(i) > numY.get(i))
                {
                    if(xSign == 1)
                    {
                        return 1;
                    }
                    return -1;
                }
                else if(numList.get(i) < numY.get(i))
                {
                    if(xSign == 1)
                    {
                        return -1;
                    }
                    return 1;
                }
            }
            return 0;
        }
    }

    public int sign()
    {
        if(numList.size() == 2 && numList.get(1) == 0)
        {
            return 0;
        }
        return (numList.get(0) < 5 ? 1 : -1);
    }

    public BigNumber divide(BigNumber y) {
        BigNumber quotient = new BigNumber("0");
        BigNumber rem = this;
        BigNumber div = y;

        if(rem.numList.get(0) != 0)
            rem = rem.negate();
        if(div.numList.get(0) != 0)
            div = div.negate();

        while(rem.compareTo(div) >= 0) {
            String zeros = "";
            for(int k = 0; k < rem.numList.size() - div.numList.size() - 1; k++)
                zeros = zeros +  "0";
            String temp = 1 + zeros;
            BigNumber tempBN = new BigNumber(temp);
            rem = rem.subtract(div.multiply(tempBN));
            quotient = quotient.add(tempBN);
        }

        if(this.numList.get(0) == 0 && y.numList.get(0) != 0)
            return quotient.negate();
        if(this.numList.get(0) != 0 && y.numList.get(0) == 0)
            return quotient.negate();

        return quotient;
    }

    public BigNumber gcd(BigNumber y) {
        BigNumber rem;
        BigNumber a = this;
        while (!y.equals(new BigNumber("0"))){
            rem = a.getMod(y);
            a = y;
            y = rem;
        }
        return a;
    }

    public boolean equals(BigNumber y)
    {
        LinkedList<Integer> numY = y.toList();

        // If the two numbers do not have the same number of digits, then they are not equal
        // The constructor trims any leading zeroes in the user's input so that
        // non-significant leading zeroes (ie. ones that do not correspond to sign) do not
        // have an impact on the length of numList. Else 00900 would not be equal to 0900, but
        // it technically should be.
        if(numY.size() == numList.size())
        {
            for(int i = 0; i < numList.size(); i++)
            {
                if(numList.get(i) != numY.get(i))
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }

        return true;
    }

}

