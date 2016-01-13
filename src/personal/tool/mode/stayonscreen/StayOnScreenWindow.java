/**
 * Author : Yufei,T
 * Time : 3:24:33 PM
 * Description :
 */
package personal.tool.mode.stayonscreen;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JWindow;


public class StayOnScreenWindow extends JWindow{
	private BufferedImage image;
	private int pressedPointX;
	private int pressedPointY;
	private int startX;
	private int startY;
	
	private boolean isConstringent = false;
	
	private JPopupMenu rightMenu = null;

	public StayOnScreenWindow(BufferedImage image){
		super();
		this.image = image;
//		ImageIcon imageIcon = new ImageIcon(image);
//		JLabel imageLable = new JLabel(imageIcon);
		this.setSize(image.getWidth(), image.getHeight());
		this.setLayout(new BorderLayout());
//		this.add(imageLable,BorderLayout.CENTER);
		
		this.setAlwaysOnTop(true);
		
		rightMenu = new StayImageMenu(); 
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				pressedPointX = e.getX();
				pressedPointY = e.getY();
				
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				//Though once click will do nothing , So I can use this way to ignore once clicked event
				if(e.getClickCount() == 2 ){
					doubleClickedEvent(e);
				}
				if(e.getButton()==MouseEvent.BUTTON3){
					showRightMenu(e);
				}
				
			}
			
		});
		
		this.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				addMoveListener(e);
			}
		});
		
	}
	
	@Override
	public void paint(Graphics g){
		g.drawImage(image,0,0,this);
		
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(Color.RED);
		g2d.setStroke(new BasicStroke(1.0f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND,
				2.0f,new float[]{15,10,},0f));
		g2d.drawRect(0, 0, getWidth()-1, getHeight()-1);
	}
	
	public void showRightMenu(MouseEvent e) {
		rightMenu.show(this,(int)e.getX(),(int)e.getY());
	}
	
	public void doubleClickedEvent(MouseEvent e){
		if(!isConstringent){
			int x = (int) ((e.getLocationOnScreen().getX()-25)>0?(e.getLocationOnScreen().getX()-25):0);
			int y = (int) ((e.getLocationOnScreen().getY()-25)>0?(e.getLocationOnScreen().getY()-25):0);
			this.setSize(50, 50);
			this.setLocation(x, y);
			isConstringent = true;
		}else{
			int x = (int) (e.getLocationOnScreen().getX() - image.getWidth()/2);
			x = x>0?x:0;
			int y = (int) (e.getLocationOnScreen().getY() - image.getHeight()/2);
			y = y>0?y:0;
			this.setSize(image.getWidth(), image.getHeight());
			this.setLocation(x, y);
			isConstringent = false;
		}
		repaint();
	}
	
	public void addMoveListener(MouseEvent e){
		startX = this.getLocation().x;
		startY = this.getLocation().y;
		int offsetX = e.getX() - pressedPointX;
		int offsetY = e.getY() - pressedPointY;
		this.setLocation(startX+offsetX, startY+offsetY);
	}
	

}

