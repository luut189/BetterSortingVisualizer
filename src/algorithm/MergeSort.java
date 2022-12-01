package algorithm;

import gfx.Renderer;
import sfx.Sound;

public class MergeSort extends Sort {

    public MergeSort(Renderer render, Sound player, boolean hasSound, int delay) {
        super(render, player, hasSound, delay);
    }
    
    @Override
    public void sort(int[] arr, int length) throws InterruptedException {
        tone = getTone(length);
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
                if(hasSound) player.play(leftArr[i]/tone, delay);
                searchingIndex = i + k;
                arr[k] = leftArr[i];
                i++;
            } else {
                if(hasSound) player.play(rightArr[j]/tone, delay);
                searchingIndex = j + k;
                arr[k] = rightArr[j];
                j++;
            }
            k++;
            render.repaint();
            if(!hasSound) Thread.sleep(delay);
        }

        while(i < leftLength) {
            if(hasSound) player.play(leftArr[i]/tone, delay);
            checkingIndex = k;
            searchingIndex = i + k;
            arr[k] = leftArr[i];
            i++;
            k++;
            render.repaint();
            if(!hasSound) Thread.sleep(delay);
        }

        while(j < rightLength) {
            if(hasSound) player.play(rightArr[j]/tone, delay);
            checkingIndex = k;
            searchingIndex = j + k;
            arr[k] = rightArr[j];
            j++;
            k++;
            render.repaint();
            if(!hasSound) Thread.sleep(delay);
        }
    }

    @Override
    public String getName() {
        return "Merge Sort";
    }
}
