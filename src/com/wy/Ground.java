package cn.com.victorysoft;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

/**
 * 
 * @ClassName: Ground
 * @Description: ”Œœ∑ΩÁ√Ê
 * @author wangyong
 * @date Jan 24, 2011 3:02:31 PM
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Ground extends JPanel implements Runnable {
	protected int score;
	protected int sleepTime = 800;
	protected int step = 1;
	protected boolean isNext;
	protected Shape shape;
	private int[][] arr;
	private JTable table;
	private JLabel label;
	private TableModel model;
	protected boolean running = true;
	TableCellRenderer renderer = new TableCellRenderer() {

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			Component com = new DefaultTableCellRenderer()
					.getTableCellRendererComponent(table, value, isSelected,
							hasFocus, row, column);
			if (arr[row][column] == 1) {
				com.setBackground(Color.RED);
			}
			return com;
		}

	};

	public Ground(final int width, final int height) {
		super();
		arr = new int[height][width];
		model = new AbstractTableModel() {
			public int getColumnCount() {
				return width;
			}

			public int getRowCount() {
				return height;
			}

			public Object getValueAt(int rowIndex, int columnIndex) {
				return null;
			}
		};
		table = new JTable(model);
		table.setDefaultRenderer(Object.class, renderer);
		table.setEnabled(false);
		table.setRowHeight(20);
		table.setBorder(new LineBorder(Color.GRAY));
		for (int i = 0; i < table.getColumnCount(); i++)
			table.getColumnModel().getColumn(i).setPreferredWidth(20);
		this.add(table);
		label = new JLabel("0");
		this.add(label);
	}

	public void startDown() throws InterruptedException {
		synchronized (this) {
			if (!running)
				wait();
		}
		if (canDown()) {
			down();
			Thread.sleep(200);
		}
		if (!canDown()) {
			int romoveCount = 0;
			for (int i = shape.getTop(); i <= shape.getBottom(); i++) {
				if (isLine(i)) {
					removeRow(i);
					romoveCount++;
				}
			}
			if (romoveCount > 0)
				score += step * (100 * romoveCount + (romoveCount - 1) * 25);
			while (score >= Math.pow(step, 2) * 1000) {
				step++;
				if (sleepTime >= 100 / step + 70)
					sleepTime -= 100 / step + 70;
			}
			label.setText(String.valueOf(score));
			this.repaint();
			isNext = true;
			run();
		}
		Thread.sleep(sleepTime);
		startDown();
	}

	public void run() {
		shape = ShapeFactory.getShape();
		shape.right(model.getColumnCount() / 2);
		try {
			startDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected boolean canDown() {
		for (Block b : shape.blockArr) {
			if (b.getY() >= table.getRowCount() - 1
					|| (b.getY() + 1 >= 0 && arr[b.getY() + 1][b.getX()] == 1 && !shape
							.contain(b.getX(), b.getY() + 1))) {
				return false;
			}
		}
		return true;
	}

	protected boolean canLeft() {
		for (Block b : shape.blockArr) {
			if (b.getX() <= 0
					|| (b.getY() >= 0 && arr[b.getY()][b.getX() - 1] == 1 && !shape
							.contain(b.getX() - 1, b.getY()))) {
				return false;
			}
		}
		return true;
	}

	protected boolean canRight() {
		for (Block b : shape.blockArr) {
			if (b.getX() >= table.getColumnCount() - 1
					|| (b.getY() >= 0 && arr[b.getY()][b.getX() + 1] == 1 && !shape
							.contain(b.getX() + 1, b.getY()))) {
				return false;
			}
		}
		return true;
	}

	protected boolean canUp() {
		Shape tmp = (Shape) shape.clone();
		tmp.up();
		adjustAfterUp(tmp);
		for (Block b : tmp.blockArr) {
			if (b.getY() >= 0 && arr[b.getY()][b.getX()] == 1
					&& !shape.contain(b.getX(), b.getY())) {
				tmp = null;
				return false;
			}
		}
		tmp = null;
		return true;
	}

	protected void down() {
		for (Block b : shape.blockArr) {
			if (b.getY() >= 0) {
				arr[b.getY()][b.getX()] = 0;
			}
		}
		shape.down(1);
		for (Block b : shape.blockArr) {
			if (b.getY() >= 0) {
				arr[b.getY()][b.getX()] = 1;
			}
		}
		this.repaint();
	}

	protected void left() {
		for (Block b : shape.blockArr) {
			if (b.getY() >= 0)
				arr[b.getY()][b.getX()] = 0;
		}
		shape.left(1);
		for (Block b : shape.blockArr) {
			if (b.getY() >= 0)
				arr[b.getY()][b.getX()] = 1;
		}
		this.repaint();
	}

	protected void right() {
		for (Block b : shape.blockArr) {
			if (b.getY() >= 0)
				arr[b.getY()][b.getX()] = 0;
		}
		shape.right(1);
		for (Block b : shape.blockArr) {
			if (b.getY() >= 0)
				arr[b.getY()][b.getX()] = 1;
		}
		this.repaint();
	}

	protected void up() {
		for (Block b : shape.blockArr) {
			if (b.getY() >= 0)
				arr[b.getY()][b.getX()] = 0;
		}
		shape.up();
		adjustAfterUp(shape);
		for (Block b : shape.blockArr) {
			if (b.getY() >= 0)
				arr[b.getY()][b.getX()] = 1;
		}
		this.repaint();
	}

	protected boolean isLine(int row) {
		for (int i : arr[row]) {
			if (i == 0)
				return false;
		}
		return true;
	}

	protected void removeRow(int row) {
		for (int i = row; i > 0; i--) {
			arr[i] = arr[i - 1];
		}
		arr[0] = new int[table.getColumnCount()];
	}

	private void adjustAfterUp(Shape shape) {
		int left = shape.getLeft();
		int right = shape.getRight();
		if (left < 0) {
			shape.right(0 - left);
		}
		if (right >= table.getColumnCount()) {
			shape.left(right - table.getColumnCount() + 1);
		}
	}

	public void suspend() {
		running = false;
	}

	public void resume() {
		running = true;
		synchronized (this) {
			this.notifyAll();
		}
	}
}
