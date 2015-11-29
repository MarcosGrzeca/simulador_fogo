
public class Ambulancia extends Elemento {

	public Ambulancia(int id, int l, int c) {
		super(id, l, c);
	}

	public void run(){
		this.amb.setElemento(this);
		this.amb.countAmbulancias(1);
		
		while(true) {
			try{
			    this.sleep(this.amb.unTempo*30);
			}catch(Exception e){}
		}
	}
	
}
