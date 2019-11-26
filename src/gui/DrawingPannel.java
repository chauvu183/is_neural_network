package gui;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class DrawingPannel extends CustomPanel implements MouseMotionListener, MouseListener {

    public DrawingPannel(int width, int height, int count) {
        super(width, height, count);

        addMouseMotionListener(this);
        addMouseListener(this);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        paintSection(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        paintSection(e);
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
    public void mouseMoved(MouseEvent e) {
    }

    private void paintSection(MouseEvent e){
        if(SwingUtilities.isLeftMouseButton(e)){
            for(Section s: sections){
                if(e.getX() > s.getX() && e.getX() < s.getX() + s.getWidth()
                && e.getY() > s.getY() && e.getY() < s.getY() + s.getHeight()
                ){
                    s.setActive(true);
                }
            }
        }else if(SwingUtilities.isRightMouseButton(e)){
            for(Section s : sections){
                if(e.getX() > s.getY() && e.getX() < s.getX() + s.getWidth() && e.getY() > s.getY() && e.getY() < s.getY() + s.getHeight()){
                    s.setActive(false);
                }
            }
        }
        repaint();
    }
}
