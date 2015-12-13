import java.util.ArrayList;


public class Refugiado extends Elemento {
	private int isVitima = 0;
	
	public Refugiado(int id, int l, int c) {
		super(id, l, c);
	}

	/* (non-Javadoc)
	 * @see Elemento#andar()
	 */
	@Override
	public void andar() {
		if (this.isVitima == 0) {
			ArrayList<Elemento> campos = this.getPercepcao();
			for (Elemento e : campos) {
				if (e instanceof Vitima) {
					Vitima v = (Vitima) e;
					v.semVitima.up();
				}
				if (e instanceof Fogo) {
					this.isVitima = 1;
					this.transformaVitima();
					return;
				}
			}
	
			int[] mv = this.getMovimentoRand();
			int nl = mv[0];
			int nc = mv[1];
			this.moveComSemaforo(nl, nc);
		}
	}
	
	public void transformaVitima() {
		Vitima v = new Vitima(this.id, this.l, this.c);
		this.amb.setElemento(v);

		try{
		    Thread.sleep(this.amb.unTempo);
		}catch(Exception e){}

		v.start();

		this.amb.countRefugiados(-1);
		
		try {
			this.stop();	
		} catch (Exception e) {
			System.out.println("N�o foi poss�vel parar a Thread Refugiado");
		}
	}

	public void run(){
		this.amb.countRefugiados(1);
		super.run();
	}

}
