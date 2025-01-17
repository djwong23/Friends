package friends;
import java.util.ArrayList;
import java.util.HashMap;
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
            return null;
        }
        HashMap<String, String> prev = new HashMap<>(); //keeps the friend's "parent" or previous to store the chain
        while (q.size() != 0) { //while the queue has people in it
            p = (Person) q.dequeue();
            dq.push(p.name); //dq stack will be used to remember the order of the people
            Friend f = p.first;
            while (f != null) { //while this person currently dequeued has friends
                if (!visited[f.fnum]) { //if it isn't visited, mark it as such and enqueue it
                    visited[f.fnum] = true;
                    q.enqueue(g.members[f.fnum]);
                    prev.put(g.members[f.fnum].name, p.name);
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
        } else { /*
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
                */
            String s = out.get(0);
            while (prev.get(s) != null) { //adds the people to the out in reverse order
                out.add(0, prev.get(s));
                s = prev.get(s);
            }
            //out.add(0, (String) dq.pop());
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
        boolean[] visited = new boolean[g.members.length];
        ArrayList<ArrayList<String>> out = new ArrayList<ArrayList<String>>();
        ArrayList<String> in = new ArrayList<String>();
        Person p = null;
        Queue q = new Queue<Person>();
        for (int j = 0; j < visited.length; j++) {
            System.out.println(g.members[j].name);//go through all people who are not visited and check all their friends + schools
            if (visited[j] == false) {
                p = g.members[j];
                visited[j] = true;
                q.enqueue(p);
                while (q.size() != 0) { //while the queue has people in it
                    p = (Person) q.dequeue();
                    Friend f = p.first;
                    while (f != null) { //while this person currently dequeued has friends
                        if (!visited[f.fnum]) { //if it isn't visited, mark it as such and enqueue it
                            visited[f.fnum] = true;
                            System.out.println(g.members[f.fnum].name);
                            String sch = g.members[f.fnum].school;
                            if (sch != null) {//if this friend is the same school then put him in "in"
                                if (sch.equals(school)) {
                                    q.enqueue(g.members[f.fnum]);
                                    in.add(g.members[f.fnum].name);
                                }
                            }
                        }
                        f = f.next;
                    }
                    if (p.school != null) {
                        if (p.school.equals(school) && in.indexOf(p.name) == -1)
                            in.add(p.name);
                    }
                }
                if (in.size() > 0)
                    out.add((ArrayList<String>) in.clone());
                in.clear();
            }
        }
        return (out.size() == 0) ? null : out;
    }
    /**
     * Finds and returns all connectors in the graph.
     *
     * @param g Graph for which connectors needs to be found.
     * @return Names of all connectors. Null if there are no connectors.
     */
    public static ArrayList<String> connectors(Graph g) {
        boolean visited[] = new boolean[g.members.length];
        int[] dfsNum = new int[g.members.length];
        int[] back = new int[g.members.length];
        ArrayList<String> connectors = new ArrayList<>();
        ArrayList<String> cons = new ArrayList<>();
        boolean[] backed = new boolean[g.members.length];
        int count = 1;
        for (int i = 0; i < visited.length; i++) {
            if (visited[i] == false) {
                DFS(true, g, g.members[i], visited, dfsNum, back, connectors, count, cons, backed);
            }
        }
        return (connectors.size() > 0) ? connectors : null;
    }
    private static void DFS(boolean start, Graph g, Person p, boolean[] visited, int[] dfsNum, int[] back, ArrayList<String> connectors, int count, ArrayList<String> cons, boolean[] backed) {
        int num = g.map.get(p.name);
        visited[num] = true;
        dfsNum[num] = count;
        back[num] = count;
        count++;
        Friend f = p.first;
        while (f != null) {
            if (visited[f.fnum] == false) {
                DFS(false, g, g.members[f.fnum], visited, dfsNum, back, connectors, count, cons, backed); //if friend not visited, recurse dfs on friend
                if (dfsNum[num] > back[f.fnum])
                    back[num] = Math.min(back[num], back[f.fnum]);
                else if (dfsNum[num] <= back[f.fnum]) {
                    if (!start) {
                        if (cons.indexOf(p.name) == -1) {
                            connectors.add(p.name);
                            cons.add(p.name);
                        }
                    } else if (start) { //if it is the start of the DFS, as long as it has been backed up once, it should still be a connector
                        if (backed[g.map.get(p.name)])
                            if (cons.indexOf(p.name) == -1) {
                                connectors.add(p.name);
                                cons.add(p.name);
                            }
                    }
                }
                backed[g.map.get(p.name)] = true;
            } else
                back[num] = Math.min(back[num], dfsNum[f.fnum]);
            f = f.next;
        }
    }
}
