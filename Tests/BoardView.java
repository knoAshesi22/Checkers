package Checkers.Tests;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class BoardView extends JPanel implements MouseListener, MouseMotionListener {

    private int bHeight;
    private int bWidth;
    private enum Color{BLACK,WHITE};

    public BoardView(int bHeight, int bWidth){
        this.bHeight=bHeight;
        this.bWidth=bWidth;
        JFrame boardFrame=new JFrame("Checkers");

    }

    public JPanel genFrame(int bheight,int bwidth){
        return new JPanel();

    }

    public static void colorTIle(Color color){

    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
