package GraKPN;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

public class GraKPN extends JFrame {

    public GraKPN()
    {
        super("Kamien Papier Nozyce");
        this.setDefaultCloseOperation(3);
        
        int szerokoscEkranu = Toolkit.getDefaultToolkit().getScreenSize().width;
        int wysokoscEkranu = Toolkit.getDefaultToolkit().getScreenSize().height;
        
        this.setSize(606, 658);
        
        int osX = this.getSize().width;
        int osY = this.getSize().height;
        
        this.setLocation( (szerokoscEkranu - osX) / 2 , (wysokoscEkranu - osY) / 2 );
        
        this.setResizable(false);
        initComponents();
    }
        

    void initComponents() 
    {
        this.setJMenuBar(pasekMenu);
        
        JMenu plik = pasekMenu.add(new JMenu("Ustawienia"));
        
        JMenuItem resetPunktow = plik.add(new JMenuItem("Resetuj Punkty"));
        
        panelWynikow.add(mojWynik = new JLabel());
       
        for(int i = 0 ; i <punktacjaGraczy.length; i++ )
        {
            panelWynikow.add(punktacjaGraczy[i] = new JLabel());
        }
        
        resetPunktow.addActionListener((ActionEvent ae) -> 
        {
            ileRazyRemis = 0;
            mojePunkty = 0;
            
            poleGracza[7].setIcon(new ImageIcon("NiebieskieTlo.gif"));
            poleGracza[4].setIcon(new ImageIcon("NiebieskieTlo.gif"));
            poleGracza[2].setIcon(new ImageIcon("NiebieskieTlo.gif"));
            
            for(int i = 0 ; i < punktyGracza.length ; i++)
            {
                punktyGracza[i] = 0;
                poleGracza[i].setIcon(new ImageIcon("NiebieskieTlo.gif"));
            }
           
            ustawPunktacjeGraczy();
        });
        
        JMenu iloscGraczy = (JMenu) plik.add(new JMenu("Ilosc Przeciwników"));
        
        for(int i = 0 ; i < ileGraczy.length ; i++)
        {
            int k = i;
            iloscGraczy.add(ileGraczy[i] = new JRadioButtonMenuItem(""+ (i+1)));
            grupaGraczy.add(ileGraczy[i]);
            ileGraczy[i].addActionListener((ActionEvent ae) -> 
            {
                gracze = k + 1;
                resetPunktow.doClick();
            });
        }
        ileGraczy[0].setSelected(true);
        
        JMenuItem informacje = plik.add(new JMenuItem("Informacje"));
        
        informacje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                JOptionPane.showConfirmDialog(rootPane, " Gra \"Kamień Papier Nożyce \"" + " \n" + " Wersja : 1.0" + "\n" + " Autor : Dominik Michnik" , "Informacje" , -1);
            }
        });
        JMenuItem zamknij = plik.add(new JMenuItem("Zamknij"));
        
        zamknij.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                System.exit(0);
            }
        });
        
        this.getContentPane().setLayout(null);
        
        this.getContentPane().add(panelWynikow);
        panelWynikow.setLayout(new GridLayout(10, 1));
        panelWynikow.setLocation(450,0);
        panelWynikow.setSize(150, 600);
        
        panelWynikow.setBackground(Color.yellow);
        panelWynikow.add(etykietaRemisu );
        panelWynikow.add(reset);
        reset.setBorder(BorderFactory.createBevelBorder(2));
        reset.setIcon(new ImageIcon("ResetujPunkty.gif"));
        reset.addActionListener((ActionEvent ae) -> 
        {
            resetPunktow.doClick(0);
        });
        
        this.getContentPane().add(panelGraczy);
        panelGraczy.setLocation(0,0);
        panelGraczy.setSize(450, 450);
        panelGraczy.setLayout(new GridLayout(3, 3));
        
        this.getContentPane().add(panelWyboru);
        
        panelWyboru.setLocation(0,450);
        panelWyboru.setSize(450, 150);
        panelWyboru.setLayout(new GridLayout(1, 3));
        
        dodajPolaGraczy();
        dodajPrzyciskWyboru();
        ustawPunktacjeGraczy();
        test();
    }
    void test()
    {
        System.out.println("test");
    }

    
    JMenuBar pasekMenu = new JMenuBar();
    
    JRadioButtonMenuItem[] ileGraczy = new JRadioButtonMenuItem[2];
    
    ButtonGroup grupaGraczy = new ButtonGroup();
    int gracze = 1;
    
    Random generator = new Random();
    JPanel panelWynikow = new JPanel();
    JPanel panelGraczy = new JPanel();
    JPanel panelWyboru = new JPanel();
    
    
    JLabel[] poleGracza = new JLabel[9];
    JButton[] przyciskWyboru = new JButton[3];
    JButton reset = new JButton("Resetuj Punkty");
    
    int mojePole;
    JLabel mojWynik = new JLabel();
    int mojePunkty= 0;
    int ileRazyRemis= 0;
    
    
    int poleGracza1;
    int poleGracza2;
   
    int[] punktyGracza = new int[2];
    JLabel[] punktacjaGraczy = new JLabel[2];
    
    JLabel etykietaRemisu = new JLabel();
    
    void dodajPolaGraczy()
    {
        for(int i = 0 ; i < 9; i++)
        {
            panelGraczy.add(poleGracza[i] = new JLabel());

            poleGracza[i].setIcon(new ImageIcon("NiebieskieTlo.gif"));
        }
    }

    void dodajPrzyciskWyboru()
    {
        for(int i = 0 ; i < 3; i++)
        {
            int k = i;
            String[] znaki = {"NiebieskiPapier.gif" , "NiebieskiKamien.gif" , "NiebieskieNozyce.gif" };
            String[] znakiZaznaczenia = {"ZaznaczonyNiebieskiPapier.gif" , "ZaznaczonyNiebieskiKamien.gif" , "ZaznaczonyNiebieskieNozyce.gif" };
            panelWyboru.add(przyciskWyboru[i] = new JButton());

            przyciskWyboru[i].setBorder(BorderFactory.createBevelBorder(2));
            przyciskWyboru[i].setIcon(new ImageIcon(znaki[i]));
            przyciskWyboru[i].addActionListener((ActionEvent ae) -> 
            {
                ustawMojePole(k);
                ustawPolaGraczy();
            });
            przyciskWyboru[i].addMouseListener(new MouseAdapter() 
            {
                @Override
                public void mouseEntered(MouseEvent me) 
                {
                    przyciskWyboru[k].setIcon(new ImageIcon(znakiZaznaczenia[k]));
                }

                @Override
                public void mouseExited(MouseEvent me) 
                {
                    przyciskWyboru[k].setIcon(new ImageIcon(znaki[k]));
                }
            });
        }
    }

    void ustawMojePole(int i)
    {
        if( i == 0)
        poleGracza[7].setIcon(new ImageIcon("NiebieskiPapier.gif"));
        if( i == 1)
        poleGracza[7].setIcon(new ImageIcon("NiebieskiKamien.gif"));
        if( i == 2)
        poleGracza[7].setIcon(new ImageIcon("NiebieskieNozyce.gif"));
        
        mojePole = i;
    }

    void ustawPolaGraczy()
    {
        if(gracze == 1)                                                     // DLA JEDNEGO GRACZA
        {
            int losowanie = 3;
            int wynikLosowania = generator.nextInt(losowanie);
            poleGracza1 = wynikLosowania;

            if( wynikLosowania == 0)
            poleGracza[1].setIcon(new ImageIcon("NiebieskiPapier.gif"));
            if( wynikLosowania == 1)
            poleGracza[1].setIcon(new ImageIcon("NiebieskiKamien.gif"));
            if( wynikLosowania == 2)
            poleGracza[1].setIcon(new ImageIcon("NiebieskieNozyce.gif"));
        }
        
        if(gracze == 2)                                                     // DLA DWÓCH GRACZY
        {
            for(int i = 0 ; i <= gracze ; i++) 
            {
                if(i == 1)
                    continue;
                int losowanie = 3;
                int wynikLosowania = generator.nextInt(losowanie);

                if(i == 0)
                poleGracza1 = wynikLosowania;

                if(i == 2)
                poleGracza2 = wynikLosowania;
                
                if( wynikLosowania == 0)
                poleGracza[i].setIcon(new ImageIcon("NiebieskiPapier.gif"));
                if( wynikLosowania == 1)
                poleGracza[i].setIcon(new ImageIcon("NiebieskiKamien.gif"));
                if( wynikLosowania == 2)
                poleGracza[i].setIcon(new ImageIcon("NiebieskieNozyce.gif"));
            }
        }
        instrukcjaWygrranej();
    }

    void instrukcjaWygrranej()
    {
        if(gracze == 1)                                                         // DLA JEDNEGO GRACZA
        {
            for(int i = 0; i < 3 ; i++)
                for(int k = 0 ; k < 3; k++)
                {
                    if(i == mojePole && k == poleGracza1 && i == k)
                    {
                        ileRazyRemis++;
                        ustawPoleWyniku("REMIS!.gif");
                    }
                    else if(i == mojePole && k == poleGracza1 &&  ((i == 0 && k == 1) || (i == 1 && k == 2) || (i == 2 && k == 0)))
                    {
                        mojePunkty++;
                        ustawPoleWyniku("JaWygrywam.gif");
                    }
                    else if(i == mojePole && k == poleGracza1 &&  ((i == 1 && k == 0) || (i == 2 && k == 1) || (i == 0 && k == 2)))
                    {
                        punktyGracza[0]++;
                        ustawPoleWyniku("WygrywaGracz1.gif");
                    }
                }
        }
        if(gracze == 2)                                                         // DLA DWÓCH GRACZY
        {
            int suma = mojePole + poleGracza1 + poleGracza2;
            
            int[] warunkiRemisu = {0 , 1 , 3 , 4 , 6};
            int[] warunkiWygranej = {2 , 5};
            
            for(int i = 0 ; i < warunkiRemisu.length ; i++)
            {
                if(suma == warunkiRemisu[i])
                {
                    ustawPoleWyniku("REMIS!.gif");
                    ileRazyRemis++;
                }
            }
            for(int i = 0 ; i < warunkiWygranej.length ; i++)
                if ( suma == warunkiWygranej[i] && ((mojePole == 0 && poleGracza1 == 1) || (mojePole == 1 && poleGracza1 == 2) || (mojePole == 2 && poleGracza1 == 0)) )
                {
                    ustawPoleWyniku("JaWygrywam.gif");
                     mojePunkty++;
                }
            
            for(int i = 0 ; i < warunkiWygranej.length ; i++)
                if ( suma == warunkiWygranej[i] && ((poleGracza1 == 0 && mojePole == 1) || (poleGracza1 == 1 && mojePole == 2) || (poleGracza1 == 2 && mojePole == 0)) )
                {
                    ustawPoleWyniku("WygrywaGracz1.gif");
                    punktyGracza[0]++;
                }
            
            for(int i = 0 ; i < warunkiWygranej.length ; i++)
                if ( suma == warunkiWygranej[i] && ((poleGracza2 == 0 && poleGracza1 == 1) || (poleGracza2 == 1 && poleGracza1 == 2) || (poleGracza2 == 2 && poleGracza1 == 0)) )
                {
                    ustawPoleWyniku("WygrywaGracz2.gif");
                    punktyGracza[1]++;
                }
        }
        
        ustawPunktacjeGraczy();
    }
    
    void ustawPoleWyniku(String wynik)
    {
        poleGracza[4].setIcon(new ImageIcon(wynik));
    }

    void ustawPunktacjeGraczy()
    {
        
        etykietaRemisu.setText("              Remis : " + ileRazyRemis);
        etykietaRemisu.setForeground(Color.BLUE);
        mojWynik.setText("              JA          : " + mojePunkty);
        mojWynik.setForeground(Color.BLUE);
        
        for(int i = 0 ; i < punktacjaGraczy.length ; i++)
        {
            String pustyTekst =  "              Gracz " + (i + 1) + " : " + punktyGracza[i];
            
            if( i >= gracze )
                pustyTekst = "";
                
                punktacjaGraczy[i].setText(pustyTekst);
                punktacjaGraczy[i].setForeground(Color.BLUE);
        }
    }
    
    public static void main(String[] args) {
        
        new GraKPN().setVisible(true);
    }
}
