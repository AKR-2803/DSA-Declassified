class Solution {
    public int[] finalPrices(int[] prices) {
        //monotonic stack approach
        Stack<Integer> stack = new Stack<>();

        int[] result = prices.clone();

        for(int i = 0; i < prices.length; i++){
            // pop index of elements and apply discount, when they meet the criteria
            // that the element should be lesser than the current indexed value
            while(!stack.isEmpty() && prices[stack.peek()] >= prices[i]){
                int popIndex = stack.pop();
                result[popIndex] -= prices[i];
            }

            // otherwise you can't apply discount as of now, keep pushing index of elements 
            stack.push(i);
        }

        return result;
    }
}