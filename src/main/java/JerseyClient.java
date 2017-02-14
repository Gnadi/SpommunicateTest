import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import javafx.geometry.Pos;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Gnadlinger on 14-Feb-17.
 */
public class JerseyClient {
    public static void main(String[] args) throws JSONException {


        Client client = Client.create();

        WebResource webResource = client
                .resource("http://gladiolus.htl-leonding.ac.at/SpommunicateService/api/positionen");

        ClientResponse response = webResource.accept("application/json")
                .get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }

        String output = response.getEntity(String.class);
        JSONObject responseObject = new JSONObject(output);
        JSONArray data = responseObject.getJSONArray("DtoPositionart");
        for (int i = 0; i < data.length(); i++) {
            JSONObject obj = data.getJSONObject(i);
            Positionen product = new Positionen();
            product.setPositionartId(obj.getString("PositionartId"));
            product.setName(obj.getString("Name"));
            product.setKurzzeichen(obj.getString("Kurzzeichen"));


        }
        System.out.println("Output from Server .... \n");
        System.out.println(output);
    }
}
