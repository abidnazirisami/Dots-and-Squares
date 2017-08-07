package projectrs;

import java.awt.Color;
import java.awt.Graphics;

public class Line {
    int xf,xl,yf,yl,lineCnt;
    boolean isWhite=false,isGray=false;
    Line(int x1,int y1,int x2,int y2)
    {
        xf=x1;
        xl=x2;
        yf=y1;
        yl=y2;
        
    }
    boolean getWhite(myCollections mc){
        lineCnt = mc.lineCount;
        //System.out.println("Check");
        for(int i =0 ;i<lineCnt;i++)
        {
            
            if((this.xf==(mc.line[i].xf)) && (this.xl==(mc.line[i].xl)) && ((this.yf==(mc.line[i].yf)) ) && (this.yl==(mc.line[i].yl))){
                
                return mc.line[i].isWhite;
            }
        }
        
        return false;
      
    }
    boolean getGray(myCollections mc){
        lineCnt = mc.lineCount;
        //System.out.println("Check");
        for(int i =0 ;i<lineCnt;i++)
        {
            
            if((this.xf==(mc.line[i].xf)) && (this.xl==(mc.line[i].xl)) && ((this.yf==(mc.line[i].yf)) ) && (this.yl==(mc.line[i].yl))){
                
                return mc.line[i].isGray;
            }
        }
        
        return false;
      
    }
    boolean getTrueValue(myCollections mc)
    {
        lineCnt = mc.lineCount;
        //System.out.println("Check");
        for(int i =0 ;i<lineCnt;i++)
        {
            
            if((this.xf==(mc.line[i].xf)) && (this.xl==(mc.line[i].xl)) && ((this.yf==(mc.line[i].yf)) ) && (this.yl==(mc.line[i].yl))){
                
                return true;
            }
        }
        
        return false;
    }  
}
