import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;

import javax.swing.JButton;

/*
* 
*  DateCell.java
*
*  author: Ephraim Becker
*
*  Creates JLabels filling in each day of the month
*
*/

public class DateCell extends JButton {
    private Calendar date;

    /**
     * 
     * @param text    The date text
     * @param weekend If its a weekend
     * @param date    The date
     */
    public DateCell(String text, boolean weekend, Calendar date) {
        // Sets the text on the button
        super(text);

        // Sets the date
        this.date = date;

        // Turns transparency on
        setOpaque(false);

        // Sets a white background
        setBackground(Color.WHITE);

        // Plain font
        setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));

        // Sets the preferred size
        setPreferredSize(new Dimension(92, 58));
    }

    /**
     * 
     * @return The date
     */
    public Calendar getDate() {
        return date;
    }

    /**
     * 
     * @param month The month
     * @return An array of date cells
     */
    public static DateCell[] forMonth(Calendar month) {
        Calendar date = (Calendar) month.clone();
        date.set(Calendar.DAY_OF_MONTH, 1);

        // We must account for blank dates in the calendar grid, so create cells for
        // daysInMonth + leadingBlankDays
        int leadingBlankDays = date.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY;
        int daysInMonth = date.getActualMaximum(Calendar.DAY_OF_MONTH);
        DateCell[] cells = new DateCell[daysInMonth + leadingBlankDays];

        for (int index = 0; index < leadingBlankDays; index++) {
            cells[index] = new DateCell("", false, null);
        }

        // Loop through days in the month
        for (int day = 1; day <= daysInMonth; day++) {
            // Set a local index variable to the leading blank days plus the day its up to
            // minus 1
            int index = leadingBlankDays + day - 1;

            // Accesses that day in the date cells and assigns it to a new date cell with
            // the day its up to
            Calendar cellDate = (Calendar) date.clone();
            cellDate.set(Calendar.DAY_OF_MONTH, day);

            cells[index] = new DateCell(
                    String.valueOf(day),
                    true,
                    cellDate);
        }

        return cells;
    }
}
