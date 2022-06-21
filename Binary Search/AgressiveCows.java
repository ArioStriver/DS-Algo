/*

Given an array of length ‘N’, where each element denotes the position of a stall. Now you have ‘N’ stalls and an integer ‘K’ which denotes the number of cows that are aggressive. To prevent 
the cows from hurting each other, you need to assign the cows to the stalls, such that the minimum distance between any two of them is as large as possible. Return the largest minimum 
distance.

i) Input: No of stalls = 5 
       Array: {1,2,8,4,9}
       And number of cows: 3

Output: One integer, the largest minimum distance 3

ii) Input: No of stalls = 6 
	 Array: {0 3 4 7 10 9}
	 cows: 4

Output: 3

iii) Input: No of stalls = 6 
		Array: {0 4 3 7 10 9}
		cows: 3

Output: 4


METHOD 1:(BRUTE FORCE)

TIME: O(N * M), M is the Max Distance.

SPACE: O(1).
*/

public class Solution 
{
    private static boolean canPlaceCows(ArrayList<Integer> stalls, int dist, int cows) {
        
        int k = stalls.get(0);   // placing the cow1 at the 0th index
        cows--;                 // bcz first cow is already placed
        
        // travers for the rest of the cows
        for(int i = 1; i < stalls.size(); i++) {
            // if the disatnce between two cow is atleast dist, then that is acceptable
            if(stalls.get(i) - k >= dist) {
                cows--;
                
                if(cows == 0) {
                    return true;
                }
                k = stalls.get(i);    // updating the new cow position
            }
        }
        
        return false;
    }
    
    public static int aggressiveCows(ArrayList<Integer> stalls, int k) 
    {
        int n = stalls.size();
        int ans = 0;
        
        Collections.sort(stalls);
        int maxDis = stalls.get(n-1) - stalls.get(0);
        
        // increasing the distance one by one
        for(int d = 1; d <= maxDis; d++) {
            boolean possible = canPlaceCows(stalls, d, k);
            
            if(possible) {
                ans = Math.max(ans, d);
            }
        }
        
        return ans;
    }
}


/*
METHOD 2:(BINARY SEARCH)

TIME: O(N * log(M)). For BS the search space is M, O(log(M)) & for each search we iterate over max N stalls to check O(N).

SPACE: O(1).
*/

public class Solution 
{
    private static boolean canPlaceCows(ArrayList<Integer> stalls, int dist, int cows) {
        
        int lastPlacedCow = stalls.get(0);   // placing the cow1 at the 0th index
        cows--;                 // bcz first cow is already placed
        
        // travers for the rest of the cows
        for(int i = 1; i < stalls.size(); i++) {
            // if the disatnce between two cow is atleast dist, then that is acceptable
            if(stalls.get(i) - lastPlacedCow >= dist) {
                cows--;
                
                if(cows == 0) {
                    return true;
                }
                lastPlacedCow = stalls.get(i);    // updating the new cow position
            }
        }
        
        return false;
    }
    
    public static int aggressiveCows(ArrayList<Integer> stalls, int k) 
    {
        int n = stalls.size();
        int ans = 0;
        
        Collections.sort(stalls);
        int maxDis = stalls.get(n-1) - stalls.get(0);
        
        // increasing the distance one by one
        int low = 1, high = maxDis, res = -1;
        
        while(low <= high) {
            
            int mid = low + (high - low) / 2;
            
            if(canPlaceCows(stalls, mid, k)) {
                res = mid;
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }
        
        return res;
    }
}