import java.applet.*;
import java.net.*;
import java.awt.*; 
public class CRYPTOGRAPHY extends java.applet.Applet
{
    Button b=new Button("ENCRYPT");
    Button b1=new Button("DECRYPT");
    TextField secretNumber=new TextField(10);
    TextField secretNumber1=new TextField(10);
    TextArea message=new TextArea();
    TextArea encryptedMessage=new TextArea();
    TextField publicKey=new TextField(10);
    Label l1=new Label("PUBLIC KEY");
    Label l2=new Label("SECRET NUMBER r");
    Label l3=new Label("SECRET NUMBER s");
    public void init()
    {
        add(b);
        add(b1);
        add(secretNumber);
        add(secretNumber1);
        add(publicKey);
        add(message);
        add(encryptedMessage);
        add(l1);
        add(l2);
        add(l3);
        setLayout(null);
        publicKey.setSize(100,20);
        publicKey.setLocation(150,50);
        secretNumber.setSize(100,20);
        secretNumber.setLocation(150,100);
        secretNumber1.setSize(100,20);
        secretNumber1.setLocation(150,140);
        l1.setSize(120,20);
        l1.setLocation(20,50);
        l2.setSize(120,20);
        l2.setLocation(20,100);
        l3.setSize(120,20);
        l3.setLocation(20,140);
        message.setSize(500,400);
        message.setLocation(20,200);
        encryptedMessage.setSize(500,400);
        encryptedMessage.setLocation(550,200);
        b.setSize(70,20);
        b.setLocation(20,620);
        b1.setSize(70,20);
        b1.setLocation(120,620);
        setSize(1200,1000);
    }

    public String encrypt(int r,int s,int a,String message)
    {
        //Generation of Private Keys
        int ka=(int)Math.pow(a,r)%26;
        int kb=(int)Math.pow(a,s)%26;
        //Generation of Shared Secret Key
        int sk1=(int)Math.pow(ka,s)%26;
        int sk2=(int)Math.pow(kb,r)%26;
        String mes=message.toLowerCase();
        String answer="";
        String template="abcdefghijklmnopqrstuvwxyz";
        for(int i=0;i<message.length();i++)
        {
            if(Character.isLetter(mes.charAt(i)))
            {
                int xx=template.indexOf(mes.charAt(i));
                int crypt=(xx*sk1)%26;
                answer=answer+template.charAt(crypt);
            }
            else
                answer=answer+mes.charAt(i);
        }
        return answer;
    }

    public String decrypt(int r,int s,int a,String message)
    {
        //Generation of Private Keys
        int ka=(int)Math.pow(a,r)%26;
        int kb=(int)Math.pow(a,s)%26;
        //Generation of Shared Secret Key
        int sk1=(int)Math.pow(ka,s)%26;
        int sk2=(int)Math.pow(kb,r)%26;
        String mes=message.toLowerCase();
        String answer="";
        String template="abcdefghijklmnopqrstuvwxyz";
        int sk2Inverse=modInverse(sk2,26);
        for(int i=0;i<message.length();i++)
        {
            if(Character.isLetter(mes.charAt(i)))
            {
                int xx=template.indexOf(mes.charAt(i));
                int crypt=(xx*sk2Inverse)%26;
                answer=answer+template.charAt(crypt);
            }
            else
                answer=answer+mes.charAt(i);
        }
        return answer;
    }

    int modInverse(int a, int m)
    {
        a = a%m;
        int ans=0;
        for (int x=1; x<m; x++)
        {
            if ((a*x) % m == 1)
            {
                ans=x;
                break;
            }
        }
        return ans;
    }

    public boolean action(Event e,Object j)
    {
        if(e.target instanceof Button)
        {
            if(j.equals("ENCRYPT"))
            {
                //String message="message";

                int r=Integer.valueOf(secretNumber.getText());

                int s=Integer.valueOf(secretNumber1.getText());
                int a=Integer.valueOf(publicKey.getText());

                String message1=message.getText();
                String em=encrypt(r,s,a,message1);
                encryptedMessage.setText(em);

            }

            if(j.equals("DECRYPT"))
            {
                //String message="message";

                int r=Integer.valueOf(secretNumber.getText());

                int s=Integer.valueOf(secretNumber1.getText());
                int a=Integer.valueOf(publicKey.getText());

                String message1=encryptedMessage.getText();
                String em=decrypt(r,s,a,message1);

                message.setText(em);

            }
        }
        return true;
    }
}