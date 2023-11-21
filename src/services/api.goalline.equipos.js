import axios from 'axios';

// URL base de la API
const baseURL = `http://localhost:8081/equipo`;

class ApiGoallineService {
    constructor() {
        this.resource = axios.create({
            baseURL,
        });
    }

    getTeamById(teamId) {
        return this.resource.get(`/${teamId}`);
    }

    getAllTeams() {
        return this.resource.get(`/all`);
    }
}

export default new ApiGoallineService();