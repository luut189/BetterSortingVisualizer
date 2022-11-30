package algorithm;

import gfx.Renderer;

public class SelectionSort extends Sort {

    public SelectionSort(Renderer render, int delay) {
        super(render, delay);
    }

    @Override
    public void sort(int[] arr, int length) throws InterruptedException {
        for(int i = 0; i < length; i++) {
            int min = i;
            this.searchingIndex = min;
            for(int j = i + 1; j < length; j++) {
                this.checkingIndex = j;
                if(arr[min] > arr[j]) {
                    min = j;
                }
                render.repaint();
                Thread.sleep(delay);
            }
            swap(arr, i, min);
        }
    }

    @Override
    public String getName() {
        return "Selection Sort";
    }
}
