import DataStructure.Graph;
import DataStructure.Node;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class useGraph {
    void run() throws Exception {
        getDatafromWeb pullData = new getDatafromWeb();

        Graph graph = new Graph(pullData.data);

        Node root = null;
        for (Node node : graph.nodes.values()) {
            if (node.in.isEmpty()) {
                root = node;
                break;
            }
        }

        if (root == null) {
            System.out.println("No root node found!");
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(root);
        visited.add(root.id);

        List<Node> orderedNodes = new ArrayList<>();

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            orderedNodes.add(current);

            for (String nextId : current.out) {
                if (!visited.contains(nextId)) {
                    visited.add(nextId);
                    queue.add(graph.nodes.get(nextId));
                }
            }
        }

        List<String> nodesList = new ArrayList<>();
        List<String> addressInList = new ArrayList<>();
        List<String> addressOutList = new ArrayList<>();

        for (Node node : orderedNodes) {
            nodesList.add(node.type);
            addressInList.add(String.join(", ", node.in));
            addressOutList.add(String.join(", ", node.out));
        }

        System.out.println("nodes = [" + String.join(", ", nodesList) + "]");
        System.out.println("addressIn = [" + String.join(", ", addressInList) + "]");
        System.out.println("addressOut = [" + String.join(", ", addressOutList) + "]");
    }
}
