import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import DataStructure.POJO.RawData;
import DataStructure.POJO.RawEdge;
import DataStructure.POJO.RawNode;

public class useReletion {
    public void run() throws Exception {
        getDatafromWeb pullData = new getDatafromWeb();
        HashMap<String, String> id2type = pullData.id2type;
        HashMap<String, List<String>> target2source = target2source(pullData.data);
        HashMap<String, List<String>> source2target = source2target(pullData.data);

        List<String> nodes = new ArrayList<>();
        List<String> addressIn = new ArrayList<>();
        List<String> addressOut = new ArrayList<>();

        String root = null;
        for (var entry : target2source.entrySet()) {
            if (entry.getValue().isEmpty()) {
                root = entry.getKey();
                break;
            }
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        if (root != null) {
            queue.add(root);
            visited.add(root);
        }

        while (!queue.isEmpty()) {
            String nodeId = queue.poll();

            nodes.add(id2type.get(nodeId));

            List<String> sources = target2source.get(nodeId);
            addressIn.add(sources.isEmpty() ? "" : String.join(", ", sources));

            List<String> targets = source2target.get(nodeId);
            addressOut.add(targets.isEmpty() ? "" : String.join(", ", targets));

            for (String next : targets) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.add(next);
                }
            }
        }

        System.out.println("nodes = [" + String.join(", ", nodes) + "]");
        System.out.println("addressIn = [" + String.join(", ", addressIn) + "]");
        System.out.println("addressOut = [" + String.join(", ", addressOut) + "]");
    }

    private HashMap<String, List<String>> target2source(RawData data) {
        HashMap<String, List<String>> target2source = new HashMap<>();
        for (RawNode node : data.nodes) {
            target2source.put(node.id, new ArrayList<>());
        }
        for (RawEdge edge : data.edges) {
            target2source.get(edge.target).add(edge.source);
        }
        return target2source;
    }

    private HashMap<String, List<String>> source2target(RawData data) {
        HashMap<String, List<String>> source2target = new HashMap<>();
        for (RawNode node : data.nodes) {
            source2target.put(node.id, new ArrayList<>());
        }
        for (RawEdge edge : data.edges) {
            source2target.get(edge.source).add(edge.target);
        }
        return source2target;
    }
}
