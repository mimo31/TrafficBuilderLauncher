import java.io.IOException;

public class StartClass {
	
	public static void main(String[] args) throws IOException{
		FileDriver.onLoad();
		Variables.myGui = new Gui();
		Gui.updateGui();
		System.out.println("The First one: " + Variables.versionList[0]);
		System.out.println("The Second one: " + Variables.versionList[1]);
	}
	
}
