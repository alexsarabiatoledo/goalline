package com.example.apistore.ApiCalls;

import com.example.apistore.models.OutstandingProduct;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OutstandingProductService {

    public List<OutstandingProduct> getProducts(String mercadoLibreURL) {
        List<OutstandingProduct> productList = new ArrayList<>();

        String json="";
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(mercadoLibreURL);

            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                int statusCode = response.getStatusLine().getStatusCode();

                if (statusCode == 200) {
                    HttpEntity entity = response.getEntity();

                    if (entity != null) {
                        json = EntityUtils.toString(entity);
                    }
                } else {
                    System.err.println("La solicitud GET falló con el código de estado: " + statusCode);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(json).get("results");
            for (int i=0; i<rootNode.size(); i++){
                var product = OutstandingProduct.builder()
                        .id(rootNode.get(i).get("id").asText())
                        .title(rootNode.get(i).get("title").asText())
                        .permalink(rootNode.get(i).get("permalink").asText())
                        .thumbnail(rootNode.get(i).get("thumbnail").asText())
                        .price(rootNode.get(i).get("price").asText())
                        .originalPrice(rootNode.get(i).get("original_price").asText())
                        .picture(getProductImage(rootNode.get(i).get("thumbnail").asText()))
                        .build();
                productList.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

    public String getProductImage(String url){
        String convertedUrl = url.replace("http://", "https://");
        int jpgIndex = convertedUrl.indexOf(".jpg");
        if (jpgIndex != -1 && jpgIndex > 0) {
            convertedUrl = convertedUrl.substring(0, jpgIndex - 1) + "O" + convertedUrl.substring(jpgIndex);
        }
        return convertedUrl;
    }
}
