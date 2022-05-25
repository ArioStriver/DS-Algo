/*
Given a sorted array, find the element in the array which has minimum difference with the given number. . 

(i) a = {4, 6, 9, 11, 15, 20} , key = 7
   o/p : 6

(ii) a = {1, 3, 5, 8, 10, 12, 15, 20} , key = 12
   o/p : 0


METHOD: (UISNG BINARY SERACH)

TIME: O(logN).

SPACE: O(1).
*/

int minDifference(int[] a, int key, int n) {

	int start = 0, end = n-1;

	while(start <= end) {
		
		int mid = (start + end) / 2;

		if(a[mid] == key) {
			return a[mid];
		}

		if(a[mid] < key) {
			start = mid + 1;
		}
		else {
			end = mid - 1;
		}
	}

	int diff1 = Math.abs(a[start] - key);
	int diff2 = Math.abs(a[end] - key);

	return Math.min(diff1, diff2);
}
