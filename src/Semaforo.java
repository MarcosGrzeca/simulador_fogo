public class Semaforo {
	private int total;

	public Semaforo(int n){
		//quantidade de threads executando simultaneamente
		this.total = n;
	}

	public synchronized void down(){ //P
		//bloqueia threads
		while(this.total == 0){
//			System.out.println("Dormiu");
			try {
				this.wait();
			} catch(InterruptedException e) {}
		}
		this.total--;
	}

	public synchronized void up(){ //V
		//libera threads
		this.total++;
		if (this.total == 2) {
			System.out.println("Erro");
		}
		this.notifyAll();
	}

	public synchronized int getTotal() {
		return this.total;
	}
}