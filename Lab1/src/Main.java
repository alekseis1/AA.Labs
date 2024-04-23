import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Algorithms alg = new Algorithms();
        int[] firstInput = {5, 7, 10, 12, 15, 17, 20, 22, 25, 27, 30, 32, 35, 37, 40, 42, 45};
        int[] secondInput = {501, 631, 794, 1000, 1259, 1585, 1995, 2512, 3162, 3981, 5012, 6310, 7943, 10000, 12589, 15849};

        long[] timeRecursive = new long[firstInput.length];
        long[] timeIterative = new long[firstInput.length];
        long[] timeMatrix = new long[firstInput.length];
        long[] timeMemoization = new long[firstInput.length];
        long[] timeBinet = new long[firstInput.length];
        long[] timeBottomUp = new long[firstInput.length];

        for (int i = 0; i < firstInput.length; i++) {
            int n = firstInput[i];

            long start = System.nanoTime();
            alg.recursiveApproach(n);
            long end = System.nanoTime();
            timeRecursive[i] = end - start;

            start = System.nanoTime();
            alg.iterativeApproach(n);
            end = System.nanoTime();
            timeIterative[i] = end - start;

            start = System.nanoTime();
            alg.fibonacciMatrix(n);
            end = System.nanoTime();
            timeMatrix[i] = end - start;

            start = System.nanoTime();
            alg.fibonacciMemoization(n);
            end = System.nanoTime();
            timeMemoization[i] = end - start;

            start = System.nanoTime();
            alg.fibonacciBinet(n);
            end = System.nanoTime();
            timeBinet[i] = end - start;

            start = System.nanoTime();
            alg.fibonacciBottomUp(n);
            end = System.nanoTime();
            timeBottomUp[i] = end - start;
        }

        // Create dataset for first input
        XYSeriesCollection dataset1 = createDataset(firstInput, timeRecursive, timeIterative, timeMatrix, timeMemoization, timeBinet, timeBottomUp);

        // Create chart for first input
        JFreeChart chart1 = ChartFactory.createXYLineChart(
                "Results for the first input", // Chart title
                "Input Size",                  // X-axis label
                "Time (nanoseconds)",          // Y-axis label
                dataset1                       // Dataset
        );

        // Create panel for first input
        ChartPanel panel1 = new ChartPanel(chart1);

        // Create frame for first input
        JFrame frame1 = new JFrame("Results for the first input");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setLayout(new BorderLayout());
        frame1.add(panel1, BorderLayout.CENTER);
        frame1.pack();
        frame1.setVisible(true);

        // Print results for the first input
        printResults(firstInput, timeRecursive, timeIterative, timeMatrix, timeMemoization, timeBinet, timeBottomUp);

        // Reinitialize time arrays for second input
        timeIterative = new long[secondInput.length];
        timeMatrix = new long[secondInput.length];
        timeMemoization = new long[secondInput.length];
        timeBinet = new long[secondInput.length];
        timeBottomUp = new long[secondInput.length];

        for (int i = 0; i < secondInput.length; i++) {
            int n = secondInput[i];

            long start = System.nanoTime();
            alg.iterativeApproach(n);
            long end = System.nanoTime();
            timeIterative[i] = end - start;

            start = System.nanoTime();
            alg.fibonacciMatrix(n);
            end = System.nanoTime();
            timeMatrix[i] = end - start;

            start = System.nanoTime();
            alg.fibonacciMemoization(n);
            end = System.nanoTime();
            timeMemoization[i] = end - start;

            start = System.nanoTime();
            alg.fibonacciBinet(n);
            end = System.nanoTime();
            timeBinet[i] = end - start;

            start = System.nanoTime();
            alg.fibonacciBottomUp(n);
            end = System.nanoTime();
            timeBottomUp[i] = end - start;
        }

        // Create dataset for second input
        XYSeriesCollection dataset2 = createDataset(secondInput, timeIterative, timeMatrix, timeMemoization, timeBinet, timeBottomUp);

        // Create chart for second input
        JFreeChart chart2 = ChartFactory.createXYLineChart(
                "Results for the second input", // Chart title
                "Input Size",                   // X-axis label
                "Time (nanoseconds)",           // Y-axis label
                dataset2                        // Dataset
        );

        // Create panel for second input
        ChartPanel panel2 = new ChartPanel(chart2);

        // Create frame for second input
        JFrame frame2 = new JFrame("Results for the second input");
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setLayout(new BorderLayout());
        frame2.add(panel2, BorderLayout.CENTER);
        frame2.pack();
        frame2.setVisible(true);

        // Print results for the second input
        printResults(secondInput, timeIterative, timeMatrix, timeMemoization, timeBinet, timeBottomUp);
    }

    private static XYSeriesCollection createDataset(int[] input, long[]... times) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        String[] labels = {"Recursive", "Iterative", "Matrix", "Memoization", "Binet", "Bottom Up"};

        for (int i = 0; i < times.length; i++) {
            XYSeries series = new XYSeries(labels[i]);
            for (int j = 0; j < input.length; j++) {
                series.add(input[j], times[i][j]);
            }
            dataset.addSeries(series);
        }

        return dataset;
    }

    private static void printResults(int[] input, long[]... times) {
        System.out.printf("%-10s %-12s %-12s %-12s %-12s %-12s%n",
                "Input Size", "Iterative", "Matrix", "Memoization", "Binet", "Bottom Up");
        for (int i = 0; i < input.length; i++) {
            System.out.printf("%-10d ", input[i]);
            for (long[] time : times) {
                System.out.printf("%-12d ", time[i]);
            }
            System.out.println();
        }
    }
}
