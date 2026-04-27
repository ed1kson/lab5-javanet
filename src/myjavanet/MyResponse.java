package myjavanet;

public class MyResponse implements Response {

    private Task task;
    private long time;

    MyResponse(Task task) {
        this.task = task;
    }

    @Override
    public Object output() {
        long start = System.nanoTime();
        Object output = task.execute();
        time = System.nanoTime()-start;
        return output;
    }

    @Override
    public long runtime() {
        return time;
    }
}
