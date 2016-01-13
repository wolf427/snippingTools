/**
 * Author : Yufei,T
 * Time : 10:50:30 AM
 * Description :
 */
package personal.tool.panel;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

import personal.tool.utils.FileSaveDialogue;

public class ImageHandleToolsbarPanel extends ToolsbarPanel{
	
	private BufferedImage image ;
	
	private JButton saveImgButton;

	/**
	 * @param image
	 */
	public ImageHandleToolsbarPanel(BufferedImage image) {
		super();
		this.image = image;
		saveImgButton = new JButton();
		saveImgButton.setText("save");
		saveImgButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				imageSave();
			}
		});
		saveImgButton.setPreferredSize(new Dimension(70, 40));
		add(saveImgButton);
	}
	
	public void imageSave() {
		FileSaveDialogue fileSaveDialogue = new FileSaveDialogue();
		String savePath = fileSaveDialogue.getSavePathByFileChooser();
		if(savePath==null||"".equals(savePath))
			return;
		try {
			ImageIO.write(image, "PNG", new File(savePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

