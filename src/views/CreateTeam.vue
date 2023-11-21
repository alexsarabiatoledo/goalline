<template>
    <div class="container">
        <div class="row">
            <div class="col-4">
            </div>
            <div class="col-4 mt-2">
                <h1>
                    Crear equipo
                </h1>
            </div>
            <div class="col-4">
            </div>
            <hr class="mx-auto" style="width: 500px;">
        </div>

        <div class="row">
            <div class="col-3 ms-5">
            </div>
            <div class="col-3 ms-5">
                <img src="@/assets/crearclase.png" alt="crearclaseimg" width="140" height="120" hspace="80">
            </div>
            <div class="col-3 ms-5">

            </div>
            <div class="col-3 ms-5">
            </div>
        </div>

        <div class="row mt-4" style="margin-left: 300px;">
            <div class="col-3 mb-3">
                <label for="topico" class="mb-2">Nombre equipo</label>

                <input type="text" class="form-control" placeholder="Ingrese nombre del equipo" aria-label="topico"
                    id="nombre" v-model="this.nombreEquipo" style="width: 250px;" required>

                <p class="mt-2">Categoría</p>
                <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown"
                        aria-expanded="false" style="width: 250px">
                        {{ this.selectedCategoria || 'Elija categoría' }}
                    </button>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" @click="selectCategoria('Junior')">Junior</a></li>
                        <li><a class="dropdown-item" @click="selectCategoria('SemiJunior')">Semi Junior</a></li>
                        <li><a class="dropdown-item" @click="selectCategoria('Medio')">Medio</a></li>
                    </ul>
                </div>
            </div>

            <div class="row">
                <div class="col-4 mt-3">

                </div>
                <div class="col-4">

                </div>
                <div class="col-4">
                    <router-link class="nav-link" to="/myteams">
                        <button type="submit" class="btn btn-primary" style="width: 40;" @click="this.postForm()">Crear y
                            volver</button>
                    </router-link>
                </div>
            </div>
        </div>

    </div>
</template>
<script>
//import $ from 'jquery';
import axios from 'axios';


export default {
    name: 'CreateTeam',
    data() {
        return {
            nombreEquipo: '',
            selectedCategoria: ''
        }
    },
    methods: {
        selectCategoria(categoria) {
            this.selectedCategoria = categoria;
        },
        async postForm() {
            const baseURL = `http://localhost:8081/equipo`;
            var resource = axios.create({
                baseURL,
            });
            var optionAxios = {
                headers: {
                    "Content-Type": "application/json",
                },
            };

            // Utiliza async/await para esperar la respuesta de la solicitud POST
            return resource.post("/add", {
                "equNombre": this.nombreEquipo,
                "equCategoria": this.selectedCategoria,
            }, optionAxios)
                .then((res) => {
                    console.log(res);
                    return res; // Devuelve la respuesta
                })
                .catch(function (error) {
                    console.log(error);
                    throw error; // Lanza el error como una promesa rechazada
                });
        },


    }
}

</script>

<style></style>