//package model.util.javaHeapTracker;

/*
 * Example of how to use the Java live heap tracker
 *
 * @author godmar@gmail.com for CS 3214 Fall 2014
 */
public class TrackerSample
{
    public static void main(String []av) throws Exception {
	
	int [][] a = new int[4][];
	//HeapTracker.startTrace(); // start tracing
	//HeapTracker.takeLiveHeapSample(); // take sample
	a[0] = new int[50000];
	//HeapTracker.takeLiveHeapSample(); // take sample
	Thread.sleep(100);
	a[1] = new int[20000];
	//HeapTracker.takeLiveHeapSample(); // take sample
	Thread.sleep(100);
	a[2] = new int[10000];
	//HeapTracker.takeLiveHeapSample(); // take sample
	Thread.sleep(100);
	a[3] = new int[5000];
	//HeapTracker.takeLiveHeapSample(); // take sample
	//HeapTracker.stopTrace(); // stop tracing
   
    }
}
