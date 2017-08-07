package gameserver;
public class Square {
    Line one,two,three,four;
    boolean printed=false;
   Square(Line l1,Line l2,Line l3,Line l4)
   {
       Line[] array = new Line[4];
       array[0]=l1;
       array[1]=l2;
       array[2]=l3;
       array[3]=l4;
       int min;
       for(int row=0;row<4;row++)
       {
           min=row;
           for(int col=0;col<4;col++)
           {
               if(array[col].xf <= array[min].xf && array[col].yf <= array[min].yf)
               {
                   min = col;
               }
           }
           Line temp = array[min];
           array[min] = array[row];
           array[row] = temp;
       }
       one= array[0];
       two = array[1];
       three = array[2];
       four = array[3];
   }
   boolean ValidSquare()
   {

       myCollections mt = new myCollections(20);
       //System.out.println("There1");
       Ppoint one1 = new Ppoint(one.xf,one.yf);
       // System.out.println("There1");
       mt.addTPpoint(one1);
       //System.out.println("There1");
       Ppoint one2 = new Ppoint(one.xl,one.yl);
       mt.addTPpoint(one2);
       Ppoint two1 = new Ppoint(two.xf,two.yf);
       mt.addTPpoint(two1);
       Ppoint two2 = new Ppoint(two.xl,two.yl);
       mt.addTPpoint(two2);
       Ppoint thr1 = new Ppoint(three.xf,three.yf);
       mt.addTPpoint(thr1);
       Ppoint thr2 = new Ppoint(three.xl,three.yl);
       mt.addTPpoint(thr2);
       Ppoint four1 = new Ppoint(four.xf,four.yf);
       mt.addTPpoint(four1);
       Ppoint four2 = new Ppoint(four.xl,four.yl);
       mt.addTPpoint(four2);
        //System.out.println(mt.pCount);
       mt.addPpoint(one1);
       
       for(int i = 1;i < mt.pTcount;i++)
       {
           boolean put =true;
           for(int j=0;j<mt.pCount;j++)
           {
               if(mt.temp[i].xx== mt.point[j].xx && mt.temp[i].yy==mt.point[j].yy)put = false;
           }
           if(put)
           {
               mt.addPpoint(mt.temp[i]);

           }
       }
       //System.out.println(mt.pCount);
        if(mt.pCount == 4){
            //JLabel square = new JLabel("A");
            return true;
      
        }
      else return false;
       
   }
}

