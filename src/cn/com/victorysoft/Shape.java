/**
 * 
 */
package cn.com.victorysoft;

/**
 * @ClassName: Shape
 * @Description: ÐÎ×´Àà
 * @author wangyong
 * @date Jan 24, 2011 3:28:35 PM
 * @version 1.0
 */
public abstract class Shape implements Cloneable {
	protected final static int NORTH = 0;
	protected final static int WEST = 1;
	protected final static int SOUTH = 2;
	protected final static int EAST = 3;
	protected int direction = NORTH;
	protected Block[] blockArr = new Block[4];

	public int getTop() {
		Integer result = null;
		for (Block b : blockArr) {
			result = (result == null || result > b.getY()) ? b.getY() : result;
		}
		return result;
	}

	public int getBottom() {
		Integer result = null;
		for (Block b : blockArr) {
			result = (result == null || result < b.getY()) ? b.getY() : result;
		}
		return result;
	}

	public int getLeft() {
		Integer result = null;
		for (Block b : blockArr) {
			result = (result == null || result > b.getX()) ? b.getX() : result;
		}
		return result;
	}

	public int getRight() {
		Integer result = null;
		for (Block b : blockArr) {
			result = (result == null || result < b.getX()) ? b.getX() : result;
		}
		return result;
	}

	public Shape down(int i) {
		for (Block b : blockArr) {
			b.down(i);
		}
		return this;
	}

	public Shape left(int i) {
		for (Block b : blockArr) {
			b.left(i);
		}
		return this;
	}

	public Shape right(int i) {
		for (Block b : blockArr) {
			b.right(i);
		}
		return this;
	}

	public abstract Shape up();

	public boolean contain(int x, int y) {
		for (Block b : blockArr) {
			if (x == b.getX() && y == b.getY())
				return true;
		}
		return false;
	}

	public Object clone() {
		Shape shape = null;
		try {
			shape = (Shape) super.clone();
			Block[] copyArr = new Block[4];
			for (int i = 0; i < blockArr.length; i++) {
				copyArr[i] = (Block) blockArr[i].clone();
			}
			shape.blockArr = copyArr;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return shape;
	}
}

class Shape0 extends Shape {
	public Shape0() {
		blockArr[0] = new Block(-1, -1);
		blockArr[1] = new Block(0, -1);
		blockArr[2] = new Block(1, -1);
		blockArr[3] = new Block(2, -1);
	}

	@Override
	public Shape up() {
		switch (direction) {
		case NORTH:
			blockArr[0].up().up().up();
			blockArr[1].up().up().left();
			blockArr[2].up().left().left();
			blockArr[3].left().left().left();
			direction = WEST;
			break;
		case WEST:
			blockArr[0].down().down().down();
			blockArr[1].down().down().right();
			blockArr[2].down().right().right();
			blockArr[3].right().right().right();
			direction = NORTH;
			break;
		}
		return this;
	}
}

class Shape1 extends Shape {
	public Shape1() {
		blockArr[0] = new Block(0, -3);
		blockArr[1] = new Block(0, -2);
		blockArr[2] = new Block(1, -2);
		blockArr[3] = new Block(1, -1);
	}

	@Override
	public Shape up() {
		switch (direction) {
		case NORTH:
			blockArr[0].down().down();
			blockArr[1].down().right();
			blockArr[3].up().right();
			direction = WEST;
			break;
		case WEST:
			blockArr[0].up().up();
			blockArr[1].up().left();
			blockArr[3].down().left();
			direction = NORTH;
			break;
		}
		return this;
	}
}

class Shape2 extends Shape {
	public Shape2() {
		blockArr[0] = new Block(1, -3);
		blockArr[1] = new Block(1, -2);
		blockArr[2] = new Block(0, -2);
		blockArr[3] = new Block(0, -1);
	}

	@Override
	public Shape up() {
		switch (direction) {
		case NORTH:
			blockArr[0].down().left();
			blockArr[2].down().right();
			blockArr[3].right().right();
			direction = WEST;
			break;
		case WEST:
			blockArr[0].up().right();
			blockArr[2].up().left();
			blockArr[3].left().left();
			direction = NORTH;
			break;
		}
		return this;
	}
}

class Shape3 extends Shape {
	public Shape3() {
		blockArr[0] = new Block(0, -3);
		blockArr[1] = new Block(0, -2);
		blockArr[2] = new Block(0, -1);
		blockArr[3] = new Block(1, -1);
	}

	@Override
	public Shape up() {
		switch (direction) {
		case NORTH:
			blockArr[0].down().down();
			blockArr[1].down().right();
			blockArr[2].right().right();
			blockArr[3].right().up();
			direction = WEST;
			break;
		case WEST:
			blockArr[0].right();
			blockArr[1].up();
			blockArr[2].left().up().up();
			blockArr[3].left().left().up();
			direction = SOUTH;
			break;
		case SOUTH:
			blockArr[0].right().up();
			blockArr[2].left().down();
			blockArr[3].down().down();
			direction = EAST;
			break;
		case EAST:
			blockArr[0].left().left().up();
			blockArr[1].left();
			blockArr[2].down();
			blockArr[3].right();
			direction = NORTH;
			break;
		}
		return this;
	}
}

class Shape4 extends Shape {
	public Shape4() {
		blockArr[0] = new Block(1, -3);
		blockArr[1] = new Block(1, -2);
		blockArr[2] = new Block(1, -1);
		blockArr[3] = new Block(0, -1);
	}

	@Override
	public Shape up() {
		switch (direction) {
		case NORTH:
			blockArr[0].left().down();
			blockArr[2].up().right();
			blockArr[3].right().right();
			direction = WEST;
			break;
		case WEST:
			blockArr[0].down();
			blockArr[1].left();
			blockArr[2].left().left().up();
			blockArr[3].left().up().up();
			direction = SOUTH;
			break;
		case SOUTH:
			blockArr[0].right().right();
			blockArr[1].right().down();
			blockArr[2].down().down();
			blockArr[3].down().left();
			direction = EAST;
			break;
		case EAST:
			blockArr[0].left().up().up();
			blockArr[1].up();
			blockArr[2].right();
			blockArr[3].down();
			direction = NORTH;
			break;
		}
		return this;
	}
}

class Shape5 extends Shape {
	public Shape5() {
		blockArr[0] = new Block(0, -2);
		blockArr[1] = new Block(-1, -1);
		blockArr[2] = new Block(0, -1);
		blockArr[3] = new Block(1, -1);
	}

	@Override
	public Shape up() {
		switch (direction) {
		case NORTH:
			blockArr[0].left();
			blockArr[1].right();
			blockArr[2].up();
			blockArr[3].left().up().up();
			direction = WEST;
			break;
		case WEST:
			blockArr[0].down().right();
			blockArr[1].up().right();
			blockArr[3].down().left();
			direction = SOUTH;
			break;
		case SOUTH:
			blockArr[0].up();
			blockArr[1].left().left().up();
			blockArr[2].left();
			blockArr[3].down();
			direction = EAST;
			break;
		case EAST:
			blockArr[1].down().down();
			blockArr[2].down().right();
			blockArr[3].right().right();
			direction = NORTH;
			break;
		}
		return this;
	}
}

class Shape6 extends Shape {
	public Shape6() {
		blockArr[0] = new Block(0, -2);
		blockArr[1] = new Block(1, -2);
		blockArr[2] = new Block(0, -1);
		blockArr[3] = new Block(1, -1);
	}

	@Override
	public Shape up() {
		return this;
	}
}