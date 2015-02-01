import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.Timer;


public class screen {
	static boolean versionsClicked = false;
	static float verListPosition;
	static ActionListener scrollUp = new ActionListener(){
		@Override
		public void actionPerformed(final ActionEvent arg0)
		{
			verListPosition = (float) (verListPosition - 0.1f);
			if(getUpTriangle().contains(Variables.lastMousePosition) == false){
				scrollingUp.stop();
			}
			if(verListPosition < 0){
				verListPosition = 0;
				scrollingUp.stop();
			}
			Gui.updateGui();
		}
	};
	static ActionListener scrollDown = new ActionListener(){
		@Override
		public void actionPerformed(final ActionEvent arg0)
		{
			verListPosition = (float) (verListPosition + 0.1f);
			if(getDownTriangle().contains(Variables.lastMousePosition) == false){
				scrollingDown.stop();
			}
			if(verListPosition > (Variables.versionList.length + 0.5f)){
				verListPosition = Variables.versionList.length + 0.5f;
				scrollingDown.stop();
			}
			Gui.updateGui();
		}
	};
	static Timer scrollingDown = new Timer(25, scrollDown);
	static Timer scrollingUp = new Timer(25, scrollUp);
	public static void paint(Graphics g){
		Graphics2D graph2 = (Graphics2D)g;
		final int barHeight = Variables.height / 8;
		graph2.fillRect(0, Variables.height - barHeight, Variables.width, barHeight);
		final Rectangle button;
		if(((int) Math.round((Variables.height / 8) * 2.5 - 25)) < Variables.width / 2){
			button = new Rectangle(5, Variables.height - Variables.height / 8 + 5, (int) Math.round((Variables.height  / 8 - 10) * 2.5), Variables.height  / 8 - 10);
		}
		else {
			button = new Rectangle(5, Variables.height - Variables.height / 8 + 5, Variables.width / 2 - 10, Variables.height  / 8 - 10);
		}
		if(button.contains(Variables.lastMousePosition)){
			graph2.setColor(Color.red);
			graph2.fill(button);
			graph2.setColor(Color.yellow);
			StringDraw.drawMaxString(graph2, button.height / 4, "WOW", StringDraw.Middle, button, Font.BOLD);			
		}
		else {
			graph2.setColor(Color.white);
			graph2.fill(button);
			graph2.setColor(Color.black);
			StringDraw.drawMaxString(graph2, button.height / 4, "Play", StringDraw.Middle, button, Font.BOLD);
		}
		graph2.setColor(Color.white);
		final Rectangle verTextBounds = new Rectangle(Variables.width / 8 * 5, Variables.height - barHeight / 4 * 3, Variables.width / 8, barHeight / 2);
		StringDraw.drawMaxString(graph2, "Versions:", StringDraw.Right, verTextBounds);
		if(versionsClicked){
			graph2.setColor(Color.black);
			final Rectangle listBorder = new Rectangle(Variables.width / 4 * 3 + Variables.width / 32, 0, Variables.width / 128, Variables.height);
			graph2.fill(listBorder);
			final Rectangle versionList = new Rectangle(Variables.width / 4 * 3 + Variables.width / 32 + Variables.width / 128, 0, Variables.width - listBorder.x - listBorder.width, Variables.height);
			graph2.setColor(Color.lightGray);
			graph2.fill(versionList);
			final String[] versions = getVersions();
			final int verRectHeight = Variables.width / 24;
			final int verRectWidth;
			final boolean showMoveBar = (Variables.height / verRectHeight < versions.length);
			if(showMoveBar){
				final Rectangle scrollTroll = new Rectangle(Variables.width - Variables.width / 48, 0, Variables.width / 48, Variables.height);
				graph2.setColor(Color.green);
				graph2.fill(scrollTroll);
				final Polygon downTriangle = getDownTriangle();
				if(downTriangle.contains(Variables.lastMousePosition)){
					graph2.setColor(Color.black);
				}
				else {
					graph2.setColor(Color.white);
				}
				graph2.fill(downTriangle);
				final Polygon upTriangle = getUpTriangle();
				if(upTriangle.contains(Variables.lastMousePosition)){
					graph2.setColor(Color.black);
				}
				else {
					graph2.setColor(Color.white);
				}
				graph2.fill(upTriangle);
				verRectWidth = (Variables.width / 4 - Variables.width / 32 - Variables.width / 128 - Variables.width / 48) / 5 * 4;
			}
			else {
				verRectWidth = (Variables.width / 4 - Variables.width / 32 - Variables.width / 128) / 5 * 4;
			}
			int spaceUsed = (int) (verRectHeight * (Math.floor(verListPosition) - verListPosition + 1));
			int lastVersionPainted = (int) Math.floor(verListPosition);
			final Rectangle verRect = new Rectangle(Variables.width / 4 * 3 + Variables.width / 32 + Variables.width / 128, Variables.height - spaceUsed, verRectWidth, verRectHeight);
			if(versions[lastVersionPainted].equals(Variables.lastChosenVersion)){
				graph2.setColor(Color.cyan);
			}
			else {
				if(verRect.contains(Variables.lastMousePosition)){
					graph2.setColor(Color.yellow);
				}
				else {
					graph2.setColor(Color.white);
				}
			}
			graph2.fill(verRect);
			final String versionName;
			final String versionNumber;
			if(versions[lastVersionPainted].equals("Latest")){
				versionName = "Latest (" + Variables.versionList[0] + ")";
				versionNumber = Variables.versionList[0];
			}
			else {
				versionName = versions[lastVersionPainted];
				versionNumber = versions[lastVersionPainted];
			}
			graph2.setColor(Color.black);
			StringDraw.drawMaxString(graph2, verRect.height / 4, versionName, StringDraw.Left, verRect, Font.BOLD);
			if(FileDriver.isVersionOnLocal(versionNumber)){
				graph2.setColor(Color.green);
			}
			else {
				graph2.setColor(Color.red);
			}
			final Rectangle localDetectRect;
			if(showMoveBar){
				localDetectRect = new Rectangle(verRect.x + verRect.width, verRect.y, Variables.width - Variables.width / 4 * 3 - Variables.width / 32 - Variables.width / 128 - Variables.width / 48 - verRect.width , verRect.height);
			}
			else {
				localDetectRect = new Rectangle(verRect.x + verRect.width, verRect.y, Variables.width - Variables.width / 4 * 3 - Variables.width / 32 - Variables.width / 128 - verRect.width , verRect.height);
			}
			graph2.fill(localDetectRect);
			while((spaceUsed < Variables.height) && (lastVersionPainted < versions.length - 1)){
				final Rectangle curVerRect = new Rectangle(Variables.width / 4 * 3 + Variables.width / 32 + Variables.width / 128, Variables.height - spaceUsed - verRectHeight, verRectWidth, verRectHeight);
				if(versions[lastVersionPainted + 1].equals(Variables.lastChosenVersion)){
					graph2.setColor(Color.cyan);
				}
				else {
					if(curVerRect.contains(Variables.lastMousePosition)){
						graph2.setColor(Color.yellow);
					}
					else {
						graph2.setColor(Color.white);
					}
				}
				graph2.fill(curVerRect);
				graph2.setColor(Color.black);
				StringDraw.drawMaxString(graph2, curVerRect.height / 4, versions[lastVersionPainted + 1], StringDraw.Left, curVerRect, Font.BOLD);
				if(FileDriver.isVersionOnLocal(versions[lastVersionPainted + 1])){
					graph2.setColor(Color.green);
				}
				else {
					graph2.setColor(Color.red);
				}
				final Rectangle curLocalDetectRect;
				if(showMoveBar){
					curLocalDetectRect = new Rectangle(curVerRect.x + curVerRect.width, curVerRect.y, Variables.width - Variables.width / 4 * 3 - Variables.width / 32 - Variables.width / 128 - Variables.width / 48 - curVerRect.width , curVerRect.height);
				}
				else {
					curLocalDetectRect = new Rectangle(curVerRect.x + curVerRect.width, curVerRect.y, Variables.width - Variables.width / 4 * 3 - Variables.width / 32 - Variables.width / 128 - curVerRect.width , curVerRect.height);
				}
				graph2.fill(curLocalDetectRect);
				lastVersionPainted++;
				spaceUsed = spaceUsed + verRectHeight;
			}
		}
		else {
			final Rectangle versionsTextbox = new Rectangle(Variables.width / 4 * 3 + Variables.width / 32, Variables.height - barHeight / 4 * 3, Variables.width / 4 - Variables.width / 16, barHeight / 2);
			final Rectangle verTxtboxWithoutBounds = new Rectangle(versionsTextbox.x + Variables.width / 128, versionsTextbox.y + Variables.width / 128, versionsTextbox.width - Variables.width / 64, versionsTextbox.height - Variables.width / 64);
			graph2.setColor(Color.black);
			graph2.fill(versionsTextbox);
			if(versionsTextbox.contains(Variables.lastMousePosition)){
				graph2.setColor(Color.yellow);
			}
			else {
				graph2.setColor(Color.white);
			}
			graph2.fill(verTxtboxWithoutBounds);
			final Rectangle TxtboxTextBounds = Functions.addBounds(verTxtboxWithoutBounds, verTxtboxWithoutBounds.height / 8);
			graph2.setColor(Color.black);
			if(Variables.lastChosenVersion.equals("Latest")){
				StringDraw.drawMaxString(graph2, "Latest (" + Variables.versionList[0] + ")", StringDraw.Left, TxtboxTextBounds);
			}
			else {
				StringDraw.drawMaxString(graph2, Variables.lastChosenVersion, StringDraw.Left, TxtboxTextBounds);
			}
		}
	}
	
	public static void mouseClicked(MouseEvent event){
		final Rectangle playButton;
		if(((int) Math.round((Variables.height / 8) * 2.5 - 25)) < Variables.width / 2){
			playButton = new Rectangle(5, Variables.height - Variables.height / 8 + 5, (int) Math.round((Variables.height  / 8 - 10) * 2.5), Variables.height  / 8 - 10);
		}
		else {
			playButton = new Rectangle(5, Variables.height - Variables.height / 8 + 5, Variables.width / 2 - 10, Variables.height  / 8 - 10);
		}
		final int barHeight = Variables.height / 8;
		final Rectangle verTextbox = new Rectangle(Variables.width / 4 * 3 + Variables.width / 32, Variables.height - barHeight / 4 * 3, Variables.width / 4 - Variables.width / 16, barHeight / 2);
		if(versionsClicked == false){
			if(playButton.contains(event.getPoint())){
				try {
					final String versionNumber;
					if(Variables.lastChosenVersion.equals("Latest")){
						versionNumber = Variables.versionList[0];
					}
					else{
						versionNumber = Variables.lastChosenVersion;
					}
					FileDriver.runGame(versionNumber);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if(verTextbox.contains(event.getPoint())) {
				versionsClicked = true;
				Gui.updateGui();
			}
		}
		else{
			if(event.getX() < Variables.width / 4 * 3) {
				versionsClicked = false;
				Gui.updateGui();
			}
			else if(event.getX() > Variables.width / 4 * 3 + Variables.width / 128 + Variables.width / 32){
				final String[] versions = getVersions();
				final int verRectHeight = Variables.width / 24;
				final boolean showMoveBar = (Variables.height / verRectHeight < versions.length);
				if(showMoveBar && (event.getX() < Variables.width - Variables.width / 48 - (Variables.width / 4 - Variables.width / 32 - Variables.width / 128 - Variables.width / 48) / 5) ||
							((showMoveBar == false) && (event.getX() < Variables.width - (Variables.width / 4 - Variables.width / 32 - Variables.width / 128) / 5))){
					final int relToListClickPos = (int) Math.floor((Variables.height - event.getY()) / verRectHeight + verListPosition);
					if(versions.length > relToListClickPos){
						Variables.lastChosenVersion = versions[relToListClickPos];
						Gui.updateGui();
					}
				}
			}
		}
	}
	
	public static int getTriangleHeight(){
		return (int) (Math.sqrt(3) * Variables.width / 48 * 3 / 4 / 2);
	}
	
	public static Polygon getUpTriangle(){
		return new Polygon(new int[]{Variables.width - Variables.width / 48 * 7 / 8, Variables.width - Variables.width / 384, Variables.width - Variables.width / 96},
				new int[]{Variables.height - Variables.width / 384 - getTriangleHeight(), Variables.height - Variables.width / 384 - getTriangleHeight(), Variables.height - Variables.width / 384},
				3);
	}
	
	public static Polygon getDownTriangle(){
		return new Polygon(new int[]{Variables.width - Variables.width / 96, Variables.width - Variables.width / 384, Variables.width - Variables.width / 48 + Variables.width / 384},
				new int[]{Variables.width / 384, Variables.width / 384 + getTriangleHeight(), Variables.width / 384 + getTriangleHeight()},
				3);
	}
	
	public static String[] getVersions(){
		String[] versions = new String[Variables.versionList.length + 1];
		versions[0] = "Latest";
		int counter = 0;
		while(counter < Variables.versionList.length){
			versions[counter + 1] = Variables.versionList[counter];
			counter++;
		}
		return versions;
	}
	
	public static void mousePressed(MouseEvent event){
		if(Variables.height / (Variables.width / 24) < Variables.versionList.length + 1){
			if(getUpTriangle().contains(event.getPoint())){
				scrollingUp.start();
			}
			else if(getDownTriangle().contains(event.getPoint())){
				scrollingDown.start();
			}
		}
	}
	
	public static void mouseReleased(MouseEvent event){
		scrollingUp.stop();
		scrollingDown.stop();
	}
}
