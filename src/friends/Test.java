package friends;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Test {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("test.txt"));
        Graph graph = new Graph(sc);
        Scanner re = new Scanner(System.in);
        System.out.println("Friend 1: ");
        String f1 = re.nextLine();
        System.out.println("Friend 2: ");
        String f2 = re.nextLine();
        ArrayList<String> shortest = Friends.shortestChain(graph, f1, f2);
        if (shortest == null) {
            System.out.println("Found no link");
        } else {
            for (String person : shortest) {
                System.out.println(person);
            }
        }
        /*
        System.out.println("\n\n");

        ArrayList<ArrayList<String>> cliques = Friends.cliques(graph, "foreign");
        if(cliques != null) {
            for(ArrayList<String> perClique : cliques) {
                System.out.println(perClique.toString());
            }
        }
        System.out.println("\n\n");

        ArrayList<String> connectors = Friends.connectors(graph);
        for(String connector : connectors) {
            System.out.println(connector);
        }

         */

    }

}