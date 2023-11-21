<template>
    <div class="container">
    <div class="row">
      <div class="col-md-6 offset-md-3">
        <div class="card my-5">

          <form @submit="handleSignUp" class="card-body cardbody-color p-lg-5">
            <h2 class="text-center text-dark mt-1">Regístrate</h2>
        <div class="text-center mb-5 text-dark">Es fácil y rápido.</div>
            
            <div class="row">
                <div class="col mb-3">
              <input v-model="name" type="text" class="form-control" id="name" aria-describedby="emailHelp"
                placeholder="Ingrese su nombre">
            </div>
             <div class="col mb-3">
              <input v-model="lastName" type="text" class="form-control" id="lastName" aria-describedby="emailHelp"
                placeholder="Ingrese su apellido">
            </div>

            </div>
            <div class="mb-3">
              <input @input="validateEmail" v-model="email" type="text" class="form-control" id="email" aria-describedby="emailHelp"
                placeholder="Ingrese su correo electrónico">
            </div>
            <div class="mb-3 input-group">
              <input @input="validatePassword" v-model="password" type="password" class="form-control" id="password" placeholder="Ingrese su contraseña">
              <button type="button" class="btn btn-light border" @click="togglePasswordVisibility">
                <i v-if="passwordVisible" class="bi bi-eye"></i>
                <i v-else class="bi bi-eye-slash"></i>
              </button>
            </div>
            <div class="mb-3 input-group">
              <input @input="checkPassword" v-model="repeatPassword" type="password" class="form-control" id="repeatPassword" placeholder="Ingrese nuevamente su contraseña">
              <button type="button" class="btn btn-light border" @click="toggleRepeatPasswordVisibility">
                <i v-if="repeatPasswordVisible" class="bi bi-eye"></i>
                <i v-else class="bi bi-eye-slash"></i>
              </button>
            </div>
            <div class="text-center"><button type="submit" class="btn btn-color px-5 mb-5 w-100">Registrarse</button></div>
            <div>
              <div v-if="error" class="alert alert-danger">{{ error }}</div>
              <div v-if="!emailValid" class="alert alert-danger">Formato de correo electrónico inválido.</div>
              <div v-if="!passwordStrong" class="alert alert-danger">Contraseña insegura.</div>
              <div v-if="registerSuccess" class="alert alert-success">{{ registerSuccess }}</div>
            </div>

            <div id="emailHelp" class="form-text text-center mb-1 text-dark">¿Ya posees una cuenta?
              <router-link to="/login" class="text-dark fw-bold"> Iniciar sesión</router-link>
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
    name: 'RegisterView',
    data() {
      return {
        name: '',
        lastName: '',
        email: '',
        password: '',
        repeatPassword: '',
        error: '',
        registerSuccess: '',
        passwordsMatch: true,
        passwordVisible: false,
        repeatPasswordVisible: false,
        emailValid: true,
        passwordStrong: true,
        
      }
    },
    methods: {
      validatePassword() {
        const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
        this.passwordStrong = passwordRegex.test(this.password);
      },
      validateEmail() {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        this.emailValid = emailRegex.test(this.email);
      },
      togglePasswordVisibility() {
        this.passwordVisible = !this.passwordVisible;
        const passwordInput = document.getElementById('password');

        if (this.passwordVisible) {
          passwordInput.type = 'text';
        } else {
          passwordInput.type = 'password';
        }
      },
      toggleRepeatPasswordVisibility() {
        this.repeatPasswordVisible = !this.repeatPasswordVisible;
        const passwordInput = document.getElementById('repeatPassword');

        if (this.repeatPasswordVisible) {
          passwordInput.type = 'text';
        } else {
          passwordInput.type = 'password';
        }
      },
      checkPassword(){
        this.passwordsMatch = this.password === this.repeatPassword;
      },
      async handleSignUp(event){
        event.preventDefault();

        if (!this.name || !this.lastName || !this.email || !this.password || !this.repeatPassword) {
          this.error = 'Existen campos vacíos en el formulario. Por favor llenar todos los campos.';
          setTimeout(() => {
          this.error = '';
        }, 3000);
        return;
        
      }else if(!this.emailValid || !this.passwordStrong){
          this.error = 'Error en el registro. Verifica los campos ingresados.';
          setTimeout(() => {
          this.error = '';
        }, 3000);

        return;
        }else if(!this.passwordsMatch){
          this.error = 'Las contraseñas no coinciden.';
          setTimeout(() => {
          this.error = '';
        }, 3000);

        return;
        }

        await axios.post("http://localhost:8081/auth/signup", {
          email: this.email,
          password: this.password,
          name: this.name,
          lastName: this.lastName
        })
        .then((resp => {
          console.log('Registro exitoso');
          console.log(resp);
          this.registerSuccess = 'Cuenta creada con éxito.';
          setTimeout(() => {
            this.registerSuccess = '';
            this.$router.push('/login');
          }, 2000);
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