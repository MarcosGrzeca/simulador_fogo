
public class Fogo extends Elemento {

	public Fogo(int id, int l, int c) {
		super(id, l, c);
		// TODO Auto-generated constructor stub
	}
	
	public void apaga() {
		Ambiente amb = Ambiente.getInstance();
		
		int randl = Util.rand(0, amb.getLinhas()-1);
		int randc = Util.rand(0, amb.getColunas()-1);

		this.move(randl, randc);
	}
	
	public void run(){
		Ambiente amb = Ambiente.getInstance();
		amb.setElemento(this);
		
		while(true) {
			try{
			    this.sleep(10000);
			}catch(Exception e){}
			
			// teste temporario, depois bombeiro vai apagar
			this.apaga();
		}
	}
	
}
