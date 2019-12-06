package friends;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Test {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("test3.txt"));
        Graph graph = new Graph(sc);
        Scanner re = new Scanner(System.in);
        int num = 0;
        String f1 = "";
        String f2 = "";
        ArrayList<String> shortest = new ArrayList<>();
        ArrayList<String> out = Friends.connectors(graph);
        if (out != null) {
            for (String s : out) {
                System.out.print(s + " ");
            }
        } else
            System.out.println("No connectors");
        //shortest = Friends.shortestChain(graph, "sam", "sergei");
        //System.out.println("path from " + f1 + " to " + f2 + ": "+ shortest);
        /*for (int i=0; i< graph.members.length; i++) {
            for (int j=0; j<graph.members.length;j++) {
                f1 = graph.members[i].name;
                f2 = graph.members[j].name;
                shortest = Friends.shortestChain(graph, f1, f2);
                System.out.println("path from " + f1 + " to " + f2 + ": "+ shortest);
            }
        }
        */

        /*
        System.out.print("Method? ");
        num = re.nextInt();
        while (num != -1) {

            re.nextLine();
            if (num == 1) {
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
            } else if (num == 2) {
                System.out.println("What school? ");
                String sch = re.nextLine();
                ArrayList<ArrayList<String>> cliques = Friends.cliques(graph, sch);
                if (cliques != null) {
                    for (ArrayList<String> perClique : cliques) {
                        System.out.println(perClique.toString());
                    }
                } else
                    System.out.println("No cliques");
            } else {
                ArrayList<String> out = Friends.connectors(graph);
                if (out != null) {
                    for (String s : out) {
                        System.out.print(s + " ");
                    }
                } else
                    System.out.println("No connectors");
            }
            System.out.print("Method? ");
            num = re.nextInt();
        }
        */


    }

}