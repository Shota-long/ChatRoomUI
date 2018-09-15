package chatFrame;
import java.util.*;

public class ListTest {
    public static void main(String... arg)
    {
        Map map = new HashMap();
        map.put("hello", new Test());

        Test t = (Test) map.get("hello");
        t.i = 3;
        Test t2 = (Test) map.get("hello");
        System.out.println(t2.i);
    }
}

class Test
{
    public int i = 0;
}
