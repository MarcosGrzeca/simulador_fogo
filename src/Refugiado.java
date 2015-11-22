import java.util.ArrayList;


public class Refugiado extends Elemento {

	public Refugiado(int id, int l, int c) {
		super(id, l, c);
		// TODO Auto-generated constructor stub
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

		int[] mv = getMovimentoRand();
		int nl = mv[0];
		int nc = mv[1];
		this.moveComSemaforo(nl, nc);
	}
	
	public void transformaVitima() {
		this.amb.removeElemento(this.l, this.c);

		Vitima v = new Vitima(0, this.l, this.c);
		this.amb.setElemento(v);

		try{
		    this.sleep(1000);
		}catch(Exception e){}

		v.start();

		this.stop();
		//fazer thread terminar
	}

}
