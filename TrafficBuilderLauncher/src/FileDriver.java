import java.io.File;
import java.nio.file.Files;


public class FileDriver  {
	public static void initializeFiles() throws Exception{
		if(Files.exists(Functions.getInGameDirPath(""))){
			if(Files.exists(Functions.getInGameDirPath("\\Resources"))){
				if(new File(System.getenv("APPDATA") + "\\TrafficBuilder\\Resources").listFiles() == null){
					downloadDefaultResources();
				}
			}
			else{
				Files.createDirectory(Functions.getInGameDirPath("\\Resources"));
				downloadDefaultResources();
			}
		}
		else{
			Files.createDirectory(Functions.getInGameDirPath(""));
			Files.createDirectory(Functions.getInGameDirPath("\\Resources"));
			Files.createDirectory(Functions.getInGameDirPath("\\Saves"));
			Files.createDirectory(Functions.getInGameDirPath("\\Game"));
			Files.createDirectory(Functions.getInGameDirPath("\\SmallData"));
			downloadDefaultResources();
			Functions.writeTextToFile("Default", System.getenv("APPDATA") + "\\TrafficBuilder\\SmallData\\LatestResources.txt", true);
			Functions.writeBytesToFile(Functions.downloadFile("http://gjk.cz/~xfukv01/TrafficBuilder/main.jar"), System.getenv("APPDATA") + "\\TrafficBuilder\\Game\\main.jar", true);
		}
		//Runtime.getRuntime().exec("javaw -jar " + System.getenv("APPDATA") + "\\TrafficBuilder\\Game\\main.jar -Xmx 1g");;
		System.exit(0);
	}
	
	public static void getResource(String InternetName, String LocalName) throws Exception{
		Functions.writeBytesToFile(Functions.downloadFile("http://gjk.cz/~xfukv01/TrafficBuilder/" + InternetName), System.getenv("APPDATA") + "\\TrafficBuilder\\Resources\\Default\\" + LocalName, true);
	}
	
	public static void downloadDefaultResources() throws Exception{
			Files.createDirectory(Functions.getInGameDirPath("\\Resources\\Default"));
			getResource("Font.font", "Font.font");
	}
}
