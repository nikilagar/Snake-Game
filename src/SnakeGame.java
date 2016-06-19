

import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javafx.scene.media.AudioClip;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class SnakeGame extends GraphicsProgram implements KeyListener{
public static final int APPLICATION_WIDTH=300;
public static final int APPLICATION_HEIGHT=300;
    
int mx=1,my=0,tx[]=new int[1000],ty[]=new int[1000],s=0,par[]=new int[1000],count=0,tracex[]=new int[1000],tracey[]=new int[1000],size=5,l=2,score=0,spee=1;
GOval food,blin=new GOval(0,0,0,0);
boolean ss=true,sw=true;
GRect snak[]=new GRect[1000];
final AudioClip s1 = 
            new AudioClip(SnakeGame.class.getResource("1.mp3").toString());
final AudioClip s2 = 
            new AudioClip(SnakeGame.class.getResource("2.mp3").toString());
final AudioClip s3 = 
            new AudioClip(SnakeGame.class.getResource("3.mp3").toString());

Timer b=new Timer(200,new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent ae) {
if(ss==true){
    double a=Math.random()*getWidth();
    double b=Math.random()*getHeight();
    blin=new GOval(a,b,2*l,2*l);
blin.setFilled(true);
add(blin);
ss=false;
}
if(sw==true){
    blin.setVisible(false);
sw=false;
}
else{
    blin.setVisible(true);
sw=true;
}
count++;
if(count==15){
remove(blin);
count=0;
b.stop();
}       
    }
});

        public void run(){
   
    
   int a=(JOptionPane.showConfirmDialog(null,"Want Big Snake?","Size of snake",0));
   if(a==JOptionPane.YES_OPTION)
       l=8;
   else
       l=4;
   tx[0]=l;
    int t=0;
    ty[0]=0;
   spee=Integer.parseInt(JOptionPane.showInputDialog("Enter level between 1 and 10"));
   while(!(spee>=1&&spee<=10))
   spee=Integer.parseInt(JOptionPane.showInputDialog("Enter level between 1 and 10"));
   setTitle("Snake:level "+spee);
   setBackground(Color.yellow);
  GRect loa[]=new GRect[4];
  for(int i=0;i<=3;i++){
  loa[i]=new GRect(getWidth()/2+i*10,getHeight()/2+10,10,10);
  loa[i].setColor(Color.red);
  loa[i].setFilled(true);
  }
   GLabel w=new GLabel("Set frame size game begins in 3",getWidth()/2-getWidth()/4,getHeight()/2);
   w.setColor(Color.red);

   w.setFont("Monotype Corsiva-15");
   add(w);
   add(loa[0]);
    pause(1000);

    w.setLabel("Set frame size game begins in 2");
    add(loa[1]);
    pause(1000);
    w.setLabel("Set frame size game begins in 1");
    add(loa[2]);
    pause(1000);
    w.setLabel("Set frame size game begins in 0");
    add(loa[3]);
    pause(1000);
    remove(w);
    for(int i=0;i<=3;i++){
        remove(loa[i]);
    }
  GLabel sco=new GLabel("Score: "+score,getWidth()/2,getHeight()/2);
  sco.setColor(Color.GRAY);
  add(sco);
  for(int i=0;i<size;i++){
        
    snak[i]=new GRect(size*l-i*l+20,25,l,l);
    snak[i].setFilled(true);
    add(snak[i]);
    }
 food=new GOval((int)(Math.random()*getWidth()-2),(int)(Math.random()*getHeight()-2),l,l);
 food.setFilled(true);
 add(food);

    addKeyListeners();
 
for(int j=0;j<size;j++){
//    if(snak[0].getX()>=getWidth()||snak[0].getX()<=0||snak[0].getY()>=getHeight()||snak[0].getY()<=0){
//s3.play();
//
//        JOptionPane.showMessageDialog(this,"you lose and score is "+score+" for level "+spee);
//        
//break;
//    
//    }
    if(s==0){
        snak[j].move(l, 0);
    }
if(s>=1){
     if(snak[j].getX()==tracex[par[j]]&&snak[j].getY()==tracey[par[j]]){
         if(par[j]<s)   
         par[j]++;

}
     snak[j].move(tx[par[j]],ty[par[j]]); 
 }
for(int k=2;k<size;k++){
    
if((snak[k].contains(snak[0].getX()+1,snak[0].getY()+1)&&snak[k].contains(snak[0].getX()+l-1,snak[0].getY()+1))||(snak[0].getX()>=getWidth()||snak[0].getX()<=0||snak[0].getY()>=getHeight()||snak[0].getY()<=0)){
t=1;
}
}
if(t==1){
s3.play();    
JOptionPane.showMessageDialog(this,"you lose and score is "+score+" for level "+spee);
System.exit(0);
break;}
if(j==size-1)
{j=-1;
    pause(110-10*spee);}

if((snak[0].getX()<=food.getX()+l/2&&snak[0].getX()>=food.getX()-l/2&&snak[0].getY()>=food.getY()-l/2&&snak[0].getY()<=food.getY()+l/2)||(
 snak[0].getX()<=blin.getX()+l&&snak[0].getX()>=blin.getX()-l&&snak[0].getY()>=blin.getY()-l&&snak[0].getY()<=blin.getY()+l))
{

    if(snak[0].getX()<=blin.getX()+l&&snak[0].getX()>=blin.getX()-l&&snak[0].getY()>=blin.getY()-l&&snak[0].getY()<=blin.getY()+l)
    {b.stop();
    blin.setLocation(-15,-15);
    score+=(16-count)*5;
    count=0;
    s2.play();
    }
    else{
    food.setLocation((int)(Math.random()*(getWidth())-2),(int)(Math.random()*(getHeight()))-2);
s1.play();
    }
      score++;
if((score)%5==0&&score!=0&&!b.isRunning())
{ss=true;
b.start();
s2.play();
}
  
sco.setLabel("Score: "+score);
if(!b.isRunning())
    food.setLocation((int)(Math.random()*(getWidth())-2),(int)(Math.random()*(getHeight()))-2);
 if(snak[size-1].getX()==tracex[par[size-1]-1]&&snak[size-1].getY()==tracey[par[size-1]-1]){
 if(tx[par[size-1]-1]==l)
    snak[size]=new GRect(snak[size-1].getX()-l,snak[size-1].getY(),l,l);
if(tx[par[size-1]-1]==-l)
    snak[size]=new GRect(snak[size-1].getX()+l,snak[size-1].getY(),l,l);
if(ty[par[size-1]-1]==l)
    snak[size]=new GRect(snak[size-1].getX(),snak[size-1].getY()-l,l,l);
if(ty[par[size-1]-1]==-l)
    snak[size]=new GRect(snak[size-1].getX(),snak[size-1].getY()+l,l,l);
     
 }else {
    if(tx[par[size-1]]==l)
    snak[size]=new GRect(snak[size-1].getX()-l,snak[size-1].getY(),l,l);
if(tx[par[size-1]]==-l)
    snak[size]=new GRect(snak[size-1].getX()+l,snak[size-1].getY(),l,l);
if(ty[par[size-1]]==l)
    snak[size]=new GRect(snak[size-1].getX(),snak[size-1].getY()-l,l,l);
if(ty[par[size-1]]==-l)
    snak[size]=new GRect(snak[size-1].getX(),snak[size-1].getY()+l,l,l);}
snak[size].setFilled(true);
add(snak[size]);
   par[size]=par[size-1];
size++;
}

}

}

    @Override
    public void keyPressed(KeyEvent e) {
        tracex[s]=(int) snak[0].getX();
        tracey[s]=(int) snak[0].getY();
 s++;
        if(e.getKeyCode()==KeyEvent.VK_DOWN)
        {
            if(ty[s-1]!=-l)
            {my=l;
        mx=0;
            }        
        }
        else if(e.getKeyCode()==KeyEvent.VK_UP)
        {if(ty[s-1]!=l){
            my=-l;
        mx=0;}
        }
        else if(e.getKeyCode()==KeyEvent.VK_LEFT)
        {if(tx[s-1]!=l){
            my=0;
        mx=-l;}
                }
        else if(e.getKeyCode()==KeyEvent.VK_RIGHT)
        {if(tx[s-1]!=-l){
            my=0;
        mx=l;
        }
        }
tx[s]=mx;
ty[s]=my;
    }
 }