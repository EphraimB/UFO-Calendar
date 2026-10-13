
/**
 * 
 * Author: Ephraim Becker
 * 
 * Implements a flying saucer that starts at today's date and 
 * flies to wherever you click it
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JViewport;
import javax.swing.Timer;

public class FlyingSaucer extends JViewport {
    // Image properties
    BufferedImage fgimage, bgimage;
    TexturePaint texture;

    // Position properties
    private int saucerX;
    private int saucerY;

    /**
     * 
     * Sets the background
     * 
     * @param url Image path
     * @throws IOException
     */
    public void setBackgroundTexture(URL url) throws IOException {
        // Reads the image path
        bgimage = ImageIO.read(url);

        // Puts image in a rectangle
        Rectangle rect = new Rectangle(0, 0,
                bgimage.getWidth(null), bgimage.getHeight(null));

        // Sets the texture
        texture = new TexturePaint(bgimage, rect);
    }

    /**
     * 
     * Sets the foreground
     * 
     * @param url Image path
     * @throws IOException
     */
    public void setForegroundBadge(URL url) throws IOException {
        // Reads the image path
        fgimage = ImageIO.read(url);
    }

    /**
     * 
     * Sets the position of the flying saucer
     * 
     * @param x X position
     * @param y Y position
     */
    public void setSaucerPosition(int x, int y) {
        saucerX = x;
        saucerY = y;

        repaint();
    }

    /**
     * 
     * Flies to the targeted position
     * 
     * @param targetX X position to fly to
     * @param targetY Y position to fly to
     */
    public void flyTo(int targetX, int targetY) {
        // Sets a timer
        Timer timer = new Timer(15, null);

        // Adds an action listener on the timer
        timer.addActionListener(e -> {
            // Sets the speed
            int speed = 5;

            // Guides where to fly to
            if (saucerX < targetX)
                saucerX += speed;
            else if (saucerX > targetX)
                saucerX -= speed;

            if (saucerY < targetY)
                saucerY += speed;
            else if (saucerY > targetY)
                saucerY -= speed;

            // Close enough: snap into final position
            if (Math.abs(saucerX - targetX) <= speed &&
                    Math.abs(saucerY - targetY) <= speed) {

                saucerX = targetX;
                saucerY = targetY;

                timer.stop();
            }

            repaint();
        });

        // Start the timer
        timer.start();
    }

    /**
     * (non-Javadoc)
     * 
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (texture != null) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setPaint(texture);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    /**
     * (non-Javadoc)
     * 
     * @see javax.swing.JComponent#paintChildren(java.awt.Graphics)
     */
    public void paintChildren(Graphics g) {
        super.paintChildren(g);
        if (fgimage != null) {
            g.drawImage(
                    fgimage,
                    saucerX,
                    saucerY,
                    80,
                    40,
                    this);
        }
    }

    /**
     * 
     * Makes the view transparent
     * 
     * @param view
     */
    public void setView(JComponent view) {
        view.setOpaque(false);
        super.setView(view);
    }
}
