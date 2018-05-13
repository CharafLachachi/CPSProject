package gui;



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import components.Cow;
import components.Engine;
import components.Environement;
import components.Player;
import services.Dir;
import services.EngineService;
import services.EntityService;
import services.EnvironnementService;


public class Labyrinthe extends JApplet implements KeyListener {

	private static final long serialVersionUID = -5413967253672451073L;


	private static final String I_avancer = "avancer.gif";

	private static final String I_gauche = "gauche.gif";

	private static final String I_droite = "droite.gif";

	private static final String I_reculer = "reculer.gif";

	private static final String I_nord = "nord.gif";

	private static final String I_ouest = "ouest.gif";

	private static final String I_sud = "sud.gif";

	private static final String I_est = "est.gif";


	private EngineService labyrinthe = new Engine();
	
	private JPlanDangeon planLabyrinthe;

	private JButton avancer, gauche, droite, reculer;



	private ImageIcon[] vent = new ImageIcon[4];


	/** Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String nomDuFichier) {
		java.net.URL imgURL = Labyrinthe.class.getResource("images/"
				+ nomDuFichier);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Fichier introuvable: " + nomDuFichier);
			return null;
		}
	}

	private JPanel panelJeux() {
		JPanel c = new JPanel();
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
		avancer = new JButton(createImageIcon(I_avancer));
		gauche = new JButton(createImageIcon(I_gauche));
		droite = new JButton(createImageIcon(I_droite));
		reculer = new JButton(createImageIcon(I_reculer));
		avancer.setBorderPainted(false);
		gauche.setBorderPainted(false);
		droite.setBorderPainted(false);
		reculer.setBorderPainted(false);
	//	gauche.addActionListener(this);
	//	avancer.addActionListener(this);
	//	droite.addActionListener(this);
	//	reculer.addActionListener(this);
		JPanel p = new JPanel();
		JPanel m = new JPanel(new GridLayout(2, 1));
		m.add(avancer);
		m.add(reculer);
		p.add(gauche);
		p.add(m);
		p.add(droite);
		c.add(p);
		p = new JPanel();
		c.add(p);
		c.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
		return c;
	}



	public void init() {
		EntityService cow = new Cow();
		EntityService player = new Player();
		EnvironnementService env = new Environement();
		env.init(500, 500);
		
		cow.init(env, 3, 3, Dir.N,4);
		player.init(env, 0, 0, Dir.N);
		labyrinthe.init(env);
		labyrinthe.addEntity(player);
		labyrinthe.addEntity(cow);
		cow.step();
		
		
		vent[0] = createImageIcon(I_nord);
		vent[1] = createImageIcon(I_ouest);
		vent[2] = createImageIcon(I_sud);
		vent[3] = createImageIcon(I_est);
		
		planLabyrinthe = new JPlanDangeon(labyrinthe);
		JSplitPane ll = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				planLabyrinthe, null);
		ll.setResizeWeight(0.0);
		ll.setOneTouchExpandable(true);
		ll.setContinuousLayout(true);
		JPanel p = new JPanel(new BorderLayout());
		p.add(ll, BorderLayout.CENTER);
		p.add(panelJeux(), BorderLayout.SOUTH);
	
		p.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		actualiserBoutons();
		getContentPane().add(p);
	}

	public static void main(String s[]) {
		JFrame frame = new JFrame("Le Labyrinthe");
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		JApplet applet = new Labyrinthe();
		frame.getContentPane().add(applet, BorderLayout.CENTER);
		applet.init();
		frame.setSize(new Dimension(950, 750));
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		frame.setVisible(true);
		
	}
//
	public void actionPerformed(ActionEvent e) {
		Object s = e.getSource();
		 if (s == gauche)
			labyrinthe.getEntity(0).turnL();
		else if (s == avancer)
			labyrinthe.getEntity(0).forward();
		else if (s == reculer)
			labyrinthe.getEntity(0).backward();
		else if (s == droite)
			labyrinthe.getEntity(0).turnR();
		
		actualiserBoutons();
	}


	private void actualiserBoutons() {
		planLabyrinthe.repaint();

	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("je click");
		System.err.println(e.getKeyCode());
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("je click");

		System.err.println(e.getKeyCode());
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.err.println(e.getKeyCode());
		
	}
}
