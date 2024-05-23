//import org.example.Item;
import org.example.SILab2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SILab2Test {
    private List<SILab2.Item> createItems(SILab2.Item... items) {
        return new ArrayList<>(Arrays.asList(items));
    }
    @Test
    void EveryBranchTest(){

        //first test case - empty list
        RuntimeException ex;
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, 100));
        assertTrue(ex.getMessage().contains("allItems list can't be null!"));

        //second test case - character in barcode
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart( createItems(new SILab2.Item("FirstItem", "80c", 13, 0)),100));
        assertTrue(ex.getMessage().contains("Invalid character in item barcode!"));

        //third test case - name is null
        assertTrue(SILab2.checkCart( createItems(new SILab2.Item(null, "180", 100, 0)),150));

        //fourth test case - no barcode
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart( createItems(new SILab2.Item("Item0", null, 100, 0)),100));
        assertTrue(ex.getMessage().contains("No barcode!"));

        //fifth test case - all good - return false
        assertFalse(SILab2.checkCart( createItems(new SILab2.Item("Item1", "021", 360, 20)),100));
    }

    @Test
    void MultipleConditionTest(){
        RuntimeException ex;

        //T, T, T
        SILab2.Item TTT = new SILab2.Item("Test1", "0123", 350, 3);
        assertFalse(SILab2.checkCart(createItems(TTT), 100));

        //T, T, F
        SILab2.Item TTF = new SILab2.Item("Test2", "123", 350, 3);
        assertFalse(SILab2.checkCart(createItems(TTF), 100));

        //T, F, X
        SILab2.Item TFX = new SILab2.Item("Test3", "263", 350, -1);
        assertFalse(SILab2.checkCart(createItems(TFX), 100));

        //F, X, X
        SILab2.Item FXX = new SILab2.Item("Test4", "263", 100, 0);
        assertFalse(SILab2.checkCart(createItems(FXX), 50));
    }

}