package algorithm;

import gfx.Panel;

public class MergeSort extends Sort {

    public MergeSort(Panel panel, int delay) {
        super(panel, delay);
    }
    
    @Override
    public void sort(int[] arr, int length) throws InterruptedException {
        mergeSort(arr, 0, length - 1);
    }

    public void mergeSort(int[] arr, int left, int right) throws InterruptedException {
        if(left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    public void merge(int[] arr, int left, int mid, int right) throws InterruptedException {
        int leftLength = mid - left + 1;
        int rightLength = right - mid;
        
        int[] leftArr = new int[leftLength];
        int[] rightArr = new int[rightLength];

        for(int i = 0; i < leftLength; i++) {
            leftArr[i] = arr[left + i];
        }
        
        for(int i = 0; i < rightLength; i++) {
            rightArr[i] = arr[mid + 1 + i];
        }

        int i = 0, j = 0, k = left;

        while(i < leftLength && j < rightLength) {
            checkingIndex = i + k - 1;
            searchingIndex = j + k - 1;
            if(leftArr[i] < rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
            panel.repaint();
            Thread.sleep(delay);
        }

        while(i < leftLength) {
            arr[k] = leftArr[i];
            i++;
            k++;
            panel.repaint();
            Thread.sleep(delay);
        }

        while(j < rightLength) {
            arr[k] = rightArr[j];
            j++;
            k++;
            panel.repaint();
            Thread.sleep(delay);
        }
    }

    @Override
    public String getName() {
        return "Merge Sort";
    }
}
