package gfx;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter {

    private Frame frame;
    private Renderer render;

    public KeyHandler(Frame frame, Renderer panel) {
        this.frame = frame;
        this.render = panel;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch(key) {
            case KeyEvent.VK_R:
                if(render.isSorting() || render.isShuffling()) break;

                render.sort();
                break;
            case KeyEvent.VK_Q:
                render.pauseSorter(true);
                break;
            case KeyEvent.VK_SPACE:
                if(render.isSorting() || render.isShuffling()) break;

                render.shuffle();
                break;
            case KeyEvent.VK_S:
                if(render.isSorting() || render.isShuffling()) break;

                if(render.getBlockSize() < 50) {
                    if(!render.isSorting() && !render.isShuffling()) {
                        render.setBlockSize(1);
                        render.setLength(render.getPadding());
                        render.createArray();
                        render.repaint();
                    }
                }
                break;
            case KeyEvent.VK_W:
                if(render.isSorting() || render.isShuffling()) break;

                if(render.getBlockSize() > 1) {
                    if(!render.isSorting() && !render.isShuffling()) {
                        render.setBlockSize(-1);
                        render.setLength(render.getPadding());
                        render.createArray();
                        render.repaint();
                    }
                }
                break;
            case KeyEvent.VK_A:
                if(render.isSorting()) break;
                if(render.getSorterIndex() == 0) break;

                render.setSorterIndex((render.getSorterIndex()-1) % render.getNumOfAlgorithm());
                frame.updateTextArea();
                break;
            case KeyEvent.VK_D:
                if(render.isSorting()) break;
                if(render.getSorterIndex() == render.getNumOfAlgorithm()-1) break;
                
                render.setSorterIndex((render.getSorterIndex()+1) % render.getNumOfAlgorithm());
                frame.updateTextArea();
                break;
            default:
                break;
        }
    }
}