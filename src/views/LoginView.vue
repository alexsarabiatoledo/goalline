<template>
    <div class="container">
    <div class="row">
      <div class="col-md-6 offset-md-3">
        <div class="card my-5">

          <form @submit="handleLogin" class="card-body cardbody-color p-lg-5">
            <h2 class="text-center text-dark mt-1">Inicia sesión</h2>
        <div class="text-center mb-5 text-dark">Para acceder a tu cuenta de Goalline Manager.</div>
            <div class="mb-3">
              <input v-model="email" type="text" class="form-control" id="email" aria-describedby="emailHelp"
                placeholder="Ingrese su correo electrónico">
            </div>
            <div class="mb-3">
              <input v-model="password" type="password" class="form-control" id="password" placeholder="Ingrese su contraseña">
            </div>
            <div class="text-center"><button type="submit" class="btn btn-color px-5 mb-5 w-100">Iniciar sesión</button></div>
            <div>
              <div v-if="error" class="alert alert-danger">{{ error }}</div>
            </div>
            <div id="emailHelp" class="form-text text-center mb-1 text-dark">¿Olvidaste tu contraseña?
              <router-link to="/register" href="#" class="text-dark fw-bold">Contacta a un administrador</router-link>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import jwt_decode from 'jwt-decode';

function decodeToken(token) {
  const decoded = jwt_decode(token);
  return decoded;
}

export default {
    name: 'LoginView',
    data() {
      return {
        email: '',
        password: '',
        error: ''
      }
    },
    methods: {
      async handleLogin(event){
        event.preventDefault();

        if (!this.email || !this.password) {
          this.error = 'Existen campos vacíos en el formulario. Por favor llenar todos los campos.';
          setTimeout(() => {
            this.error = '';
          }, 3000);

        return;
        }
        
        await axios.post("http://localhost:8081/auth/signin", {
          email: this.email,
          password: this.password
        })
        .then((resp => {
          const token = resp.data.token;
          sessionStorage.setItem('token', token);
          const decodedToken = decodeToken(token);
          const rol = decodedToken.rol;

          console.log("Rol: " + rol);

          axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
          this.$store.dispatch('updateUserRole', rol);
          this.$store.dispatch('updateLoginStatus', true);
          this.$router.push('/home');
        }))
        .catch(error => {
          this.error = 'Error en el inicio de sesión. Verifica tu correo electrónico y contraseña.';
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
  background-color: #E2F4CB;
}

a{
  text-decoration: none;
}
</style>