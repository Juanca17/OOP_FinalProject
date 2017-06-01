/**
	Juan Carlos Sanchez A01631462
*/
import java.awt.*;
import javax.swing.*;

public class Memorama extends JFrame{
	public Memorama(){
		super("Memorama");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(150, 0, 1000, 1000);
		this.setResizable(false);
		PanelTablero pt = new PanelTablero();
		PanelControles pc=new PanelControles(pt);
		pt.setPanelControl(pc);
		this.add(pt);
		this.add(pc,BorderLayout.WEST);
		
		this.pack();
		this.setVisible(true);
	}
	
	public static void main(String args[]){
		Memorama ventanaPrincipal = new Memorama();
	}
}