import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class Gui extends JFrame{
	public Gui() {
		this.setTitle("Traffic Builder Launcher");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2);
		this.setLocation(this.getWidth() / 2, this.getHeight() / 2);
		this.setMinimumSize(new Dimension(200, 200));
		this.setVisible(true);
		this.addMouseListener(new MouseEvents());
		this.addComponentListener(new ComponentEvents());
	}
	
	public static void updateGui() {
		Container Test = Variables.myGui.getContentPane();
		Variables.height = Test.getHeight();
		Variables.width = Test.getWidth();
		Variables.myGui.add(new StartClass.paintIt());
	}
	
	public class MouseEvents implements MouseListener{

		
		public void mouseClicked(MouseEvent e){
			
			if(5 < e.getX() && e.getX() < 145 && Variables.height - 65 < e.getY() && e.getY() < Variables.height - 5){
				try {
					FileDriver.initializeFiles();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
		}

		public void mouseEntered(MouseEvent arg0) {
			
			
		}

		public void mouseExited(MouseEvent arg0) {
			
			
		}

		public void mousePressed(MouseEvent arg0) {
			
			
		}

		public void mouseReleased(MouseEvent arg0) {
			
			
		}
		
	}
	
	public class ComponentEvents implements ComponentListener{

		public void componentHidden(ComponentEvent arg0) {
			
			
		}

		public void componentMoved(ComponentEvent arg0) {
			
			
		}

		public void componentResized(ComponentEvent arg0) {
			updateGui();
			
		}

		public void componentShown(ComponentEvent arg0) {
			
			
		}
		
	}
}
