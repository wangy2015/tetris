/**
 * 
 */
package cn.com.victorysoft;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

/**
 * @ClassName: Main
 * @Description: ´°ÌåÖ÷³ÌÐò
 * @author wangyong
 * @date Jan 24, 2011 2:45:05 PM
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Main extends JFrame {
	public Ground g;
	public Thread t;

	public Main() {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(320, 500);
		g = new Ground(12, 20);
		this.add(g);
		this.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (!g.running || g.isNext)
					return;
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					if (g.canDown()) {
						g.down();
					}
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					if (g.canLeft()) {
						g.left();
					}
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					if (g.canRight()) {
						g.right();
					}
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					if (g.canUp()) {
						g.up();
					}
				}
			}

			public void keyReleased(KeyEvent e) {
				g.isNext = false;
			}

			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == ' ') {
					Thread.State state = t.getState();
					System.out.println(state);
					if (state.equals(Thread.State.NEW)) {
						t.start();
					} else if (state.equals(Thread.State.TIMED_WAITING)
							|| state.equals(Thread.State.RUNNABLE)) {
						g.suspend();
					} else if (state.equals(Thread.State.WAITING)) {
						System.out.println("WAITING");
						g.resume();
					}
				}
			}
		});
		t = new Thread(g);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new Main();
	}
}
