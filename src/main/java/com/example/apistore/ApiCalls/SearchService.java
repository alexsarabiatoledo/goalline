package com.example.apistore.ApiCalls;


import com.example.apistore.models.SearchModule.Category;
import com.example.apistore.models.SearchModule.Filter;
import com.example.apistore.models.SearchModule.FilterValue;
import com.example.apistore.models.SearchModule.Search;
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
public class SearchService {
    //método para obtener la search
    public Search getSearch(String query) {
        String url = "https://api.mercadolibre.com/sites/MLC/search?q="+query;
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
            if(objectMapper.readTree(json).get("filters").get(0)!=null){
                //Search(searchCategory, pathFromRootCategoryList, listaFiltrosDisponibles);
                JsonNode categoriaBusqueda = objectMapper.readTree(json).get("filters").get(0).get("values").get(0); //esta puede ser null
                Category searchCategory = new Category(categoriaBusqueda.get("id").asText(), categoriaBusqueda.get("name").asText());
                JsonNode categoryListJson=categoriaBusqueda.get("path_from_root");
                ArrayList<Category> pathFromRootCategoryList = new ArrayList<>();
                if(categoryListJson!=null){
                    for (int i=0; i<categoryListJson.size(); i++){
                        pathFromRootCategoryList.add(new Category(categoryListJson.get(i).get("id").asText(), categoryListJson.get(i).get("name").asText()));
                    }
                }
            //ahora debo obtener los filtros disponibles

            ObjectMapper objectMapper2 = new ObjectMapper();

            //se guardan los filtros disponibles en un JsonNode llamado filtrosDisponibles
            JsonNode filtrosDisponibles=objectMapper2.readTree(json).get("available_filters");

            //Se inicializa la lista que contendrá los filtros disponibles
            ArrayList<Filter> listaFiltrosDisponibles=new ArrayList<>();
            ObjectMapper objectMapper3 = new ObjectMapper();
            for (int i=0; i< filtrosDisponibles.size(); i++){
                String idFiltroDisponible=filtrosDisponibles.get(i).get("id").asText();
                String nombreFiltroDisponible=filtrosDisponibles.get(i).get("name").asText();
                JsonNode valoresDeFiltro=objectMapper3.readTree(json).get("available_filters").get(i).get("values");
                ArrayList<FilterValue> listaValores = new ArrayList<>(); // Nueva lista de valores para cada filtro
                for (int j=0; j< valoresDeFiltro.size(); j++){
                    listaValores.add(new FilterValue(valoresDeFiltro.get(j).get("id").asText(), valoresDeFiltro.get(j).get("name").asText(), valoresDeFiltro.get(j).get("results").asInt()));
                }
                listaFiltrosDisponibles.add(new Filter(idFiltroDisponible, nombreFiltroDisponible, listaValores));
            }
            return new Search(searchCategory, pathFromRootCategoryList, listaFiltrosDisponibles);
            }else{
                ObjectMapper objectMapper2 = new ObjectMapper();

                //se guardan los filtros disponibles en un JsonNode llamado filtrosDisponibles
                JsonNode filtrosDisponibles=objectMapper2.readTree(json).get("available_filters");

                //Se inicializa la lista que contendrá los filtros disponibles
                ArrayList<Filter> listaFiltrosDisponibles=new ArrayList<>();
                ObjectMapper objectMapper3 = new ObjectMapper();
                for (int i=0; i< filtrosDisponibles.size(); i++){
                    String idFiltroDisponible=filtrosDisponibles.get(i).get("id").asText();
                    String nombreFiltroDisponible=filtrosDisponibles.get(i).get("name").asText();
                    JsonNode valoresDeFiltro=objectMapper3.readTree(json).get("available_filters").get(i).get("values");
                    ArrayList<FilterValue> listaValores = new ArrayList<>(); // Nueva lista de valores para cada filtro
                    for (int j=0; j< valoresDeFiltro.size(); j++){
                        listaValores.add(new FilterValue(valoresDeFiltro.get(j).get("id").asText(), valoresDeFiltro.get(j).get("name").asText(), valoresDeFiltro.get(j).get("results").asInt()));
                    }
                    listaFiltrosDisponibles.add(new Filter(idFiltroDisponible, nombreFiltroDisponible, listaValores));
                }
                return new Search(listaFiltrosDisponibles);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
