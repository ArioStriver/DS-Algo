/*
Given an array, print all subarrays in the array which has sum 0.

Examples: 

Input:  arr = [6, 3, -1, -3, 4, -2, 2, 4, 6, -12, -7]

Output:  
Subarray found from Index 2 to 4
Subarray found from Index 2 to 6          
Subarray found from Index 5 to 6
Subarray found from Index 6 to 9
Subarray found from Index 0 to 10


METHOD 1:(BRUTE FORCE)
	APPROACH:
		A simple solution is to consider all subarrays one by one and check if sum of every subarray is equal to 0 or not

TIME: O(N^2).

SPACE: O(1).


METHOD 2:(USING HASHING)

TIME: O(N).

SPACE: O(N).
*/


class Pair {
    int start, end;
    
    Pair(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class Solution{
    //Function to count subarrays with sum equal to 0.
    public static long findSubarray(long[] arr ,int n) 
    {
        
        Map<Long, ArrayList<Integer>> map = new HashMap<>();
        
        // create an empty array of pairs to store subarray starting and ending index
        ArrayList<Pair> result = new ArrayList<>();
        
        // Maintains sum of elements so far
        long sum = 0;
        
        for(int i = 0; i < n; i++) {
            
            // add current element to sum
            sum += arr[i];
            
            // if sum is 0, we found a subarray starting
            // from index 0 and ending at index i
            if(sum == 0) {
                result.add(new Pair(0, i));
            }
            
            ArrayList<Integer> li = new ArrayList<>();
            
            // If sum already exists in the map there exists at-least one subarray ending at index i with 0 sum
            if(map.containsKey(sum)) {
                
                // map[sum] stores starting index of all subarrays
                li = map.get(sum);
                
                // finding the subarrays
                for(int j = 0; j < li.size(); j++) {
                    
                    // here we will store the starting and ending index of those subrrays 
                    // which will give us the sum as 0
                    result.add(new Pair(li.get(j) + 1, i));
                }
            }
            
            // store the end index of the current sum in a list
            // now why list ?? bcz it may occur in the future so for that we need the last inedx at which 
            // this summ occured to find the subarray
            li.add(i);
            map.put(sum, li);
        }
        
        return result.size();
    }
}