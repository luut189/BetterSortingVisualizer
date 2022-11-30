package algorithm;

import gfx.Renderer;

public class MergeSort extends Sort {

    public MergeSort(Renderer render, int delay) {
        super(render, delay);
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
            checkingIndex = k;
            if(leftArr[i] < rightArr[j]) {
                searchingIndex = i + k;
                arr[k] = leftArr[i];
                i++;
            } else {
                searchingIndex = j + k;
                arr[k] = rightArr[j];
                j++;
            }
            k++;
            render.repaint();
            Thread.sleep(delay);
        }

        while(i < leftLength) {
            checkingIndex = k;
            searchingIndex = i + k;
            arr[k] = leftArr[i];
            i++;
            k++;
            render.repaint();
            Thread.sleep(delay);
        }

        while(j < rightLength) {
            checkingIndex = k;
            searchingIndex = j + k;
            arr[k] = rightArr[j];
            j++;
            k++;
            render.repaint();
            Thread.sleep(delay);
        }
    }

    @Override
    public String getName() {
        return "Merge Sort";
    }
}
