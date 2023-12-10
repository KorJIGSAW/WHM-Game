import javax.sound.sampled.Clip;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class TimerThread extends Thread {
    private int timeLeft;
    private JProgressBar progressBar;
    private GFrame gFrame;
    Clip clip;

    public TimerThread(int timeLimit, JProgressBar progressBar, GFrame gFrame, Clip clip) {
        this.timeLeft = timeLimit;
        this.progressBar = progressBar;
        this.gFrame = gFrame;
        this.clip = clip;
    }

    @Override
    public void run() {
        while (timeLeft > 0 && !Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);  // Wait for 1 second
            } catch (InterruptedException e) {
                return;
            }
            timeLeft--;
            final int finalTimeLeft = timeLeft;
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    progressBar.setValue(finalTimeLeft);
                    progressBar.setString(finalTimeLeft + " 초");
                }
            });
        }
        if(!Thread.currentThread().isInterrupted()){
            SwingUtilities.invokeLater(new Runnable(){
                @Override
                public void run(){
                    new TimeOverFrame();
                    clip.stop();
                    gFrame.dispose();
                    //임의로 잠깐 timeoverFrame을 만들어서 넣어놨음/
                    //추후에 첫 프레임 메인 메뉴 프레임이 완성되면 그 프레임을 되돌려 보낼 것.
                }
            });
        }
    }
}
