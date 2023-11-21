<template>
    <div class="container">
        <div class="row">
            <div class="card cardbody-color my-5 col-8" style="margin: auto">
                <h2 class="text-center text-dark mt-5">Asignar estudiantes</h2>
                <div class="d-flex justify-content-center">
                    <!--
                    <div class="d-flex justify-content-start" style="margin-top: 50px">
                        <router-link to="/add_parent" class="btn btn-color border" style="position: absolute">
                            <i class="bi bi-arrow-left-circle"></i>
                            Volver</router-link>
                    </div>
                    -->
                    <div style="margin-top: 40px">
                        <!--
                            <div class="form-group my-2">
                                <input @input="searchStudent" v-model="alumno" type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Buscar estudiantes...">
                            </div>
                        -->
                        <table id="myTable" class="table table-sm table-bordered table-hover text-center" style="width: 700px;">
                        <thead>
                            <tr>
                                <th class="align-middle th-color">
                                    <router-link to="/add_parent" class="btn btn-color border text-black" style="border-radius: 30px">
                                        <i class="bi bi-arrow-left-circle-fill" style="color: #8CB976"></i>
                                        Volver
                                    </router-link>  
                                </th>
                                <th class="th-color" colspan="4">
                                    <div class="form-group my-2">
                                        <input @input="searchStudent" v-model="alumno" style="border-radius: 10px" type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Buscar estudiantes...">
                                    </div>
                                </th>
                            </tr>
                            <tr>
                            <th class="th-color" scope="col">Rut</th>
                            <th class="th-color" scope="col">Nombre</th>
                            <th class="th-color" scope="col">Apellido</th>
                            <th class="th-color" scope="col">Email</th>
                            <th class="th-color" scope="col">Seleccionar</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="alumno in alumnos" :key="alumno.id">
                            <th scope="row">{{ alumno.alumRut }}</th>
                            <td>{{ alumno.alumNombre }}</td>
                            <td>{{ alumno.alumApellido }}</td>
                            <td>{{ alumno.usuario.email }}</td>
                            <td><input v-model="alumno.seleccionado" @change="handleCheckboxChange(alumno)" type="checkbox" value=""></td>
                            </tr>
                        </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    name: 'SelectStudent',
    data(){
        return {
            alumno: '',
            alumnos: [],
            alumnosSeleccionados: [],
        }
    },
    methods: {
        handleCheckboxChange(alumno) {
            if (alumno.seleccionado) {
                this.alumnosSeleccionados.push(alumno);
                this.$store.commit('updateSelectedStudents', this.alumnosSeleccionados);
            } else {
                const index = this.alumnosSeleccionados.indexOf(alumno);
                if (index !== -1) {
                    this.alumnosSeleccionados.splice(index, 1);
                }
            }
        },
        searchStudent(){
            var filter, table, tr, td, i, txtValue;
            filter = this.alumno.toUpperCase();
            table = document.getElementById("myTable");
            tr = table.getElementsByTagName("tr");

            for (i = 0; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td")[0];
                if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
                }
            }
        },
        async getStudents(){
            axios.get("http://localhost:8081/alumnos/getAll")
            .then((resp => {
                this.alumnos = resp.data;
            }))
            .catch((err =>{
                console.log(err);
            }))
        }
    },
    async mounted() {
        this.getStudents();
    }
}
</script>

<style scoped>
.btn-color{
background-color: #F8F8F8;
color: #fff;

}

.th-color{
    background-color: #E2F4CB;
}

.cardbody-color{
background-color: #f8f8f8;
}
</style>