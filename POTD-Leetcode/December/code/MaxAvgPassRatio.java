class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<double[]> heap = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));

        // store `max gain ratio` in the max heap
        // one that has max gain after adding 1 student will be at the top
        for(int[] c : classes){
            double pass = c[0];
            double total = c[1];
            double currentGain = (pass + 1) / (total + 1) - (pass / total);

            // heap will maintain 
            heap.offer(new double[]{currentGain, pass, total});
        }

        // keep adding extraStudent 1 by 1
        for(int i = 0; i < extraStudents; i++){
            // keep choiosing the top for max gain
            double[] top = heap.poll();
            double passI = top[1] + 1;
            double totalI = top[2] + 1;

            double newGain = (passI + 1) / (totalI + 1) - (passI / totalI);
            
            // send the updated gain for this class back to the max heap
            heap.offer(new double[]{newGain, passI, totalI});
        }

        // now calculate total pass ratio for all classes
        double totalRatio = 0.0;

        // simply take the pass ratios of each class
        for(double[] c: heap){
            totalRatio += c[1] / c[2];
        }

        return totalRatio / classes.length;
    }
}