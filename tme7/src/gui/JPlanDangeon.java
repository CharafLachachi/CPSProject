package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Polygon;

import javax.swing.JComponent;

import services.Cell;
import services.Dir;
import services.EngineService;
import services.EntityService;

public class JPlanDangeon extends JComponent{

	private static final Color BORD = Color.BLACK;

	private static final Color SORTIE = Color.GREEN;

	private static final Color CROIX = Color.YELLOW;

	private static final Color PLAYER = Color.BLUE;
	
	private EngineService labyrinthe;
	
	private int l, c, lc, w, w0, h, h0;
	
	public JPlanDangeon(EngineService engine) {
		this.labyrinthe = engine;
	}
	
	private void actualiserConstantes() {
		l = 5;
		c = 5;
		Dimension size = getSize();
		Insets in = getInsets();
		w = labyrinthe.getEnv().getWidth();
		h = labyrinthe.getEnv().getHeight();
		int lw = (w - in.left - in.right - 6) / c;
		int lh = (h - in.top - in.bottom - 6) / l;
		lc = (lw < lh ? lw : lh);
		w0 = (w - c * lc) / 2;
		h0 = (h - l * lc) / 2;
	}
	
	private void paintPalyer(Graphics g) {
		EntityService voyageur = labyrinthe.getEntity(0);
		int vl = voyageur.getCol();
		int vc = voyageur.getRow();
		Polygon p = new Polygon();
		if (voyageur.getFace() == Dir.N) {
			p.addPoint(w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 4);
			p.addPoint(w0 + vc * lc + lc / 4, h0 + vl * lc + 3 * lc / 4);
			p.addPoint(w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 2);
			p.addPoint(w0 + vc * lc + 3 * lc / 4, h0 + vl * lc + 3 * lc / 4);
		}
		if (voyageur.getFace() == Dir.W) {
			p.addPoint(w0 + vc * lc + lc / 4, h0 + vl * lc + lc / 2);
			p.addPoint(w0 + vc * lc + 3 * lc / 4, h0 + vl * lc + lc / 4);
			p.addPoint(w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 2);
			p.addPoint(w0 + vc * lc + 3 * lc / 4, h0 + vl * lc + 3 * lc / 4);
		}
		if (voyageur.getFace() == Dir.S) {
			p.addPoint(w0 + vc * lc + lc / 2, h0 + vl * lc + 3 * lc / 4);
			p.addPoint(w0 + vc * lc + lc / 4, h0 + vl * lc + lc / 4);
			p.addPoint(w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 2);
			p.addPoint(w0 + vc * lc + 3 * lc / 4, h0 + vl * lc + lc / 4);
		}
		if (voyageur.getFace() == Dir.E) {
			p.addPoint(w0 + vc * lc + 3 * lc / 4, h0 + vl * lc + lc / 2);
			p.addPoint(w0 + vc * lc + lc / 4, h0 + vl * lc + lc / 4);
			p.addPoint(w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 2);
			p.addPoint(w0 + vc * lc + lc / 4, h0 + vl * lc + 3 * lc / 4);
		}
		g.setColor(PLAYER);
		g.fillPolygon(p);
	}
	private void paintCow(Graphics g) {
		EntityService cow = labyrinthe.getEntity(1);
		int vl = cow.getCol();
		int vc = cow.getRow();
		Polygon p = new Polygon();
		if (cow.getFace() == Dir.N) {
			p.addPoint(w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 4);
			p.addPoint(w0 + vc * lc + lc / 4, h0 + vl * lc + 3 * lc / 4);
			p.addPoint(w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 2);
			p.addPoint(w0 + vc * lc + 3 * lc / 4, h0 + vl * lc + 3 * lc / 4);
		}
		if (cow.getFace() == Dir.W) {
			p.addPoint(w0 + vc * lc + lc / 4, h0 + vl * lc + lc / 2);
			p.addPoint(w0 + vc * lc + 3 * lc / 4, h0 + vl * lc + lc / 4);
			p.addPoint(w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 2);
			p.addPoint(w0 + vc * lc + 3 * lc / 4, h0 + vl * lc + 3 * lc / 4);
		}
		if (cow.getFace() == Dir.S) {
			p.addPoint(w0 + vc * lc + lc / 2, h0 + vl * lc + 3 * lc / 4);
			p.addPoint(w0 + vc * lc + lc / 4, h0 + vl * lc + lc / 4);
			p.addPoint(w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 2);
			p.addPoint(w0 + vc * lc + 3 * lc / 4, h0 + vl * lc + lc / 4);
		}
		if (cow.getFace() == Dir.E) {
			p.addPoint(w0 + vc * lc + 3 * lc / 4, h0 + vl * lc + lc / 2);
			p.addPoint(w0 + vc * lc + lc / 4, h0 + vl * lc + lc / 4);
			p.addPoint(w0 + vc * lc + lc / 2, h0 + vl * lc + lc / 2);
			p.addPoint(w0 + vc * lc + lc / 4, h0 + vl * lc + 3 * lc / 4);
		}
		g.setColor(CROIX);
		g.fillPolygon(p);
	}
	
	private void paintCase(Graphics g, int ll, int cc) {
		Cell maCase = labyrinthe.getEnv().getCellNature(ll, cc);
		if (maCase == Cell.WLL) {
			g.setColor(BORD);
			g.fillRect(w0 + cc * lc + 2, h0 + ll * lc + 2, lc - 2, lc - 2);
		}
		if (maCase == Cell.IN) {
			g.setColor(SORTIE);
			g.fillRect(w0 + cc * lc + 2, h0 + ll * lc + 2, lc - 2, lc - 2);
		}
	}
	public void paint(Graphics g) {
		actualiserConstantes();
		for (int ll = 0; ll < l; ll++)
			for (int cc = 0; cc < c; cc++) {
				paintCase(g, ll, cc);
			}
		paintPalyer(g);
		paintCow(g);
		g.setColor(BORD);
		g.drawRect(w0, h0, c * lc + 1, l * lc + 1);
	}
}
