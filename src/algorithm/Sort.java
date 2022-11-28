package algorithm;

import gfx.Panel;

public class Sort {
    
    protected int searchingIndex;
    protected int checkingIndex;

    protected Panel panel;
    protected int delay;

    public Sort(Panel panel, int delay) {
        this.searchingIndex = -1;
        this.checkingIndex = -1;
        
        this.panel = panel;
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