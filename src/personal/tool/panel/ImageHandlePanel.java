/**
 * Author : Yufei,T
 * Time : 4:47:30 PM
 * Description :
 */
package personal.tool.panel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImageHandlePanel extends JPanel{
	
	private BufferedImage image;

	public ImageHandlePanel(BufferedImage image) {
		super();
		this.image = image;
		this.setVisible(true);
		this.setPreferredSize(new Dimension((int)(image.getWidth()*1.2),(int)(image.getHeight()*1.2)));
	}
	
	@Override
	public void paint(Graphics g) {
		Dimension dimension = this.getSize();
		int x = (int)(dimension.getWidth() - image.getWidth())>>1;
		int y = (int)(dimension.getHeight() - image.getHeight())>>1;
        g.drawImage(image,x , y, null); 
    }

}

