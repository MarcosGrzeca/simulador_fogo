import javax.swing.JFrame;

public class Main {

	public static TelaInicial frameTelaInicial;
	
	public static void iniciarSimulacao() {
		Ambiente amb = Ambiente.getInstance();
		amb.init();
		amb.setVisible(true);
		frameTelaInicial.dispose();
    }
    
	public static void main(String[] args) {
        //Iniciar tela para informar parametros
        frameTelaInicial = new TelaInicial();
        frameTelaInicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
        frameTelaInicial.setLocationByPlatform(true);
        frameTelaInicial.setVisible(true);
        frameTelaInicial.setTitle("Simulação Resgate");
	}
}
