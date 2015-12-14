public class Semaforo {
	private int total;

	public Semaforo(int n){
		//quantidade de threads executando simultaneamente
		this.total = n;
	}

	public synchronized void down(){ //P
		//bloqueia threads
		while(this.total == 0){
			try {
				this.wait();
			} catch(InterruptedException e) {}
		}
		this.total--;
	}

	public synchronized void up(){ //V
		//libera threads
		this.total++;
		this.notifyAll();
	}

	public synchronized int getTotal() {
		return this.total;
	}
}