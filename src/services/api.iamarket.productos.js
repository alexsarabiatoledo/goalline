import axios from 'axios';

// URL base de la API
const baseURL = `http://localhost:8082/`;

class IaMarketService {
    constructor() {
        this.resource = axios.create({
            baseURL,
        });
    }

    getItemById(idItem){
        return this.resource.get(`search/${idItem}`);
    }

    getSearchByQuery(squery) {
        return this.resource.get(`search/query/${squery}`);
    }

    getAvailableFilters(squery){
        return this.resource.get(`filter/obtener/${squery}`);
    }

    getSearchByCategory(category){
        return this.resource.get(`/category/${category}`)
    }
}

export default new IaMarketService();