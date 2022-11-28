package algorithm;

import gfx.Panel;

public class InsertionSort extends Sort {

    public InsertionSort(Panel panel, int delay) {
        super(panel, delay);
    }

    @Override
    public void sort(int[] arr, int length) throws InterruptedException {
        for(int i = 1; i < length; i++) {
            int key = arr[i];
            int j = i - 1;
            
            this.searchingIndex = i;

            while(j >= 0 && arr[j] > key) {
                this.checkingIndex = j+1;
                swap(arr, j+1, j);
                panel.repaint();
                j--;
                Thread.sleep(this.delay);
            }
        }
    }
    
    @Override
    public String getName() {
        return "Insertion Sort";
    }
}
