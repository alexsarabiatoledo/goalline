<template>
  <div class="container">
    <div class="row">
      <div class="col-md-6 offset-md-3">
        <div class="card my-5">

          <form @submit="registrarEstudiante" class="card-body cardbody-color p-lg-5">
            <h2 class="text-center text-dark mt-1">Registrar alumno</h2>
            <div class="text-center">
              <img src="https://cdn-icons-png.flaticon.com/512/4323/4323067.png"
                class="img-fluid profile-image-pic img-thumbnail rounded-circle my-3" width="200px" alt="profile">
            </div>
            <div class="row">
              <div class="col mb-3">
                <input v-model="name" type="text" class="form-control" id="name" aria-describedby="emailHelp"
                  placeholder="Nombre del alumno">
              </div>
              <div class="col mb-3">
                <input v-model="lastName" type="text" class="form-control" id="lastName" aria-describedby="emailHelp"
                  placeholder="Apellido del alumno">
              </div>

            </div>
            <div class="mb-3">
              <input v-model="email" type="text" class="form-control" id="email" aria-describedby="emailHelp"
                placeholder="Correo electrónico del alumno">
            </div>
            <div class="mb-3">
              <input v-model="rut" type="text" class="form-control" id="rut" placeholder="Rut del alumno">
            </div>
            <div class="mb-3">
              <select v-model="category" class="form-control form-select" id="category" aria-describedby="emailHelp">
                <option value="" disabled selected>Seleccione una categoría</option>
                <option value="Junior">Junior</option>
                <option value="Semi senior">Semi senior</option>
                <option value="Senior">Senior</option>
              </select>
            </div>
            <div class="text-center"><button type="submit" class="btn btn-color px-5 mb-2 w-100">Registrar alumno</button>
            </div>
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
  name: 'AddStudent',
  data() {
    return {
      name: '',
      lastName: '',
      email: '',
      rut: '',
      category: '',
      error: '',
      success: '',
    }
  },
  methods: {
    async registrarEstudiante(event) {
      event.preventDefault();

      if (!this.name || !this.lastName || !this.email || !this.rut || !this.category) {
        this.error = 'Existen campos vacíos en el formulario. Por favor llenar todos los campos.';
        setTimeout(() => {
          this.error = '';
        }, 3000);
        return;
      }

      await axios.post("http://localhost:8081/auth/registrar_alumno", {
        email: this.email,
        password: this.rut,
        nombre: this.name,
        apellido: this.lastName,
        rut: this.rut,
        categoria: this.category
      })
        .then((resp => {
          console.log('Registro exitoso');
          console.log(resp);
          this.success = 'Registro exitoso';
          setTimeout(() => {
            this.success = '';
          }, 3000);

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
.btn-color {
  background-color: #7caa44;
  color: #fff;

}

.profile-image-pic {
  height: 200px;
  width: 200px;
  object-fit: cover;
}



.cardbody-color {
  background-color: #f8f8f8;
}

a {
  text-decoration: none;
}
</style>