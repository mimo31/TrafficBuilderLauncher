import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;


public class StartClass {
	public static void main(String[] args){
		Variables.updateApproved = true;
		Variables.myGui = new Gui();
		Gui.updateGui();
}
	
	@SuppressWarnings("serial")
	public static class paintIt extends JComponent{
		public void paint(Graphics g){
			Graphics2D graph2 = (Graphics2D)g;
			graph2.fillRect(0, Variables.height - 70, Variables.width, 70);
			graph2.setColor(Color.white);
			graph2.fillRect(5, Variables.height - 65, 140, 60);
			graph2.setColor(Color.black);
			graph2.setFont(new Font("Arial", Font.BOLD, 20));
			graph2.drawString("Play", 55, (int) Variables.height - 25);
		}
		
	}
}
