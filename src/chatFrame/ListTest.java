package chatFrame;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListTest {
    List user_list = new ArrayList();
    private ListTest(){
        user_list.add("a");
        user_list.add("b");
        user_list.add("c");
        for (Iterator iterators = user_list.iterator(); iterators.hasNext();){
            String list =  (String )iterators.next();
            if (list.equals("b"))
                iterators.remove();
        }
        for (Iterator iterators = user_list.iterator();iterators.hasNext();){
            String list =  (String )iterators.next();
            System.out.println(list);
        }
    }
    public static void main(String[] args){
        new ListTest();
    }
}
