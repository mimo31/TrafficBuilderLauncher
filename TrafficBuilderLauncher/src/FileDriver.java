import java.nio.file.Files;


public class FileDriver  {
	public static void initializeFiles() throws Exception{
		if(Files.exists(Functions.getInGameDirPath(""))){
			
		}
		else{
			Files.createDirectory(Functions.getInGameDirPath(""));
			Files.createDirectory(Functions.getInGameDirPath("\\Resources"));
			Files.createDirectory(Functions.getInGameDirPath("\\Resources\\Default"));
			Files.createDirectory(Functions.getInGameDirPath("\\Saves"));
			Files.createDirectory(Functions.getInGameDirPath("\\Game"));
			Files.createDirectory(Functions.getInGameDirPath("\\SmallData"));
			getResource("Font.ttf", "Font.ttf");
		}
		System.exit(0);
	}
	
	public static void getResource(String InternetName, String LocalName) throws Exception{
			Functions.writeBytesToFile(Functions.downloadFile("http:\\gjk.cz\\~xfukv01\\TrafficBuilder\\" + InternetName), System.getenv("APPDATA") + "TrafficBuilder\\Resources\\Defualt\\" + LocalName, true);
		}
}
