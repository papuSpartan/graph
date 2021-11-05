import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;

public class McGraph {
    Hashtable<String, Vertex> vertices = new Hashtable<>();

    class Edge {
        Vertex src, dest;
        int cost;

        Edge(Vertex src, Vertex dest, int cost) {
            this.src = src;
            this.dest = dest;
            this.cost = cost;
        }
    }

    class Vertex {
        String key;
        LinkedList<Edge> vEdges = new LinkedList<>();

        public LinkedList<Edge> getEdges() {
            return vEdges;
        }

        Vertex(String key) {
            this.key = key;
            if(!vertices.contains(key)) {
                vertices.put(key, this);
            } else {
                System.out.println("WARN: adding already existing vertex...");
            }
        }

        public boolean hasEdgeTo(Vertex dest) {
            for(Edge e : vEdges) {
                if(e.dest.equals(dest)) return true;
            }
            return false;
        }
        //add new edge with this Vertex being the source
        public void addEdge(String dest, int cost) {
            //check if destination vertex even exists
            if(vertices.contains(dest)) {
                Vertex destObj = vertices.get(dest);
                vEdges.add(new Edge(this, destObj, cost));

                //if it already existed check if it has the edge we just created but in coming from it
                //else make the edge
                if(!destObj.hasEdgeTo(this)) {
                    destObj.vEdges.add(new Edge(destObj, this, cost));
                }

            } else {
                //create vertex that didn't exist and create edges
                Vertex newV = new Vertex(dest);
                //creating edge from this vertex to the new vertex we just made
                vEdges.add(new Edge(this, newV, cost));
                //since vertex was only just created, we know we will have to replicate the edge on its end
                newV.vEdges.add(new Edge(newV, this, cost));
            }

            }

        }

    public void add(String src, String dest, int cost) {
        if(vertices.contains(src)) {
            Vertex srcObj = vertices.get(src);
            srcObj.addEdge(dest, cost);
        } else {
            Vertex newV = new Vertex(src);
            newV.addEdge(dest, cost);
        }
    }

    public McGraph(){}
}
