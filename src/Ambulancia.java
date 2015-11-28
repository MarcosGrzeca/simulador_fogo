
public class Ambulancia extends Elemento {

	public Ambulancia(int id, int l, int c) {
		super(id, l, c);
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
