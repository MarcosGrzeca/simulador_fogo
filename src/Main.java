import javax.swing.JFrame;

public class Main {

	public static TelaConfiguracao frameTelaConfiguracao;
	
	public static void iniciarSimulacao(int nroLinhas, int nroColunas, int nroRefugiados, int nroBombeiros, int nroFogos, int nroAmbulancias) {
		Ambiente amb = Ambiente.getInstance();
		amb.init(nroLinhas, nroColunas, nroRefugiados, nroBombeiros, nroFogos, nroAmbulancias);
		amb.setVisible(true);
		frameTelaConfiguracao.dispose();
    }
    
	public static void main(String[] args) {
        //Iniciar tela para informar parametros
        frameTelaConfiguracao = new TelaConfiguracao();
        frameTelaConfiguracao.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
        frameTelaConfiguracao.setLocationByPlatform(true);
        frameTelaConfiguracao.setVisible(true);
        frameTelaConfiguracao.setTitle("Simulação Resgate");
	}
}
