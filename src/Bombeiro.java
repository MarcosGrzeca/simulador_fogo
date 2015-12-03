import java.util.ArrayList;

public class Bombeiro extends Elemento {
	public Vitima vitima;
	private Ambulancia ambulancia;

	public Bombeiro(int id, int l, int c) {
		super(id, l, c);
	}

	/* (non-Javadoc)
	 * @see Elemento#andar()
	 */
	@Override
	public void andar() {
		int nl;
		int nc;
		ArrayList<Elemento> campos = this.getPercepcao();
		if (this.vitima == null) {
			//anda normal
			for (Elemento e : campos) {
				if (e instanceof Fogo) {
					Fogo fogo = (Fogo) e;
					fogo.apagar();
				} else if (e instanceof Vitima) {
					Vitima vitima = (Vitima) e;
					this.resgatar(vitima);
				}
			}
			int[] mv = this.getMovimentoRand();
			nl = mv[0];
			nc = mv[1];
		} else {
			//resgata vitima
			for (Elemento e : campos) {
				if (e instanceof Ambulancia) {
					this.resgatarFinal(1);
					return;
				}
			}
			if (this.vitima.tempo > this.vitima.tempoMaxMorte) {
				this.resgatarFinal(0);
			}
			Ambulancia amb_proxima = this.getAmbulanciaMaisProxima();
			nl = this.l;
			nc = this.c;
			int f = Util.rand(0, 1);
			if (f == 0) {
				if (amb_proxima.getLinha() < this.l) {
					nl = this.l - 1;
				} else if (amb_proxima.getLinha() > this.l) {
					nl = this.l + 1;
				} else if (amb_proxima.getColuna() < this.c) {
					nc = this.c - 1;
				} else if (amb_proxima.getColuna() > this.c) {
					nc = this.c + 1;
				}
			} else {
				if (amb_proxima.getColuna() < this.c) {
					nc = this.c - 1;
				} else if (amb_proxima.getColuna() > this.c) {
					nc = this.c + 1;
				} else if (amb_proxima.getLinha() < this.l) {
					nl = this.l - 1;
				} else if (amb_proxima.getLinha() > this.l) {
					nl = this.l + 1;
				}
			}
		}
		this.moveComSemaforo(nl, nc);
	}

	public Ambulancia getAmbulanciaMaisProxima() {
		if (this.ambulancia == null) {
			float d = 0, distance = 9999;
			ArrayList<Ambulancia> ambulancias = this.amb.getListAmbulancias();
			for (Ambulancia ambulancia : ambulancias) {
				d = (ambulancia.getLinha() - this.l) + (ambulancia.getColuna() - this.c);
				if (d <= distance) {
					distance = d;
					this.ambulancia = ambulancia;
				}
			}
		}
		return this.ambulancia;
	}
	
	public void resgatar(Vitima vitima) {
		this.bt.setIcon(this.amb.getBotaoElemento(this));
		
		vitima.resgatar();
		this.vitima = vitima;
	}
	
	public void resgatarFinal(int curar) {
		if (curar == 1) {
			this.vitima.curar(this.ambulancia.getLinha(), this.ambulancia.getColuna());
		}
		this.vitima = null;
		this.ambulancia = null;
		
		this.bt.setIcon(this.amb.getBotaoElemento(this));
	}

	public void run(){
		this.amb.countBombeiros(1);
		super.run();
	}
	
}
