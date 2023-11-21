package com.example.apistore.ApiCalls;

import com.example.apistore.models.Attribute;
import com.example.apistore.models.Product;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class ApiMercadolibreService {
    ArrayList<Product> productList=new ArrayList<Product>();
    public ArrayList<Product> getProductsByQuery(String query) {
        productList.clear();
        query = query.replaceAll(" ", "%20");
        String url = "https://api.mercadolibre.com/sites/MLC/search?q="+query; // Reemplaza con la URL de donde deseas obtener el JSON.
        String json="";
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(url);

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
            JsonNode rootNode = objectMapper.readTree(json);

            JsonNode nombre = rootNode.get("results");

            for (int i=0; i<nombre.size(); i++){
                String productId=nombre.get(i).get("id").asText();
                String productName=nombre.get(i).get("title").asText();
                String productPrice=nombre.get(i).get("price").asText();
                String productCondition=nombre.get(i).get("condition").asText();
                String productPhotoLink=nombre.get(i).get("thumbnail").asText();
                //obtener true o false si el producto tiene envío gratis
                Boolean freeShipping=nombre.get(i).get("shipping").get("free_shipping").asBoolean();

                productList.add(new Product(productId,productName, productPrice, productCondition, productPhotoLink, freeShipping));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        json="";
        return productList;
    }
    public Product getProductById(String id) {
        String url = "https://api.mercadolibre.com/items?ids="+id; // Reemplaza con la URL de donde deseas obtener el JSON.
        String json="";
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(url);

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
            JsonNode rootNode = objectMapper.readTree(json);

            JsonNode productJson = rootNode.get(0).get("body");
            JsonNode productAttributesJson=productJson.get("attributes");
            String brand="", model="";
            ArrayList<Attribute> attributes = new ArrayList<>();
            for (int i=0; i<productAttributesJson.size();i++){
                String attributeName=productAttributesJson.get(i).get("name").asText();
                String attributeValue=productAttributesJson.get(i).get("values").get(0).get("name").asText();
                Attribute newAttribute=new Attribute(attributeName, attributeValue);
                attributes.add(newAttribute);

                if(attributeName.equals("Marca")){
                    brand=productAttributesJson.get(i).get("values").get(0).get("name").asText();
                }
                if(attributeName.equals("Modelo")){
                    model=productAttributesJson.get(i).get("values").get(0).get("name").asText();
                }
            }
            String productDisponibility="";
            String productId=productJson.get("id").asText();
            String productName=productJson.get("title").asText();
            String productPrice=productJson.get("price").asText();
            String productCondition=productJson.get("condition").asText();
            String productPhotoLink=productJson.get("thumbnail").asText();
            if(productJson.get("available_quantity")!=null){
                productDisponibility=productJson.get("available_quantity").asText();
            }else{
                productDisponibility="0";
            }



            Product product=new Product(productId, productName, productPrice, productCondition, productPhotoLink, brand, model, productDisponibility, attributes);


            return product;
        } catch (Exception e) {
            e.printStackTrace();
        }
        json="";
        return null;
    }
    public ArrayList<Product> getProductsByCategory(String category) {
        productList.clear();
        String url = "https://api.mercadolibre.com/sites/MLC/search?category="+category; // Reemplaza con la URL de donde deseas obtener el JSON.
        String json="";
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(url);

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
            JsonNode rootNode = objectMapper.readTree(json);

            JsonNode nombre = rootNode.get("results");
            for (int i=0; i<nombre.size(); i++){
                String productId=nombre.get(i).get("id").asText();
                String productName=nombre.get(i).get("title").asText();
                String productPrice=nombre.get(i).get("price").asText();
                String productCondition=nombre.get(i).get("condition").asText();
                String productPhotoLink=getProductImage(nombre.get(i).get("thumbnail").asText());;
                //obtener true o false si el producto tiene envío gratis
                Boolean freeShipping=nombre.get(i).get("shipping").get("free_shipping").asBoolean();
                productList.add(new Product(productId,productName, productPrice, productCondition, productPhotoLink, freeShipping));
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