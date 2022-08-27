/*
(GOLDMANSACHS, EXPEDIA, OLA)

Given two rectangles, find if the given two rectangles overlap or not.
Note that a rectangle can be represented by two coordinates, top left and bottom right. So mainly we are given following four coordinates. 
l1: Top Left coordinate of first rectangle. 
r1: Bottom Right coordinate of first rectangle. 
l2: Top Left coordinate of second rectangle. 
r2: Bottom Right coordinate of second rectangl

Input: rec1 = [0,8,8,0], rec2 = [5,5,15,0]

Output: True

METHOD:
	APPROACH:
		Overlapping condition ----
			1. x co-ordinate(Top left point of one rectangle) < x co-ordinate(bottom right point of other rectangle)
			2. y co-ordinate(Top left point of one rectangle) > y co-ordinate(bottom right point of one rectangle)

		Inverse of this is a condition for Not Overlapping----
			1. x co-ordinate(Top left point of one rectangle) > x co-ordinate(bottom right point of other rectangle)
			2. y co-ordinate(Top left point of one rectangle) < y co-ordinate(bottom right point of one rectangle)

		Or we can say ------
			Two rectangles do not overlap if one of the following conditions is true. 
				1) One rectangle is above top edge of other rectangle. 
				2) One rectangle is on left side of left edge of other rectangle

TIME: O(1).

SPACE: O(1).
*/

class Solution {
 
   static class Point {
 
        int x, y;
    }
 
 // Returns true if two rectangles (l1, r1) and (l2, r2) overlap
 static  boolean doOverlap(Point l1, Point r1, Point l2, Point r2) {

        // if rectangle has area 0, no overlap
        if (l1.x == r1.x || l1.y == r1.y || r2.x == l2.x || l2.y == r2.y)
            return false;
     
           // If one rectangle is on left side of other
        if (l1.x > r2.x || l2.x > r1.x) {
            return false;
        }
 
        // If one rectangle is above other
        if (r1.y > l2.y || r2.y > l1.y) {
            return false;
        }
 
        return true;
    }
}
