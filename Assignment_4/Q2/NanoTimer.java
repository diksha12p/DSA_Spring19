/*
    Aim: Measure the running time (nanoseconds)
    Reference : https://introcs.cs.princeton.edu/java/stdlib/Stopwatch.java.html
 */

public class NanoTimer {
    private long start_time;

    NanoTimer(){
        start_time = System.nanoTime();
    }

    public long time_elpased(){
        long end_time = System.nanoTime();
        return (end_time - start_time);
    }
}
