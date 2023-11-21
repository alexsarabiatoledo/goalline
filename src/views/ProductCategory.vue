<template>
  <section class="">
    <div class="container">
      <div class="row">
        <div class="col-lg-3">
        </div>
        <div class="col-lg-9">
          <header class="d-sm-flex align-items-center border-bottom mb-4 pb-3">
          </header>
          <!--Productos-->
          <div>
            <div class="row justify-content-center mb-3" v-for="(producto, index) in productos" :key="index">
              <div class="col-md-12">
                <div class="card shadow-0 border rounded-3">
                  <div class="card-body">
                    <div class="row g-0">
                      <div class="col-xl-3 col-md-4 d-flex justify-content-center">
                        <div class="bg-image hover-zoom ripple rounded ripple-surface me-md-3 mb-3 mb-md-0">
                          <img alt="producto" :src="producto.picture" class="w-100" />
                          <a href="#!">
                            <div class="hover-overlay">
                              <div class="mask" style="background-color: rgba(253, 253, 253, 0.15);"></div>
                            </div>
                          </a>
                        </div>
                      </div>
                      <div class="col-xl-6 col-md-5 col-sm-7">
                        <router-link :to="{ name: 'detail', params: { id: producto.id } }">
                          <h6>{{ producto.title }}</h6>
                        </router-link>
                        <div class="d-flex flex-row">
                          <div class="text-warning mb-1 me-2">
                            <i v-for="star in producto.estrellas" :key="star" class="fa fa-star"></i>
                          </div>
                        </div>
                      </div>
                      <div class="col-xl-3 col-md-3 col-sm-5">
                        <div class="d-flex flex-row align-items-center mb-3">
                          <h5 class="mb-1 me-1">${{ producto.price }} CLP</h5>
                        </div>
                        <div class="d-flex flex-row align-items-center mb-1">
                          <a :href="producto.permalink" target="_blank"
                            class="btn category-change shadow-0 me-1 text-white shop">Ver en tienda</a>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <hr />
        </div>
      </div>
    </div>
  </section>
</template>
  
<script>
import axios from 'axios';

export default {
  data() {
    return {
      productos: [],
    };
  },
  created() {
    this.obtenerProductos(this.$route.params.category);
  },
  watch: {
    '$route.params.category': function (newCategory, oldCategory) {
      if (newCategory !== oldCategory) {
        this.obtenerProductos(newCategory);
      }
    }
  },
  methods: {
    async obtenerProductos(categoria) {
      try {
        const response = await axios.get(`http://localhost:8082/search/category/${categoria}`);
        this.productos = response.data;
      } catch (error) {
        console.error('Error al obtener productos:', error);
        this.productos = [];
      }
    }
  }
};
</script>

  
  
<style scoped>
@import "@/assets/styles.css";

.icon-hover:hover i {
  color: #E73C17 !important;
}

.shop {
  background-color: #E73C17;
}
</style>
  