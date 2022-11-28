package gfx;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter {

    private Frame frame;
    private Panel panel;

    public KeyHandler(Frame frame, Panel panel) {
        this.frame = frame;
        this.panel = panel;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch(key) {
            case KeyEvent.VK_R:
                if(panel.isSorting() || panel.isShuffling()) break;

                panel.sort();
                break;
            case KeyEvent.VK_Q:
                panel.pauseSorter(true);
                break;
            case KeyEvent.VK_SPACE:
                if(panel.isSorting() || panel.isShuffling()) break;

                panel.shuffle();
                break;
            case KeyEvent.VK_S:
                if(panel.getBlockSize() < 50) {
                    if(!panel.isSorting() && !panel.isShuffling()) {
                        panel.setBlockSize(1);
                        panel.setLength(panel.getPadding());
                        panel.createArray();
                        panel.repaint();
                    }
                }
                break;
            case KeyEvent.VK_W:
                if(panel.getBlockSize() > 1) {
                    if(!panel.isSorting() && !panel.isShuffling()) {
                        panel.setBlockSize(-1);
                        panel.setLength(panel.getPadding());
                        panel.createArray();
                        panel.repaint();
                    }
                }
                break;
            case KeyEvent.VK_A:
                if(panel.getSorterIndex() == 0) break;

                panel.setSorterIndex((panel.getSorterIndex()-1) % panel.getNumOfAlgorithm());
                frame.updateTextArea();
                break;
            case KeyEvent.VK_D:
                if(panel.getSorterIndex() == panel.getNumOfAlgorithm()-1) break;
                
                panel.setSorterIndex((panel.getSorterIndex()+1) % panel.getNumOfAlgorithm());
                frame.updateTextArea();
                break;
            default:
                break;
        }
    }
}