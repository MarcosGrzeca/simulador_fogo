
public class Vitima extends Elemento {
	private int tempo;
	private int tempoMaxMorte = 50;
	
	public Vitima(int id, int l, int c) {
		super(id, l, c);

		this.tempo = 0;
	}

	public void resgatar() {
		
	}

	public void curar() {
		
	}
	
	public void morrer() {
		this.amb.removeElemento(this.l, this.c);
		
		this.amb.getSemaforo(this.l, this.c).up();
		
		this.stop();
	}

	public void run(){
		while(true) {
			this.andar();
			
			if (this.tempo > this.tempoMaxMorte) {
				this.morrer();
			}
			this.tempo++;
		}
	}
	
}
