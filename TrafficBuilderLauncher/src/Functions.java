import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
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
	public static Rectangle getStringBounds(Graphics2D g2, String str, float x, float y) {
        FontRenderContext frc = g2.getFontRenderContext();
        GlyphVector gv = g2.getFont().createGlyphVector(frc, str);
        return gv.getPixelBounds(null, x, y);
    }
	
	public static int getMaxFontSize(Graphics2D g2, String str, int maxWidth, int maxHeight){
		int Counter = 1;
		g2.setFont(g2.getFont().deriveFont(0, 1));
		while(getStringBounds(g2, str, 0, 0).width < maxWidth && getStringBounds(g2, str, 0, 0).height <  maxHeight){
			Counter++;
			g2.setFont(g2.getFont().deriveFont(0, Counter));
		}
		return Counter - 1;
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
