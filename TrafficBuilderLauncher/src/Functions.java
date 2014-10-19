import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;


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
}
