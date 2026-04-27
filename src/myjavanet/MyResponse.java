package myjavanet;

public class MyResponse implements Response {

    private Object output;
    private long time;

    MyResponse(Task task) {
        long start = System.nanoTime();
        Object output = task.execute();
        time = System.nanoTime()-start;
    }

    @Override
    public Object output() {
        return output;
    }

    @Override
    public long executionTime() {
        return time;
    }
}
