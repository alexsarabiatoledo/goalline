<template>
  <header>
    <div class="bg text-white py-5 masthead shadow p-3">
      <div class="container py-5">
        <h1>
          Encuentra los mejores productos <br />
          de tecnología en IAMarket
        </h1>
        <p>
          ¿Necesitas ayuda? ¿No sabes mucho sobre tecnología?
        </p>
        <button @click="openChat" type="button" class="btn shadow-0 text-white pt-2 category-change"
          style="background-color: #E73C17; margin-left: 3px">
          <span class="pt-1"><b>¡Pregúntale a nuestro asistente virtual!</b></span>
        </button>
      </div>
    </div>
  </header>
  <section>
    <div class="container my-5">
      <header class="mb-4">
        <h3 style="text-align: left; color: #525252">Telefonía con envío gratis <span
            style="color: #E73C17; font-size: 20px"><i class="bi bi-telephone"></i>
          </span></h3>
      </header>
      <div class="row">
        <div v-for="(product, index) in freeShippingProducts" :key="index" class="col-lg-3 col-md-6 col-sm-6 d-flex">
          <div class="card w-100 my-2 shadow-2-strong">

            <img alt="producto" @click="checkProductDetail(product)" :src="product.picture"
              class="card-img-top mx-auto mt-3" style="aspect-ratio: 1 / 1; width: 50%; height: auto" />

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
                <a :href="product.permalink" target="_blank" class="btn category-change shadow-0 me-1 text-white shop">Ver
                  en tienda</a>
                <a href="#" class="btn btn-light border px-2 pt-2 icon-hover" @click.prevent="addToWishList(product)">
                  <i class="bi bi-heart-fill bi-lg text-secondary px-1"
                    :class="{ 'added-to-wish-list': addedToWishList.includes(product.id) }"></i>
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <section>
    <div class="container my-5">
      <header class="mb-4">
        <h3 style="text-align: left; color: #525252">Descuento en videojuegos <span
            style="color: #E73C17; font-size: 20px"><i class="bi bi-controller"></i>
          </span></h3>
      </header>
      <div class="row">
        <div v-for="(product, index) in consoleDiscountProducts" :key="index" class="col-lg-3 col-md-6 col-sm-6 d-flex">
          <div class="card w-100 my-2 shadow-2-strong">

            <img alt="producto" @click="checkProductDetail(product)" :src="product.picture"
              class="card-img-top mx-auto mt-3" style="aspect-ratio: 1 / 1; width: 50%; height: auto" />

            <div class="card-body d-flex flex-column" style="max-height: 300px;">
              <h5 class="card-title">{{ product.title }}</h5>
              <p class="card-text">${{ product.price }} CLP</p>
              <p class="card-text">
                <small class="text-muted">
                  <p style="text-decoration:line-through;">${{ product.originalPrice }} CLP</p>
                </small>
              </p>
              <div class="card-footer d-flex align-items-end justify-content-center pt-3 px-0 pb-0 mt-auto">
                <a :href="product.permalink" target="_blank" class="btn category-change shadow-0 me-1 text-white shop">Ver
                  en tienda</a>
                <a href="#" class="btn btn-light border px-2 pt-2 icon-hover" @click.prevent="addToWishList(product)">
                  <i class="bi bi-heart-fill bi-lg text-secondary px-1"
                    :class="{ 'added-to-wish-list': addedToWishList.includes(product.id) }"></i>
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <CategoriesCarousel />
</template>

<script>
import axios from 'axios';
import CategoriesCarousel from '@/components/CategoriesCarousel.vue';
export default {
  name: 'HomePage',
  components: {
    CategoriesCarousel
  },
  data() {
    return {
      freeShippingUrl: 'http://localhost:8082/search/free_shipping',
      freeShippingProducts: [],
      consoleDiscountUrl: 'http://localhost:8082/search/console_discount',
      consoleDiscountProducts: [],
      addedToWishList: [], // Nuevo estado para manejar productos agregados a la lista de deseos
    }
  },
  methods: {
    openChat() {
    },
    checkProductDetail(product) {
      this.$router.push({ name: 'detail', params: { id: product.id } });
    },
    async getProducts() {
      try {
        const response = await axios.get(this.freeShippingUrl);
        this.freeShippingProducts = response.data;
        return this.freeShippingProducts;
      } catch (err) {
        console.log(err);
      }
    },
    async getDiscountProducts() {
      try {
        const response = await axios.get(this.consoleDiscountUrl);
        this.consoleDiscountProducts = response.data;
        return this.consoleDiscountProducts;
      } catch (err) {
        console.log(err);
      }
    },
    async addToWishList(product) {
      try {
        // Realizar la solicitud POST a la API para agregar el producto a la lista de deseos
        await axios.post('http://localhost:8082/wishlist/add', product);


        // Actualizar el estado para el producto agregado
        this.addedToWishList.push(product.id);

        console.log('Producto agregado a la lista de deseos con éxito.');



      } catch (error) {
        console.error('Error al agregar el producto a la lista de deseos:', error);

        alert(' El producto ya se encuentra en la lista de deseos.');
      }
    },

  },
  mounted() {
    this.getProducts();
    this.getDiscountProducts();
  }
}
</script>

<style scoped>
.icon-hover:hover {
  border-color: #E73C17 !important;
  background-color: white !important;
}

.icon-hover:hover i {
  color: #E73C17 !important;
}

.shop {
  background-color: #E73C17;
}

.masthead {
  background-image: linear-gradient(to right, rgba(176, 48, 16, 0.7), rgba(0, 0, 0, 0.84)), url(../assets/categorias/masthead.jpg)
}

.overlay-container {
  position: relative;
}

.overlay {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(16, 107, 176, 0.081);
}


.custom-caption {
  height: 38px;
  background-color: rgba(9, 16, 21, 0.7);
  color: #ffffff;
  padding: 10px;
  border-radius: 50px;
  margin-bottom: 20px;
}

.btn-top-left {
  background-color: #E73C17;
  position: absolute;
  width: 150px;
  border-top-right-radius: 20px;
  border-bottom-right-radius: 20px;
  top: 10px;
  left: 2px;
  border: none
}

.category-change:hover {
  background-color: rgb(255, 60, 0) !important;
}

.added-to-wish-list {
  color: #E73C17 !important;
}
</style>