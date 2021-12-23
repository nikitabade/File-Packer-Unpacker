import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class UNPackerFront extends Template implements ActionListener
{
  JButton SUBMIT,PREVIOUS;
  JLabel label1,label2,title;
  final JTextField text1;

  public UNPackerFront()
  {
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    title=new JLabel("UNpacking portal");
    Dimension size=title.getPreferredSize();
    title.setBounds(40,50,size.width+60,size.height);
    title.setFont(new Font("century",Font.BOLD,17));
    title.setForeground(Color.blue);
    
    label1=new JLabel();
    label1.setText("file name");
    label1.setForeground(Color.white);
    label1.setBounds(350,50,size.width,size.height);

    text1=new JTextField(15);
    Dimension tsize=text1.getPreferredSize();
    text1.setBounds(500,50,tsize.width,tsize.height);
    text1.setToolTipText("enter the name of directory");

    SUBMIT=new JButton("extract here");
    Dimension bsize=SUBMIT.getPreferredSize();
    SUBMIT.setBounds(350,200,bsize.width,bsize.height);
    SUBMIT.addActionListener(this);

    PREVIOUS=new JButton("PREVIOUS");
    Dimension b2size=PREVIOUS.getPreferredSize();
    SUBMIT.setBounds(500,200,b2size.width,b2size.height);
    SUBMIT.addActionListener(this);

    _header.add(title);
    _content.add(label1);
    _content.add(text1);
    _content.add(SUBMIT);
    _content.add(PREVIOUS);

    this.setSize(1000,400);
    this.setResizable(false);
    this.setVisible(true);
    text1.requestFocusInWindow();

  }

  public void actionPerformed(ActionEvent ae)
  {
    if(ae.getSource()==exit)
    {
      this.setVisible(false);
      System.exit(0);
    }

    if(ae.getSource()==minimize)
    {
      this.setState(this.ICONIFIED);
    }

    if(ae.getSource()==SUBMIT)
    {
      try
      {
        UNPacker obj=new UNPacker(text1.getText());
        this.dispose();
        Nextpage t=new Nextpage("admin");
      }
      catch(InvalidFileException obj)
      {
        this.setVisible(false);
        this.dispose();

        JOptionPane.showMessageDialog(this,"invalid packed file","error",JOptionPane.ERROR_MESSAGE);
        Nextpage t=new Nextpage("Projectadmin");
      }
      catch(Exception e)
      {}

    }
    if(ae.getSource()==PREVIOUS)
    {
      this.setVisible(false);
      this.dispose();
      Nextpage t=new Nextpage("admin");
    }
  }
}