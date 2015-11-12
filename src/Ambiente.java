import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class Ambiente extends Frame {

	public int qtdClick = 0;
	public BotaoTab btAnt;
	public int jogador = 1;
//	public EstadoDamas damas;
	public Panel tab;
	public int nivel = 3;
	
    private int xOld;
    private int yOld;
	
    public int linhas = 8;
    public int colunas = 8;
    
    boolean tipo = true;
	
	public BufferedImage imgBrancas;
	public BufferedImage imgPretas;
	public BufferedImage imgPretaDamas;
	public BufferedImage imgBrancaDamas;
	public BufferedImage imgFundoBranca;
	public BufferedImage imgFundoPreta;
	
	public class BotaoTab extends JButton implements MouseListener {  
	    
	    JButton bt = new JButton();
	    
	    private int x;
	    private int y;
	    private int corFundo;
	    
	    //usa o construtor da classe super (JButton), e adiciona o mouselistener ao objeto  
	    BotaoTab(ImageIcon img, int x, int y, int corFundo)  
	    {  
	        this.setIcon(img);
	        this.x = x;
	        this.y = y;
	        this.corFundo = corFundo;
	        
	        this.setBackground(Color.WHITE);
	        this.setBorder(new LineBorder(Color.WHITE, 0));
	        
	        this.setFocusPainted(false);
	        
	        addMouseListener(this);  
	    }
	    
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
			// Primeiro clique, devo aguardar o proximo
			if (qtdClick == 0){
				
				this.setBorder(new LineBorder(Color.BLACK, 6));
				this.setContentAreaFilled(false);
				
				qtdClick++;
				btAnt = this;
				
				xOld = x;
				yOld = y;
			
			}else{
				
				if (btAnt.corFundo == 0)
					btAnt.setBorder(new LineBorder(Color.WHITE, 0));
				else if (btAnt.corFundo == 1)
					btAnt.setBorder(new LineBorder(new Color(205,201,201) ));
				
				tab.removeAll();
				
//				atualizaTab(damas.getDamas(), tab);
				
				tab.repaint();
				
				qtdClick = 0;
				
			}
			
		}
		
		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}  
	}
	
	public void atualizaTab(int[][] mat, Panel p){
        
		JButton bt = null;
		
		for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
            	if (tipo){
            		bt = new BotaoTab(new ImageIcon(imgFundoPreta), i, j, 1);
            	}else{
                	bt = new BotaoTab(new ImageIcon(imgFundoBranca), i, j, 0);
            	}
            	
            	p.add(bt);
            	tipo = !tipo;
            }
            tipo = !tipo;
        }

//		this.pack();
		
	}
	
    public Ambiente(int[][] matriz) {
    	
    	this.setTitle("Simulação combate ao fogo e resgate de vítimas");
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        Panel painel = new Panel(new GridLayout(linhas, colunas));
//        painel.setPreferredSize(new Dimension(600, 600));
        
        tab = painel;
        
        try {
            imgFundoBranca = ImageIO.read(getClass().getResourceAsStream("img/fundo_branco.jpg"));
        	} catch (IOException e) {
        }

        try {
            imgFundoPreta = ImageIO.read(getClass().getResourceAsStream("img/fundo_escuro.jpg"));
        	} catch (IOException e) {
        }
        
        atualizaTab(matriz, painel);
        
        this.setLayout(new BorderLayout());
        this.add(BorderLayout.CENTER, painel);
        
        WindowListener listener = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Object origem = e.getSource();
                if (origem == Ambiente.this) {
                    System.exit(0);
                }
            }
        };
        this.addWindowListener(listener);
        
    }
}
