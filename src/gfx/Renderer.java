package gfx;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.SwingWorker;

import algorithm.BubbleSort;
import algorithm.InsertionSort;
import algorithm.MergeSort;
import algorithm.QuickSort;
import algorithm.SelectionSort;
import algorithm.Sort;
import sfx.Sound;

public class Renderer extends JPanel {

    private int width;
    private int height;

    private int padding;

    private int size = 10;

    private int length = 0;

    private int[] arr;

    private int largestValue = 0;

    private boolean useRandomNumber = false;

    private boolean isSorting = false;
    private boolean isShuffling = false;

    // Index for visualizer
    private int currentShuffleIndex = -1, shuffleRandomIndex = -1;
    private int colorIndex = -1;

    private int delay = 1;

    private boolean hasSound = false;

    private Random rand = new Random();
    private Sound player = new Sound();

    public Sound getPlayer() {
        return player;
    }

    BubbleSort bubble = new BubbleSort(this, player, hasSound, delay);
    InsertionSort insertion = new InsertionSort(this, player, hasSound, delay);
    SelectionSort selection = new SelectionSort(this, player, hasSound, delay);
    MergeSort merge = new MergeSort(this, player, hasSound, delay);
    QuickSort quick = new QuickSort(this, player, hasSound, delay);
    
    private int sorterIndex = 0;
    private Sort[] sortList = {bubble, insertion, selection, merge, quick};

    private SwingWorker<Void, Void> sorter;

    public Renderer(int windowSize) {
        this.width = windowSize;
        this.height = windowSize;
        this.padding = windowSize/10;
        setLength(padding, true);
        
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new BorderLayout());

        if(hasSound) {
            try {
                player.play("4C#", 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        createArray();
    }

    public void createArray() {
        arr = new int[length];
        for(int i = 0; i < length; i++) {
            arr[i] = this.useRandomNumber ? rand.nextInt(length-1)+1 : i+1;
            largestValue = Math.max(largestValue, arr[i]);
        }
        for(int i = 0; i < getNumOfAlgorithm(); i++) {
            sortList[i].setLargestValue(largestValue);
        }
        for(int i = 0; i < length; i++) {
            int randomIndex = rand.nextInt(length);
            int temp = arr[randomIndex];
            arr[randomIndex] = arr[i];
            arr[i] = temp;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        /*
        for(int i = 0; i < width/size; i++) {
            g.drawLine(0, i*size, width, i*size);
        }
        for(int i = 0; i < height/size; i++) {
            g.drawLine(i*size, 0, i*size, height);
        }
        */
        Color[] grad = {new Color(130, 130, 130), new Color(168, 168, 168), new Color(206, 206, 206)};
        Color[] gradGreen = {new Color(48, 99, 61), new Color(67, 138, 85), new Color(85, 176, 108)};

        for(int i = 0; i < length; i++) {
            g.setColor(grad[i%3]);
            if(isShuffling) {
                if(i == currentShuffleIndex) {
                    g.setColor(Color.cyan);
                } else if(i == shuffleRandomIndex) {
                    g.setColor(Color.magenta);
                }
            }
            if(isSorting) {
                if(i == sortList[sorterIndex].getSearchingIndex()) {
                    g.setColor(Color.green);
                } else if(i == sortList[sorterIndex].getCheckingIndex()) {
                    g.setColor(Color.red);
                }
            }
            if(i < colorIndex + 1 && checkSort(arr)) {
                g.setColor(gradGreen[i%3]);
            }
            g.fillRect(i*size+(padding/2), height-arr[i]*size, size, arr[i]*size);
        }
    }

    public void pauseSorter(boolean mayInterruptIfRunning) {
        player.closeSynth();

        if(sorter == null) return;
        
        sorter.cancel(mayInterruptIfRunning);
    }

    public void sort() {
        sorter = new SwingWorker<Void,Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                isSorting = true;
                if(checkSort(arr)) return null;

                sortList[sorterIndex].sort(arr, length);

                return null;
            }

            @Override
            protected void done() {
                if(!this.isCancelled()) {
                    super.done();
                    sortList[sorterIndex].done();
                    repaint();
                    finishColoring();
                }
                isSorting = false;
                colorIndex = 0;
            }
        };
        sorter.execute();
    }

    public void finishColoring() {
        SwingWorker<Void, Void> coloring = new SwingWorker<Void,Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                colorIndex = 0;
                while(colorIndex < length) {
                    if(hasSound) player.play(arr[colorIndex], delay, largestValue);
                    colorIndex++;
                    repaint();
                    if(!hasSound) Thread.sleep(delay);
                }
                return null;
            }

            @Override
            protected void done() {
                super.done();
                colorIndex = -1;
            }
        };
        coloring.execute();
    }

    public void shuffle() {
        SwingWorker<Void, Void> shuffler = new SwingWorker<Void,Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                isShuffling = true;
                for(int i = 0; i < length; i++) {
                    int randomIndex = rand.nextInt(length);
                    if(hasSound) player.play(arr[randomIndex], delay, largestValue);
                    int temp = arr[randomIndex];
                    arr[randomIndex] = arr[i];
                    arr[i] = temp;

                    currentShuffleIndex = i;
                    shuffleRandomIndex = randomIndex;

                    repaint();
                    if(!hasSound) Thread.sleep(delay);
                }
                return null;
            }

            @Override
            protected void done() {
                super.done();
                isShuffling = false;
                currentShuffleIndex = -1;
                shuffleRandomIndex = -1;
                repaint();
            }
        };
        shuffler.execute();
    }

    public boolean checkSort(int[] arr) {
        for(int i = 0; i < arr.length-1; i++) {
            if(arr[i] > arr[i+1]) {
                return false;
            }
        }
        return true;
    }

    // Getter and Setter
    public void setValueAtIndex(int index, int value) {
        this.arr[index] = value;
    }

    public int getDelay() {
        return this.delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
        for(int i = 0; i < getNumOfAlgorithm(); i++) {
            sortList[i].setDelay(delay);
        }
    }

    public boolean hasSound() {
        return this.hasSound;
    }

    public void setHasSound(boolean hasSound) {
        player.resumeSynth();
        try {
            player.play("4A", 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.hasSound = hasSound;
        for(int i = 0; i < getNumOfAlgorithm(); i++) {
            sortList[i].setHasSound(hasSound);
        }
    }

    public boolean isUseRandomNumber() {
        return this.useRandomNumber;
    }

    public void setUseRandomNumber(boolean useRandomNumber) {
        this.useRandomNumber = useRandomNumber;
    }

    public void setLength(int padding, boolean isUp) {
        int oldLength = this.length;
        int newLength = (width-padding)/size;

        while(oldLength == newLength) {
            oldLength = newLength;
            newLength = (width-padding)/size;
            size += isUp ? 1 : -1;
        }
        
        this.length = newLength;
    }

    public int getPadding() {
        return padding;
    }

    public int getBlockSize() {
        return size;
    }

    public void setBlockSize(int addition) {
        this.size += addition;
    }

    public int getLength() {
        return length;
    }

    public boolean isSorting() {
        return isSorting;
    }

    public boolean isShuffling() {
        return isShuffling;
    }

    // Getter and Setter for Algorithm
    public int getSorterIndex() {
        return sorterIndex;
    }

    public void setSorterIndex(int sorterIndex) {
        this.sorterIndex = sorterIndex;
    }

    public int getNumOfAlgorithm() {
        return sortList.length;
    }

    public Sort getSorterAtIndex(int index) {
        return sortList[index];
    }
}