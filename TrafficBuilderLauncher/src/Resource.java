
public class Resource {
	public String name;
	public String version;
	
	public Resource(String name, String version){
		this.name = name;
		this.version = version;
	}
	
	public Resource(String line){
		int counter = 0;
		while(line.toCharArray()[counter] != ' '){
			counter++;
		}
		this.name = line.substring(0, counter);
		this.version = line.substring(counter + 1);
	}
}
