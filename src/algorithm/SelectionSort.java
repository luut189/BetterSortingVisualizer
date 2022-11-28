package algorithm;

import gfx.Panel;

public class SelectionSort extends Sort {

    public SelectionSort(Panel panel, int delay) {
        super(panel, delay);
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
                panel.repaint();
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
