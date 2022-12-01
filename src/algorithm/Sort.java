package algorithm;

import gfx.Renderer;
import sfx.Sound;

public class Sort {
    
    protected int searchingIndex;
    protected int checkingIndex;

    protected Renderer render;
    protected int delay;

    protected boolean hasSound;

    protected Sound player;
    protected int tone;

    public Sort(Renderer render, Sound player, boolean hasSound, int delay) {
        this.searchingIndex = -1;
        this.checkingIndex = -1;
        
        this.render = render;
        this.delay = delay;

        this.player = player;
        this.hasSound = hasSound;
    }

    public void setHasSound(boolean hasSound) {
        this.hasSound = hasSound;
    }

    public int getTone(int length) {
        return length/27;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
    
    public int[] swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
        return arr;
    }

    public void sort(int[] arr, int length) throws InterruptedException {
        
    }

    public void done() {
        searchingIndex = -1;
        checkingIndex = -1;
    }

    public int getSearchingIndex() {
        return searchingIndex;
    }

    public void setSearchingIndex(int searchingIndex) {
        this.searchingIndex = searchingIndex;
    }

    public int getCheckingIndex() {
        return checkingIndex;
    }

    public void setCheckingIndex(int checkingIndex) {
        this.checkingIndex = checkingIndex;
    }

    public String getName() {
        return "";
    }
}