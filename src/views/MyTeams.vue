<template>
    <div class="container-fluid" style="margin-left: 250px; max-width: 70%;">
        <div class="row">
            <div class="col-4">
            </div>
            <div class="col-4 mt-3">
                <h1>
                    Mis equipos
                </h1>
            </div>
            <div class="col-4">
            </div>
        </div>
        <hr class="mx-auto" style="width: 500px;">
        <div class="row">
            <div class="col-4 table-container px-2">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Equipo</th>
                            <th>Miembros</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="equipo in equipos" :key="equipo.equId">
                            <th>
                                <a @click="seleccionarCategoria(equipo.equId)">{{ equipo.equNombre }}</a>
                            </th>
                            <th>{{ equipo.equCantidadMiembros }}</th>
                        </tr>
                    </tbody>
                </table>
                <button type="button" class="btn btn-primary" style="margin-left: 50%;"><router-link class="nav-link"
                        to="/newteam">Nuevo equipo</router-link></button>
            </div>
            <div class="col-4 table-container">
                <img src="@/assets/cr7.png" alt="cr7" class="img-fluid h-25 ms-5">
                <h2>{{ this.alumnoSeleccionado.alumNombre }} {{ this.alumnoSeleccionado.alumApellido }}</h2>
                <h4>{{ this.alumnoSeleccionado.alumEdad }}</h4>
                <table class="table table-striped">
                    <tbody>
                        <tr>
                            <th>Torneos jugados</th>
                            <th>{{ this.alumnoSeleccionado.alumPartidosJugados }}</th>
                        </tr>
                        <tr>
                            <th>Goles anotados</th>
                            <th>{{ this.alumnoSeleccionado.alumGoles }}</th>
                        </tr>
                        <tr>
                            <th>Posición favorita</th>
                            <th>{{ this.alumnoSeleccionado.alumPosicion }}</th>
                        </tr>
                        <tr>
                            <th>Pie dominante</th>
                            <th>IZQ</th>
                        </tr>
                        <tr>
                            <th>Apoderado</th>
                            <th><a href="">Benita Brione</a></th>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="col-4">
                <div class="col-4 ">
                    <table class="table table-striped" style="width: max-content;">
                        <thead>
                            <tr>
                                <th>Nombre</th>
                                <th>Edad</th>
                                <th>Acción</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="alumno in filtrarAlumnosPorCategoria(alumnos)" :key="alumno.alumId">
                                <td>
                                    <a @click="obtenerAlumnoPorId(alumno.alumId)">
                                        {{ alumno.alumNombre }} {{ alumno.alumApellido }}
                                    </a>
                                </td>
                                <td>
                                    {{ alumno.alumEdad }}
                                </td>
                                <td>
                                    <a name="add" id="add" class="btn" role="button"
                                        :class="alumno.alumEquipo === equipoSeleccionado.equId ? 'btn-warning' : 'btn-success'"
                                        @click="alumno.alumEquipo === equipoSeleccionado.equId ? sacarJugador(alumno.alumId, this.equipoSeleccionado.equId) : agregarAlumnoAEquipo(this.equipoSeleccionado.equId, alumno.alumId)">
                                        {{ alumno.alumEquipo === equipoSeleccionado.equId ? 'E' : 'A' }}
                                    </a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </div>
</template>
<script>
import apiGoallineEquipos from '../services/api.goalline.equipos.js';
import apiGoallineAlumnos from '../services/api.goalline.alumnos.js';
import axios from 'axios';
export default {
    name: 'MyTeams',
    data() {
        return {
            categorias: [
                { id: 1, nombre: "Junior" },
                { id: 2, nombre: "SemiJunior" },
                { id: 3, nombre: "Medio" },
            ],
            categoriaSeleccionada: "Categoría",
            alumnos: [],
            alumnoSeleccionado: {},
            equipos: [],
            equipoSeleccionado: {},
            idEquipo: 0,
            idAlumno: 0,
        }
    },
    mounted() {
        this.obtenerEquipos();
        this.obtenerAlumnos();
    },
    methods: {
        async sacarJugador(aluId, teamId) {
            this.sacarJugadorDeEquipo(aluId);
            this.quitarMiembroAEquipo(teamId);
        },
        async agregarAlumnoAEquipo(teamId, aluId) {
            this.agregarMiembroAEquipo(teamId)
            const baseURL = `http://localhost:8081/alumnos`;
            const resource = axios.create({
                baseURL,
            });
            const optionAxios = {
                headers: {
                    "Content-Type": "application/json",
                },
            };

            const url = `/asignarEquipo/${teamId}/${aluId}`;

            resource.post(url, null, optionAxios)
                .then((res) => {
                    console.log(res);
                })
                .catch(function (error) {
                    console.log(error);
                });
        },



        sacarJugadorDeEquipo(aluId) {
            const baseURL = `http://localhost:8081/alumnos`;
            const resource = axios.create({
                baseURL,
            });
            const optionAxios = {
                headers: {
                    "Content-Type": "application/json",
                },
            };

            const url = `/sacarEquipo/${aluId}`;

            resource.post(url, null, optionAxios)
                .then((res) => {
                    console.log(res);
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        quitarMiembroAEquipo(teamId) {
            const baseURL = `http://localhost:8081/equipo`;
            const resource = axios.create({
                baseURL,
            });
            const optionAxios = {
                headers: {
                    "Content-Type": "application/json",
                },
            };
            const url = `/restar/${teamId}`;
            resource.post(url, null, optionAxios)
                .then((res) => {
                    console.log(res);
                })
                .catch(function (error) {
                    console.log(error);
                });
        },



        agregarMiembroAEquipo(teamId) {
            const baseURL = `http://localhost:8081/equipo`;
            const resource = axios.create({
                baseURL,
            });
            const optionAxios = {
                headers: {
                    "Content-Type": "application/json",
                },
            };
            const url = `/sumar/${teamId}`;
            resource.post(url, null, optionAxios)
                .then((res) => {
                    console.log(res);
                })
                .catch(function (error) {
                    console.log(error);
                });
        },

        seleccionarCategoria(idEquipo) {
            this.obtenerEquipoPorId(idEquipo);
            this.categoriaSeleccionada = this.equipoSeleccionado.equCategoria;
        },
        filtrarAlumnosPorCategoria(alumnos) {
            return alumnos.filter(alumno => alumno.alumCategoria === this.categoriaSeleccionada);
        },
        async obtenerAlumnos() {
            const { data } = await apiGoallineAlumnos.getAll();
            this.alumnos = data;
        },
        async obtenerAlumnoPorId(idAlumno) {
            const { data } = await apiGoallineAlumnos.getAlumno(idAlumno);
            this.alumnoSeleccionado = data;
        },
        async obtenerEquipos() {
            const { data } = await apiGoallineEquipos.getAllTeams();
            this.equipos = data;
        },
        async obtenerEquipoPorId(idEquipo) {
            const { data } = await apiGoallineEquipos.getTeamById(idEquipo);
            this.equipoSeleccionado = data;
        }
    },
    beforeRouteEnter(to, from, next) {
    next(vm => {
        if (from.path === '/newteam') { // Comprueba si viene de la pantalla "CreateTeam"
            vm.obtenerEquipos();
            vm.obtenerAlumnos();
        }
    });
},
}

</script>
<style>
.table-container {
    max-height: 400px;
    overflow-y: auto;
}
</style>