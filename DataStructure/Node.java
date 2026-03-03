package DataStructure;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public String id;
    public String type;
    public List<String> in;
    public List<String> out;

    public Node(String id, String type) {
        this.id = id;
        this.type = type;
        this.in = new ArrayList<>();
        this.out = new ArrayList<>();
    }
}
