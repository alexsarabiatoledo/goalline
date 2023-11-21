<template>
    <div class="container">
        <div class="ms-5 mb-5">
            <div class="row">
                <div class="col-4">
                </div>
                <div class="col-4 my-4 ms-5 w-100">
                    <h1>
                        Torneos - Crear torneo - Seleccionar equipo (1/3)
                    </h1>
                </div>
                <div class="col-4">
                </div>
            </div>
            <div class="mb-3">
                <label for="nombre" class="form-label">Nombre del torneo:</label>
                <input type="nombre" v-model="this.nombreTorneo" class="form-control w-25" id="nombre"
                    placeholder="Ingrese nombre del torneo">
            </div>
            <div class="mb-3">
                <p>Modalidad del torneo:</p>
                <div class="form-check" v-for="(cantidad, fase) in fases" :key="fase">
                    <input v-if="nombreTorneo == ''" class="form-check-input" :value="fase" type="radio"
                        name="flexRadioDefault" :id="fase" v-model="seleccionFase" @click="definirEquiposASeleccionar(fase)"
                        disabled>
                    <input v-else class="form-check-input" :value="fase" type="radio" name="flexRadioDefault" :id="fase"
                        v-model="seleccionFase" @click="definirEquiposASeleccionar(fase)">
                    <label class="form-check-label" :for="fase">
                        {{ fase }}
                    </label>
                </div>
            </div>
            <div class="mb-4">
                <h3>Equipos restantes a elegir: {{ this.restantes }}</h3>
                <p v-if="equiposInsuficientes" style="color: red;">
                    Atención: no hay equipos suficientes para jugar el campeonato
                </p>
            </div>
            <div class="row">
                <div class="d-flex justify-content-between">
                    <h4 class="mb-4">Equipos disponibles: {{ equipos.length }} </h4>
                    <h4 style="margin-right: 27%;">Equipos seleccionados: {{ equiposSeleccionados.length }} </h4>
                </div>
                <div class="col-5 table-container">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Equipo</th>
                                <th>Disponibilidad</th>
                            </tr>
                        </thead>
                        <tbody v-if="seleccionFase == ''">
                            <tr v-for="(equipo) in this.equipos" :key="equipo.equId">
                                <th>
                                    {{ equipo.equNombre }}
                                </th>
                                <th>
                                    {{ equipo.jugable }}
                                </th>
                            </tr>
                        </tbody>
                        <tbody v-else>
                            <tr v-for="(equipo, index) in this.equipos" :key="equipo.equId">
                                <th>
                                    <a href="#" @click.prevent="elegirEquipo(index, equipo.equId)">
                                        {{ equipo.equNombre }}
                                    </a>
                                </th>
                                <th>
                                    {{ equipo.jugable }}
                                </th>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="col-1">
                </div>
                <div class="col-5 table-container">
                    <table class="table table-striped">
                        <thead>
                            <h3 class="mb-4"></h3>
                            <tr>
                                <th>Equipo</th>
                                <th>Disponibilidad</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="(equipo, index) in this.equiposSeleccionados" :key="equipo.equId">
                                <th>
                                    <a @click.prevent="sacarEquipo(index)" href="#">
                                        {{ equipo.equNombre }}
                                    </a>
                                </th>
                                <th>
                                    {{ equipo.jugable }}
                                </th>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="col-1 contenedor-flex">
                    <div v-if="this.restantes != 0 || this.equiposSeleccionados == 0">
                        <button type="submit" class="btn btn-primary" style="width: 130px;" disabled>Siguiente</button>
                    </div>
                    <div v-else>
                        <router-link
                            :to="{ path: '/newTournamentGenerateMatches', query: { ids: idsEquiposSeleccionados, nombre: nombreTorneo, fase: seleccionFase } }">
                            <button type="submit" class="btn btn-primary" style="width: 130px;">Siguiente (1/3)</button>
                        </router-link>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
//import axios from 'axios';
import apiGoallineEquipos from '../services/api.goalline.equipos.js';

export default {
    name: 'NewTournamentSelectTeams',
    data() {
        return {
            equipos: [],
            equiposSeleccionados: [],
            idsEquiposSeleccionados: [],
            nombreTorneo: '',
            seleccionFase: '',
            fases: {
                Cuartos: 8,
                Octavos: 16,
                Dieciseisavos: 32
            },
            restantes: 0,

        }
    },
    mounted() {
        this.obtenerEquipos();
        //this.definirEquiposASeleccionar();
    },
    computed: {
        equiposInsuficientes() {
            const faseSeleccionada = this.seleccionFase;
            const cantidadEquipos = this.equipos.length + this.equiposSeleccionados.length;
            return cantidadEquipos < this.fases[faseSeleccionada];
        }
    },
    methods: {
        definirEquiposASeleccionar(fase) {
            this.restantes = this.fases[fase];
        },
        elegirEquipo(index, equId) {
            const equipoSeleccionado = this.equipos.splice(index, 1)[0];
            this.equiposSeleccionados.push(equipoSeleccionado);
            this.restantes = this.restantes - 1
            this.idsEquiposSeleccionados.push(equId)
        },
        sacarEquipo(index) {
            const equipoASacar = this.equiposSeleccionados.splice(index, 1)[0];
            this.equipos.push(equipoASacar);
            const itemIndex = this.idsEquiposSeleccionados.indexOf(equipoASacar.equId);
            if (itemIndex !== -1) {
                this.idsEquiposSeleccionados.splice(index, 1);
            }
            this.restantes = this.restantes + 1
        },
        async obtenerEquipos() {
            const { data } = await apiGoallineEquipos.getAllTeams();
            this.equipos = data;
        },
    },
}
</script>

<style>
.table-container {
    max-height: 400px;
    overflow-y: auto;
}

.contenedor-flex {
    display: flex;
    flex-direction: column;
    justify-content: flex-end;
    height: 400px; /* Establece la altura deseada del contenedor */
}
</style>