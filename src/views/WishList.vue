import { Trash } from 'bootstrap-icons/icon/trash-fill.vue';
<template>
    <section>
      <div class="container my-5">
        <header class="mb-4">
          <h3 style="border-bottom: 3px solid #E73C17; display: inline-block; padding-bottom: 2px; border-radius: 3px">Lista de Deseos</h3>
        </header>
        <div class="row">
          <div v-for="(product, index) in wishList" :key="index" class="col-lg-3 col-md-6 col-sm-6 d-flex">
            <div class="card w-100 my-2 shadow-2-strong">
              <a href="#">
                <img alt="producto" :src="product.picture" class="card-img-top mx-auto mt-3" style="aspect-ratio: 1 / 1; width: 50%; height: auto" />
              </a>
              <div class="card-body d-flex flex-column" style="max-height: 300px;">
                <h5 class="card-title">{{ product.title }}</h5>
                <p class="card-text">${{ product.price }} CLP</p>
                <p class="card-text">
                  <small class="text-muted">
                    <i class="bi bi-truck"></i>
                    Envío gratis
                  </small>
                </p>
                <div class="card-footer d-flex align-items-end justify-content-center pt-3 px-0 pb-0 mt-auto">
                  <a :href="product.permalink" target="_blank" class="btn category-change shadow-0 me-1 text-white shop">Ver en tienda</a>
                  <button @click="removeFromWishList(product)" class="btn btn-light border px-2 pt-2 icon-hover">
                    <Trash class="bi bi-trash bi-lg text-secondary px-1"></Trash>
                  </button>
                </div>
              </div>
            </div>
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
        wishList: [],
      };
    },
    created() {
      // Llamada a la API para obtener los productos de la lista de deseos al cargar el componente
      this.getWishList();
    },
    methods: {
      async getWishList() {
        try {
          // Realizar la solicitud GET para obtener los productos de la lista de deseos
          const response = await axios.get('http://localhost:8082/wishlist/items');
          this.wishList = response.data; // Asignar los productos a la lista wishList
        } catch (error) {
          console.error('Error al obtener la lista de deseos:', error);
        }
      },
      async removeFromWishList(product) {
       try {
      // Hacer una solicitud DELETE a la API para eliminar el producto de la lista de deseos
        await axios.delete('http://localhost:8082/wishlist/remove', { data: product });

      // Actualizar la lista de deseos en la aplicación después de la confirmación de la API
       const index = this.wishList.findIndex(item => item.id === product.id);

       if (index !== -1) {
         this.wishList.splice(index, 1);
       }
     } catch (error) {
      console.error('Error al eliminar el producto de la lista de deseos:', error);
      }
     },
    },
  };
  </script>

<style scoped>
.icon-hover:hover {
  border-color: #E73C17 !important;
  background-color: white !important;
}

.icon-hover:hover i {
  color: #E73C17 !important;
}

.shop{
    background-color: #E73C17;
}

.category-change:hover{
  background-color: rgb(255, 60, 0) !important;
}

</style>