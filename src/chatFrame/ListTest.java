package chatFrame;
import java.util.*;

public class ListTest {

    private ListTest() {
       String s="0# ";
       int i = s.indexOf("#");
        System.out.println(i);
       String s1 = s.substring(0,i);
       String s2 = s.substring(i+1,s.length()).trim();
       System.out.println("s1"+s1);
        System.out.println("s2"+s2);
        System.out.println(s2.equals(""));
    }
    public static void main(String[] args){
        new ListTest();
    }
}
