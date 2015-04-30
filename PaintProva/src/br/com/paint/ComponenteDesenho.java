package br.com.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class ComponenteDesenho extends JComponent implements MouseListener,
		MouseMotionListener {

	private ArrayList<Point> pontos;
	private int size = 8;
	private int halfsize = size / 2;
	private Color cor;

	public ComponenteDesenho(Color cor) {
		this.cor = cor;
		pontos = new ArrayList<Point>(1024);
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public Color getCor() {
		return cor;
	}

	public void setCor(Color cor) {
		this.cor = cor;
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(cor);
		for (Point p : pontos)
			g2d.fillOval(p.x - halfsize, p.y - halfsize, size, size);

	}

	@Override
	public void mousePressed(MouseEvent e) {
		pontos.add(e.getPoint());
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		pontos.add(e.getPoint());
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}
}