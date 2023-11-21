<template>
  <div>
    <h2 style="margin-left: 260px; margin-top: 60px;">Alumnos Matriculados</h2>
    <table style="margin-left: 360px; margin-top: 180px;">
      <thead>
        <tr>
          <th>Nombre</th>
          <th>Apellido</th>
          <th>RUT</th>
          <th>Opciones</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(student, index) in students" :key="index">
          <td>{{ student.alumNombre }}</td>
          <td>{{ student.alumApellido }}</td>
          <td>{{ student.alumRut }}</td>
          <td>
            <a @click="verPupilo(student.alumId)" class="ver-pupilo-link">Ver Pupilo</a>
            
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      students: []
    };
  },
  created() {
    this.fetchStudents(); // Llamar a la función para obtener alumnos cuando el componente se crea
  },
  methods: {
    fetchStudents() {
      axios.get('http://localhost:8081/apoderados/pupilos') // Reemplaza la URL con tu endpoint real
        .then(response => {
          this.students = response.data; // Asignar los alumnos obtenidos a la variable 'students'
        })
        .catch(error => {
          console.error('Error al obtener alumnos:', error);
        });
    },
    verPupilo(alumId) {
      //this.$store.commit('setStudentId', alumId);

      this.$router.push({ path: `/see_student/${alumId}` });
    

    },
  },
};
</script>

<style>
table {
  width: 60%;
  margin: 0 auto;
  border-collapse: collapse;
}

th, td {
  border: 1px solid #020202;
  padding: 8px;
  text-align: center;
}

th {
  background-color: #E2F4CB;
}

.ver-pupilo-link {
  cursor: pointer; /* Cambia el cursor a una mano */
}
</style>
