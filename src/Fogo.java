
public class Fogo extends Elemento {

	public Fogo(int id, int l, int c) {
		super(id, l, c);
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
		this.amb.countFogo(1);
		
		while(true) {
			try{
			    Thread.sleep(this.amb.unTempo*30);
			}catch(Exception e){}
		}
	}
	
}
