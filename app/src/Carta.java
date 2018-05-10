/**
	Juan Carlos Sanchez A01631462
*/
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Carta extends JPanel implements MouseListener{
	private boolean disponible;
	private int clave;
	private Image background;
	private String categoria;
	
	public Carta(){
		this.setPreferredSize(new Dimension(50,50));
		this.ocultar();
		this.disponible=true;
		this.addMouseListener(this);
		this.categoria="";
	}
	
	public void setClave(int clave){
		this.clave=clave;
	}
	
	public void setDisponible(boolean disponible){
		this.disponible=disponible;
	}
	
	public void setCategoria(String categoria){
		this.categoria=categoria;
	}
	
	public String getCategoria(){
		return this.categoria;
	}
	
	public boolean getDisponible(){
		return this.disponible;
	}
	public int getClave(){
		return this.clave;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (this.background != null) {
			g.drawImage(this.background, 0, 0, this.getWidth(), this.getHeight(), null);
		}
	}

	public void setFondo(String ruta) {
		this.background = new ImageIcon(ruta).getImage();
		repaint();
	}
	
	public void ocultar(){
		this.setFondo("fondo.jpg");
		this.setDisponible(true);
	}

	public void mouseClicked(MouseEvent e) {
		if(this.disponible){
			this.setFondo(this.categoria+"/"+this.categoria+this.clave+".jpg");
		}
	}
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {	}
}