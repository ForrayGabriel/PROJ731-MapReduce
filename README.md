# PROJ731-MapReduce
 
This goal of this project is to create Map-Reduce mecanisme in Java.

The program takes a big text file as an input, parse it and share it between multiple Map threads, each one counting the number of time each word appears, then giving its result to some Reduce threads merging all the result from the Maps

## v1.0 - Working with a boolean with multiple maps and one reduce

**First working version of the project**

This version can read a text, split it, give each part to a thread that count the words and send its result to a single reduce (not a thread for now)
At the end, the program return the processing time calculated between the first launch of a map thread and the end of the reduce. 
For now, it uses a boolean as a semaphor to keep the maps from trying to write at the same time in the reduce (Not good, need to be changed)

_To do : change the map to a thread and stop using a boolean_
