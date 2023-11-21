import axios from 'axios';

// URL base de la API
const baseURL = `http://localhost:8081/torneo/`;

class ApiGoallineService {
    constructor() {
        this.resource = axios.create({
            baseURL,
        });
    }

    getQuantityOfTeams(idTorneo) {
        return this.resource.get(`getQuantityOfTeams/${idTorneo}`);
    }
    
    getTorneos() {
        return this.resource.get(`getAll`);
    }
    getTorneoById(idTorneo) {
        return this.resource.get(`${idTorneo}`);
    }
}

export default new ApiGoallineService();