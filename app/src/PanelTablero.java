/**
	Juan Carlos Sanchez A01631462
*/
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class PanelTablero extends JPanel implements MouseListener {
	private static final byte NUM_DE_CARTAS = 16;
	private int intentos,ganador;
	private byte banderaR;
	private Carta[] cartas = new Carta[NUM_DE_CARTAS];
	private ArrayList<Carta> seleccion = new ArrayList<Carta>();
	private PanelControles pc;
	private Color cFondo = new Color(255,222,173),
				  cMarco = Color.WHITE,
				  cLetra = Color.BLACK;
	
	public PanelTablero(){
		super();
		this.setPreferredSize(new Dimension(1000, 750));
		this.setBackground(cFondo);
		this.agregarTablero();
		this.banderaR=0;
	}

	public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(cMarco);
        g.fillOval(230, 230, 500, 200);
        g.setColor(cLetra);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Memorama", 400, 290);
        g.setFont(new Font("Arial", Font.BOLD, 25));
        g.drawString("POO Proyecto Final 2016", 340, 320);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Juan Carlos Sanchez A01631462", 350, 360);
        g.drawString("Emilio Silva A01225431", 390, 385);
    }
	
	public byte getBanderaR(){
		return this.banderaR;
	}
	public void setPanelControl(PanelControles pc){
		this.pc=pc;
	}
	
	public void cambiarCategoria(String categoria){
		for(Carta c:this.cartas){
			c.setCategoria(categoria);
		}
	}
	
	public void reiniciar(){
		String safeCategoria=this.cartas[0].getCategoria();
		for(int i=0;i<this.cartas.length;i++){
			this.cartas[i].ocultar();
		}
		if(this.seleccion.size()!=0){
			this.seleccion.clear();
		}
		this.intentos=0;
		this.ganador=0;
		this.pc.setTextIntentos(this.intentos);
		if(this.cartas!=null){
			for(Carta c:this.cartas){
				this.remove(c);
			}
		}
		this.cartas=ordenaCartas();
		this.cambiarCategoria(safeCategoria);
		this.cMarco=this.cFondo;
		this.cLetra=this.cFondo;
		this.banderaR=1;
	}
	
	private Carta[] ordenaCartas(){
		int[] claves = getClaves();
		Carta[] cartasNuevas = new Carta[this.cartas.length];
		for(int i=0;i<this.cartas.length;i++){
			Carta c=new Carta();
			cartasNuevas[i]=c;
			c.setClave(claves[i]);
		}
		agregarCartas(cartasNuevas);
		return cartasNuevas;
	}
	
	public void salir(){
		for(Carta c:this.cartas){
			c.setVisible(false);
		}
		this.intentos=0;
		this.ganador=0;
		this.pc.setTextIntentos(this.intentos);
		this.banderaR=1;
		this.cMarco=Color.WHITE;
		this.cLetra=Color.BLACK;
	}
	
	public boolean getVisibilidad(){
		return true;
	}
	
	public void agregarTablero(){
		this.setLayout(new GridLayout(4,4,5,5));
		this.cartas=ordenaCartas();
		for(Carta c:this.cartas){
			c.setVisible(false);
		}
		this.intentos=0;
		this.ganador=0;
	}
	
	private void agregarCartas(Carta[] cartas){
		for(Carta c:cartas){
			c.addMouseListener(this);
			this.add(c);
		}
	}
	
	private void eventoCarta(MouseEvent e){
		for(int i=0;i<this.cartas.length;i++){
			if(e.getSource()==this.cartas[i]){
				//System.out.println(this.cartas[i].getSize());
				if(this.cartas[i].getDisponible()){
					this.seleccion.add(cartas[i]);
					cartas[i].setDisponible(false);
				}
				this.banderaR=0;
			}
		}
	}
	
	private void eventoComparar(MouseEvent e){
		if(this.seleccion.size()==2){
			int clave1 = this.seleccion.get(0).getClave(), 
				clave2 = this.seleccion.get(1).getClave();
			if(clave1>8){clave1-=8;}
			if(clave2>8){clave2-=8;}
			if(clave1!=clave2){
				this.seleccion.get(0).ocultar();
				this.seleccion.get(1).ocultar();
				this.intentos++;
				this.pc.setTextIntentos(this.intentos);
			}else{
				this.seleccion.get(0).setDisponible(false);
				this.seleccion.get(1).setDisponible(false);
				this.ganador++;
				this.intentos++;
				this.pc.setTextIntentos(this.intentos);
			}
			this.seleccion.clear();
		}
		if(this.ganador==8){
			JOptionPane.showMessageDialog(null, "Ganaste en "+this.intentos+" intentos!");
		}
	}
	
	public int getIntentos(){
		return this.intentos;
	}
	
	public int[] getClaves() {
		int[] numeros = new int[16];
		for(int i= 0;i<numeros.length;i++){
			numeros[i]=i+1;
		}
		Random x=new Random();
        int tmp;
        int pR;
        for(int i=0;i<numeros.length;i++){
            pR = x.nextInt(16);
            tmp = numeros[i];
            numeros[i] = numeros[pR];
            numeros[pR] = tmp;
        }
        return numeros;
    }/*
	public int[] getClaves() {
		int[] numeros = new int[16];
		for(int i= 0;i<numeros.length;i++){
			numeros[i]=i+1;
		}
		Random x=new Random();
        int tmp;
        int pR;
        for(int i=0;i<numeros.length;i++){
            pR = x.nextInt(16);
            tmp = numeros[i];
            numeros[i] = numeros[pR];
            numeros[pR] = tmp;
        }
        return numeros;
    }*/

	public void mouseClicked(MouseEvent e) {
		this.eventoCarta(e);
	}
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {
		if(this.ganador!=8){
			this.eventoComparar(e);
		}
	}
}