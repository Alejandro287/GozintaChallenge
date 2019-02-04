package algorithm;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class GozintaChainsTest {

    @Test
    void getGozintaChain() {
        GozintaChains gozintaChains = new GozintaChains(12);
        long[][] matrix = {{1, 12}, {1, 2, 12}, {1, 6, 12}, {1, 2, 6, 12},
                {1, 3, 6, 12}, {1, 3, 12}, {1, 4, 12}, {1, 2, 4, 12}};
        LinkedList<LinkedList<Long>> expectedResult = new LinkedList<>();
        for(int i = 0 ; i < matrix.length ; i++){
            LinkedList<Long> currentList = new LinkedList<>();
            for(int j = 0 ; j < matrix[i].length ; j++){
                currentList.addLast(matrix[i][j]);
            }
            expectedResult.addLast(currentList);
        }
        assertEquals(expectedResult, gozintaChains.getGozintaChain());
    }

    @Test
    void getNumberOfChains() {
        GozintaChains gozintaChains = new GozintaChains(120);
        assertEquals(132L, gozintaChains.getNumberOfChains());
    }

    @Test
    void createChains() {
        GozintaChains gozintaChains = new GozintaChains();
        long[][] matrix = {{1, 6}, {1, 2, 6}, {1, 3, 6}};
        LinkedList<LinkedList<Long>> expectedResult = new LinkedList<>();
        for(int i = 0 ; i < matrix.length ; i++){
            LinkedList<Long> currentList = new LinkedList<>();
            for(int j = 0 ; j < matrix[i].length ; j++){
                currentList.addLast(matrix[i][j]);
            }
            expectedResult.addLast(currentList);
        }
        assertEquals(expectedResult, gozintaChains.createChains(6));
    }
}