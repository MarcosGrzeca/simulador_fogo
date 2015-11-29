

public class Vitima extends Elemento {
	public int tempo;
	public int tempoMaxMorte = 50;
	private int resgatado = 0;
	
	public Vitima(int id, int l, int c) {
		super(id, l, c);

		this.tempo = 0;
	}

	public void resgatar() {
		this.resgatado = 1;
		
		this.amb.removeElemento(this.l, this.c);
		this.amb.getSemaforo(this.l, this.c).up();
	}

	public void curar(int linha, int coluna) {
		this.l = linha;
		this.c = coluna;
		
		while (true) {
			int[] mv = this.getMovimentoRand();
			int nl = mv[0];
			int nc = mv[1];
			Elemento elemento_atual = this.amb.getElemento(nl, nc);
			if (elemento_atual instanceof Vazio) {
				this.amb.getSemaforo(nl, nc).down();
				
				Refugiado r = new Refugiado(this.id, nl, nc);
				this.amb.setElemento(r);
				r.start();
				
				this.stop();
				break;
			} else {
				try{
				    this.sleep(1000);
				}catch(Exception e){}
			}
		}
	}
	
	public void morrer() {
		if (this.resgatado == 0) {
			this.amb.removeElemento(this.l, this.c);
			this.amb.getSemaforo(this.l, this.c).up();
		}
		
		System.out.println("Morreu "+this.id);
		
		this.stop();
	}

	public void run(){
		while(true) {
			if (this.resgatado == 0) {
				this.andar();
			} else {
				try{
				    this.sleep(1000);
				}catch(Exception e){}
			}
			
			if (this.tempo > this.tempoMaxMorte) {
				this.morrer();
			}
			this.tempo++;
		}
	}
	
}
