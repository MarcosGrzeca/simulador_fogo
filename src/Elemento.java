import java.util.ArrayList;

import javax.swing.JButton;

/*
 * 
 * Bombeiro, ambulancia, refugados e vitimas extend
 */
public class Elemento extends Thread {
	private int id;
	private int l; //linha
	private int c; //coluna
	public JButton bt; //botao da interface

	public Elemento(int id, int l, int c) {
		this.id = id;
		this.l = l;
		this.c = c;
	}
	
	public ArrayList<Elemento> getPercepcao() {
		
		return null;
	}
	
	public void move() {
		Ambiente amb = Ambiente.getInstance();
		
		int la = this.l;
		int ca = this.c;
		int valid = 0, nl, nc;
		while (true) {
			nl = Util.rand(-1, 1) + this.l;
			nc = Util.rand(-1, 1) + this.c;
			
//			if (nl == this.l && nc == this.c) {
//				continue;
//			}
			
			valid = amb.checkPosition(nl, nc);
			if (valid == 1) {
				this.l = nl;
				this.c = nc;
				break;
			}
		}

		amb.removeElemento(la, ca);
		
		amb.setElemento(this);

//    	amb.printMatriz();
	}

	public void run(){
		while(true) {
			this.move();
			
			try{
			    this.sleep(1000);
			}catch(Exception e){}
		}
	}
	
	public void setLinha(int l) {
		this.l = l;
	}

	public void setColuna(int c) {
		this.c = c;
	}
	
	public int getLinha() {
		return this.l;
	}

	public int getColuna() {
		return this.c;
	}

	public String toString() {
		return "Elemento " + this.id;
	}

}
