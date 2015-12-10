
public class Fogo extends Elemento {

	public Fogo(int id, int l, int c) {
		super(id, l, c);
	}
	
	public void apagar() {
		while (true) {
			int randl = Util.rand(0, this.amb.getLinhas()-1);
			int randc = Util.rand(0, this.amb.getColunas()-1);
			this.amb.mutexMove.down();
			if (this.amb.getSemaforo(randl, randc).getTotal() > 0) {
				this.amb.mutexMove.up();
				this.move(randl, randc);
				break;
			} else {
				this.amb.mutexMove.up();
			}
		}
	}
	
	public void run(){
		this.amb.setElemento(this);
		this.amb.countFogo(1);

		this.amb.mutexBarreira.down();
		this.amb.totalPassaramBarreira++;
		if (this.amb.totalPassaramBarreira < this.amb.totalBarreira) {
			this.amb.mutexBarreira.up();
			this.amb.barreira.down();
		} else {
			this.amb.mutexBarreira.up();
		}
		this.amb.barreira.up();
		
		while(true) {
			try{
			    Thread.sleep(this.amb.unTempo*30);
			}catch(Exception e){}
		}
	}
	
}
