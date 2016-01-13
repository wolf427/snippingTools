/**
 * Author : Yufei,T
 * Time : 4:40:53 PM
 * Description :
 */
package personal.tool.mode.handleimage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import personal.tool.panel.ImageHandlePanel;
import personal.tool.panel.ImageHandleToolsbarPanel;
import personal.tool.panel.ToolsbarPanel;

public class ImageHandleFrame extends JFrame {

    /**
     * Creates new form ToolbarForSnippedImageFrame
     */
	private BufferedImage image;
    
	public ImageHandleFrame() {
        initComponents();
    }

    /**
	 * @param image
	 * @throws HeadlessException
	 */
	public ImageHandleFrame(BufferedImage image) throws HeadlessException {
		this.image = image;
		initComponents();
	}

	/**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {
    	setTitle("Image Handle");

        toolsbarpanel = new ImageHandleToolsbarPanel(image);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       
        getContentPane().add(toolsbarpanel,BorderLayout.NORTH);
        
        imageHandlePanel = new ImageHandlePanel(image);
        
        getContentPane().add(imageHandlePanel,BorderLayout.CENTER);

        pack();
        setSize((int)(Math.max(imageHandlePanel.getWidth(),toolsbarpanel.getWidth())*1.2), 
        		(int)((imageHandlePanel.getHeight()+toolsbarpanel.getHeight())*1.2));
       
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    	int x = (int)(dimension.getWidth()-getWidth())>>1;
    	int y = (int)(dimension.getHeight()-getHeight())>>2;
        setLocation(x, y);
    }                        

    private ToolsbarPanel toolsbarpanel;
    private ImageHandlePanel imageHandlePanel;
}


