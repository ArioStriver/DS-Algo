/*
Implement the RandomizedSet class:

RandomizedSet() Initializes the RandomizedSet object.
bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the same 
probability of being returned.
You must implement the functions of the class such that each function works in average O(1) time complexity.

Example 1:

Input
["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
[[], [1], [2], [2], [], [1], [2], []]

Output
[null, true, false, true, 2, true, false, 2]

Explanation
RandomizedSet randomizedSet = new RandomizedSet();
randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
randomizedSet.getRandom(); // getRandom() should return either 1 or 2 randomly.
randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
randomizedSet.insert(2); // 2 was already in the set, so return false.
randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom() will always return 2.


METHOD:

TIME: O(1) for the all the methods.
*/

class RandomizedSet {

    ArrayList<Integer> array;
    Map<Integer, Integer> map;
    
    public RandomizedSet() {
        array = new ArrayList<>();
        map = new HashMap<>();
    }
    
    public boolean insert(int val) {
        
        // if the element is present return false
        if(map.containsKey(val)) return false;
        
        // otherwise adding the current element into the array and the map
        array.add(val);
        map.put(val, array.size()-1);
        
        return true;
    }
    
    public boolean remove(int val) {
        
        // if not present then return false
        if(!map.containsKey(val)) return false;
        
        // otherwise first get the index of the removed element 
        int idx = map.get(val);
        int size = array.size();
        int lastEle = array.get(size-1);       // getting the last element
        Collections.swap(array, idx, size-1);  // swapping the removed element and the last element
        
        // update the position of the last element first bcz may be the 
        // last element is the removed element
        map.put(lastEle, idx);                
        
        // removing the value from the map and array
        array.remove(size-1);                  
        map.remove(val);
        
        return true;
    }
    
    public int getRandom() {
        
        // generating the random value with the array size
        Random rand = new Random();
        int idx = rand.nextInt(array.size());
        
        return array.get(idx);
    }
}