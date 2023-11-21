import axios from 'axios';

// URL base de la API
const baseURL = `http://localhost:8081/alumnos`;

class ApiGoallineService {
    constructor() {
        this.resource = axios.create({
            baseURL,
        });
    }

    getAll() {
        return this.resource.get(`/getAll`);
    }

    getAlumno(aluId) {
        return this.resource.get(`/${aluId}`);
    }

}

export default new ApiGoallineService();