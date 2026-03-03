package DataStructure;

import DataStructure.POJO.RawData;
import DataStructure.POJO.RawEdge;
import DataStructure.POJO.RawNode;
import java.util.HashMap;

public class Graph {
    public HashMap<String, Node> nodes = new HashMap<>();

    public Graph(RawData data) {

        for (RawNode rNode : data.nodes) {
            this.nodes.put(rNode.id, new Node(rNode.id, rNode.type));
        }

        for (RawEdge rEdge : data.edges) {
            Node source = this.nodes.get(rEdge.source);
            Node target = this.nodes.get(rEdge.target);

            if (source != null && target != null) {
                source.out.add(target.id);
                target.in.add(source.id);

            }
        }
    }
}
