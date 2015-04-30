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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class Janela extends JFrame {

	JButton corButton = new JButton("Escolha a cor");
	JButton abrirButton = new JButton("Abrir");
	JButton salvarButton = new JButton("Salvar");
	JPanel panel = new JPanel();
	JLabel jl = new JLabel();
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

		corButton.setBounds(10, 80, 150, 25);
		abrirButton.setBounds(0, 0, 150, 25);
		salvarButton.setBounds(150, 0, 150, 25);

		c1 = new ComponenteDesenho(cor);

		c1.setBounds(10, 130, 600, 420);

		c1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		getContentPane().add(corButton);
		getContentPane().add(abrirButton);
		getContentPane().add(salvarButton);
		getContentPane().add(c1);

		/* ABRIR IMAGEM! */
		abrirButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter filtro = new FileNameExtensionFilter(
						"Image Files", "jpeg", "jpg", "gif", "png", "bmp");
				fc.setFileFilter(filtro);
				int res = fc.showOpenDialog(null);

				if (res == JFileChooser.APPROVE_OPTION) {

					File arquivo = fc.getSelectedFile();
					JOptionPane.showMessageDialog(null,
							"Voce escolheu o arquivo: " + arquivo.getName());

					try {

						BufferedImage img = ImageIO.read(arquivo);
						c1.setBi(img);
						c1.repaint();

					} catch (IOException error) {
						error.printStackTrace();
					}
				} else
					JOptionPane.showMessageDialog(null,
							"Voce nao selecionou nenhum arquivo.");
			}
		});

		/* SALVAR IMAGEM! */
		salvarButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Rectangle r = c1.getBounds();
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

		/* SELECIONAR COR! */
		corButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				c1.setCor(JColorChooser.showDialog(Janela.this,
						"Selecione uma cor... ", c1.getCor()));

			}
		});

	}
}