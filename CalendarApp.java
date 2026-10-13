/*
*   
*   CalendarApp.java
*
*   author: Ephraim Becker
*
*   The CalendarApp class is the main driver program 
*   that will invoke all of our components on the EDT.
*
*/

import java.util.Calendar;
import java.text.DateFormatSymbols;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class CalendarApp {
    private static final Calendar cal = Calendar.getInstance();
    private static final JLabel monthText = new JLabel(
            new DateFormatSymbols().getMonths()[cal.get(Calendar.MONTH)]);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WeekdayHeaders header = new WeekdayHeaders();
            DateCell[] month = DateCell.forMonth(cal);

            CalendarFrame frame = null;

            // Catch the exception on the Calendar
            try {
                frame = new CalendarFrame(cal, monthText, header, month);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}