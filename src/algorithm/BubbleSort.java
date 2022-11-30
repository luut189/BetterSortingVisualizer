package algorithm;

import gfx.Renderer;

public class BubbleSort extends Sort {

    public BubbleSort(Renderer render, int delay) {
        super(render, delay);
    }

    @Override
    public void sort(int[] arr, int length) throws InterruptedException {
        int index = 0;
        while(true) {
            boolean isFlag = false;
            for(int j = 0; j < length-index-1; j++) {
                searchingIndex = j;
                checkingIndex = j+1;
                if(arr[j] > arr[j+1]) {
                    swap(arr, j, j+1);
                    isFlag = true;
                }
                render.repaint();
                Thread.sleep(this.delay);
            }
            index++;
            if(!isFlag) break;
        }
    }

    @Override
    public String getName() {
        return "Bubble Sort";
    }
}