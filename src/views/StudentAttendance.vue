<template>
    <div class="container">
        <div class="row">
            <side-nav></side-nav>
            <div class="col-3">
            </div>
            <div class="col-6 ">
                <h1 class="mt-5">
                    Clases-Asistencia
                </h1>
            </div>
            <div class="col-3">
            </div>
        </div>
        <hr class="mx-auto" style="width: 70%;">
        <div class="row">
            <h3 style="margin-left: 21%;">Clase</h3>
            <div class="col-md-6 offset-md-3">
                <div class="card my-3">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Tópico</th>
                                <th scope="col">Fecha</th>
                                <th scope="col">Categoría</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th scope="row">{{ this.$route.params.id }}</th>
                                <td>{{ this.clase.claTopico }}</td>
                                <td>{{ this.clase.claFecha }}</td>
                                <td>{{ this.clase.claCategoria }}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div class="row my-5">
            <h3 style="margin-left: 21%;">Lista</h3>
            <div class="col-md-6 offset-md-3">
                <div class="my-3">
                    <div class="table-container w-50">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th scope="col">Nombre</th>
                                    <th scope="col">Asistencia</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="alumno in alumnos" :key="alumno.alumId">
                                    <th scope="row">
                                        <p>{{ alumno.alumNombre }} {{ alumno.alumApellido }}</p>
                                    </th>
                                    <td><input type="checkbox" v-model="isChecked" @change="saveToLocalStorage">
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-4">
            </div>
            <div class="col-4">
            </div>
            <div class="col-4">
                <button type="button" class="btn btn-primary" v-on:click="changeCheck">
                    <router-link class="nav-link" to="/classes">Guardar y salir</router-link>
                </button>
            </div>
        </div>

    </div>
</template>

<script>
import apiGoallineClases from '../services/api.goalline.clases.js';
import apiGoallineAlumnos from '../services/api.goalline.alumnos.js';
import SideNav from "../components/SideBar.vue"
export default {
    name: 'StudentAttendance',
    data() {
        return {
            clase: {},
            alumnos: {},
            isChecked: false,
        } 
    },
    components: {
        SideNav,
    },
    created() {
        // Al cargar la página, verifica el estado almacenado en localStorage
        const savedState = localStorage.getItem('isChecked');
        if (savedState) {
            this.isChecked = JSON.parse(savedState);
        }
    },

    mounted() {
        this.obtenerClase();
        this.obtenerAlumnos();
    },
    methods: {
        async obtenerClase() {
            const { data } = await apiGoallineClases.getClass(this.$route.params.id);
            this.clase = data;
        },
        async obtenerAlumnos() {
            const { data } = await apiGoallineAlumnos.getAll();
            this.alumnos = data;
        },
        changeCheck() {
            if(!this.isChecked){
                this.isChecked=true    
            }
        },
        saveToLocalStorage() {
            // Almacena el estado en localStorage
            localStorage.setItem('isChecked', JSON.stringify(this.isChecked));
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