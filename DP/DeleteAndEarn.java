/*
Delete and Earn(Variation of House Robber)

You are given an integer array nums. You want to maximize the number of points you get by performing the following operation any number of times:

Pick any nums[i] and delete it to earn nums[i] points. Afterwards, you must delete every element equal to nums[i] - 1 and every element equal to nums[i] + 1.
Return the maximum number of points you can earn by applying the above operation some number of times.

Example 1:

Input: nums = [3,4,2]
Output: 6

Explanation: You can perform the following operations:
- Delete 4 to earn 4 points. Consequently, 3 is also deleted. nums = [2].
- Delete 2 to earn 2 points. nums = [].
You earn a total of 6 points.


Example 2:

Input: nums = [2,2,3,3,3,4]
Output: 9

Explanation: You can perform the following operations:
- Delete a 3 to earn 3 points. All 2's and 4's are also deleted. nums = [3,3].
- Delete a 3 again to earn 3 points. nums = [3].
- Delete a 3 once more to earn 3 points. nums = [].
You earn a total of 9 points.


METHOD 1:(USING RECURSION)
	APPROACH:
		Here first we sort the array. Why bcz it will hlep us to eleminate one of the given condition . Then we count the frequncy of each number. By counting 
		the frequency of each number it will automatically help us to consider to all the instances of the current number and also help us to eleminate all the 
		instances of the (element + 1) or (element - 1).

TIME: O(2^N), as each number has two choices pick ot not pick.

SPACE: O(N), at worst case.
*/

class Solution {
    public int deleteAndEarn(int[] nums) {
        
        int n = nums.length;
        
        // sorting the array
        // if we sort the array then we can easily eliminate one of the given condition
        // that is eliminate (num[i] + 1) or (nums[i] - 1)
        Arrays.sort(nums);
        
        // extract the maximum so that we can able to declare the size of the array
        int max = nums[n-1];
        
        int[] freq = new int[max+1];
            
        for(int i : nums){
            freq[i]++;
        }
        
        return maxPointsEarned(freq, max);
    }
    
    private int maxPointsEarned(int[] freq, int n){
        
        // base case
        if(n <= 0) return 0;
        
        // we have two options either we can pick the current element or we can skip 
        // if we pick the current element then we have to pick all its instances if any exists there
        // and then move to (n - 2) element why? bcz that's how we ignore the all the 
        // instances of (nums[i] - 1) if we pick the current element
        int pick = n * freq[n] + maxPointsEarned(freq, n - 2);
        
        // not picking the current element
        int notPick = maxPointsEarned(freq, n - 1);
        
        return Math.max(pick ,notPick);
    }
}

/*
METHOD 2:(USING MEMOIZATION)

TIME: O(N) + O(N) == O(N).

SPACE: O(N).
*/

class Solution {
    public int deleteAndEarn(int[] nums) {
        
        int n = nums.length;
        
        Arrays.sort(nums);
        
        // extract the maximum so that we can able to declare the size of the array
        int max = nums[n-1];
        
        int[] memo = new int[max+1];
        Arrays.fill(memo, -1);
        
        int[] freq = new int[max+1];
            
        for(int i : nums){
            freq[i]++;
        }
        
        return maxPointsEarned(freq, max, memo);
    }
    
    private int maxPointsEarned(int[] freq, int n, int[] memo){
        
        // base case
        if(n <= 0) return 0;
        
        if(memo[n] != -1) return memo[n];
        
        
        int pick = n * freq[n] + maxPointsEarned(freq, n - 2, memo);
        
        int notPick = maxPointsEarned(freq, n - 1, memo);
        
        return memo[n] = Math.max(pick ,notPick);
    }
}

/*
METHOD 3:(USING TABULATION METHOD)

TIME: O(N) + O(N) == O(N).

SPACE: O(N).
*/

class Solution {
    public int deleteAndEarn(int[] nums) {
        
        int n = nums.length;
        
        int[] freq = new int[10001];
        int maxNum = -1;
            
        for(int i : nums){
            freq[i]++;
            maxNum = Math.max(maxNum, i);
        }
        
        int[] dp = new int[10001];
        dp[0] = 0;
        
        for(int i = 1; i <= maxNum; i++){
            
            int pick = i * freq[i];
            
            if(i > 1) pick += dp[i-2];
            
            int notPick = dp[i-1];
            
            dp[i] = Math.max(pick, notPick);
        }
        
        return dp[maxNum];
    }
}

/*
METHOD 4:(SPACE OPTIMIZED)

TIME: O(N).

SPACE: O(1).
*/

class Solution {
    public int deleteAndEarn(int[] nums) {
        
        int[] dp = new int[10001];
        int maxNum = -1;
        
        // storing the frequency of each number
        for(int i : nums){
            dp[i]++;
            
            // finding the maximum num
            maxNum = Math.max(maxNum, i);
        }
        
        // prev1 : dop[i-1]
        // prev2 : dp[i-2]
        int prev1 = 0, prev2 = 0;
        
        // traverse form 1 to upto maximum number in the array
        for(int i = 1; i <= maxNum; i++){
            
            int pick = i * dp[i] + prev2;
            
            int notPick = prev1;
            
            int curi = Math.max(pick, notPick);
            
            prev2 = prev1;
            prev1 = curi;
        }
        
        return prev1;
    }
}