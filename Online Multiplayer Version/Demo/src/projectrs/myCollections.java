package projectrs;

public class myCollections {
    public Line line[];
    public Square square[];
    public Ppoint point[];
    public Ppoint temp[];
    public int lineCount,sqCount,pCount,pTcount;
    public myCollections(int size)
    {
        line = new Line[size];
        lineCount = 0;
        sqCount = 0;
        square = new Square[size];
        point = new Ppoint[size];
        temp = new Ppoint[size];
        pCount = 0;
        pTcount = 0;
    }
    
    void addLine(Line l)
    {
        line[lineCount] = l;
        lineCount++;
    }
    void addSquare(Square sq)
    {
        square[sqCount] = sq;
        sqCount++;
    }
    void addPpoint(Ppoint pp)
    {
        point[pCount] = pp;
        pCount++;
    }
    void addTPpoint(Ppoint pp)
    {
        
        temp[pTcount] = pp;
        pTcount++;
    }
    void addCompletedSquare(Ppoint pointy) {
        for(int i=0;i<pCount;i++){
            if(pointy.xx == point[i].xx && pointy.yy == point[i].yy) {
                point[i].completedSquare=true;
            }
        }
    }
    boolean CompletedSquare(Ppoint pointy) {
        for(int i=0;i<pCount;i++){
            if(pointy.xx == point[i].xx && pointy.yy == point[i].yy) {
                return point[i].completedSquare;
            }
        }
        return false;
    }
}
