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
        int[] inputSizes = {1000, 5000, 10000, 20000, 30000, 40000, 50000, 100000};
        long[] timeQuick = new long[inputSizes.length];
        long[] timeHeap = new long[inputSizes.length];
        long[] timeMerge = new long[inputSizes.length];
        long[] timeCount = new long[inputSizes.length];

        for (int i = 0; i < inputSizes.length; i++) {
            int[] arr = generateRandomArray(inputSizes[i]);

            long start = System.nanoTime();
            alg.quicksort(arr, 0, arr.length - 1);
            long end = System.nanoTime();
            timeQuick[i] = end - start;

            start = System.nanoTime();
            alg.heapSort(arr);
            end = System.nanoTime();
            timeHeap[i] = end - start;

            start = System.nanoTime();
            alg.mergeSort(arr);
            end = System.nanoTime();
            timeMerge[i] = end - start;

            start = System.nanoTime();
            int[] sortedArray = alg.countSort(arr);
            end = System.nanoTime();
            timeCount[i] = end - start;
        }

        // Create dataset
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries quickSeries = new XYSeries("QuickSort");
        XYSeries heapSeries = new XYSeries("HeapSort");
        XYSeries mergeSeries = new XYSeries("MergeSort");
        XYSeries countSeries = new XYSeries("CountSort");

        for (int i = 0; i < inputSizes.length; i++) {
            quickSeries.add(inputSizes[i], timeQuick[i]);
            heapSeries.add(inputSizes[i], timeHeap[i]);
            mergeSeries.add(inputSizes[i], timeMerge[i]);
            countSeries.add(inputSizes[i], timeCount[i]);
        }

        dataset.addSeries(quickSeries);
        dataset.addSeries(heapSeries);
        dataset.addSeries(mergeSeries);
        dataset.addSeries(countSeries);

        // Create chart
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Sorting Algorithms Comparison", // Chart title
                "Input Size",                    // X-axis label
                "Time (nanoseconds)",           // Y-axis label
                dataset                         // Dataset
        );

        // Create panel
        ChartPanel panel = new ChartPanel(chart);

        // Create frame
        JFrame frame = new JFrame("Sorting Algorithms Comparison");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 100000);
        }
        return arr;
    }
}
