package exercise0;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Radu.Hoaghe on 4/20/2015.
 *
 * Exercise 0: Iterate over the keys of a Map using keySet method (this method returns a Set of all the map keys) and
 *          print all its elements.
 */
public class Exercise0 {

    public Exercise0(){

    }

    public void iterateThroughMap(){

        // TODO Exercise #0 a) Create a Map (HashMap) and add elements to it (using put() method)
        // TODO Exercise #0 a) Hint: Don't forget to specify the types of the key and value when creating the Map
        HashMap<Integer, String> myHashMap = new HashMap<Integer, String>();
        myHashMap.put(1, "Marti");
        myHashMap.put(0, "Luni");
        myHashMap.put(6, "Duminica");
        // TODO Exercise #0 b) Iterate over the Map using keySet() method and print all its elements
        // TODO Exercise #0 b) The elements could be printed like this: [key1=value1, key2=value2, ...]
        //LinkedList<Integer> keysList = myHashMap.keySet();
        System.out.print("[ ");
        for(int key : myHashMap.keySet()) {
            System.out.print(key + "=" + myHashMap.get(key) + " ");
        }
        System.out.print("]");
    }

    public static void main(String[] args) {
        Exercise0 exercise0 = new Exercise0();
        exercise0.iterateThroughMap();
    }
}
