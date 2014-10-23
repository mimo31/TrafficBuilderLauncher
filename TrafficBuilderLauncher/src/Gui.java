import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
		this.getContentPane().addMouseListener(new MouseEvents());
		this.addComponentListener(new ComponentEvents());
	}
	
	public static void updateGui(){
		Container Test = Variables.myGui.getContentPane();
		Variables.height = (int) Test.getSize().getHeight();
		Variables.width = (int) Test.getSize().getWidth();
		Variables.myGui.add(new StartClass.paintIt());
	}
	
	public class MouseEvents implements MouseListener{

		
		public void mouseClicked(MouseEvent e){
			System.out.println("Breakpoint1");
			System.out.println(e.getPoint().getX());
			System.out.println(e.getPoint().getY());
			System.out.println(Variables.width);
			System.out.println(Variables.height);
			if(5 < e.getPoint().getX() && e.getPoint().getX() < 145 && Variables.height - 65 < e.getPoint().getY() && e.getPoint().getY() < Variables.height - 5){
				try {
					System.out.println("Breakpoint2");
					FileDriver.initializeFiles();
				} catch (Exception e1) {
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
