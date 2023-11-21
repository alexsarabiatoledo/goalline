<template>
    <div class="container">
    <div class="row">
      <div class="col-md-6 offset-md-3">
        <div class="card my-5">

          <form @submit="handleLogin" class="card-body cardbody-color p-lg-5">
            <h2 class="text-center text-dark mt-1">Inicia sesión</h2>
        <div class="text-center mb-5 text-dark">Para ver y agregar productos a tu lista de deseados.</div>
            <div class="mb-3">
              <input v-model="email" type="text" class="form-control" id="email" aria-describedby="emailHelp"
                placeholder="Ingrese su correo electrónico">
            </div>
            <div class="mb-3 input-group">
              <input v-model="password" type="password" class="form-control" id="password" placeholder="Ingrese su contraseña">
              <button type="button" class="btn btn-light border" @click="togglePasswordVisibility">
                <i v-if="passwordVisible" class="bi bi-eye"></i>
                <i v-else class="bi bi-eye-slash"></i>
              </button>
            </div>
            <div class="text-center"><button type="submit" class="btn btn-color px-5 mb-5 w-100">Iniciar sesión</button></div>
            <div>
              <div v-if="error" class="alert alert-danger">{{ error }}</div>
              <div v-if="loginSuccess" class="alert alert-success">{{ loginSuccess }}</div>
            </div>
            <div id="emailHelp" class="form-text text-center mb-1 text-dark">¿No tienes una cuenta?
              <router-link to="/register" href="#" class="text-dark fw-bold">Crear una cuenta</router-link>
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
    name: 'LoginView',
    data() {
      return {
        email: '',
        password: '',
        error: '',
        loginSuccess: '',
        passwordVisible: false,
      }
    },
    methods: {
      togglePasswordVisibility() {
        this.passwordVisible = !this.passwordVisible;
        const passwordInput = document.getElementById('password');

        if (this.passwordVisible) {
          passwordInput.type = 'text';
        } else {
          passwordInput.type = 'password';
        }
      },
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
          console.log('Login exitoso');
          sessionStorage.setItem('token', token);
          axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
          this.$store.commit('login');
          this.loginSuccess = 'Inicio de sesión exitoso.';
          setTimeout(() => {
            this.loginSuccess = '';
            this.$router.push('/');
          }, 2000);
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
  background-color: #E73C17;
  color: #fff;
  
}

.cardbody-color{
  background-color: #f4f4f4;
}

a{
  text-decoration: none;
}
</style>