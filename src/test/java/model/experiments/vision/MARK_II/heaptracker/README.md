## Heap Tracker Experiment 1

### Why

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

### How to use above code

`HowMARK_II_FitsInToBrainAnatomy.java` creates the `NervousSystem` 
 and stores the amount of the heap that is used each second in bytes
 and writes it into the file `heapSizeLogData.txt`. After you have
 run `HowMARK_II_FitsInToBrainAnatomy.java`, run 
 `HeaptrakerDataToJavascriptArray.java` to produce the data
 formatted into a Javascript array. 
 
 Next paste your `dataset` variable into the file in the WalnutiQ/toph
 repo [here](https://github.com/WalnutiQ/toph/blob/master/MARK_II/vision/experiment_1/generic_scatter_plot.js)
 and open the file in a browser (only tested with Google Chrome) to 
 view your heap usage. 