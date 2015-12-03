import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Ambiente extends Frame {

	private static Ambiente instance = null;

    private int linhas = 15;
	private int colunas = 15;
	public int unTempo = 1000;

	private ArrayList<ArrayList> m;
	private ArrayList<ArrayList> semaforos;

	private int countBombeiros;
	private JLabel labBombeiros;
	private int countAmbulancias;
	private JLabel labAmbulancias;
	private int countRefugiados;
	private JLabel labRefugiados;
	private int countFogo;
	private JLabel labFogo;
	private int countVitimas;
	private JLabel labVitimas;
	private int countVitimasSalvas;
	private JLabel labVitimasSalvas;
	private int countVitimasFatais;
	private JLabel labVitimasFatais;
	private JLabel labTempo;
	private Panel painel;
	
	public ImageIcon imgFundoBranca;
	public ImageIcon imgFundoPreta;
	public ImageIcon imgBombeiro;
	public ImageIcon imgBombeiroResgate;
	public ImageIcon imgRefugiado;
	public ImageIcon imgVitima;
	public ImageIcon imgAmbulancia;
	public ImageIcon imgFogo;
	
	public static Ambiente getInstance() {
		if(instance == null) {
			instance = new Ambiente();
		}
		return instance;
	}
	
    public Ambiente() {
    	this.setTitle("Simula��o combate ao fogo e resgate de v�timas");
        this.setSize((this.linhas*40)+100, this.colunas*40);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.painel = new Panel(new GridLayout(linhas, colunas));
//        this.painel.setPreferredSize(new Dimension(600, 600));

        BufferedImage img;
        try {
        	img = ImageIO.read(getClass().getResourceAsStream("img/fundo_branco.jpg"));
            imgFundoBranca = new ImageIcon(img);
        } catch (IOException e) {}
        try {
        	img = ImageIO.read(getClass().getResourceAsStream("img/fundo_escuro.jpg"));
        	imgFundoPreta = new ImageIcon(img);
        } catch (IOException e) {}
        try {
        	img = ImageIO.read(getClass().getResourceAsStream("img/bombeiro.png"));
            imgBombeiro = new ImageIcon(img);
        } catch (IOException e) {}
        try {
        	img = ImageIO.read(getClass().getResourceAsStream("img/bombeiro_resgate.png"));
        	imgBombeiroResgate = new ImageIcon(img);
        } catch (IOException e) {}
        try {
        	img = ImageIO.read(getClass().getResourceAsStream("img/refugiado.jpg"));
        	imgRefugiado = new ImageIcon(img);
        } catch (IOException e) {}
        try {
        	img = ImageIO.read(getClass().getResourceAsStream("img/vitima.jpg"));
        	imgVitima = new ImageIcon(img);
        } catch (IOException e) {}
        try {
        	img = ImageIO.read(getClass().getResourceAsStream("img/ambulancia.jpg"));
        	imgAmbulancia = new ImageIcon(img);
        } catch (IOException e) {}
        try {
        	img = ImageIO.read(getClass().getResourceAsStream("img/fogo.jpg"));
        	imgFogo = new ImageIcon(img);
        } catch (IOException e) {}
        
        
        this.setLayout(new BorderLayout());
        this.add(BorderLayout.CENTER, this.painel);
        
		JToolBar toolBar = new JToolBar();
		toolBar.setFont(new Font("Dialog", Font.BOLD, 10));
		toolBar.setRollover(true);
		toolBar.setInheritsPopupMenu(true);
		toolBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		toolBar.setOrientation(SwingConstants.VERTICAL);
		toolBar.setSize(100, 0);
		this.add(BorderLayout.WEST, toolBar);
		
		JButton btnResetar = new JButton("Iniciar");
		btnResetar.setMargin(new Insets(2, 2, 2, 2));
		btnResetar.setFont(new Font("Dialog", Font.BOLD, 10));
		btnResetar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		toolBar.add(btnResetar);

		JSeparator separator = new JSeparator();
		separator.setMinimumSize(new Dimension(1, 1));
		separator.setMaximumSize(new Dimension(32767, 2));
		separator.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		separator.setPreferredSize(new Dimension(0, 1));
		toolBar.add(separator);
		
		this.labTempo = new JLabel();
		labTempo.setText("Tempo: ");
		toolBar.add(labTempo);

		separator = new JSeparator();
		separator.setMinimumSize(new Dimension(1, 1));
		separator.setMaximumSize(new Dimension(32767, 2));
		separator.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		separator.setPreferredSize(new Dimension(0, 1));
		toolBar.add(separator);
		
		this.labBombeiros = new JLabel();
		toolBar.add(this.labBombeiros);

		separator = new JSeparator();
		separator.setMinimumSize(new Dimension(1, 1));
		separator.setMaximumSize(new Dimension(32767, 2));
		separator.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		separator.setPreferredSize(new Dimension(0, 1));
		toolBar.add(separator);
		
		this.labAmbulancias = new JLabel();
		toolBar.add(this.labAmbulancias);

		separator = new JSeparator();
		separator.setMinimumSize(new Dimension(1, 1));
		separator.setMaximumSize(new Dimension(32767, 2));
		separator.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		separator.setPreferredSize(new Dimension(0, 1));
		toolBar.add(separator);
		
		this.labRefugiados = new JLabel();
		toolBar.add(this.labRefugiados);

		separator = new JSeparator();
		separator.setMinimumSize(new Dimension(1, 1));
		separator.setMaximumSize(new Dimension(32767, 2));
		separator.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		separator.setPreferredSize(new Dimension(0, 1));
		toolBar.add(separator);
		
		this.labFogo = new JLabel();
		toolBar.add(this.labFogo);

		separator = new JSeparator();
		separator.setMinimumSize(new Dimension(1, 1));
		separator.setMaximumSize(new Dimension(32767, 2));
		separator.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		separator.setPreferredSize(new Dimension(0, 1));
		toolBar.add(separator);

		this.labVitimas = new JLabel();
		toolBar.add(this.labVitimas);

		separator = new JSeparator();
		separator.setMinimumSize(new Dimension(1, 1));
		separator.setMaximumSize(new Dimension(32767, 2));
		separator.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		separator.setPreferredSize(new Dimension(0, 1));
		toolBar.add(separator);

		this.labVitimasSalvas = new JLabel();
		toolBar.add(this.labVitimasSalvas);

		separator = new JSeparator();
		separator.setMinimumSize(new Dimension(1, 1));
		separator.setMaximumSize(new Dimension(32767, 2));
		separator.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		separator.setPreferredSize(new Dimension(0, 1));
		toolBar.add(separator);

		this.labVitimasFatais = new JLabel();
		toolBar.add(this.labVitimasFatais);

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

	public ImageIcon getBotaoElemento(Elemento e) {
		ImageIcon img = null;
		if (e instanceof Bombeiro) {
			if (((Bombeiro) e).vitima == null) {
				img = imgBombeiro;
			} else {
				img = imgBombeiroResgate;
			}
		} else if (e instanceof Refugiado) {
			img = imgRefugiado;
		} else if (e instanceof Vitima) {
			img = imgVitima;
		} else if (e instanceof Ambulancia) {
			img = imgAmbulancia;
		} else if (e instanceof Fogo) {
			img = imgFogo;
    	} else {
    		img = imgFundoBranca;
    	}
		return img;
	}
	
    public void init() {
    	Elemento e;
        this.m = new ArrayList<ArrayList>();
        this.semaforos = new ArrayList<ArrayList>();
        for (int i = 0; i < this.linhas; i++) {
        	this.m.add(new ArrayList<Elemento>());
        	this.semaforos.add(new ArrayList<Semaforo>());
        	
        	for (int j = 0; j < this.colunas; j++) {
        		JButton bt = new BotaoTab(this.getBotaoElemento(null));
        		bt.setBorder(new LineBorder(new Color(205,201,201)));
        		
        		e = new Vazio(0, i, j);
        		e.bt = bt;
        		this.m.get(i).add(e);

        		this.semaforos.get(i).add(new Semaforo(1));
        		
        		this.painel.add(bt);
        	}
        }
        
        this.countVitimas(0);
        this.countVitimasSalvas(0);
        this.countVitimasFatais(0);
        
        this.criaElementos();
    }

    public void criaElementos() {
    	//colocar na interface para escolher as quantidades
    	int i, randl, randc;
    	int nbombeiros = 5;
    	for (i = 1; i <= nbombeiros; i++) {
    		randl = Util.rand(0, this.linhas-1);
    		randc = Util.rand(0, this.colunas-1);
    		
	        Bombeiro b = new Bombeiro(i, randl, randc);
	        b.start();
    	}
    	
    	int nrefugiados = 10;
    	for (i = 1; i <= nrefugiados; i++) {
    		randl = Util.rand(0, this.linhas-1);
    		randc = Util.rand(0, this.colunas-1);
    		
    		Refugiado r = new Refugiado(i, randl, randc);
	        r.start();
    	}
    	
    	int nfogo = 5;
    	for (i = 1; i <= nfogo; i++) {
    		randl = Util.rand(0, this.linhas-1);
    		randc = Util.rand(0, this.colunas-1);
    		
    		Fogo r = new Fogo(i, randl, randc);
	        r.start();
    	}

    	int nambulancias = 2;
    	for (i = 1; i <= nambulancias; i++) {
    		randl = Util.rand(0, this.linhas-1);
    		randc = Util.rand(0, this.colunas-1);
    		
    		Ambulancia r = new Ambulancia(i, randl, randc);
	        r.start();
    	}
    	
    	Tempo t = new Tempo(this);
    	t.start();
    	
    }
    
    public Elemento getElemento(int l, int c) {
    	return (Elemento) this.m.get(l).get(c);
    }
    
    public void setElemento(Elemento e) {
    	int l = e.getLinha();
    	int c = e.getColuna();
    	JButton bt = this.getElemento(l, c).bt;
    	
    	bt.setIcon(this.getBotaoElemento(e));
    	e.bt = bt;
    	
    	this.m.get(l).set(c, e);
    }
    
    public void removeElemento(int l, int c) {
    	JButton bt = this.getElemento(l, c).bt;
    	
    	Elemento e = new Vazio(0, l, c);
    	e.bt = bt;
    	e.bt.setIcon(this.getBotaoElemento(null));
    	this.m.get(l).set(c, e);
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
		System.out.println();
    }
    
    public Semaforo getSemaforo(int l, int c) {
    	return (Semaforo) this.semaforos.get(l).get(c);
    }

    public void printSemaforos() {
    	for (int i = 0; i < this.semaforos.size(); i++) {
    		for (int j = 0; j < this.semaforos.get(i).size(); j++) {
    			System.out.print(this.getSemaforo(i, j).getTotal());
    		}
    		System.out.println();
    	}
		System.out.println();
    }
    
    public ArrayList<Ambulancia> getListAmbulancias() {
    	ArrayList<Ambulancia> arr = new ArrayList<Ambulancia>();
    	for (int i = 0; i < this.semaforos.size(); i++) {
    		for (int j = 0; j < this.semaforos.get(i).size(); j++) {
    			if (this.getElemento(i, j) instanceof Ambulancia) {
        			arr.add((Ambulancia) this.getElemento(i, j));
    			}
    		}
    	}
    	return arr;
    }
    
	public int getLinhas() {
		return linhas;
	}

	public void setLinhas(int linhas) {
		this.linhas = linhas;
	}

	public int getColunas() {
		return colunas;
	}

	public void setColunas(int colunas) {
		this.colunas = colunas;
	}

	public void atualizarTempo(String tempo) {  
        this.labTempo.setText("Tempo: " + tempo + " s");
	}
	
	public void countBombeiros(int n) {
		this.countBombeiros += n;
		this.labBombeiros.setText("Bombeiros: "+this.countBombeiros);
	}

	public void countAmbulancias(int n) {
		this.countAmbulancias += n;
		this.labAmbulancias.setText("Ambulâncias: "+this.countAmbulancias);
	}

	public void countRefugiados(int n) {
		this.countRefugiados += n;
		this.labRefugiados.setText("Refugiados: "+this.countRefugiados);
	}

	public void countFogo(int n) {
		this.countFogo += n;
		this.labFogo.setText("Incêncios: "+this.countFogo);
	}

	public void countVitimas(int n) {
		this.countVitimas += n;
		this.labVitimas.setText("Vítimas: "+this.countVitimas);
	}

	public void countVitimasSalvas(int n) {
		this.countVitimasSalvas += n;
		this.labVitimasSalvas.setText("Vítimas salvas: "+this.countVitimasSalvas);
	}

	public void countVitimasFatais(int n) {
		this.countVitimasFatais += n;
		this.labVitimasFatais.setText("Vítimas fatais: "+this.countVitimasFatais);
	}

	public class BotaoTab extends JButton implements MouseListener {  
	    
	    //usa o construtor da classe super (JButton), e adiciona o mouselistener ao objeto  
	    BotaoTab(ImageIcon img) //, int x, int y, int corFundo)  
	    {  
	        this.setIcon(img);
	        
	        this.setBackground(Color.WHITE);
	        this.setBorder(new LineBorder(Color.WHITE, 0));
	        
	        this.setFocusPainted(false);
	        
	        addMouseListener(this);
	    }
	    
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
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
