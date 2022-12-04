package algorithm;

import gfx.Renderer;
import sfx.Sound;

public class QuickSort extends Sort {

    public QuickSort(Renderer render, Sound player, boolean hasSound, int delay) {
        super(render, player, hasSound, delay);
    }

    @Override
    public void sort(int[] arr, int length) throws InterruptedException {
        quickSort(arr, 0, length-1);
    }

    private void quickSort(int[] arr, int lowIndex, int highIndex) throws InterruptedException {
        if(lowIndex > highIndex) return;

        int pivot = getPivot(arr, lowIndex, highIndex);

        quickSort(arr, lowIndex, pivot-1);
        quickSort(arr, pivot+1, highIndex);
    }

    private int getPivot(int[] arr, int lowIndex, int highIndex) throws InterruptedException {
        int pivotValue = arr[highIndex];
        int i = lowIndex - 1;
        for (int j = lowIndex; j <= highIndex - 1; j++) {
            setSearchingIndex(i);
            if (arr[j] <= pivotValue) {
                i++;
                if(hasSound) player.play(arr[j], delay, largestValue);
                swap(arr, i, j);
                setCheckingIndex(j);
                render.repaint();
                if(!hasSound) Thread.sleep(delay);
            }
        }
        if(hasSound) player.play(arr[i+1], delay, largestValue);
        swap(arr, i + 1, highIndex);
        render.repaint();
        if(!hasSound) Thread.sleep(delay);
        return i + 1;
    }

    @Override
    public String getName() {
        return "Quick Sort";
    }
    
}
