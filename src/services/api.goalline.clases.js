import axios from 'axios';

// URL base de la API
const baseURL = `http://localhost:8081/clase`;

class ApiGoallineService {
    constructor() {
        this.resource = axios.create({
            baseURL,
        });
    }

    getClass(classId) {
        return this.resource.get(`/${classId}`);
    }

    getClasses() {
        return this.resource.get(`/getAll`);
    }
}

export default new ApiGoallineService();