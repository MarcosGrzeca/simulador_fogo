
public class Ambulancia extends Elemento {

	public Ambulancia(int id, int l, int c) {
		super(id, l, c);
	}

	public void run(){
		this.amb.setElemento(this);
		this.amb.countAmbulancias(1);
		
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
