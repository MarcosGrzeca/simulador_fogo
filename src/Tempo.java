import java.util.Calendar;

public class Tempo extends Thread {
	private Calendar horaInicio;
	private java.text.DateFormat dataInicio;
	Ambiente amb;
	
	public Tempo(Ambiente amb) {
		this.amb = amb;
        this.horaInicio = Calendar.getInstance();
        this.dataInicio = new java.text.SimpleDateFormat("HH:mm:ss");
	}

	public void atualizar() {
	    Calendar dataFinal = Calendar.getInstance();  
        long diferenca = dataFinal.getTimeInMillis() - dataFinal.getTimeInMillis();  
        long diferenca1 = dataFinal.getTimeInMillis() - this.horaInicio.getTimeInMillis();
        String tempo = String.valueOf(((diferenca1 - diferenca) / 1000));
        this.amb.atualizarTempo(tempo);
	}

	public void run() {
		while (true) {
			try {
				this.atualizar();
				this.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}