package friends;
import java.util.ArrayList;
import structures.Queue;
import structures.Stack;
public class Friends {
    /**
     * Finds the shortest chain of people from p1 to p2.
     * Chain is returned as a sequence of names starting with p1,
     * and ending with p2. Each pair (n1,n2) of consecutive names in
     * the returned chain is an edge in the graph.
     *
     * @param g  Graph for which shortest chain is to be found.
     * @param p1 Person with whom the chain originates
     * @param p2 Person at whom the chain terminates
     * @return The shortest chain from p1 to p2. Null if there is no
     * path from p1 to p2
     */
    public static ArrayList<String> shortestChain(Graph g, String p1, String p2) {

        //bfs; gets p1's index and creates a pointer to it, adds it to bfs queue and sets that it is visited
        boolean visited[] = new boolean[g.members.length];
        Queue q = new Queue<Person>();
        Stack dq = new Stack<String>();
        int beginNum = g.map.get(p1);
        Person begin = g.members[beginNum];
        visited[beginNum] = true;
        q.enqueue(begin);
        Person p = null;
        ArrayList<String> out = new ArrayList<String>();
        if (p1.equals(p2)) {
            out.add(p1);
            return out;
        }
        while (q.size() != 0) { //while the queue has people in it
            p = (Person) q.dequeue();
            dq.push(p.name); //dq stack will be used to remember the order of the people
            Friend f = p.first;
            while (f != null) { //while this person currently dequeued has friends
                if (!visited[f.fnum]) { //if it isn't visited, mark it as such and enqueue it
                    visited[f.fnum] = true;
                    q.enqueue(g.members[f.fnum]);
                }
                if (g.members[f.fnum].name.equals(p2)) { //if this friend is who you are looking for then break out, and look at your stack of dequeued people
                    out.add(g.members[f.fnum].name);
                    break;
                }
                f = f.next;
            }
            if (out.size() > 0) //if you found person stop looking at queue
                break;
        }
        if (out.size() == 0) {
            return null;
        } else {
            if (!p1.equals(p.name))
                out.add(0, p.name); //p is currently the person who directly links to the target
            while (dq.size() > 1) {
                Friend f = p.first;
                String pers = (String) dq.pop();
                int pNum = g.map.get(pers);
                while (f != null) { //check all the dequeued people; if the person's array number matches the fnum, then they are a part of the chain
                    if (pNum == f.fnum) {
                        out.add(0, pers);
                        p = g.members[pNum];
                        break;
                    }
                    f = f.next;
                }
            }
            out.add(0, (String) dq.pop());
            return out;
        }
    }

    /**
     * Finds all cliques of students in a given school.
     * <p>
     * Returns an array list of array lists - each constituent array list contains
     * the names of all students in a clique.
     *
     * @param g      Graph for which cliques are to be found.
     * @param school Name of school
     * @return Array list of clique array lists. Null if there is no student in the
     * given school
     */
    public static ArrayList<ArrayList<String>> cliques(Graph g, String school) {
        /** COMPLETE THIS METHOD **/
        // FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY
        // CHANGE AS REQUIRED FOR YOUR IMPLEMENTATION
        return null;
    }

    /**
     * Finds and returns all connectors in the graph.
     *
     * @param g Graph for which connectors needs to be found.
     * @return Names of all connectors. Null if there are no connectors.
     */
    public static ArrayList<String> connectors(Graph g) {
        /** COMPLETE THIS METHOD **/
        // FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY
        // CHANGE AS REQUIRED FOR YOUR IMPLEMENTATION
        return null;
    }
}