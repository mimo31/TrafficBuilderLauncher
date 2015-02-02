import java.io.IOException;

public class StartClass {
	
	public static void main(String[] args) throws IOException{
		FileDriver.onLoad();
		Variables.myGui = new Gui();
		Gui.updateGui();
	}
	
}
