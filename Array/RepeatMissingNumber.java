/*
Given an unsorted array of size n. Array elements are in the range from 1 to n. One number from set {1, 2, …n} is missing and one number occurs twice in the array. 
Find these two numbers.

Examples: 

Input: arr[] = {3, 1, 3}
Output: Missing = 2, Repeating = 3

Explanation: In the array, 2 is missing and 3 occurs twice 

Input: arr[] = {4, 3, 6, 2, 1, 1}
Output: Missing = 5, Repeating = 1


METHOD 1:(USING SORTING)
	APPROACH:
		1. Sort the array.
		2. Find the missing and the repeating number.


METHOD 2:(USING HASH ARRAY)

TIME: O(2N), bcz O(N) for storing the frequency of each elemnt and another O(N) for finding the missing and the repeating element.

SPACE: O(N).
*/

class Solve {
    int[] findTwoElement(int arr[], int n) {
        
        int[] freq = new int[n+1];
        
        // storing the frequency of each element
        for(int i = 0; i < n; i++){
            freq[arr[i]]++;
        }

        // finding the missing and the repeating element
        int[] res = new int[2];
        
        for(int i = 1; i <= n; i++){

            if(freq[i] == 0){
                res[1] = i;
            }
            
            if(freq[i] > 1){
                res[0] = i;
            }
        }
        
        return res;
    }
}

/*
METHOD 3:
	APPROACH:
		Let x be the missing and y be the repeating element.
		Let N is the size of array.
		Get the sum of all numbers using formula S = N(N+1)/2
		Get the sum of square of all numbers using formula Sum_Sq = N(N+1)(2N+1)/6
		Iterate through a loop from i=1….N
		S -= A[i]
		Sum_Sq -= (A[i]*A[i])
		It will give two equations 
		x-y = S – (1) 
		x^2 – y^2 = Sum_sq 
		x+ y = (Sum_sq/S) – (2) 

TIME: O(N)

SPACE: O(1).
*/

class Solve {
    int[] findTwoElement(int arr[], int n) {
        
        // summation of all numbers from (1 - n) is
        int S = (n * (n + 1) / 2);
        int S2 = (n * (n + 1) * (2 * n + 1)) / 6;
        
        // now we need to subtract the array values one by one from the S and S2
        for(int i = 0; i < n; i++){
            S -= arr[i];
            S2 -= (arr[i] * arr[i]);
        }
        
        // as we can know that 
        // x - y = S
        // x^2 - y^2 = S^2
        // So, (x + y) = S^2 / S
        
        // Now we have 
        // x + y = S^2 / S
        // x - y = S
        // -----------------
        // x = (S + S^2 / S) / 2;
        // y = x - S
        
        int missing_number = ((S + S2 / S) / 2);
        
        int repeating_number = (missing_number - S);
        
        int[] res = new int[2];
        
        res[0] = repeating_number;
        res[1] = missing_number;
        
        return res;
    }
}


/*
METHOD 4:
	
TIME: O(5N) == O(N).

SPACE: O(1).
*/

class Solve {
    int[] findTwoElement(int arr[], int n) {
       
       // s1: first perform the XOR operations on the entire array elements
       int XOR = arr[0];
       
       for(int i = 1; i < n; i++){
           XOR = XOR ^ arr[i];
       }
       
       // s2: Again perform the XOR operations from (1 - N) elements
       
       for(int i = 1; i <= n; i++){
           XOR = XOR ^ i;
       }
       
       // s3: now we need to find the right most set bit index
       int set_bit = XOR & ~(XOR - 1);
       
       /* s4: Now divide elements into two sets by comparing
              rightmost set bit of XOR with the bit at the same 
              position in each element. */
              
        int x = 0;  // missing number
        int y = 0;  // repeating number
        
        for(int i = 0; i < n; i++){
            if((arr[i] & set_bit) != 0){
                x = x ^ arr[i];
            }
            else{
                y = y ^ arr[i];
            }
        }
        
        /* s5: now we need to find the missing and repeating number
        for that we again divide the elements form (1 - n) into 
        two sets and then prerform the XOR operation*/
        for(int i = 1; i <= n; i++){
            
            if((i & set_bit) != 0){
                x = x ^ i;
            }
            else{
                y = y ^ i;
            }
        }
        
        int[] res = new int[2];
        
        for(int a : arr){
            if(a == x){
                res[0] = x;
                res[1] = y;
            }
            if(a == y){
                res[1] = x;
                res[0] = y;
            }
        }
        return res;
    }
}