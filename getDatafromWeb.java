import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import com.google.gson.Gson;
import DataStructure.POJO.RawData;
import DataStructure.POJO.RawNode;

public class getDatafromWeb {
    public RawData data;
    public HashMap<String, String> id2type;

    public getDatafromWeb() throws Exception {
        this.data = getData();
        this.id2type = id2type();

    }

    private RawData getData() throws Exception {
        String url = "https://storage.googleapis.com/maoz-event/rawdata.txt";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        RawData data = new Gson().fromJson(response.body(), RawData.class);
        return data;
    }

    private HashMap<String, String> id2type() {
        HashMap<String, String> map = new HashMap<>();
        for (RawNode node : data.nodes) {
            map.put(node.id, node.type);
        }
        return map;
    }

}
