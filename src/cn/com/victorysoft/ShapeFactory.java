package cn.com.victorysoft;

import java.util.Random;

/**
 * 
 * @ClassName: Block
 * @Description: Shape¹¤³§
 * @author wangyong
 * @date Jan 24, 2011 2:52:18 PM
 * @version 1.0
 */
public class ShapeFactory {
	public static Shape getShape() {
		Shape shape = null;
		Random random = new Random();
		int i = random.nextInt(7);
		int j = random.nextInt(4);
		switch (i) {
		case 0:
			shape = new Shape0();
			break;
		case 1:
			shape = new Shape1();
			break;
		case 2:
			shape = new Shape2();
			break;
		case 3:
			shape = new Shape3();
			break;
		case 4:
			shape = new Shape4();
			break;
		case 5:
			shape = new Shape5();
			break;
		case 6:
			shape = new Shape6();
			break;
		}
		for (int k = 0; k < j; k++){
			shape.up();
		}
		return shape;
	}
}
