import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;


public class StartClass {
	public static void main(String[] args){
		Variables.myGui = new Gui();
		Gui.updateGui();
}
	
	@SuppressWarnings("serial")
	public static class paintIt extends JComponent{
		public void paint(Graphics g){
			Graphics graph2 = (Graphics2D)g;
			graph2.fillRect(0, Variables.height - 100, Variables.width, 100);
		}
		
	}
}
