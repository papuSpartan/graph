import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        McGraph a = new McGraph();
        a.add("home", "dorm", 45);
        a.add("home", "bingus", 20);


        for(McGraph.Vertex v : a.vertices.values()) {
            System.out.print(v.key+": ");
            for(McGraph.Edge e : v.getEdges()) {
                System.out.print(e.dest.key);
            }
            System.out.println();

        }
    }
}
