<template>
    <div class="container">
        <div class="text-center mt-5">
            <!-- Imagen del alumno -->
            <img src="https://cdn-icons-png.flaticon.com/512/4323/4323067.png"
                class="img-fluid profile-image-pic img-thumbnail rounded-circle" width="200px" alt="profile" />
            <!-- Nombre del alumno -->
            <h2 class="mt-3">{{ studentStats.alumNombre }}</h2>
        </div>

        <!-- Tabla de estadísticas -->
        <table class="table table-striped mt-3 " style="width: 25%; background-color: #E2F4CB;">
            <thead>
                <th style="font-size: 18px;">Estadísticas</th>
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

        <!-- Información del próximo partido -->
        <div class="text-right mt-3" style="position: absolute; top: 80px; right: 200px;">
            <h4>Próximo partido</h4>
            <p style="background-color: #E2F4CB;">31/10/2023</p>
        </div>
        <div class="text-right mt-3" style="position: absolute; top: 180px; right: 280px;">
            <h4>Lugar</h4>
            <p style="background-color: #E2F4CB;">Estadio UFRO</p>
        </div>
    </div>
</template>
  
<script>
import axios from 'axios';

export default {
    name: 'SeeStudent',
    data() {
        return {
            studentStats: {
                alumNombre: 'hola',
                alumPartidosJugados: 0,
                alumGoles: 0,
                alumAsistencias: 0,
                alumTarjetasAmarillas: 0,
                alumTarjetaRojas: 0,
            },
            
        };
    },
    created() {

        const studentId = this.$route.params.id; 
        
        axios.get(`http://localhost:8081/apoderados/detalle_personal/${studentId}`)
            .then(response => {
                this.studentStats = response.data;
            })
            .catch(error => {
                console.error('Error al obtener estadísticas:', error);
            });
   
    },
};
</script>
  
<style scoped>
/* Agrega estilos personalizados según sea necesario */
/* Centra la imagen de perfil en el medio y arriba del eje x */
.profile-image-pic {
    height: 150px;
    width: 150px;
    object-fit: cover;
    margin: 0 auto;
}

/* Estilo para la tabla */
table {
    width: 60%;
    margin: 0 auto;
}

/* Reduce el tamaño de la letra para el nombre del alumno */
h2 {
    font-size: 18px;
    text-align: center;
}
</style>
  