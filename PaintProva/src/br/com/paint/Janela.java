package br.com.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Janela extends JFrame {

	JButton color = new JButton("Escolha a cor");
	JButton abrir = new JButton("Abrir");
	JButton save = new JButton("Salvar");
	JPanel panel = new JPanel();
	Color cor = Color.BLACK;
	JColorChooser paletaCores;
	File file = null;
	JFileChooser chooser = new JFileChooser();
	Color newColor;
	ComponenteDesenho c1;

	public Janela() {

		super("Paint");
		setVisible(true);
		setSize(620, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initComponents();

	}

	public void initComponents() {

		getContentPane().setLayout(null);

		color.setBounds(10, 80, 150, 25);
		abrir.setBounds(0, 0, 150, 25);
		save.setBounds(150, 0, 150, 25);

		c1 = new ComponenteDesenho(cor);

		c1.setBounds(10, 130, 600, 420);

		c1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		getContentPane().add(color);
		getContentPane().add(abrir);
		getContentPane().add(save);
		getContentPane().add(c1);

		abrir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int res = fc.showOpenDialog(null);

				if (res == JFileChooser.APPROVE_OPTION) {
					File arquivo = fc.getSelectedFile();
					JOptionPane.showMessageDialog(null,
							"Voce escolheu o arquivo: " + arquivo.getName());
					BufferedImage img = null;
					try {
						img = ImageIO.read(arquivo);
						Graphics carregarImg = img.getGraphics();
						carregarImg.drawImage(img, 0, 0, null);
					} catch (IOException error) {
						error.printStackTrace();
					}
				} else
					JOptionPane.showMessageDialog(null,
							"Voce nao selecionou nenhum arquivo.");
			}
		});

		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * JFileChooser fc = new JFileChooser(); int res =
				 * fc.showSaveDialog(null);
				 * 
				 * if (res == JFileChooser.APPROVE_OPTION) { File arquivo = new
				 * File("savedimage." + ".jpg"); BufferedImage bi = new
				 * BufferedImage(c1.getWidth(), c1.getHeight(),
				 * BufferedImage.TYPE_INT_RGB); try { ImageIO.write(bi, ".jpg",
				 * arquivo); } catch (IOException e1) { e1.printStackTrace(); }
				 * JOptionPane.showMessageDialog(null, "O arquivo foi salvo " +
				 * arquivo.getName()); } else
				 * JOptionPane.showMessageDialog(null,
				 * "Problema ao salvar o arquivo.");
				 */
				Rectangle r = getBounds();
				int j = 0;
				try {
					BufferedImage i = new BufferedImage(r.width, r.height,
							BufferedImage.TYPE_INT_RGB);
					Graphics g = i.getGraphics();
					paint(g);
					ImageIO.write(i, "PNG",
							new File("C:/", "PAINT-EXAMPLE.png"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		color.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				c1.setCor(JColorChooser.showDialog(Janela.this,
						"Selecione uma cor... ", c1.getCor()));

			}
		});

	}
}