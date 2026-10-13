/*
*
*  CalendarFrame.java
*
*  author: Ephraim Becker
*
*  The calendar frame class is the parent component for WeekdayHeaders and DateCell instances.
*  CalendarFrame instances must be created with a Calendar object 
*  and the components detailed above (see constructor)
*
*  Requirements:
*  1. java.util.Calendar
*  2. WeekdayHeaders
*  3. DateCell[]
*/

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CalendarFrame extends JFrame {
        /**
         * 
         * @param cal       Calendar instance
         * @param monthText The month of the year
         * @param header    Header panel
         * @param dateCells The date cells
         * @throws Exception
         */
        public CalendarFrame(Calendar cal, JLabel monthText, WeekdayHeaders header, DateCell[] dateCells)
                        throws Exception {
                // Header container
                JPanel headerContainer = new JPanel();

                // Set the header container to transparent
                headerContainer.setOpaque(false);

                // Set a vertical layout
                headerContainer.setLayout(new BoxLayout(headerContainer, BoxLayout.Y_AXIS));

                // Sets a bold font on the month of the year
                monthText.setFont(new Font("sansSerif", Font.BOLD, 24));

                // Add the components to the container
                headerContainer.add(monthText);
                headerContainer.add(header);

                // Add the container to the frame
                add(headerContainer, BorderLayout.NORTH);

                // Add a grid panel for the dates
                JPanel panel = new JPanel(new GridLayout(0, 7));

                // Sets the grid panel to transparent
                panel.setOpaque(false);

                // Instantiate the flying saucer
                FlyingSaucer flyingSaucer = new FlyingSaucer();

                // Loop through the date cells
                for (DateCell cell : dateCells) {
                        // Add each date cell to the panel
                        panel.add(cell);

                        // Check if the date is not null
                        if (cell.getDate() != null) {
                                // Add an action listener to the cell
                                cell.addActionListener(e -> {
                                        // Set the x position to half its width and subtract 40
                                        int x = cell.getX()
                                                        + cell.getWidth() / 2
                                                        - 40;

                                        // Set the y position to half its height
                                        int y = cell.getY()
                                                        + cell.getHeight() / 2;

                                        // The flying saucer flies to that position
                                        flyingSaucer.flyTo(x, y);
                                });
                        }
                }

                // Sets the background to clouds
                flyingSaucer.setBackgroundTexture(
                                new File("img/clouds.jpg").toURI().toURL());

                // Sets the foreground to the flying saucer
                flyingSaucer.setForegroundBadge(
                                new File("img/flyingsaucer.png").toURI().toURL());

                // Put the flying saucer on the panel
                flyingSaucer.setView(panel);

                // Adds the flying saucer to the frame
                add(flyingSaucer, BorderLayout.CENTER);

                pack();

                // Get calendar instance
                Calendar today = Calendar.getInstance();

                // Loop through the date cells again
                for (DateCell cell : dateCells) {
                        // Check for today's date
                        if (cell.getDate() != null &&
                                        cell.getDate().get(Calendar.YEAR) == today.get(Calendar.YEAR) &&
                                        cell.getDate().get(Calendar.MONTH) == today.get(Calendar.MONTH) &&
                                        cell.getDate().get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH)) {

                                // Sets to the cells x position
                                int x = cell.getX()
                                                + cell.getWidth() / 2
                                                - 40;

                                // Sets to the cells y position
                                int y = cell.getY()
                                                + cell.getHeight() / 2;

                                // The flying saucer should start at today's date
                                flyingSaucer.setSaucerPosition(x, y);

                                break;
                        }
                }

                setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
}
