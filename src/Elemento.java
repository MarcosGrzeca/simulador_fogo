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
	protected Ambiente amb;
	public JButton bt; //botao da interface

	public Elemento(int id, int l, int c) {
		this.id = id;
		this.l = l;
		this.c = c;
		this.amb = Ambiente.getInstance();
	}
	
	public ArrayList<Elemento> getPercepcao() {
		int l = this.l;
		int c = this.c;
		ArrayList<Elemento> list = new ArrayList<Elemento>();
		//cima
		if (this.amb.checkPosition(l-1, c) == 1) {
			list.add(this.amb.getElemento(l-1, c));
		}
		//baixo
		if (this.amb.checkPosition(l+1, c) == 1) {
			list.add(this.amb.getElemento(l+1, c));
		}
		//esquerda
		if (this.amb.checkPosition(l, c-1) == 1) {
			list.add(this.amb.getElemento(l, c-1));
		}
		//direita
		if (this.amb.checkPosition(l, c+1) == 1) {
			list.add(this.amb.getElemento(l, c+1));
		}
		return list;
	}
	
	public void move(int nova_linha, int nova_coluna, boolean liberarSemaforo) {
		this.amb.getSemaforo(nova_linha, nova_coluna).down();
		
		this.amb.removeElemento(this.l, this.c);
		
		int ll = this.l;
		int lc = this.c;
		this.l = nova_linha;
		this.c = nova_coluna;
		
		this.amb.setElemento(this);

		if (liberarSemaforo) {
			this.amb.mutexMove.up();
		}
		this.amb.getSemaforo(ll, lc).up();
		try{
		    Thread.sleep(this.amb.unTempo);
		}catch(Exception e){}
	}
	
	public void move(int nova_linha, int nova_coluna) {
		this.amb.getSemaforo(nova_linha, nova_coluna).down();
		
		this.amb.removeElemento(this.l, this.c);
		
		int ll = this.l;
		int lc = this.c;
		this.l = nova_linha;
		this.c = nova_coluna;
		
		this.amb.setElemento(this);
		this.amb.getSemaforo(ll, lc).up();
		try{
		    Thread.sleep(this.amb.unTempo);
		}catch(Exception e){}

	}

	public void moveComSemaforo(int nl, int nc) {
		this.amb.mutexMove.down();
//		Elemento elemento_atual = this.amb.getElemento(nl, nc);
		Semaforo sem = this.amb.getSemaforo(nl, nc);
		if (sem.getTotal() > 0) {
//			this.amb.mutexMove.up();
			this.move(nl, nc, true);
		} else {
			this.amb.mutexMove.up();
			try{
			    Thread.sleep(this.amb.unTempo);
			}catch(Exception e){}
		}
	}
	
	public int[] getMovimentoRand() {
		int valid = 0, nl, nc;
		while (true) {
			nl = Util.rand(-1, 1);
			if (nl == 0) {
				nc = Util.rand(-1, 1);
			} else {
				nc = 0;
			}
			if (nl == 0 && nc == 0) {
				continue;
			}
			nl = nl + this.l;
			nc = nc + this.c;
//			if (nl == this.l && nc == this.c) {
//				continue;
//			}
			valid = this.amb.checkPosition(nl, nc);
			if (valid == 1) {
				break;
			}
		}
		int[] arr = {nl, nc};
		return arr;
	}
	
	public void andar() {
		int[] mv = this.getMovimentoRand();
		int nl = mv[0];
		int nc = mv[1];
		
		this.moveComSemaforo(nl, nc);
	}
	
	public void run(){
		this.amb.mutexBarreira.down();
		this.amb.totalPassaramBarreira++;
		if (this.amb.totalPassaramBarreira < this.amb.totalBarreira) {
			this.amb.mutexBarreira.up();
			this.amb.barreira.down();
		} else {
			this.amb.mutexBarreira.up();
		}
		this.amb.barreira.up();
		
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
		return ""+this.id;
	}

}
