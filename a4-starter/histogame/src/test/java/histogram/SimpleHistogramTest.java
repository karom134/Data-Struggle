package histogram;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;

 //TODO: Uncomment me when you're done implementing the class
public class SimpleHistogramTest {

    @Test
    public void testHistogram() {
        Character[] target = {'a','b','c','a'};
        Histogram<Character> h_bar = new SimpleHistogram<>(target);
        Histogram<Character> h=new SimpleHistogram<>(h_bar);
        SimpleHistogram<Character> checker=new SimpleHistogram<>(h_bar);
        Iterator<Character> iter = h.iterator();
        int elemCount = 0;
        while(iter.hasNext()) {
            iter.next();
            elemCount++;
        }

        assertEquals(3, elemCount);
        assertEquals(2, h.getCount('a'));
        assertEquals(1, h.getCount('b'));
        assertEquals(1, h.getCount('c'));
        assertEquals(4, h.getTotalCount());
        System.out.println(checker.equals(h_bar));
    }
}

