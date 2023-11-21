<template>
    <div class="container">
        <div class="row">
            <div class="col-4">
            </div>
            <div class="col-4 mt-5">
                <h1>
                    Clases - Registro
                </h1>
            </div>
            <div class="col-4">
            </div>
        </div>
        <hr class="mx-auto" style="width: 500px;">
        <div class="row">
            <div class="col-md-6 offset-md-3">
                <div class="card my-3 table-container">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Tópico</th>
                                <th scope="col">Fecha</th>
                                <th scope="col">Categoría</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="clase in clases" :key="clase.claId">
                                <td>
                                    <span class="fw-normal">{{ clase.claId }}</span>
                                </td>
                                <td>
                                    <span class="fw-normal">{{ clase.claTopico }}</span>
                                </td>
                                <td><span class="fw-normal">{{ clase.claFecha || 'N/A' }}</span></td>
                                <td><span class="fw-normal">{{ clase.claCategoria || 'N/A' }}</span></td>
                                <td><span class="fw-normal"><a href=""><router-link class="nav-link"
                                                :to="{ name: 'attendance', params: { id: clase.claId } }">Ver
                                                asistencia</router-link></a></span></td>
                            </tr>
                        </tbody>

                    </table>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-4">
            </div>
            <div class="col-4">
            </div>
            <div class="col-4">
                <button type="button" class="btn btn-primary"><router-link class="nav-link" to="/NewClass">Crear
                        clase</router-link></button>
            </div>
        </div>
    </div>
</template>
<script>

import apiGoalline from '../services/api.goalline.clases.js';

export default {
    name: 'ClassRegistration',
    data() {
        return {
            clases: [],
            productos:[]
        }
    },
    mounted() {
        this.getClasses();




    },
    components: {

    },
    methods: {
        async getClasses() {
            const { data } = await apiGoalline.getClasses();
            this.clases = data;
        },
    },
}
</script>
<style>
.table-container {
    max-height: 350px;
    overflow-y: auto;
}
</style>