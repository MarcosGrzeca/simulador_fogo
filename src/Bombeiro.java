import java.util.ArrayList;


public class Bombeiro extends Elemento {

	public Bombeiro(int id, int l, int c) {
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
				Fogo fogo = (Fogo) e;
				fogo.apagar();
			} else if (e instanceof Vitima) {
				Vitima vitima = (Vitima) e;
				vitima.resgatar();
			}
		}

		int[] mv = getMovimentoRand();
		int nl = mv[0];
		int nc = mv[1];
		this.moveComSemaforo(nl, nc);
	}

}
