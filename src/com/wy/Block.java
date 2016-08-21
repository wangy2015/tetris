/**
 * 
 */
package cn.com.victorysoft;

/**
 * @ClassName: Block
 * @Description: ·½¿é
 * @author wangyong
 * @date Jan 24, 2011 3:31:20 PM
 * @version 1.0
 */
public class Block implements Cloneable{
	private int x;
	private int y;

	public Block(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Block up(int i) {
		y -= i;
		return this;
	}

	public Block down(int i) {
		y += i;
		return this;
	}

	public Block left(int i) {
		x -= i;
		return this;
	}

	public Block right(int i) {
		x += i;
		return this;
	}

	public Block up() {
		y--;
		return this;
	}

	public Block down() {
		y++;
		return this;
	}

	public Block left() {
		x--;
		return this;
	}

	public Block right() {
		x++;
		return this;
	}

	public String toString() {
		return "x:" + x + ", y:" + y + ";";
	}
	
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
}
