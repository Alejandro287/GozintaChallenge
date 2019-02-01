package algorithm;

import java.util.LinkedList;
import java.util.ArrayList;

public class Test1 {

    public static void main (String[] args) {
        Graph<Integer> graph = new Graph<Integer>();
        Graph<Integer> graphInv = new Graph<Integer>();
        int n = 12;
        crateGraph(n, graph, graphInv);

        System.out.println("The current graph: " + graph);
        System.out.println("The current graph: " + graphInv);

        ArrayList<LinkedList<Integer>> lista = graph.toString2(n);

        System.out.println(lista.toString() + " " + lista.size());
    }

    public static void crateGraph(int n, Graph graph, Graph graphInv){

        for(int i = 2; i <= n ; i++){
            if(n%i==0) {
                int num = n / i;
                graph.add(n);
                graph.add(num);
                graphInv.add(n);
                graphInv.add(num);
                if (!graph.containsEdge(n, num)){
                    graph.add(n, num);
                }
                if (!graphInv.containsEdge(num, n)){
                    graphInv.add(num, n);
                }
                crateGraph(num, graph, graphInv);
            }
        }

    }

}
