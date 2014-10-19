import java.awt.Toolkit;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class Gui extends JFrame{
	public Gui() {
		this.setTitle("Traffic Builder Launcher");
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2);
		this.setVisible(true);
	}
}
