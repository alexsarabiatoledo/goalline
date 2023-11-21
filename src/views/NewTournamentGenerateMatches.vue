<template>
  <div class="container">
    <div class="ms-5 mb-5">
      <div class="row">
        <div class="col-4">
        </div>
        <div class="col-4 my-4 ms-5 w-100">
          <h1>
            Torneos - Crear torneo - Generar partidos (2/3)
          </h1>
        </div>
        <div class="col-4">
        </div>
      </div>
      <div class="row">
        <div class="col-5">
          <h3 class="mt-5 mb-4">Torneo: {{ this.$route.query.nombre }}</h3>
          <h3>Modalidad: {{ this.$route.query.fase }}</h3>
          <button class="btn btn-primary mt-3" @click="this.generarPartidos()">Aleatorio</button>
        </div>
        <div class="col-1">

        </div>

        <div class="col-5 mt-5 tabler-container">
          <ul style="list-style:none">
            <li v-for="(item, index) in emparejamientos" :key="index">
              <div class="card" style="background-color: #E2F4CB; ">
                <p class="enfrentamiento">{{ item.primerEquipo.equNombre }} <span class="vs">V/S</span> {{
                  item.segundoEquipo.equNombre }}</p>
              </div>
              <br>
            </li>
          </ul>

          <router-link
            :to="{ path: '/testeado', query: { nombre: nombreTorneo, modalidad: modalidadTorneo, equi: equipo } }">
            <button class="btn btn-primary mt-3" style="float: right;">Siguiente</button>
          </router-link>
          {{ equipo.equNombre }}
        </div>
      </div>
    </div>
  </div>
 
</template>
<script>
import apiGoallineEquipos from '../services/api.goalline.equipos.js';

export default {
  name: 'NewTournamentGenerateMatches',
  data() {
    return {
      ids: [],
      equipos: [], // Equipos obtenidos de la API según los IDs
      emparejamientos: [], // Emparejamientos de equipos
      nombreTorneo: '',
      modalidadTorneo: '',
      equipo:{}
    }
  },
  mounted() {
    this.ids = this.$route.query.ids || [];
    this.nombreTorneo = this.$route.query.nombre
    this.modalidadTorneo = this.$route.query.fase
    this.obtenerEquiposPorIds();
  },
  methods: {
    async obtenerEquiposPorIds() {
      try {
        const equiposPromesas = this.ids.map(async id => {
          const { data } = await apiGoallineEquipos.getTeamById(id);
          return data;
        });
        
        this.equipos = await Promise.all(equiposPromesas);
        this.equipo=this.equipos[0]
        this.generarPartidos();
      } catch (error) {
        console.error('Error al obtener equipos:', error);
      }
    },
    generarPartidos() {
      // Ordenar aleatoriamente los equipos obtenidos
      this.equipos.sort(() => Math.random() - 0.5);
      this.crearEmparejamientos();
    },
    crearEmparejamientos() {
      this.emparejamientos = [];

      // Crear emparejamientos de equipos de dos en dos de la lista ya aleatoria
      for (let i = 0; i < this.equipos.length; i += 2) {
        const par = {
          primerEquipo: this.equipos[i],
          segundoEquipo: this.equipos[i + 1]
        };
        this.emparejamientos.push(par);
      }
    }
  }
}
</script>


<style>
.enfrentamiento {
  font-size: 30px;
  text-align: center;
}

.tabler-container {
  max-height: 600px;
  overflow-y: auto;
}

.enfrentamiento span {
  margin: 0 10px;
  font-weight: bold;
}

.card {
  background-color: #E2F4CB;
  padding: 10px;
  margin-bottom: 10px;
  border-radius: 5px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.vs {
  margin: 0 5px;
  /* Ajusta el espacio entre 'V/S' y los nombres de los equipos */
  font-weight: bold;
  vertical-align: middle;
  /* Alinea verticalmente 'V/S' */
}
</style>