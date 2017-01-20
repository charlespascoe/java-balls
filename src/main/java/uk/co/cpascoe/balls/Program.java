package uk.co.cpascoe.balls;

import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class Program {
	public static class Surface extends JPanel {
        private World world;

        public World getWorld() { return this.world; }

        public Surface(Vector worldSize) {
            super();

            this.setPreferredSize(new Dimension((int)worldSize.getX(), (int)worldSize.getY()));

            this.world = new World(worldSize, new Vector(500, 500));

            Timer t = new Timer(16, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    world.update(16f/1000);
                    repaint();
                }
            });

            t.setInitialDelay(50);

            t.setRepeats(true);
            t.start();

            this.addMouseMotionListener(new MouseMotionListener() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    world.createBall(new Vector((float)e.getX(), (float)e.getY()));
                }

                @Override
                public void mouseMoved(MouseEvent e) {

                }
            });

        }

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
            Graphics2D graphics = (Graphics2D)g.create();
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            this.world.draw(graphics);
            graphics.dispose();
            Toolkit.getDefaultToolkit().sync();
		}
	}

	public static class BasicEx extends JFrame {

		public BasicEx() {

			initUI();
		}

		private void initUI() {
            Surface s = new Surface(new Vector(800, 600));
			add(s);

			setTitle("Java Balls");
			setSize(800, 600);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                BasicEx ex = new BasicEx();
                ex.setVisible(true);
            }
		});
	}
}
