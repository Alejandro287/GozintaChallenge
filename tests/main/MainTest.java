package main;

import algorithm.GozintaChains;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void main() {
        GozintaChains gozintaChains = new GozintaChains(1100000);
        assertEquals(377616, gozintaChains.getNumberOfChains());

        gozintaChains = new GozintaChains(8);
        long[][] matrix = {{1, 8}, {1, 2, 8}, {1, 4, 8}, {1, 2, 4, 8}};
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
}