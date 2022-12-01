package algorithm;

import gfx.Renderer;
import sfx.Sound;

public class BubbleSort extends Sort {

    public BubbleSort(Renderer render, Sound player, boolean hasSound, int delay) {
        super(render, player, hasSound, delay);
    }

    @Override
    public void sort(int[] arr, int length) throws InterruptedException {
        tone = getTone(length);
        int index = 0;
        while(true) {
            boolean isFlag = false;
            for(int j = 0; j < length-index-1; j++) {
                searchingIndex = j;
                checkingIndex = j+1;
                if(hasSound) player.play(arr[j]/tone, delay);
                if(arr[j] > arr[j+1]) {
                    swap(arr, j, j+1);
                    isFlag = true;
                }
                render.repaint();
                if(!hasSound) Thread.sleep(this.delay);
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