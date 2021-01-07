
package texteditors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author hp
 */
public class TextEditors extends JFrame{
private JRadioButton CRB,TNRB,ARB;
private JCheckBox bold,italic;
private JMenuBar menubar;
private JMenu Filemenu,Editmenu;
private JMenuItem open,save,copy,paste,saveas,nw,exite,cut;
private JTextArea text;
private JScrollPane scrolpane;
private ButtonGroup buttongroup;

public TextEditors(){
    
    getContentPane().setBackground(Color.BLUE);
    setBounds(100,100,500,700);
    setTitle("TextEditor");
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    JPanel pl=new JPanel();
    text=new JTextArea(25,25);
    text.setFont(new Font("Times New Roman",Font.PLAIN,21));
    text.setLineWrap(true);
    text.setBackground(Color.yellow);
    text.setText(" ");
    
    pl.add(text);
    scrolpane=new JScrollPane(text);
    pl.add(scrolpane);
    add(pl,BorderLayout.CENTER);
    
    menubar=new JMenuBar();
    add(menubar,BorderLayout.NORTH);
    
    Filemenu=new JMenu("File");
    Editmenu=new JMenu("Edit");
    menubar.add(Filemenu);
    menubar.add(Editmenu);
    
    open=new JMenuItem("Open");
    save=new JMenuItem("Save");
    saveas=new JMenuItem("Save as");
    nw=new JMenuItem("New");
    exite=new JMenuItem("Exit");
    Filemenu.add(open);
    Filemenu.add(save);
       Filemenu.add(saveas);
          Filemenu.add(nw);
             Filemenu.add(exite);
             
   copy=new JMenuItem("Copy");
   paste=new JMenuItem("paste");
   cut=new JMenuItem("cut");
   Editmenu.add(copy);
   Editmenu.add(cut);
   Editmenu.add(paste);
    
   JPanel panel=new JPanel();
   CRB=new JRadioButton("Cuorier");
   ARB=new JRadioButton("Arial");
   TNRB=new JRadioButton("New TimesRoman");
   bold=new JCheckBox("Bold");
   italic=new JCheckBox("Italic");
   panel.add(CRB);
   panel.add(ARB);
   panel.add(TNRB);
     panel.add(bold);
     panel.add(italic);
     add(panel,BorderLayout.SOUTH);
     
     buttongroup=new ButtonGroup();
     buttongroup.add(CRB);
     buttongroup.add(ARB);
     buttongroup.add(TNRB);
     
     paste.addActionListener(new ButtonListener());
          copy.addActionListener(new ButtonListener());
               nw.addActionListener(new ButtonListener());
                   cut.addActionListener(new ButtonListener());
                       save.addActionListener(new ButtonListener());
                             saveas.addActionListener(new ButtonListener());
                                   open.addActionListener(new ButtonListener());
                                        exite.addActionListener(new ButtonListener());

CRB.addItemListener(new Itemlistener());
ARB.addItemListener(new Itemlistener());
TNRB.addItemListener(new Itemlistener());
bold.addItemListener(new Itemlistener());
italic.addItemListener(new Itemlistener());

}
private class ButtonListener implements ActionListener{
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==copy)
            text.copy();
        if (e.getSource()==exite)
            System.exit(0);
         if (e.getSource()==paste)
            text.paste();
          if (e.getSource()==cut)
            text.cut();
           if (e.getSource()==nw)
            text.setText(" ");
        if(e.getSource()==open){
            String filename=JOptionPane.showInputDialog(null,"Enter the filename",
                    JOptionPane.QUESTION_MESSAGE);
            
            try{
                FileReader in=new FileReader(filename);
                text.read(in,null);
                in.close();
            }
            catch(IOException ex){
                JOptionPane.showMessageDialog(null,"File not found","Inpu error",
                JOptionPane.QUESTION_MESSAGE);
            }
                
            }
        if(e.getSource()==save){
            String fileName=JOptionPane.showInputDialog(null,"Enter filename",
                    JOptionPane.QUESTION_MESSAGE);
            if(fileName==null || fileName.equals(""))
            return;
           try{
               FileWriter out=new FileWriter(fileName);
               text.write(out);
               out.close();
           }
           catch(IOException ex){
               JOptionPane.showMessageDialog(null,"File cannot be saved","Kindly try again",
                       JOptionPane.QUESTION_MESSAGE);
           }
        }
                
}
}
private class Itemlistener implements ItemListener{
    public void itemStateChanged(ItemEvent e){
        int fontStyle=Font.PLAIN;
        if(bold.isSelected()){
            fontStyle+=Font.BOLD;
        }
        if(italic.isSelected()){
            fontStyle+=Font.ITALIC;
        }
        if(CRB.isSelected()){
            text.setFont(new Font("Courier",fontStyle,24));
        }
        if(ARB.isSelected()){
            text.setFont(new Font("Aerial",fontStyle,24));
            
        }
        if(TNRB.isSelected()){
            text.setFont(new Font("Times New Roman",fontStyle,24));
        }
        }
        }
public static void main(String[] args){
    TextEditors tx=new TextEditors();
    tx.show();
    }
    }
    