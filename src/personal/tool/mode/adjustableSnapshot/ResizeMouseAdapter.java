/**
 * Author : Yufei,T
 * Time : 10:40:17 AM
 * Description :
 */
package personal.tool.mode.adjustableSnapshot;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ResizeMouseAdapter extends MouseAdapter {
	private Component c;
	
	private boolean isTopLeft;
	private boolean isTop;
	private boolean isTopRight;
	private boolean isRight;
	private boolean isBottomRight;
	private boolean isBottom;
	private boolean isBottomLeft;
	private boolean isLeft;
	private final static int RESIZE_WIDTH = 5;
	private final static int MIN_WIDTH = 20;
	private final static int MIN_HEIGHT = 20;
	
	public ResizeMouseAdapter(Component c) {
		this.c = c;
	}
	
	@Override
	public void mouseMoved(MouseEvent event) {
		int x = event.getX();
		int y = event.getY();
		int width = c.getWidth();
		int height = c.getHeight();
		int cursorType = Cursor.DEFAULT_CURSOR;
		
		isTopLeft = isTop = isTopRight = isRight = isBottomRight = isBottom = isBottomLeft = isLeft = false;
		if (y <= RESIZE_WIDTH) {
			if (x <= RESIZE_WIDTH) {
				isTopLeft = true;
				cursorType = Cursor.NW_RESIZE_CURSOR;
			} else if (x >= width - RESIZE_WIDTH) {
				isTopRight = true;
				cursorType = Cursor.NE_RESIZE_CURSOR;
			} else {
				isTop = true;
				cursorType = Cursor.N_RESIZE_CURSOR;
			}
		} else if (y >= height - RESIZE_WIDTH) {
			if (x <= RESIZE_WIDTH) {
				isBottomLeft = true;
				cursorType = Cursor.SW_RESIZE_CURSOR;
			} else if (x >= width - RESIZE_WIDTH) {
				isBottomRight = true;
				cursorType = Cursor.SE_RESIZE_CURSOR;
			} else {
				isBottom = true;
				cursorType = Cursor.S_RESIZE_CURSOR;
			}
		} else if (x <= RESIZE_WIDTH) {
			isLeft = true;
			cursorType = Cursor.W_RESIZE_CURSOR;
		} else if (x >= width - RESIZE_WIDTH) {
			isRight = true;
			cursorType = Cursor.E_RESIZE_CURSOR;
		}
		
		c.setCursor(new Cursor(cursorType));
	}
	
	@Override
	public void mouseDragged(MouseEvent event) {
		int x = event.getX();
		int y = event.getY();
		int width = c.getWidth();
		int height = c.getHeight();
		
		int nextX = c.getX();
		int nextY = c.getY();
		int nextWidth = width;
		int nextHeight = height;
		if (isTopLeft || isLeft || isBottomLeft) {
			nextX += x;
			nextWidth -= x;
		}
		if (isTopLeft || isTop || isTopRight) {
			nextY += y;
			nextHeight -= y;
		}
		if (isTopRight || isRight || isBottomRight) {
			nextWidth = x;
		}
		if (isBottomLeft || isBottom || isBottomRight) {
			nextHeight = y;
		}
		if (nextWidth <= MIN_WIDTH) {
			nextWidth = MIN_WIDTH;
			if (isTopLeft || isLeft || isBottomLeft) {
				nextX = c.getX() + width - nextWidth;
			}
		}
		if (nextHeight <= MIN_HEIGHT) {
			nextHeight = MIN_HEIGHT;
			if (isTopLeft || isTop || isTopRight) {
				nextY = c.getY() + height - nextHeight;
			}
		}
		
		c.setBounds(nextX, nextY, nextWidth, nextHeight);
	}
}

