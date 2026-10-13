/*
*
* WeekdayHeaders.java
* 
* author: Ephraim Becker
*
* The WeekdayHeaders class creates a Swing component that displays a 1 x 7 grid of JLabels inside of a JPanel.
* Each JLabel will display the name of the day of the week (Monday, Tuesday, etc.)
*/

import java.util.Calendar;
import java.util.Locale;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class WeekdayHeaders extends JPanel {
    // Sets the colors
    public static final Color HEADER_COLOR = new Color(127, 127, 127);
    public static final Color FOREGROUND_COLOR = Color.WHITE;

    /**
     * Constructor
     */
    public WeekdayHeaders() {
        // Calls the JPanel class with a Grid Layout
        super(new GridLayout(1, 7, 1, 1));

        // Sets the background color to light gray
        setBackground(Color.LIGHT_GRAY);

        // Sets an array with the days of the week
        int[] weekdays = { Calendar.SUNDAY, Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY,
                Calendar.THURSDAY, Calendar.FRIDAY, Calendar.SATURDAY };

        // Sets US local
        Calendar days = Calendar.getInstance(Locale.US);

        // Loop through the days of the week
        for (int weekday : weekdays) {
            // Sets the dates to the days of the week
            days.set(Calendar.DAY_OF_WEEK, weekday);

            // Header with the day of week
            JLabel header = new JLabel(days.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US),
                    SwingConstants.CENTER);

            // Sets to opaque
            header.setOpaque(true);

            // Sets the foreground color
            header.setForeground(FOREGROUND_COLOR);

            // Sets the header color
            header.setBackground(HEADER_COLOR);

            // Sets the font to bold
            header.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

            // Sets the preferred size
            header.setPreferredSize(new Dimension(92, 32));

            // Adds the header to the panel
            add(header);
        }
    }
}
