package projectrs;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

abstract class Converter {

    public static String makeString(int x1, int y1, int x2, int y2) {
        String s;
        s = x1 + " " + y1 + " " + x2 + " " + y2;
        return s;
    }

    public static Line makeLine(String s) {
        String now = "";
        int flag = 0;
        int x1=0, y1=0, x2=0, y2=0;
        for (int i = 0; i < s.length(); i++) {
            char u = s.charAt(i);

            if (u == ' ') {
                //System.out.println(now);
                flag++;

                if (flag == 1) {
                    x1 = Integer.parseInt(now);
                }
                if (flag == 2) {
                    y1 = Integer.parseInt(now);
                }
                if (flag == 3) {
                    x2 = Integer.parseInt(now);
                }

                now = "";
            } else {
                now = now + u;
            }
        }
        y2 = Integer.parseInt(now);
        Line newLine = new Line(x1,y1,x2,y2);
        return newLine;
    }
   
    public static void sendList(List l,DataOutputStream out) throws IOException
    {
        int Size = l.size();
        String s = Integer.toString(Size);
        out.writeUTF(s);
        ListIterator<String> it = null;
        it = l.listIterator();
         while (it.hasNext()) {
            String Current = it.next();
            out.writeUTF(Current);
         }
    }
    public static List receiveList(DataInputStream in) throws IOException
    {
        List l = new LinkedList();
        int Size = Integer.parseInt(in.readUTF());
        System.out.println(Size);
        for(int i=0;i<Size;i++)
        {
            String use = in.readUTF();
            l.add(use);
        }
        return l;
    }
}

