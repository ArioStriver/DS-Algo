/*
Implement pow(x, n) % d.
In other words, given x, n and d,

find (xn % d)

Note that remainders on division cannot be negative. In other words, make sure the answer you return is non negative.

Problem Constraints
-109 <= x <= 109
0 <= n <= 109
1 <= d <= 109

Example Input
x = 2
n = 3
d = 3

Example Output
2

Example Explanation
2^3 % 3 = 8 % 3 = 2.


METHOD 1:(BRUTE FORCE)

TIME: O(N).

SPACE: O(1).
*/

public class Solution {
    int M;

    private long mod(long a) {
        return ((a % M + M) % M);
    }
    
    private long mul(long a, long b) {
        return mod(mod(a) * mod(b));
    }

    public int pow(int x, int n, int d) {

        M = d;
        long ans = 1;

        if(x == 0) return 0;

        if(n < 0) n *= -1;
        
        for(int i = 1; i <= n; i++) {
            ans = mul(ans, (long)x);
        } 

        if(n < 0) ans = mod((long)(1 / ans));

        return (int)ans;
    }
}


/*
METHOD 2: (Binary Exponentiation)(IF THE POWER IS POSITIVE)

TIME: O(logN), where n is the power.

SPACE: O(1).
*/

public class Solution {
   
    int M;

    private long mod(long a) {
        return ((a % M + M) % M);
    }
    
    private long mul(long a, long b) {
        return mod(mod(a) * mod(b));
    }

	public int pow(int x, int n, int d) {

        M = d;

        // 0 to the power of something is always zero
        if(x == 0) return 0;
        
        long res = 1, a = x;
        int y = n;

        while(y > 0){

            // if the power is odd
            if(y % 2 == 1) {
                res = mul(res, a);
                y = y - 1;         // making it even
            }
            // power is even
            else {
                a = mul(a, a);
                y = y / 2;
            }
        }

        return (int)res;
	}
}


// BUT IF THE POWER IS NEGATIVE


public class Solution {
   
    int M;

    private long mod(long a) {
        return ((a % M + M) % M);
    }
    
    private long mul(long a, long b) {
        return mod(mod(a) * mod(b));
    }

	public int pow(int x, int n, int d) {

        M = d;

        // 0 to the power of something is always zero
        if(x == 0) return 0;
        
        long res = 1, a = x;
        int y = n;
	
	  // if power is negative
	  if(y < 0) {
		y = -1 * y
	  }

        while(y > 0){

            // if the power is odd
            if(y % 2 == 1) {
                res = mul(res, a);
                y = y - 1;         // making it even
            }
            // power is even
            else {
                a = mul(a, a);
                y = y / 2;
            }
        }
	  
	  // power is negative
        if(n < 0) {
            res = mod((long)(1 / res));
        }

        return (int)res;
	}
}