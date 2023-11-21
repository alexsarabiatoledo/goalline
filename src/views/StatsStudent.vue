
<template>
  <div class="container">
    <h2 class="text-center mb-3" style="margin-left: 200px; margin-top: 85px;">{{ studentInfo.alumNombre }}</h2>
    <div class="text-center">
      <img
        src="https://cdn-icons-png.flaticon.com/512/4323/4323067.png"
        class="img-fluid profile-image-pic img-thumbnail rounded-circle my-3"
        width="200px"
        alt="profile"
      />
    </div>
    <div class="row justify-content-center"> <!-- Añadimos 'justify-content-center' para centrar el contenido horizontalmente -->
      <div class="col-md-6">
        <table class="table table-striped" style="background-color: #7caa44; width: 80%; margin-left: 180px;">
          <thead>
            <tr>
              <th>Estadísticas</th>
              <th>Valor</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>Partidos jugados</td>
              <td>{{ studentStats.alumPartidosJugados }}</td>
            </tr>
            <tr>
              <td>Goles</td>
              <td>{{ studentStats.alumGoles }}</td>
            </tr>
            <tr>
              <td>Asistencias</td>
              <td>{{ studentStats.alumAsistencias }}</td>
            </tr>
            <tr>
              <td>Tarjetas Amarillas</td>
              <td>{{ studentStats.alumTarjetasAmarillas }}</td>
            </tr>
            <tr>
              <td>Tarjetas Rojas</td>
              <td>{{ studentStats.alumTarjetaRojas }}</td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="col-md-6">
        <table class="table table-striped" style="background-color: #7caa44; width: 80%; margin-left: 180px;">
          <thead>
            <tr>
              <th>Información Personal</th>
              <th>Valor</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>Dorsal</td>
              <td>{{ studentInfo.alumDorsal }}</td>
            </tr>
            <tr>
              <td>Posición</td>
              <td>{{ studentInfo.alumPosicion }}</td>
            </tr>
            <tr>
              <td>Altura</td>
              <td>{{ studentInfo.alumnAltura }}</td>
            </tr>
            <tr>
              <td>Peso</td>
              <td>{{ studentInfo.alumnPeso }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>
  
<script>
 import axios from 'axios';

 export default {
  name: 'StatsStudent',
  data() {
    return {
      studentImage: 'URL de la imagen del perfil del alumno',
      studentStats: {
        alumPartidosJugados: 0,
        alumGoles: 0,
        alumAsistencias: 0,
        alumTarjetasAmarillas: 0,
        alumTarjetaRojas: 0,
      },
      studentInfo: {
        alumNombre:'',
        alumDorsal: 'Dorsal del alumno',
        alumPosicion: 'Posición del alumno',
        alumnAltura: 'Altura del alumno',
        alumnPeso: 'Peso del alumno',
      },
      
    };
  },
  created() {

    // Realiza una solicitud para obtener estadísticas del alumno
    axios.get('http://localhost:8081/alumnos/estadisticas')
      .then(response => {
        // Asigna los datos de respuesta a studentStats
        console.log('Respuesta de la API:', response.data);
        this.studentStats = response.data;
      })
      .catch(error => {
        console.error('Error al obtener estadísticas:', error);
      });

    // Realiza una solicitud para obtener información personal del alumno
    axios.get('http://localhost:8081/alumnos/informacionPersonal')
      .then(response => {
        // Asigna los datos de respuesta a studentInfo
        this.studentInfo = response.data;
      })
      .catch(error => {
        console.error('Error al obtener información personal:', error);
      });
  },
 };
</script>

  
  <style scoped>
  /* Agrega estilos personalizados según sea necesario */
  /* Centra la imagen de perfil en el medio y arriba del eje x */
  .profile-image-pic {
    height: 200px;
    width: 200px;
    object-fit: cover;
    margin-left: 200px;
  }
  
  /* Reduce el tamaño de la letra para el nombre del alumno */
  h2 {
    font-size: 18px;
    

  }
  </style>
  
  
  