/**
 * Author : Yufei,T
 * Time : 10:34:13 AM
 * Description : This class supports main function of the tool
 */
package personal.tool.frame;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

import javax.swing.JWindow;

import personal.tool.mode.handleimage.ImageHandleFrame;
import personal.tool.mode.stayonscreen.StayOnScreenWindow;
import personal.tool.utils.GlobalSettings;

public class SnapshotWindow extends JWindow {
	private BufferedImage screenImage;
	private BufferedImage whiteImage;
	private Point startPoint;
	private Point endPoint;

	public SnapshotWindow() throws AWTException {
		super();
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(new Rectangle(dimension));

		snapshotScreen(dimension);
		darkScreenImage();
		registerMouseListener();
		registerMouseMotionListener4Drag();

		this.setFocusableWindowState(false);
		this.setFocusable(true);
		this.setAlwaysOnTop(true);
		// this.setVisible(true);
	}

	private BufferedImage snipImage;

	@Override
	public void paint(Graphics g) {
		g.drawImage(whiteImage, 0, 0, this);
	}

	/**
	 * @time : 17:06:59 AM Sep 10, 2015
	 * @Description : Using more memory resource to eliminate flicker. If use
	 *              repaint directly, there will be flicker appears.
	 */
	@Override
	public void repaint() {
		if (startPoint != null && endPoint != null) {
			Image bufferImage = createImage(getWidth(), getHeight());
			Graphics bufferGraphics = bufferImage.getGraphics();
			bufferGraphics.drawImage(whiteImage, 0, 0, this);
			int x = Math.min((int) startPoint.getX(), (int) endPoint.getX());
			int y = Math.min((int) startPoint.getY(), (int) endPoint.getY());
			int width = Math.abs((int) startPoint.getX()
					- (int) endPoint.getX());
			int height = Math.abs((int) startPoint.getY()
					- (int) endPoint.getY());
			if (width == 0 || height == 0)
				return;
			snipImage = screenImage.getSubimage(x, y, width, height);
			bufferGraphics.drawImage(snipImage, x, y, this);
			bufferGraphics.dispose();
			getGraphics().drawImage(bufferImage, 0, 0, this);
		}
	}

	/**
	 * @time : 11:06:59 AM Sep 10, 2015
	 * @Description : Get whole screen snapshot, result is stroed into
	 *              screenImage
	 * @throws AWTException
	 */
	public void snapshotScreen(Dimension dimension) throws AWTException {
		Robot robot = new Robot();
		screenImage = robot.createScreenCapture(new Rectangle(dimension));
	}

	/**
	 * @time : 11:08:58 AM Sep 10, 2015
	 * @Description : For static snapshot, we are used to put a dark image as
	 *              back image. So the original image should be darken.
	 */
	public void darkScreenImage() {
		RescaleOp ro = new RescaleOp(0.9f, 0, null);
		whiteImage = ro.filter(screenImage, null);
	}

	public void registerMouseListener() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				startPoint = new Point(e.getX(), e.getY());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// endPoint = new Point(e.getX(), e.getY());
				if (snipImage == null)
					return;
				dispose();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						if ("imageHandleMode".equals(GlobalSettings.getMode())) {
							imageHandleMode();
						} else if ("stayOnScreenMode".equals(GlobalSettings
								.getMode())) {
							stayOnScreenMode(e);
						}
					}

				});
			}
		});
	}

	public void imageHandleMode() {
		BufferedImage subImage = copyImage(snipImage);
		disposeImageResources();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ImageHandleFrame(subImage).setVisible(true);
			}
		});
	}

	public void stayOnScreenMode(MouseEvent e) {
		BufferedImage subImage = copyImage(snipImage);
		disposeImageResources();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				StayOnScreenWindow stayOnScreenWindow = new StayOnScreenWindow(
						subImage);
				stayOnScreenWindow.setLocation((int) startPoint.getX(),
						(int) startPoint.getY());
				stayOnScreenWindow.setVisible(true);
				new StartFrame().setVisible(true);
			}
		});
	}

	public void disposeImageResources() {
		screenImage.flush();
		whiteImage.flush();
		screenImage = null;
		whiteImage = null;
	}

	public void registerMouseMotionListener4Drag() {
		this.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				endPoint = new Point(e.getX(), e.getY());
				repaint();
			}
		});
	}

	public BufferedImage copyImage(BufferedImage source) {
		BufferedImage b = new BufferedImage(source.getWidth(),
				source.getHeight(), source.getType());
		Graphics g = b.getGraphics();
		g.drawImage(source, 0, 0, null);
		g.dispose();
		return b;
	}

}
