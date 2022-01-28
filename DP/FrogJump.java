//Q. Frog Jump
// There is a frog on the 1st step of an N stairs long staircase. The frog wants to reach the Nth stair. HEIGHT[i] is the height of the (i+1)th stair.If Frog jumps from ith to jth stair, the 
// energy lost in the jump is given by |HEIGHT[i-1] - HEIGHT[j-1] |.In the Frog is on ith staircase, he can jump either to (i+1)th stair or to (i+2)th stair. Your task is to find the minimum 
// total energy used by the frog to reach from 1st stair to Nth stair.

// INPUT: 
// 2
// 4

// 10 20 30 10
// 3
// 10 50 10

// Sample Output 1:
// 20
// 0

// Explanation Of Sample Input 1:
// For the first test case,
// The frog can jump from 1st stair to 2nd stair (|20-10| = 10 energy lost).
// Then a jump from the 2nd stair to the last stair (|10-20| = 10 energy lost).
// So, the total energy lost is 20 which is the minimum. 
// Hence, the answer is 20.

// For the second test case:

// The frog can jump from 1st stair to 3rd stair (|10-10| = 0 energy lost).
// So, the total energy lost is 0 which is the minimum. 
// Hence, the answer is 0.

// Sample Input 2:

// 2
// 8
// 7 4 4 2 6 6 3 4 
// 6
// 4 8 3 10 4 4 

// Sample Output 2:
// 7
// 2


//METHOD 1:(USING RECURSION)

public class Solution {
    
    private static int minCost(int index, int[] heights){
        
        // base case
        if(index == 0) return 0;
        
        int left  = minCost(index - 1, heights)  + Math.abs(heights[index] - heights[index-1]);
        
        int right = Integer.MAX_VALUE;
        
	// only if the current index is greater than 1 otherwise it will give out of bound exception
        if(index > 1)
            right = minCost(index - 2, heights)  + Math.abs(heights[index] - heights[index-2]);
        
        return Math.min(left, right);
    }
    
    public static int frogJump(int n, int heights[]) {
		
        return minCost(n - 1, heights);
    }
}


//METHOD 2:(USING MEMOIZATION)

public class Solution {
    
    private static int minCost(int index, int[] heights, int[] memo){
        
        // base case
        if(index == 0) return 0;
        
        // check whether the value id previously computed or not
        if(memo[index] != -1)
            return memo[index];
        
        int left  = minCost(index - 1, heights, memo)  + Math.abs(heights[index] - heights[index-1]);
        
        int right = Integer.MAX_VALUE;
        
        if(index > 1)
            right = minCost(index - 2, heights, memo)  + Math.abs(heights[index] - heights[index-2]);
        
        return memo[index] = Math.min(left, right);
    }
    
    public static int frogJump(int n, int heights[]) {
		
        int[] memo = new int[heights.length + 1];
        
	// filling the array with -1
        Arrays.fill(memo, -1);
        
        return minCost(n - 1, heights, memo);
    }
}


//METHOD 3:(USING DYNAMIC PROGRAMMING)

// TIME: O(N).

// SPACE: O(N).

public class Solution {
  
    public static int frogJump(int n, int heights[]) {
        
        int[] dp = new int[n];
        
        //base case
        dp[0] = 0;
        
        // traversing the array
        for(int i = 1; i < n; i++){
            
            int left = dp[i-1] + Math.abs(heights[i] - heights[i-1]);
            
            int right = Integer.MAX_VALUE;
            
            if(i > 1) right = dp[i-2] + Math.abs(heights[i] - heights[i-2]);
            
            dp[i] = Math.min(left, right);
        }
        
        return dp[n-1];
    }
}


//METHOD 4:(SPACE OPTIMIZED)

// TIME: O(N).

// SPACE: O(1).

public class Solution {
  
    public static int frogJump(int n, int heights[]) {
        
        //base case
        // first previous and second previous
        int prev1 = 0, prev2 = 0;
        
        // traversing the array
        for(int i = 1; i < n; i++){
            
            int left = prev1 + Math.abs(heights[i] - heights[i-1]);
            
            int right = Integer.MAX_VALUE;
            
            if(i > 1) right = prev2 + Math.abs(heights[i] - heights[i-2]);
            
            // current minimum
            int current_min = Math.min(left, right);
            prev2 = prev1;
            prev1 = current_min;
        }
        
        return prev1;
    }
}
