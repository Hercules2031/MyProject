import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.text.NumberFormatter;

public class MyTimer {

    public static void main(String[] args) throws InterruptedException {
        SwingUtilities.invokeLater(MyTimer::new);
    }

    private final JButton startButton, resetButton;
    private final JTextField minutes;
    private final JTextField seconds;
    private static final int SECOND = 1000;

    private Timer timer;

    MyTimer() {
        JFrame frame = new JFrame();
        //frame.setSize(300, 200);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        /*
         * The following lines ensure that the user can
         * only enter numbers.
         */
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);

        minutes = new JFormattedTextField(formatter);
        seconds = new JFormattedTextField(formatter);
        minutes.setText("0");
        seconds.setText("0");

        /*
         * When the user presses the OK-button, the program will
         * start to count down.
         */
        startButton = new JButton("OK");
        startButton.addActionListener(actionEvent -> {
            timer();
        });

        resetButton = new JButton("Reset");
        resetButton.setEnabled(false);
        resetButton.addActionListener(actionEvent -> {
            reset();
        });

        JPanel subpanel1 = new JPanel(new GridLayout(1, 2));
        subpanel1.add(minutes);
        subpanel1.add(seconds);

        JPanel subpanel2 = new JPanel();
        subpanel2.add(startButton);
        subpanel2.add(resetButton);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(subpanel1, BorderLayout.CENTER);
        panel.add(subpanel2, BorderLayout.PAGE_END);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    void timer() {

        startButton.setEnabled(false);

        timer = new Timer(SECOND, e->{

            final int min = Integer.valueOf(minutes.getText());
            final int sec = Integer.valueOf(seconds.getText());
            if(sec == 0 && min == 0){
                reset();
            }else if(sec >= 1) {
                seconds.setText(String.valueOf(sec-1));
            }
            else if(sec == 0 || min > 0) {
                seconds.setText(String.valueOf(59));
                minutes.setText(String.valueOf(min-1));
            }
        });

        timer.setRepeats(true);
        timer.start();
        resetButton.setEnabled(true);
    }

    private void reset() {

        if(timer != null) {
            timer.stop();
        }
        minutes.setText("0");
        seconds.setText("0");
        startButton.setEnabled(true);
    }
}
