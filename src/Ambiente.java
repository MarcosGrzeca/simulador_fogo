import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class Ambiente extends Frame {

	private static Ambiente instance = null;
	
    private int linhas = 20;
    private int colunas = 20;
    
	private ArrayList<ArrayList> m;
	
//	public int qtdClick = 0;
//	public BotaoTab btAnt;
	private Panel painel;
	
//    private int xOld;
//    private int yOld;
	
	public BufferedImage imgFundoBranca;
	public BufferedImage imgFundoPreta;
	public BufferedImage imgBombeiro;
	
	public static Ambiente getInstance() {
		if(instance == null) {
			instance = new Ambiente();
		}
		return instance;
	}
	
    public Ambiente() {
    	this.setTitle("Simulação combate ao fogo e resgate de vítimas");
        this.setSize(this.linhas*40, this.colunas*40);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.painel = new Panel(new GridLayout(linhas, colunas));
//        this.painel.setPreferredSize(new Dimension(600, 600));

        try {
            imgFundoBranca = ImageIO.read(getClass().getResourceAsStream("img/fundo_branco.jpg"));
        } catch (IOException e) {}

        try {
            imgFundoPreta = ImageIO.read(getClass().getResourceAsStream("img/fundo_escuro.jpg"));
        } catch (IOException e) {}

        try {
            imgBombeiro = ImageIO.read(getClass().getResourceAsStream("img/bombeiro.png"));
        } catch (IOException e) {}
        
        this.setLayout(new BorderLayout());
        this.add(BorderLayout.CENTER, this.painel);
        
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
    
    public void init() {
        this.m = new ArrayList<ArrayList>();
        for (int i = 0; i < this.linhas; i++) {
        	this.m.add(new ArrayList<Elemento>());
        	
        	for (int j = 0; j < this.colunas; j++) {
        		this.m.get(i).add(null);
        	}
        }
        
        Bombeiro b = new Bombeiro(1, 0, 0);
        this.setElemento(b);
        this.setElemento(new Bombeiro(2, 3, 5));
        this.setElemento(new Bombeiro(3, 7, 8));
        
        b.start();
        
        this.atualizaInterface();

//        this.painel.remove(0);
        System.out.println(this.painel.getComponent(0));
        
    }
    
    public void setElemento(Elemento e) {
    	int l = e.getLinha();
    	int c = e.getColuna();
    	this.m.get(l).set(c, e);
    	
//    	JButton bt = this.getBotaoElemento(e);
////    	System.out.println((l * this.colunas) + c);
//    	this.setComponentZOrder(bt, (l * this.colunas) + c);
    }
    
    public Elemento getElemento(int l, int c) {
    	return (Elemento) this.m.get(l).get(c);
    }
    
    public void removeElemento(int l, int c) {
    	this.m.get(l).set(c, null);
    }
    
    public int checkPosition(int l, int c) {
    	if ((l >= 0 && l < this.linhas) &&
    		(c >= 0 && c < this.colunas)) {
    		return 1;
    	} else {
    		return 0;
    	}
    }
    
    public void printMatriz() {
    	for (int i = 0; i < this.m.size(); i++) {
    		for (int j = 0; j < this.m.get(i).size(); j++) {
    			System.out.print(this.getElemento(i, j)+" - ");
    		}
    		System.out.println();
    	}
    }
    
	public void atualizaInterface(){
		JButton bt = null;
		
		for (int i = 0; i < this.m.size(); i++) {
            for (int j = 0; j < this.m.get(i).size(); j++) {
            	bt = this.getBotaoElemento(this.getElemento(i, j));
            	this.painel.add(bt);
            }
        }

//		this.pack();
	}
	
	public JButton getBotaoElemento(Elemento e) {
		JButton bt = null;
		if (e instanceof Bombeiro) {
    		bt = new BotaoTab(new ImageIcon(imgBombeiro));
    	} else {
    		bt = new BotaoTab(new ImageIcon(imgFundoBranca));
    		bt.setBorder(new LineBorder(new Color(205,201,201) ));
    	}
		return bt;
	}

	public class BotaoTab extends JButton implements MouseListener {  
	    
//	    private int x;
//	    private int y;
//	    private int corFundo;
	    
	    //usa o construtor da classe super (JButton), e adiciona o mouselistener ao objeto  
	    BotaoTab(ImageIcon img) //, int x, int y, int corFundo)  
	    {  
	        this.setIcon(img);
//	        this.x = x;
//	        this.y = y;
//	        this.corFundo = corFundo;
	        
	        this.setBackground(Color.WHITE);
	        this.setBorder(new LineBorder(Color.WHITE, 0));
	        
	        this.setFocusPainted(false);
	        
	        addMouseListener(this);
	    }
	    
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
//			// Primeiro clique, devo aguardar o proximo
//			if (qtdClick == 0){
//				
//				this.setBorder(new LineBorder(Color.BLACK, 6));
//				this.setContentAreaFilled(false);
//				
//				qtdClick++;
//				btAnt = this;
//				
//				xOld = x;
//				yOld = y;
//			
//			}else{
//				
//				if (btAnt.corFundo == 0)
//					btAnt.setBorder(new LineBorder(Color.WHITE, 0));
//				else if (btAnt.corFundo == 1)
//					btAnt.setBorder(new LineBorder(new Color(205,201,201) ));
//				
//				painel.removeAll();
//				
////				atualizaInterface(damas.getDamas(), painel);
//				
//				painel.repaint();
//				
//				qtdClick = 0;
//				
//			}
			
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
	
}
