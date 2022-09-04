/*
In a party of N people, only one person is known to everyone. Such a person may be present in the party, if yes, (s)he doesn’t know anyone in the party. We can only ask questions like 
“does A know B? “. Find the stranger (celebrity) in the minimum number of questions.We can describe the problem input as an array of numbers/characters representing persons in the party. 
We also have a hypothetical function HaveAcquaintance(A, B) which returns true if A knows B, false otherwise. How can we solve the problem. 

Examples:  

Input:
MATRIX = { {0, 0, 1, 0},
           {0, 0, 1, 0},
           {0, 0, 0, 0},
           {0, 0, 1, 0} }

Output:id = 2

Explanation: The person with ID 2 does not know anyone but everyone knows him

Input:
MATRIX = { {0, 0, 1, 0},
           {0, 0, 1, 0},
           {0, 1, 0, 0},
           {0, 0, 1, 0} }

Output: No celebrity

Explanation: There is no celebrity.


METHOD 1:(USING ELIMINATION TECHNIQUE)
	APPROACH:
		There are some observations based on elimination technique (Refer Polya’s How to Solve It book). 

			> If A knows B, then A can’t be a celebrity. Discard A, and B may be celebrity.
			> If A doesn’t know B, then B can’t be a celebrity. Discard B, and A may be celebrity.
			> Repeat above two steps till there is only one person.
			> Ensure the remained person is a celebrity. 

TIME: O(N), n is the No. of elements.

SPACE: O(N).
*/


class Solution
{ 
    boolean knows(int[][] M, int i, int j) {
        
        if(M[i][j] == 1) 
            return true;
            
        return false;
    }
    
    //Function to find if there is a celebrity in the party or not.
    int celebrity(int M[][], int n)
    {
    	Stack<Integer> st = new Stack<>();
    	
    	// 1. First putting all the id's in the stack
    	for(int i = 0; i < n; i++) {
    	    st.push(i);
    	}
    	
    	// 2. Now we will try to find the potential candidate for celebrity
    	while(st.size() > 1) {
    	    
    	    int A = st.pop();
    	    int B = st.pop();
    	    
    	    // if A knows B, it means A can't be celebrity so push back B into the stack
    	    if(knows(M, A, B) == true) {
    	        st.push(B);
    	    }
    	    // otherwise A doesn't know B, so B can't be celebrity
    	    else {
    	        st.push(A);
    	    }
    	}
    	
    	int candidate = st.pop();
    	
    	// 3. now check whether the current potential candidate is really a celebrity or not
    	for(int i = 0; i < n; i++) {
    	    if(i != candidate) {
    	        
    	        // If any person doesn't know candidate or candidate know any person, return -1
    	        if(knows(M, i, candidate) == false || knows(M, candidate, i) == true) 
    	            return -1;
    	    }
    	}
    	
    	return candidate;
    }
}

/*
METHOD 2:(UISNG TWO POINTER)
	APPROACH:
		The idea is to use two pointers, one from start and one from the end. Assume the start person is A, and the end person is B. If A knows B, then A must not be the celebrity. 
		Else, B must not be the celebrity. At the end of the loop, only one index will be left as a celebrity. Go through each person again and check whether this is the celebrity. 
		The Two Pointer approach can be used where two pointers can be assigned, one at the start and the other at the end, and the elements can be compared and the search space can 
		be reduced.

TIME: O(N).

SPACE: O(1).
*/

class Solution
{ 
    //Function to find if there is a celebrity in the party or not.
    int celebrity(int M[][], int n)
    {
    	int i = 0, j = n-1;
    	
    	// finding the potential element to be celebrity
    	while(i < j) {
    	    
    	    // if A knows B, then A can't be the celebrity
    	    if(M[i][j] == 1) {
    	        i++;
    	    }
    	    // if A doesn't know B, then B can't be the celebrity
    	    else {
    	        j--;
    	    }
    	}
    	
    	// taking the potantial candidate
    	int candidate = i;
    	
    	// Now, all that is left is to check that whether the candidate is actually a celebrity i.e: he is
        // known by everyone but he knows no one
    	for(i = 0; i < n; i++) {
    	    if(i != candidate) {
    	        if(M[candidate][i] == 1 || M[i][candidate] == 0)
    	            return -1;
    	    }
    	}
    	
    	// if we reach here this means that the candidate is really a celebrity
    	return candidate;
    }
}