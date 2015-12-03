import java.util.ArrayList;


public class Refugiado extends Elemento {

	public Refugiado(int id, int l, int c) {
		super(id, l, c);
	}

	/* (non-Javadoc)
	 * @see Elemento#andar()
	 */
	@Override
	public void andar() {
		ArrayList<Elemento> campos = this.getPercepcao();
		for (Elemento e : campos) {
			if (e instanceof Fogo) {
				this.transformaVitima();
			}
		}

		int[] mv = this.getMovimentoRand();
		int nl = mv[0];
		int nc = mv[1];
		this.moveComSemaforo(nl, nc);
	}
	
	public void transformaVitima() {
		this.amb.removeElemento(this.l, this.c);

		Vitima v = new Vitima(this.id, this.l, this.c);
		this.amb.setElemento(v);

		try{
		    Thread.sleep(this.amb.unTempo);
		}catch(Exception e){}

		v.start();

		this.amb.countRefugiados(-1);
		
		this.stop();
		//fazer thread terminar
	}

	public void run(){
		this.amb.countRefugiados(1);
		super.run();
	}

}
