<template>
    <!--INICIO CONTENIDO PRINCIPAL-->
    <div class="row p-4">
        <div class="col-6 "> <!--imagen-->
            <img :src="product.linkToPhoto" alt width="400" height="300">
        </div>
        <div class="col-6"> <!--info principal-->
            <div class="row py-3">
                <h3 class="medium-text"><strong>Marca:</strong> {{ product.brand }}</h3>
                <h3 class="medium-text"><strong>Modelo:</strong>
                    {{ product.model }}</h3>
                <h3 class="medium-text"><strong>Disponibilidad:</strong>
                    {{ product.quantityAvailable }}</h3>
            </div>
            <div class="row">
                <h2>{{ product.name }}</h2>
            </div>
            <div class="row mb-4">

            </div>
            <div class="row">
                <div class="col-5">
                    <h1>${{ product.price }}</h1>
                </div>
                <div class="col-3 py-2">
                    <button type="button" class="btn btn-primary" style="background-color: #FB613F;">Ver
                        en tienda</button>
                </div>
                <div class="col-2 py-2">
                    <button @click="addToWishList" class="btn btn-light border px-2 pt-2 icon-hover">
                        <i :class="heartClass"></i>
                    </button>
                </div>
            </div>
        </div>
    </div>
    <div class="row px-4">
        <div class="col-6"> <!--especificaciones-->
            <div class="row">
                <h1>Especificaciones</h1>
            </div>
            <div class="row">
                <table class="table table-striped">
                    <caption>Esta tabla muestra las características del producto</caption>
                    <th>
                        <td></td>
                    </th>
                    <th>
                        <td></td>
                    </th>
                    <tbody>
                        <tr v-for="attribute in product.attributes" :key="attribute.name">
                            <td>{{ attribute.name }}</td>
                            <td>{{ attribute.value }}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="col">

            </div>
        </div>
        <!--FIN CONTENIDO PRINCIPAL-->
    </div>
</template>

<script>
import apiIamarketProducto from '../services/api.iamarket.productos';
import axios from 'axios';
export default {
    name: 'ProductDetail',
    data() {
        return {
            product: {},
            isAddedToWishList: false
        }
    },
    mounted() {
        this.getProduct();
    },
    computed: {
        heartClass() {
            return {
                'bi': true,
                'bi-heart-fill': true,
                'bi-lg': true,
                'text-secondary': !this.isAddedToWishList,
                'text-warning': this.isAddedToWishList,
            };
        },
    },
    methods: {
        async getProduct() {
            const { data } = await apiIamarketProducto.getItemById(this.$route.params.id);
            this.product = data;
        },
        addToWishList() {
            axios.post('http://localhost:8082/wishlist/add', this.product)
                .then(response => {
                    if (response.status === 200) {
                        this.isAddedToWishList = true; // Cambia el estado para mostrar que el producto se ha añadido a la lista de deseos
                        console.log(response.data); // Mensaje de éxito desde la API
                    } else {
                        console.error('Hubo un problema al agregar el producto a la lista de deseos');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                });
        },
    }
}
</script>

<style scoped>
@import "@/assets/styles.css";

.icon-hover:hover {
    border-color: #E73C17 !important;
    background-color: white !important;
}

.icon-hover:hover i {
    color: #E73C17 !important;
}

.text-warning {
    color: #E73C17 !important;
}
</style>