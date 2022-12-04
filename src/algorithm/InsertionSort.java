package algorithm;

import gfx.Renderer;
import sfx.Sound;

public class InsertionSort extends Sort {

    public InsertionSort(Renderer render, Sound player, boolean hasSound, int delay) {
        super(render, player, hasSound, delay);
    }

    @Override
    public void sort(int[] arr, int length) throws InterruptedException {
        for(int i = 1; i < length; i++) {
            int key = arr[i];
            int j = i - 1;
            
            this.searchingIndex = i;

            while(j >= 0 && arr[j] > key) {
                if(hasSound) player.play(arr[j], delay, largestValue);
                this.checkingIndex = j+1;
                swap(arr, j+1, j);
                render.repaint();
                j--;
                if(!hasSound) Thread.sleep(this.delay);
            }
        }
    }
    
    @Override
    public String getName() {
        return "Insertion Sort";
    }
}
