package mytcp;


import java.math.BigInteger;

public class FactorialTaskA implements Task {
    int num;

    public FactorialTaskA(int n) {
        num = n;
    }

    @Override
    public Object execute() {
        return factorial(num);
    }

    public BigInteger factorial (long n) {
        BigInteger res = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            res = res.multiply(BigInteger.valueOf(i));
        }
        return res;
    }
}
