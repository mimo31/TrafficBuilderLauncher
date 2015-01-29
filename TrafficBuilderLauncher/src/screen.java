import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;


public class screen {

	public static void paint(Graphics g){
		Graphics2D graph2 = (Graphics2D)g;
		final int barHeight = Variables.height / 8;
		graph2.fillRect(0, Variables.height - barHeight, Variables.width, barHeight);
		Rectangle button;
		if(((int) Math.round((Variables.height / 8 ) * 2.5 - 25)) < Variables.width / 2){
			button = new Rectangle(5, Variables.height - Variables.height / 8 + 5, (int) Math.round((Variables.height  / 8 - 10) * 2.5), Variables.height  / 8 - 10);
		}
		else {
			button = new Rectangle(5, Variables.height - Variables.height / 8 + 5, Variables.width / 2 - 10, Variables.height  / 8 - 10);
		}
		if(button.contains(Variables.lastMousePosition)){
			graph2.setColor(Color.red);
			graph2.fill(button);
			graph2.setColor(Color.yellow);
			StringDraw.drawMaxString(graph2, button.height / 4, "WOW", StringDraw.Middle, button, Font.BOLD);			
		}
		else {
			graph2.setColor(Color.white);
			graph2.fill(button);
			graph2.setColor(Color.black);
			StringDraw.drawMaxString(graph2, button.height / 4, "Play", StringDraw.Middle, button, Font.BOLD);
		}
		
	}
	
	public static void mouseClicked(MouseEvent event){
		if(5 < event.getPoint().getX() && event.getPoint().getX() < 145 && Variables.height - 65 < event.getPoint().getY() && event.getPoint().getY() < Variables.height - 5){
			try {
				FileDriver.initializeFiles();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
