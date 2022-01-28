//Q. FROG JUMP WITH K DISTANCES

//There are N stones, numbered 1,2,…,N. For each i (1 ≤ i≤ N), the height of Stone i is hi.

//There is a frog who is initially on Stone 1. He will repeat the following action some number of times to reach Stone N:

//If the frog is currently on Stone i, jump to one of the following: Stone i+1,i+2,…,i+K. Here, a cost of ∣hi −hj∣ is incurred, where j is the stone to land on.
//Find the minimum possible total cost incurred before the frog reaches Stone N.

 
//Input
//Input is given from Standard Input in the following format:

//N K
//h1 h2 … hN
​
// Output
// Print the minimum possible total cost incurred.

// Sample Input 1 

// 5 3
// 10 30 40 50 20

// Sample Output 1 

// 30
// If we follow the path 1 → 2 → 5, the total cost incurred would be ∣10−30∣+∣30−20∣=30.

//Sample Input 2 

// 3 1
// 10 20 10

//Sample Output 2 

//20
//If we follow the path 1 → 2 → 3, the total cost incurred would be ∣10−20∣+∣20−10∣=20.

//Sample Input 3 

//2 100
//10 10

//Sample Output 3 

//0
//If we follow the path 1 → 2, the total cost incurred would be ∣10−10∣=0.

//Sample Input 4 

//10 4
//40 10 20 70 80 10 20 70 80 60

//Sample Output 4 

//40
//If we follow the path 1 → 4 → 8 → 10, the total cost incurred would be ∣40−70∣+∣70−70∣+∣70−60∣=40.


// METHOD 1:(USING RECURSION)

class Main{
  
  int minCost(int[] a, int index, int K){
    
    if(index == 0) return 0;
    
    int minSteps = Integer.MAX_VALUE;

    for(int i = 1; i <= K; i++){
      
      // out of boundary check
      if(index - i >= 0){
        int jump = minCost(a, index - i, K) + Math.abs(a[index] - a[index-i]);
        
        minSteps = Math.min(minSteps, jump);
      }
    }
    return minSteps;
  }
  
  int minimumCostForJump(int[] heights, int N, int K){
    
    return minCost(heights, N-1, K);
  }
}


// METHOD 2:(USING MEMOIZATION)

// TIME: O(N * K), bcz for eacg index we are basically looping K times.

// SPACE: O(N) + O(N), first one is for recursion stack and second one is for memoization.


class Main{
  
  int minCost(int[] a, int index, int K, int[] memo){
    
    if(index == 0) return 0;
    
    // previously computed
    if(memo[index] != 0) return memo[index];
    
    int minSteps = Integer.MAX_VALUE;
    for(int i = 1; i <= K; i++){
      
      // out of boundary check
      // if not out of bound
      if(index - i >= 0){
        int jump = minCost(a, index - i, K, memo) + Math.abs(a[index] - a[index-i]);
        
        minSteps = Math.min(minSteps, jump);
      }
    }
    
    memo[index] = minSteps;

    return minSteps;
  }
  
  int minimumCostForJump(int[] heights, int N, int K){
    
    int[] memo = new int[N];
    return minCost(heights, N-1, K, memo);
  }
}


// METHOD 3:(USING DYNAMIC PROGRAMMING)

// TIME: O(N * K), bcz for eacg index we are basically looping K times.

// SPACE: O(N), N is the length of the array.


class Main{
 
  int minimumCostForJump(int[] a, int N, int K){
    
    int[] dp = new int[N];
     
    // base case
    // bcz from 0th to 0th there is 0 distance
    dp[0] = 0;
    
    for(int index = 1; index < N; index++){
      
      // initializing min steps
      int minSteps = Integer.MAX_VALUE;
      
      // for each index we are taking K jumps
      for(int j = 1; j <= K; j++){
        
        if(index - j >= 0){
          int jump = dp[index - j] + Math.abs(a[index] - a[index - j]);
          minSteps = Math.min(minSteps, jump);
        }
      }
      dp[index] = minSteps;
    }
    
    return dp[N-1];
  }
}
