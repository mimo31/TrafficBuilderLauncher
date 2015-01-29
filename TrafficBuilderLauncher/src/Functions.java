import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Functions {
	public static Path getInGameDirPath(String InDirPath){
		return Paths.get(System.getenv("APPDATA") + "\\TrafficBuilder" + InDirPath);
	}
	
	public static Rectangle getStringBounds(Graphics2D g2, String str, float x, float y) {
        FontRenderContext frc = g2.getFontRenderContext();
        GlyphVector gv = g2.getFont().createGlyphVector(frc, str);
        return gv.getPixelBounds(null, x, y);
    }
	
	
	public static void writeBytesToFile(byte[] Bytes, String path, boolean append){
		FileOutputStream out;
		try {
			out = new FileOutputStream(path);
			out.write(Bytes);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void writeTextToFile(String text, String path, boolean append){
		try {
			BufferedWriter bufw = new BufferedWriter(new FileWriter(path, true));
			bufw.write(text);
			bufw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void drawChangRect(final Graphics2D graph2, final Color normalColor, final Color onMouseColor, final int x, final int y, final int width, final int height){
		if(new Rectangle(x, y, width, height).contains(Variables.lastMousePosition)){
			graph2.setColor(onMouseColor);
		}
		else{
			graph2.setColor(normalColor);
		}
		graph2.fillRect(x, y, width, height);
	}

	public static void drawChangRect(final Graphics2D graph2, final Color normalColor, final Color onMouseColor, final Rectangle rectangle){
		drawChangRect(graph2, normalColor, onMouseColor, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
	}
	
	public static byte[] downloadFile(String address) throws Exception{
		URL url = new URL(address);
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		InputStream is = url.openStream();
		
		byte[] data = new byte[1024];
		int nRead;
		
		
		while ((nRead = is.read(data, 0, data.length)) != -1){
			buffer.write(data, 0, nRead);
		}
		buffer.flush();
		return buffer.toByteArray();
	}
	
	public static byte[] readBytes(String path) throws Exception{
		InputStream input = new FileInputStream(new File(path));
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		byte[] data = new byte[1024];
		int nRead;
		
		
		while ((nRead = input.read(data, 0, data.length)) != -1){
			buffer.write(data, 0, nRead);
		}
		buffer.flush();
		input.close();
		return buffer.toByteArray();
	}

	public static String readTextFile(String path){
		String[] ReadedLines = null;
		Path filePath = Paths.get(path);
		try {
			ReadedLines =  Files.readAllLines(filePath, Charset.defaultCharset()).toArray(new String[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String output = ReadedLines[0];

		int counter = 1;
		while(counter < ReadedLines.length){
			output = output + System.getProperty("line.separator") + ReadedLines[counter];
			counter++;
		}
		return output;
	}
}
