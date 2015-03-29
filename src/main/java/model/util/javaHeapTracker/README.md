# Java Heap Tracker	

## Why

The files in this folder provide an example of how to use 
a Java heap tracker. Why use a heap tracker?

In Java, a heap stores your objects that you create with
the keyword "new". For example: 
`Neuron neuron1 = new Neuron();`

When you are creating a lot of objects in our case when we are
trying to create a `NervousSystem` object with many neurons and
connections sometimes we need more space than our heap 
currently has. 

A heap tracker is useful to find out exactly how large we 
need our heap to be so that we can create a heap that is just
big enough for our code to run.

To learn more read this [stackoverflow post](http://stackoverflow.com/questions/79923/what-and-where-are-the-stack-and-heap)

## What

The heap tracker we are using is on Virginia Tech's rlogin
clustor provided to all of their computer science students.

## How to run code

1. ssh into rlogin 
2. Navigate into this folder with `cd`
3. Run example with:
   ```javac -cp /home/courses/cs3214/bin/heaptracker/heapTracker.jar:. 
   	    TrackerSample.java runtracker TrackerSample
   ```
4. Create plot of use of heap with:
	   

