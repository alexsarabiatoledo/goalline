<template>
  <div class="container">
  <div class="row">
    <div class="col-md-6 offset-md-3">
      <div class="card my-5">

        <form @submit="registrarApoderado" class="card-body cardbody-color p-lg-5">
          <h2 class="text-center text-dark mt-1">Registrar apoderado</h2>
          <div class="text-center">
            <img src="https://cdn-icons-png.flaticon.com/512/780/780259.png" class="img-fluid profile-image-pic img-thumbnail rounded-circle my-3"
              width="200px" alt="profile">
          </div>
          <div class="row">
              <div class="col mb-3">
            <input v-model="name" type="text" class="form-control" id="name" aria-describedby="emailHelp"
              placeholder="Nombre del apoderado">
          </div>
           <div class="col mb-3">
            <input v-model="lastName" type="text" class="form-control" id="lastName" aria-describedby="emailHelp"
              placeholder="Apellido del apoderado">
          </div>

          </div>
          <div class="mb-3">
            <input v-model="email" type="text" class="form-control" id="email" aria-describedby="emailHelp"
              placeholder="Correo electrónico del apoderado">
          </div>
          <div class="mb-3">
            <input v-model="rut" type="text" class="form-control" id="rut" placeholder="Rut del apoderado">
          </div>
          <div class="input-group">
            <label type="text" class="form-control text-center"  >Alumnos del apoderado</label>
            <router-link to="/select_student" style="background-color: #F8F8F8" class="btn btn-color border">
              <i class="bi bi-plus-circle-fill" style="color: #8CB976"></i>
            </router-link>
          </div>
          <div class="mb-3">
            <div v-if="selectedStudents.length > 0">
              <table class="table table-sm table-bordered table-hover text-center">
                  <tbody>
                    <tr v-for="(alumno, index) in selectedStudents" :key="index">
                      <td class="cell-padding">{{ alumno.alumNombre }}</td>
                      <td class="cell-padding">{{ alumno.alumApellido }}</td>
                      <td class="cell-padding">{{ alumno.usuario.email }}</td>
                      <td class="cell-padding">
                        <button @click="deleteStudent(index)" class="btn" type="button">
                          <i class="bi bi-x-circle-fill" style="color: #c70c0c"></i>
                        </button>
                      </td>
                    </tr>
                  </tbody>
              </table>
            </div>
            <div v-else>
                <label type="text" class="form-control text-center" style="color:grey">
                  <i>No se han asignado alumnos al apoderado</i>
                </label>
              </div>
          </div>
          <button type="submit" class="btn btn-color px-5 mb-2 w-100">Registrar apoderado</button>
          <div>
            <div v-if="error" class="alert alert-danger">{{ error }}</div>
            <div v-if="success" class="alert alert-success">{{ success }}</div>
          </div>
        </form>
      </div>

    </div>
  </div>
</div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'AddParent',
  data() {
    return {
      name: '',
      lastName: '',
      email: '',
      rut: '',
      error: '',
      success: '',
      selectedStudents: this.$store.state.selectedStudents,
    }
  },
  methods: {
    deleteStudent(index){
      this.selectedStudents.splice(index, 1);
      this.$store.commit('updateSelectedStudents', this.selectedStudents);
      return;
    },
    registrarApoderado(event) {
      event.preventDefault();

      if (!this.name || !this.lastName || !this.email || !this.rut) {
        this.error = 'Existen campos vacíos en el formulario. Por favor llenar todos los campos.';
        setTimeout(() => {
          this.error = '';
        }, 3000);
        return;
      }
      axios.post("http://localhost:8081/auth/registrar_apoderado", {
        email: this.email,
        password: this.rut,
        nombre: this.name,
        apellido: this.lastName,
        rut: this.rut
      })
        .then((resp => {
          console.log('Registro exitoso');
          console.log(resp);
          this.success = 'Registro exitoso';
          setTimeout(() => {
            this.success = '';
          }, 3000);
          this.selectedStudents.forEach(alumno => {
            axios.post(`http://localhost:8081/alumnos/asignar_apoderado/${this.email}/${alumno.alumId}`)
              .then((resp => {
                console.log('Asignacion de apoderado exitosa', resp);
              }))
              .catch((err => {
                console.log(err);
              }))
          });
        }))
        .catch(error => {
          this.error = 'Error en el registro. Verifica tu correo electrónico y contraseña.';
          setTimeout(() => {
            this.error = '';
          }, 3000);
          console.log(error);
        });
    }
  }
}
</script>


<style scoped>
.btn-color{
background-color: #7caa44;
color: #fff;

}

.profile-image-pic{
height: 200px;
width: 200px;
object-fit: cover;
}



.cardbody-color{
background-color: #f8f8f8;
}

a{
text-decoration: none;
}

.cell-padding{
    padding-left: 12px
}
</style>