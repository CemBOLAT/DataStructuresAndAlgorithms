import java.util.LinkedList;

public class Solution {
    /*
    private static int rec(int[] nums, int t, int min, int max) {
        if (min > max)
            return -1;
        int mid = (min + max) / 2;
        if (nums[mid] == t) {
            if (min == mid || nums[mid - 1] != t) {
                return mid;
            }
            return rec(nums, t, min, mid - 1);
        }
        if (nums[mid] > t) {
            return rec(nums, t, min, mid - 1);
        }
        return rec(nums, t, mid + 1, max);
    }

    public static void main(String[] arg) {
        int[] nums = {1, 1, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4, 5, 5, 5, 5, 7, 8, 9, 10, 11, 12};

        System.out.println(rec(nums, 1, 0, nums.length - 1));
        System.out.println(nums[-1]);
    }
    */

    private static recEquals(LinkedList<Integer> list, LinkedList<Integer> list2){
        if (list ) 
    }
    
    public static void main(String[] args){
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        LinkedList<Integer> list2 = new LinkedList<>();

        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);
        list2.add(5);

        System.out.println(recEquals(list, list2));
    }
}

public class TowerOfHanoi {
    public static void towerOfHanoi(int n, char fromRod, char toRod, char auxRod1, char auxRod2) {
        if (n == 1) {
            System.out.println("Move disk 1 from rod " + fromRod + " to rod " + toRod);
            return;
        }
        if (n == 2) {
            System.out.println("Move disk 1 from rod " + fromRod + " to rod " + auxRod1);
            System.out.println("Move disk 2 from rod " + fromRod + " to rod " + toRod);
            System.out.println("Move disk 1 from rod " + auxRod1 + " to rod " + toRod);
            return;
        }
        towerOfHanoi(n - 2, fromRod, auxRod2, toRod, auxRod1);
        System.out.println("Move disk " + (n - 1) + " from rod " + fromRod + " to rod " + auxRod1);
        System.out.println("Move disk " + n + " from rod " + fromRod + " to rod " + toRod);
        System.out.println("Move disk " + (n - 1) + " from rod " + auxRod1 + " to rod " + toRod);
        towerOfHanoi(n - 2, auxRod2, toRod, fromRod, auxRod1);
    }

    public static void main(String args[]) {
        int n = 4; // Number of disks
        towerOfHanoi(n, 'A', 'D', 'B', 'C'); // A, B, C, and D are the rod names
    }
}

