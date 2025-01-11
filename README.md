# S4A Airlines application

The **S4A Airlines Application** is designed to manage the operations of an airline's fleet. It offers several key features:
- calculating total available aircraft seats - You can query the total number of available seats for specified routes range and day,
- withdrawing planes - planes can be withdrawn from flight on since specified day,
- assigning/changing new aircraft max seat amount - new amount of aircraft seats can be set for withdrawn airplane or this number can be changed for particular day.


 
### Launching application
Application entry point is located in `main` method in [**S4AAirlinesApplication**](src/main/java/pl/smart4aviation/S4AAirlinesApplication.java) file. \
Run `./gradlew run` in the root folder.

### Exceptions handling
If an invalid number of parameters are provided then `ImproperNumberException` is thrown. \
If picked invalid operation then `UnsupportedOperationException` is thrown.




### Segment Tree data structure
A Segment Tree allows efficient querying and updating segments in lists or arrays in **O(log(n))** time both for range queries and single updates. \
In this case, it constructs Segment Tree based on the passed `ArrayList` which represents initial data. Segment Tree itself is stored as a `List` where each element holds a sum for specific segment of the original data. \
For the list: `[1, 2, 3, 4]` corresponding Segment Tree will look like `[10, 3, 7, 1, 2, 3, 4]`. It is a Binary Tree so left child of parent is at index `2*idx+1` and right child at index `2*idx+2`.

```
          [10]      <-- Root node (sum of all values)
        /      \
      [3]      [7]   <-- Nodes (sum of sublists)
     /   \    /   \
   [1]   [2] [3]  [4]  <-- Leaf nodes (elements in original list)
```