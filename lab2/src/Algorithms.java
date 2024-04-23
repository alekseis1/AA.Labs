import java.util.Arrays;

public class Algorithms {

    public int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    public void quicksort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quicksort(array, low, pi - 1);
            quicksort(array, pi + 1, high);
        }
    }

    public void heapify(int[] arr, int N, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < N && arr[largest] < arr[l]) {
            largest = l;
        }

        if (r < N && arr[largest] < arr[r]) {
            largest = r;
        }

        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            heapify(arr, N, largest);
        }
    }

    public void heapSort(int[] arr) {
        int N = arr.length;

        for (int i = N / 2 - 1; i >= 0; i--) {
            heapify(arr, N, i);
        }

        for (int i = N - 1; i > 0; i--) {
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            heapify(arr, i, 0);
        }
    }

    public void mergeSort(int[] arr) {
        if (arr.length > 1) {
            int mid = arr.length / 2;
            int[] L = Arrays.copyOfRange(arr, 0, mid);
            int[] R = Arrays.copyOfRange(arr, mid, arr.length);
            mergeSort(L);
            mergeSort(R);
            int i = 0, j = 0, k = 0;
            while (i < L.length && j < R.length) {
                if (L[i] <= R[j]) {
                    arr[k] = L[i];
                    i++;
                } else {
                    arr[k] = R[j];
                    j++;
                }
                k++;
            }
            while (i < L.length) {
                arr[k] = L[i];
                i++;
                k++;
            }
            while (j < R.length) {
                arr[k] = R[j];
                j++;
                k++;
            }
        }
    }

    public int[] countSort(int[] inputArray) {
        int M = Arrays.stream(inputArray).max().getAsInt();
        int[] countArray = new int[M + 1];
        for (int num : inputArray) {
            countArray[num]++;
        }
        for (int i = 1; i <= M; i++) {
            countArray[i] += countArray[i - 1];
        }
        int[] outputArray = new int[inputArray.length];
        for (int i = inputArray.length - 1; i >= 0; i--) {
            outputArray[countArray[inputArray[i]] - 1] = inputArray[i];
            countArray[inputArray[i]]--;
        }
        return outputArray;
    }
}
