import java.util.ArrayList;

import javax.swing.JButton;

/*
 * 
 * Bombeiro, ambulancia, refugados e vitimas extend
 */
public class Elemento extends Thread {
	protected int id;
	protected int l; //linha
	protected int c; //coluna
	public JButton bt; //botao da interface

	public Elemento(int id, int l, int c) {
		this.id = id;
		this.l = l;
		this.c = c;
	}
	
	public ArrayList<Elemento> getPercepcao() {
		
		return null;
	}
	
	public void move(int nova_linha, int nova_coluna) {
		Ambiente amb = Ambiente.getInstance();
		
		amb.removeElemento(this.l, this.c);
		
		this.l = nova_linha;
		this.c = nova_coluna;
		
		amb.setElemento(this);
	}

	public void andar() {
		Ambiente amb = Ambiente.getInstance();
		
		int valid = 0, nl, nc;
		while (true) {
			nl = Util.rand(-1, 1) + this.l;
			nc = Util.rand(-1, 1) + this.c;
//			if (nl == this.l && nc == this.c) {
//				continue;
//			}
			
			valid = amb.checkPosition(nl, nc);
			if (valid == 1) {
				break;
			}
		}
		amb.getSemaforo(nl, nc).down();
		
//		amb.printMatriz();
		System.out.println("t "+this.id + " l="+nl+" c="+nc);
		amb.printSemaforos();
		this.move(nl, nc);

		try{
		    this.sleep(this.id*1000);
		}catch(Exception e){}
		
		amb.getSemaforo(this.l, this.c).up();
		
	}
	
	public void run(){
//		try{
//		    this.sleep(1000);
//		}catch(Exception e){}
		
		while(true) {
			this.andar();
			
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
