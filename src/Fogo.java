
public class Fogo extends Elemento {

	public Fogo(int id, int l, int c) {
		super(id, l, c);
		// TODO Auto-generated constructor stub
	}
	
	public void apagar() {
		while (true) {
			int randl = Util.rand(0, this.amb.getLinhas()-1);
			int randc = Util.rand(0, this.amb.getColunas()-1);
			
			Elemento elemento_atual = this.amb.getElemento(randl, randc);
			if (elemento_atual instanceof Vazio) {
				this.move(randl, randc);
				break;
			}
		}
	}
	
	public void run(){
		this.amb.setElemento(this);
		
		while(true) {
			try{
			    this.sleep(30000);
			}catch(Exception e){}
		}
	}
	
}
