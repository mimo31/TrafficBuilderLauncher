import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.event.MouseInputAdapter;


@SuppressWarnings("serial")
public class Gui extends JFrame{
	public Gui() {
		this.setTitle("Traffic Builder Launcher");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2);
		this.setLocation(this.getWidth() / 2, this.getHeight() / 2);
		this.setMinimumSize(new Dimension(200, 200));
		this.setVisible(true);
		this.getContentPane().addMouseListener(new InterfaceMouseEvents());
		this.getContentPane().addMouseMotionListener(new InterfaceMouseEvents());
		this.addComponentListener(new ComponentEvents());
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(final java.awt.event.WindowEvent windowEvent) {
				FileDriver.onClose();
			}
		});
		this.add(new paintIt());
	}
	
	public static void updateGui(){
		Container Test = Variables.myGui.getContentPane();
		Variables.height = Test.getHeight();
		Variables.width = Test.getWidth();
		Variables.myGui.repaint();
	}
	
	public static class paintIt extends JComponent{
		public void paint(Graphics g){
			screen.paint(g);
		}
	}
	
	private class InterfaceMouseEvents extends MouseInputAdapter {
		
		public void mouseClicked(MouseEvent e){
			screen.mouseClicked(e);
		}

		public void mouseEntered(MouseEvent arg0) {
			
			
		}

		public void mouseExited(MouseEvent arg0) {
			
			
		}

		public void mousePressed(MouseEvent e) {
			screen.mousePressed(e);
		}

		public void mouseReleased(MouseEvent e) {
			screen.mouseReleased(e);
		}

		@Override
		public void mouseMoved(final MouseEvent event) {
			Variables.lastMousePosition = event.getPoint();
			updateGui();
			event.consume();
		}
		
		public void mouseDragged(final MouseEvent event){
			Variables.lastMousePosition = event.getPoint();
			updateGui();
			event.consume();
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
