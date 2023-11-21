<template>
  <nav class="navbar navbar-expand-lg navbar-light" style="background-color: #E73C17">
    <div class="container">
      <div class="row">
        <div class="col-1"> <!--Logo-->
          <a class="navbar-brand" href="#">
            <img src="@/assets/iamarket-logo.png" alt="Logo" width="50" class="d-inline-block align-top"
              style="border-radius: 100%">
          </a>
        </div>
        <div class="col-2 pt-2 text-center ms-2" style="margin-left: 80px"> <!--Combobox categoría-->
          <div class="nav-item dropdown">
            <div class="dropdown" style="position: static;">
              <button class="btn btn-secondary text-white dropdown rounded" type="button" data-bs-toggle="dropdown"
                aria-expanded="false" style="width: 190px; height: 38px; background-color: #E73C17; border: white">
                <i class="bi bi-list h4 align-middle"></i>
                <strong class="align-middle">Categorías</strong>
              </button>
              <ul class="dropdown-menu dropdown-menu-start text-center" style="position: absolute; top: 100%; left: 0; background-color: white;">
                <li>
                  <router-link :to="{ name: 'productCategory', params: { category: 'MLC1648' } }" class="dropdown-item">Computación</router-link>
                </li>
                <li>
                  <router-link :to="{ name: 'productCategory', params: { category: 'MLC1000' } }" class="dropdown-item">Electrónica, Audio y Video</router-link>
                </li>
                <li>
                  <router-link :to="{ name: 'productCategory', params: { category: 'MLC1051' } }" class="dropdown-item">Celulares y Teléfonos</router-link>
                </li>
                <li>
                  <router-link :to="{ name: 'productCategory', params: { category: 'MLC1039' } }" class="dropdown-item">Cámaras  y Accesorios</router-link>
                </li>
                <li>
                  <router-link :to="{ name: 'productCategory', params: { category: 'MLC1144' } }" class="dropdown-item">Consolas y Videojuegos</router-link>
                </li>
              </ul>
            </div>
          </div>
        </div>

        <div class="col-5" style="margin-left: 90px"> <!--Buscador-->
          <form class="mx-auto d-flex pt-2 position-relative" @submit.prevent="buscarProductos">
            <div style="position: relative; width: 700px;">
            <input class="form-control me-2" type="search" placeholder="Buscar productos en IAMarket..." aria-label="Search" style="width: 100%; border-radius: 20px; padding-right: 40px;" v-model="this.searchInput">
            <router-link :to="{ path: '/search', query: { q: searchInput } }">
              <button class="btn" style="position: absolute; right: 0; top: 0; bottom: 0; background-color: #525252; border: none; border-radius: 0 20px 20px 0;" type="submit"><i class="bi bi-search" style="color: white;"></i></button>
            </router-link>
          </div>
          </form>
        </div>
        <div class="col-2 px-4 d-flex align-items-center" style="margin-left: 40px">
          <!-- BOTONES DE USUARIO LOGEADO-->
          <div class="row" v-if="isLoggedIn">
            <div class="col-4">
              <router-link to="/wishlist" class="nav-link category-change rounded active">
                <i class="bi bi-bag-heart" style="font-size: 25px; color: white;"></i>
              </router-link>
            </div>
            <div class="col-4">
              <a class="nav-link category-change rounded active" aria-current="page" href="#" style="color: white">
                <i class="bi bi-person-fill-gear" style="font-size: 25px;"></i>
              </a>
            </div>
            <div class="col-4">
              <router-link to="/login" @click="logout()" class="nav-link category-change rounded active" aria-current="page" href="#" style="color: white">
                <i class="bi bi-box-arrow-right" style="font-size: 25px;"></i>
              </router-link>
            </div>
          </div>

          <!-- BOTONES DE USUARIO VISITANTE-->
          <div class="row" v-else>
            <div class="col-4 mx-auto" style="margin-left: 20px">
              <div class="nav-item dropdown">
                <div class="dropdown">
                  <button class="nav-link dropdown-toggle category-change rounded active" type="button" aria-current="page" href="#" data-bs-toggle="dropdown"
                    aria-expanded="false" style="color: white; font-weight: bold;">
                      <i class="bi bi-person-circle" style="font-size: 16px"></i>
                      <span> Inicia sesión</span>
                    
                  </button>
                  <ul class="dropdown-menu dropdown-menu-start" style="width: 90px; background-color: white">
                    <li><router-link class="dropdown-item color-change" to="/login">Iniciar sesión</router-link></li>
                    <li><router-link class="dropdown-item color-change" to="/register">Registrarse</router-link></li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </nav>
</template>

<script>
import { mapState } from 'vuex';

export default {
  name: 'NavBar',

  data() {
    return {
      searchInput: ''
    }
  },
  methods: {
    logout() {
      this.$store.commit('logout');
      this.$router.push('/login');
    },
    buscarProductos() {
      this.$router.push({ path: '/search', query: { q: this.searchInput } });
    },
  },
  computed: {
    ...mapState(['isLoggedIn']),
  },
}
</script>

<style scoped>
.color-change:hover {
  color: #E73C17;
}

.category-change:hover {
  background-color: rgb(255, 60, 0) !important;
}</style>