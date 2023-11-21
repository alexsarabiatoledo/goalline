<template>
    <div class="container">
        <div class="row">
            <div class="col-4">
            </div>
            <div class="col-4 mt-2">
                <h1>
                    Nueva clase
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
                <label for="topico" class="mb-2">Tópico</label>
                <input type="text" class="form-control" placeholder="Ingrese tópico de la clase" aria-label="topico"
                    id="topico" v-model="this.topico" style="width: 250px;" required>
                <p class="mt-2">Categoría</p>
                <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown"
                        aria-expanded="false" style="width: 250px">
                        {{ this.selectedCategoria || 'Elija categoría' }}
                    </button>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" @click="selectCategoria('Junior')">Junior</a></li>
                        <li><a class="dropdown-item" @click="selectCategoria('Semi Junior')">Semi Junior</a></li>
                        <li><a class="dropdown-item" @click="selectCategoria('Medio')">Medio</a></li>
                    </ul>
                </div>
            </div>
            <p>Fecha</p>
            <div class="col">
                <input type="date" id="datePickerInput" class="form-control" style="width: 250px;"
                    placeholder="Indique fecha" v-model="this.fecha">
            </div>
            <div class="row">
                <div class="col-4 mt-3">
                </div>
                <div class="col-4">
                </div>
                <div class="col-4">
                    <button type="submit" class="btn btn-primary" @click="this.postForm()" style="width: 40;"><router-link
                            class="nav-link" to="/classes">Crear y volver</router-link></button>
                </div>
            </div>
        </div>
    </div>
</template>
<script>

import "bootstrap-datepicker"; // Importa Bootstrap Datepicker
import "bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css"; // Importa el archivo de estilos
import $ from 'jquery';
import axios from 'axios';
export default {
    name: 'NewClass',
    data() {
        return {
            topico: '',
            selectedCategoria: '',
            fecha: '',

        };
    },
    mounted() {
        // Inicializa el selector de fechas en el componente montado
        $("#datePickerInput").datepicker({
            format: "dd/mm/yyyy", // Establece el formato de fecha deseado
        });
    },
    methods: {
        postForm() {
            const baseURL = `http://localhost:8081/clase`;
            var resource = axios.create({
                baseURL,
            });
            var optionAxios = {
                headers: {
                    "Content-Type": "application/json",
                },
            };
            resource.post("/add", {
                "claTopico": this.topico,
                "claFecha": this.fecha,
                "claCategoria": this.selectedCategoria,
                "proId": 1
            }, optionAxios).then((res) => { console.log(res) }).catch(function (error) {
                console.log(error);
            });
        },
        selectCategoria(categoria) {
            this.selectedCategoria = categoria;
        }
    }
}

$(document).ready(function () {
    $(".dropdown-item").on("click", function () {
        var selectedOption = $(this).text();
        $("#categoriaDropdown").text(selectedOption);
    });
});
</script>

<style></style>