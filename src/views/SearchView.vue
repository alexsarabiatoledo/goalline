<template>
  <div class="container-fluid">
    <!--INICIO CONTENIDO PRINCIPAL-->
    <div class="row">
      <div class="row">
        <br>
        <div style="background-color: #F1F1F1;  display: flex;">
          <div v-for="(category, index) in categoryOfPath" :key="category.categoryId">
            <p class="mt-1" style="margin-left: 430px;"><a href="#"
                @click.prevent="getProductByCategory(index, category.categoryId)">{{
                  category.categoryName }}&nbsp;->&nbsp;</a></p>
          </div>
        </div>
      </div>
      <div class="col-3 filtros px-4 py-3"> <!--Filtros-->
        <div class="row"> <!--Precio-->
          <div class="row px-2">
            <h4>Precio</h4>
          </div>
          <div class="row">
            <div class="col-4">
              <input type="text" style="width: 90px;" placeholder="Mín." v-model="this.precioMinimo">
            </div>
            <div class="col-2">
              <p>-</p>
            </div>
            <div class="col-4">
              <input type="text" style="width: 90px;" placeholder="Max." v-model="this.precioMaximo">
            </div>
            <div class="col-2 pb-2">
              <button @click="getProductsByQuery(this.$route.query.q)" class="btn btn-warning">
                <span class="bi bi-arrow-right"></span>
              </button>
            </div>
            <hr>
          </div>
        </div>
        <table class="table">
          <caption>Esta tabla muestra los filtros según la búsqueda de un usuario</caption>
          <th>
            <td></td>
          </th>
          <tbody>
            <tr v-for="filtro in availableFilters.availableFilters" :key="filtro.filterId">
              <td style="background-color: #F1F1F1; padding: 10px;">
                <p class="h5">{{ filtro.filterName }}</p>
                <ul style="list-style-type: none; padding: 0; margin: 0;">
                  <li v-for="valor in filtro.filterValues" :key="valor.filterValueId">
                    <a href="#" @click.prevent="applyFilterToSearch(filtro.filterId, valor.filterValueId)">
                      {{ valor.filterValueName }} ({{ valor.filterValueQuantityOfResults }})
                    </a>
                  </li>
                </ul>
              </td>
            </tr>
          </tbody>
        </table>

      </div>
      <div class="col-9 "> <!--lista de artículos-->
        <table>
          <caption>Esta tabla despliega los productos obtenidos de mercadolibre</caption>
          <tbody>
            <th><td></td></th>
            <tr v-for="producto in this.productos" :key="producto.id">
              <div class="row">
                <div class="col-4 border ps-3">
                  <img :src="producto.linkToPhoto" alt="foto" width="150" height="100">
                </div>
                <div class="col-8 ps-3">
                  <div class="row ">
                    <RouterLink :to="{ name: 'detail', params: { id: producto.id } }">
                      <a href="#">
                        <p style="font-size: 20px;">{{ producto.name }}</p>
                      </a>
                    </RouterLink>
                  </div>
                  <div class="row">
                    <p style="font-size: 15px;" class="subtexto">{{ producto.state }}</p>
                    <span v-if="producto.freeShipping" style="color: green; font-weight: bold;">Envío gratis</span>
                  </div>
                  <div class="row">
                    <p style="font-size: 30px;">
                      ${{ formatPrice(producto.price) }} CLP
                      <a target="_blank" class="btn category-change shadow-0 me-1 text-white shop">Ver en tienda</a>
                    </p>
                  </div>
                </div>
              </div>
              <hr>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <!--FIN CONTENIDO PRINCIPAL-->
  </div>
</template>
<script>
import axios from 'axios';
import apiIamarketProducto from '../services/api.iamarket.productos';
export default {
  name: 'SearchView',
  props: ['query'],
  data() {
    return {
      productos: [],
      precioMinimo: "",
      precioMaximo: "",
      availableFilters: [],
      filters: "",
      categoryOfPath: []
    }
  },
  created() {
    const searchQuery = this.$route.query.q;
    this.getProductsByQuery(searchQuery);
  },
  methods: {
    formatPrice(price) {
      return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
    },
    async getProductByCategory(index, idCategoria) {
      if (index >= 0 && index < this.categoryOfPath.length) {
        this.categoryOfPath.splice(index + 1);
      } else {
        console.error('Índice fuera de los límites del array.');
      }

      try {
        const response = await axios.get(`http://localhost:8082/search/category/a/${idCategoria}`);
        this.productos = response.data; // Almacena los productos en la variable del componente
      } catch (error) {
        console.error('Error al obtener productos:', error);
        this.productos = []; // Establece la lista de productos como vacía en caso de error
      }
    },
    applyFilterToSearch(idFiltro, idValorFiltro) {
      this.filters = this.filters + "&" + idFiltro + "=" + idValorFiltro
      this.getProductsByQuery(this.$route.query.q + this.filters)
    },

    async getProductsByQuery(query) {
      this.getFilterBySearch(query)
      if (this.precioMinimo != "" && this.precioMaximo != "") {
        query = query + "&price=" + this.precioMinimo + "-" + this.precioMaximo
        const { data } = await apiIamarketProducto.getSearchByQuery(query);
        this.productos = data;
      } else {
        const { data } = await apiIamarketProducto.getSearchByQuery(query);
        this.productos = data;
      }
    },
    async getFilterBySearch(query) {
      const { data } = await apiIamarketProducto.getAvailableFilters(query);
      this.availableFilters = data;
      this.categoryOfPath = this.availableFilters.pathFromRoot
    },


  },
  beforeRouteUpdate(to, from, next) {
    this.getProductsByQuery(to.query.q);
    next();
  }
}

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