package algorithm;

import gfx.Renderer;
import sfx.Sound;

public class SelectionSort extends Sort {

    public SelectionSort(Renderer render, Sound player, boolean hasSound, int delay) {
        super(render, player, hasSound, delay);
    }

    @Override
    public void sort(int[] arr, int length) throws InterruptedException {
        for(int i = 0; i < length; i++) {
            int min = i;
            this.searchingIndex = min;
            for(int j = i + 1; j < length; j++) {
                this.checkingIndex = j;
                if(hasSound) player.play(arr[j], delay, largestValue);
                if(arr[min] > arr[j]) {
                    min = j;
                }
                render.repaint();
                if(!hasSound) Thread.sleep(delay);
            }
            swap(arr, i, min);
        }
    }

    @Override
    public String getName() {
        return "Selection Sort";
    }
}
