import java.io.IOException;
import java.nio.file.Files;


public class FileDriver {
	public static void runGame(String version) throws Exception{
		if(Files.exists(Functions.getInGameDirPath(""))){
			if(Files.exists(Functions.getInGameDirPath("\\Resources\\Default")) == false){
				downloadDefaultResources();
			}
			else {
				updateDefaultResources();
			}
			if(isVersionOnLocal(version) == false){
				downloadVersion(version);
			}
			if(Files.exists(Functions.getInGameDirPath("\\SmallData\\LatestResources.txt")) == false){
				Functions.writeTextToFile("Default", System.getenv("APPDATA") + "\\TrafficBuilder\\SmallData\\LatestResources.txt", false);
			}
		}
		else{
			Files.createDirectory(Functions.getInGameDirPath(""));
			Files.createDirectory(Functions.getInGameDirPath("\\Resources"));
			Files.createDirectory(Functions.getInGameDirPath("\\Saves"));
			Files.createDirectory(Functions.getInGameDirPath("\\Game"));
			Files.createDirectory(Functions.getInGameDirPath("\\SmallData"));
			downloadDefaultResources();
			downloadVersion(version);
			Functions.writeTextToFile("Default", System.getenv("APPDATA") + "\\TrafficBuilder\\SmallData\\LatestResources.txt", false);
		}
		Runtime.getRuntime().exec("javaw -jar " + System.getenv("APPDATA") + "\\TrafficBuilder\\Game\\main" + version + ".jar -Xmx 1g");;
		onClose();
		System.exit(0);
	}
	
	public static void onLoad(){
		getVersionList();
		if(Files.exists(Functions.getInGameDirPath(""))){
			Variables.lastChosenVersion = Functions.readTextFile(Functions.getInGameDirPath("\\SmallData\\LatestVersion.txt"));
		}
		else {
			Variables.lastChosenVersion = "Latest";
		}
	}
	
	public static void onClose() {
		if(Files.exists(Functions.getInGameDirPath("")) == false) {
			try {
				Files.createDirectory(Functions.getInGameDirPath(""));
				Files.createDirectory(Functions.getInGameDirPath("\\Resources"));
				Files.createDirectory(Functions.getInGameDirPath("\\Saves"));
				Files.createDirectory(Functions.getInGameDirPath("\\Game"));
				Files.createDirectory(Functions.getInGameDirPath("\\SmallData"));
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		Functions.writeTextToFile(Variables.lastChosenVersion, Functions.getInGameDirPath("\\SmallData\\LatestVersion.txt"), false);
	}
	
	public static void downloadVersion(String version) {
		Functions.writeBytesToFile(Functions.downloadFile("http://gjk.cz/~xfukv01/TrafficBuilder/Versions/main" + version + ".jar"), Functions.getInGameDirPath("\\Game\\main" + version + ".jar"), false);
	}
	
	public static boolean isVersionOnLocal(String version){
		return Files.exists(Functions.getInGameDirPath("\\Game\\main" + version + ".jar"));
	}
	
	public static void getVersionList(){
		String ReadedFile = Functions.downloadTextFile("http://gjk.cz/~xfukv01/TrafficBuilder/Versions/METADATA.txt");
		int counter = 0;
		int counter2 = 0;
		final char lineSeparator = System.getProperty("line.separator").toCharArray()[0];
		int lineSeparatorCount = 0;
		while(counter < ReadedFile.length()){
			if(ReadedFile.toCharArray()[counter] == lineSeparator){
				lineSeparatorCount++;
			}
			counter++;
		}
		Variables.versionList = new String[lineSeparatorCount + 1];
		counter = 0;
		while(counter < ReadedFile.length()){
			if(ReadedFile.toCharArray()[counter] == lineSeparator){
				Variables.versionList[counter2] = ReadedFile.substring(0, counter);
				ReadedFile = ReadedFile.substring(counter + 2);
				counter = 0;
				counter2++;
			}
			else{
				counter++;
			}
		}
		Variables.versionList[counter2] = ReadedFile;
	}
	
	public static void getResource(String name) throws Exception{
		Functions.writeBytesToFile(Functions.downloadFile("http://gjk.cz/~xfukv01/TrafficBuilder/Resources/" + name), System.getenv("APPDATA") + "\\TrafficBuilder\\Resources\\Default\\" + name, true);
	}
	
	public static void downloadDefaultResources() {
		try{
			Files.createDirectory(Functions.getInGameDirPath("\\Resources\\Default"));
			final String metaText = Functions.downloadTextFile("http://gjk.cz/~xfukv01/TrafficBuilder/Resources/METADATA.txt");
			final Resource[] resources = getAllResources(metaText);
			Functions.writeTextToFile(metaText, Functions.getInGameDirPath("\\Resources\\Default\\METADATA.txt"), false);
			int counter = 0;
			while(counter < resources.length){
				getResource(resources[counter].name);
				counter++;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updateDefaultResources() throws Exception{
		final String localMeta = Functions.readTextFile(Functions.getInGameDirPath("\\Resources\\Default\\METADATA.txt"));
		final String serverMeta = Functions.downloadTextFile("http://gjk.cz/~xfukv01/TrafficBuilder/Resources/METADATA.txt");
		final Resource[] localResources = getAllResources(localMeta);
		final Resource[] serverResources = getAllResources(serverMeta);
		int counter = 0;
		while(counter < serverResources.length){
			int counter2 = 0;
			while(counter2 < localResources.length && localResources[counter2].name.equals(serverResources[counter]) == false){
				counter2++;
			}
			if(counter2 == localResources.length){
				getResource(serverResources[counter].name);
			}
			else {
				if(serverResources[counter].version.equals(localResources[counter2].version) == false){
					getResource(serverResources[counter].name);
				}
			}
			counter++;
		}
		Functions.writeTextToFile(serverMeta, Functions.getInGameDirPath("\\Resources\\Default\\METADATA.txt"), false);
	}
	
	public static Resource[] getAllResources(String text){
		int counter = 0;
		int newLines = 0;
		while(counter < text.length()){
			if(text.toCharArray()[counter] == '\r'){
				newLines++;
			}
			counter++;
		}
		String[] lines = new String[newLines + 1];
		Resource[] resources = new Resource[newLines + 1];
		counter = 0;
		int counter2 = 0;
		while(counter < text.length()){
			if(text.toCharArray()[counter] == '\r'){
				lines[counter2] = text.substring(0, counter);
				text = text.substring(counter + 2);
				counter = 0;
				counter2++;
			}
			else{
				counter++;
			}
		}
		lines[counter2] = text;
		counter = 0;
		while(counter < lines.length){
			resources[counter] = new Resource(lines[counter]);
			counter++;
		}
		return resources;
	}
}
