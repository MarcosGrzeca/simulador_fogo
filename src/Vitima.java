

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

	@SuppressWarnings("deprecation")
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
				try{
				    Thread.sleep(this.amb.unTempo);
				}catch(Exception e){}
				r.start();
				
				this.amb.countVitimasSalvas(1);
				this.amb.countVitimas(-1);
				
				this.stop();
				break;
			} else {
				try{
				    Thread.sleep(this.amb.unTempo);
				}catch(Exception e){}
			}
		}
	}
	
	public void morrer() {
		if (this.resgatado == 0) {
			this.amb.removeElemento(this.l, this.c);
			this.amb.getSemaforo(this.l, this.c).up();
		}
		this.amb.countVitimasFatais(1);
		
		this.amb.countVitimas(-1);
		
		this.stop();
	}

	public void run(){
		this.amb.countVitimas(1);
		while(true) {
			if (this.resgatado == 0) {
				this.andar();
			} else {
				try{
				    Thread.sleep(this.amb.unTempo);
				}catch(Exception e){}
			}
			
			if (this.tempo > this.tempoMaxMorte) {
				this.morrer();
			}
			this.tempo++;
		}
	}
	
}
