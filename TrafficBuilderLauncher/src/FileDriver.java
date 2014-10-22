import java.io.IOException;
import java.nio.file.Files;


public class FileDriver  {
	public static void initializeFiles() throws IOException{
		if(Files.exists(Functions.getInGameDirPath(""))){
			
		}
		else{
			Files.createDirectory(Functions.getInGameDirPath(""));
			Files.createDirectory(Functions.getInGameDirPath("\\Resources"));
			Files.createDirectory(Functions.getInGameDirPath("\\Saves"));
			Files.createDirectory(Functions.getInGameDirPath("\\Game"));
			Files.createDirectory(Functions.getInGameDirPath("\\SmallData"));
		}
		System.exit(0);
	}
}
