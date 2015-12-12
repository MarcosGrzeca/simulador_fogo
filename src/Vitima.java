public class Vitima extends Elemento {
	public int tempo;
	public int tempoMaxMorte = 50;
	public int resgatado = 0;
	protected Semaforo semVitima = new Semaforo(1);
	
	public Vitima(int id, int l, int c) {
		super(id, l, c);

		this.tempo = 0;
	}

	public void resgatar() {
		if (this.resgatado == 0) {
			this.resgatado = 1;
			this.amb.removeElemento(this.l, this.c);
			this.amb.getSemaforo(this.l, this.c).up();
		}
	}

	@SuppressWarnings("deprecation")
	public void curar(int linha, int coluna) {
		this.l = linha;
		this.c = coluna;
		
		while (true) {
			int[] mv = this.getMovimentoRand();
			int nl = mv[0];
			int nc = mv[1];
			if (this.l == nl && this.c == nc) {
				try{
				    Thread.sleep(this.amb.unTempo);
				}catch(Exception e){}
				continue;
			}
			this.amb.mutexMove.down();
			if (this.amb.getSemaforo(nl, nc).getTotal() > 0) {
				this.amb.getSemaforo(nl, nc).down();
				this.amb.mutexMove.up();
				
				Refugiado r = new Refugiado(this.id, nl, nc);
				this.amb.setElemento(r);

				try{
				    Thread.sleep(this.amb.unTempo);
				}catch(Exception e){}

				r.start();
				
				this.amb.countVitimasSalvas(1);
				this.amb.countVitimas(-1);
				
				try {
					this.stop();
				} catch (Exception e) {
					System.out.println("----------------NAO consegui parar a Thread");
				}
				break;
			} else {
				this.amb.mutexMove.up();
				try{
				    Thread.sleep(this.amb.unTempo);
				}catch(Exception e){}
			}
		}
	}
	
	public void morrer() {
		this.semVitima.down();
		
		if (this.resgatado == 0) {
			this.amb.removeElemento(this.l, this.c);
			this.amb.getSemaforo(this.l, this.c).up();
		}
		this.amb.countVitimasFatais(1);
		
		this.amb.countVitimas(-1);
		this.semVitima.up();
		
		this.stop();
	}

	public void run(){
		this.amb.countVitimas(1);
		while(true) {
			this.amb.mutexBombeiro.down();
			this.semVitima.down();
			if (this.resgatado == 0) {
				this.amb.mutexBombeiro.up();
				this.semVitima.up();
				this.andar();
			} else {
				this.amb.mutexBombeiro.up();
				this.semVitima.up();
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