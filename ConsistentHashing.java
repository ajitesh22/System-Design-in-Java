//https://www.lintcode.com/problem/519/

public class Solution {
    /*
     * @param n: a positive integer
     * @return: n x 3 matrix
     */
    public List<List<Integer>> consistentHashing(int n) {
        // write your code here
         List<List<Integer>> ans = new ArrayList<>();
         TreeSet<List<Integer>> treeSet = new TreeSet<> (new MyTreeSetComparator());
         List<Integer> firstRange = new ArrayList<>();
         firstRange.add(0);
         firstRange.add(359);
         firstRange.add(1);
         
         treeSet.add(firstRange);
         for(int i=2;i<=n;i++){
             List<Integer> largestRange = treeSet.first();
             int x = largestRange.get(0);
             int y = largestRange.get(1);
             int z = largestRange.get(2);

             List<Integer> rangeA = new ArrayList<>();
             List<Integer> rangeB = new ArrayList<>();
             rangeA.add(x);
             rangeA.add((x+y)/2);
             rangeA.add(z);

             rangeB.add((x+y)/2  +  1);
             rangeB.add(y);
             rangeB.add(i);

             treeSet.remove(largestRange);
             treeSet.add(rangeA);
             treeSet.add(rangeB);


         }

         for(List<Integer> range : treeSet){
             ans.add(range);
         }

         Collections.sort(ans,new MyNodeComparator());

        return ans;

    }
}

class MyTreeSetComparator implements Comparator<List<Integer>> {

    @Override 
    public int compare(List<Integer> a , List<Integer> b){
        int rangeA = a.get(1) - a.get(0);
        int rangeB = b.get(1) - b.get(0);

        if(rangeA!=rangeB){
            return rangeB - rangeA;
        }
        return a.get(2) - b.get(2);

    }

}

class MyNodeComparator implements Comparator<List<Integer>> {

    @Override
    public int compare(List<Integer> a , List<Integer> b){
        return a.get(0) - b.get(0);
    }
}
