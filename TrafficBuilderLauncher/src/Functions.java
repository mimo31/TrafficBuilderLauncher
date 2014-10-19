import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.omg.CORBA.portable.InputStream;


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
	
	public static byte[] downloadFile(String address){
		byte[] output = null;
		try {
			URL url = new URL(address);
			java.io.InputStream inStream = url.openStream();
			int length = -1;
			while ((length = inStream.read(output)) > -1){
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
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
