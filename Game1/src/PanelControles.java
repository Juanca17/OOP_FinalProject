/**
	Juan Carlos Sanchez A01631462
*/
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class PanelControles extends JPanel implements ActionListener{
	private int intentos;
	private JButton bReiniciar,
					bSalir,
					bColores,
					bAnimales,
					bComida,
					bPaisajes;
	private JPanel marco;
	private JLabel lIntentos;
	private PanelTablero pt;
	
	public PanelControles(PanelTablero pt){
		super();
		this.pt = pt;
		this.setPreferredSize(new Dimension(150,750));
		this.setBackground(new Color(139,69,19));
		this.bReiniciar = new JButton("Reiniciar");
		this.bSalir = new JButton("Salir");
		this.bColores = new JButton("Colores");
		this.bAnimales = new JButton("Animales");
		this.bComida = new JButton("Comida");
		this.bPaisajes = new JButton("Paisajes");
		this.lIntentos = new JLabel();
		
		this.bAnimales.addActionListener(this);
		this.add(this.bAnimales);
		
		this.bColores.addActionListener(this);
		this.add(this.bColores);
		
		this.bComida.addActionListener(this);
		this.add(this.bComida);
		
		this.bPaisajes.addActionListener(this);
		this.add(this.bPaisajes);
		
		this.marco=new JPanel();
		this.marco.setPreferredSize(new Dimension(120,100));
		this.marco.setBackground(new Color(255,222,173));
		this.add(marco);
		this.lIntentos.setForeground(Color.BLACK);
		this.lIntentos.setText("Intentos: "+this.intentos);
		this.lIntentos.setFont(new Font("Arial", Font.BOLD, 14));
		this.marco.add(this.lIntentos);
		
		this.bReiniciar.addActionListener(this);
		this.marco.add(this.bReiniciar);
		
		this.bSalir.addActionListener(this);
		this.marco.add(this.bSalir);	
	}
	
	public void setTextIntentos(int intentos){
		this.lIntentos.setText("Intentos: "+intentos);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.bColores){
			this.pt.reiniciar();
			this.pt.updateUI();
			this.pt.cambiarCategoria("color");
		}if(e.getSource() == this.bAnimales){
			this.pt.reiniciar();
			this.pt.updateUI();
			this.pt.cambiarCategoria("animal");
		}if(e.getSource() == this.bComida){
			this.pt.reiniciar();
			this.pt.updateUI();
			this.pt.cambiarCategoria("comida");
		}if(e.getSource() == this.bPaisajes){
			this.pt.reiniciar();
			this.pt.updateUI();
			this.pt.cambiarCategoria("paisaje");
		}if(e.getSource() == this.bReiniciar){
			if(this.pt.getBanderaR()!=1){
				this.pt.reiniciar();
			}
		}if(e.getSource() == this.bSalir){
			this.pt.salir();
		}
	}
}