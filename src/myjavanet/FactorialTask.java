package myjavanet;


public class FactorialTask implements Task {
    int num;

    public FactorialTask(int n) {
        num = n;
    }

    @Override
    public Object execute() {
        return factorial(num);
    }

    public long factorial (long n) {
        if ( n <= 1 ) {
            return 1;
        }
        return n*factorial(n);
    }
}
