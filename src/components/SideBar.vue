<template>
    <div v-if="$store.state.isLoggedIn" class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark" style="background-color: #E2F4CB">
            <!-- Navbar Brand-->
            <div class="col-1"> <!--Logo-->
                <router-link class="navbar-brand ms-4" to="/home">
                    <img src="@/assets/goalline_transparent.svg" alt="Logo" width="70" height="70"
                        class="d-inline-block align-top rounded">
                </router-link>
            </div>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i
                    class="fas fa-bars"></i></button>
            <!-- Navbar Search-->
            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
                <div class="input-group">
                    <router-link @click="signOut" to="/" class="nav-link active" aria-current="page">
                        <i class="bi bi-box-arrow-right" style="font-size: 27px; color: grey"></i>
                    </router-link>
                </div>
            </form>
            <!-- Navbar-->
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown"
                        aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#!">Settings</a></li>
                        <li><a class="dropdown-item" href="#!">Activity Log</a></li>
                        <li>
                            <hr class="dropdown-divider" />
                        </li>
                        <li><a class="dropdown-item" href="#!">Logout</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-light bg-muted" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">

                            <!--PROFESOR-->
                            <router-link to="/home" v-if="$store.state.userRole === 'Profesor'" class="nav-link mt-3"
                                :class="{ 'active': activeButton === 'principal' }" @click="setActiveButton('principal')">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Principal
                            </router-link>

                            <router-link to="/newTournamentSelectTeams" v-if="$store.state.userRole === 'Profesor'" class="nav-link mt-3"
                                :class="{ 'active': activeButton === 'torneos' }" @click="setActiveButton('torneos')">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Torneos
                            </router-link>

                            <router-link to="/myTeams" v-if="$store.state.userRole === 'Profesor'" class="nav-link mt-3"
                                :class="{ 'active': activeButton === 'misequipos' }" @click="setActiveButton('misequipos')">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Mis equipos
                            </router-link>

                            <router-link to="/classes" v-if="$store.state.userRole === 'Profesor'" class="nav-link mt-3"
                                :class="{ 'active': activeButton === 'clases' }" @click="setActiveButton('clases')">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Clases
                            </router-link>

                            <!--ADMINISTRADOR-->
                            <router-link to="/home" v-if="$store.state.userRole === 'Administrador'" class="nav-link mt-3"
                                :class="{ 'active': activeButton === 'principal' }" @click="setActiveButton('principal')">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Principal
                            </router-link>
                            <a v-if="$store.state.userRole === 'Administrador'" class="nav-link collapsed" href="#"
                                data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false"
                                aria-controls="collapseLayouts" :class="{ 'active': activeButton === 'alumnos' }"
                                @click="setActiveButton('alumnos')">
                                <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                                Alumnos
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>

                            <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne"
                                data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="#">Gestionar alumnos</a>
                                    <router-link class="nav-link" to="/add_student">Registrar alumno</router-link>
                                </nav>
                            </div>

                            <a v-if="$store.state.userRole === 'Administrador'" class="nav-link collapsed" href="#"
                                data-bs-toggle="collapse" data-bs-target="#collapsePages" aria-expanded="false"
                                aria-controls="collapsePages" :class="{ 'active': activeButton === 'apoderados' }"
                                @click="setActiveButton('apoderados')">
                                <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div>
                                Apoderados
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>

                            <div class="collapse" id="collapsePages" aria-labelledby="headingTwo"
                                data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                                    <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
                                        data-bs-target="#pagesCollapseAuth" aria-expanded="false"
                                        aria-controls="pagesCollapseAuth">
                                        Gestionar apoderados
                                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                    </a>
                                    <router-link class="nav-link" to="/add_parent">Registrar apoderado</router-link>
                                </nav>
                            </div>
                            <a v-if="$store.state.userRole === 'Administrador'" class="nav-link collapsed" href="#"
                                data-bs-toggle="collapse" data-bs-target="#collapseProfesor" aria-expanded="false"
                                aria-controls="collapseProfesor" :class="{ 'active': activeButton === 'profesores' }"
                                @click="setActiveButton('profesores')">
                                <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div>
                                Profesores
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapseProfesor" aria-labelledby="headingTwo"
                                data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                                    <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
                                        data-bs-target="#pagesCollapseAuth" aria-expanded="false"
                                        aria-controls="pagesCollapseAuth">
                                        Gestionar profesores
                                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                    </a>
                                    <router-link class="nav-link" to="/add_teacher">Registrar profesor</router-link>
                                </nav>
                            </div>




                            <!--ALUMNO-->
                            <div class="sb-sidenav-menu">
                                <div class="nav">
                                    
                                    <a v-if="$store.state.userRole === 'Estudiante'" class="nav-link mt-3" 
                                        :class="{ 'active': activeButton === 'principal' }"
                                        @click="setActiveButton('principal')">
                                        <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                        
                                        <router-link class="nav-link" to="/home">Principal</router-link>
                                    </a>
                                


                                    <a v-if="$store.state.userRole === 'Estudiante'" class="nav-link mt-3" 
                                        :class="{ 'active': activeButton === 'estadisticas' }"
                                        @click="setActiveButton('estadisticas')">
                                        <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                        
                                        <router-link class="nav-link" to="/student_stats">Mis estadísticas</router-link>
                                    </a>

                                    <a v-if="$store.state.userRole === 'Estudiante'" class="nav-link mt-3" 
                                        :class="{ 'active': activeButton === 'torneos' }"
                                        @click="setActiveButton('torneos')">
                                        <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                                Mis torneos
                                    </a>

                                </div>
                            </div>

                            <!--APODERADO-->
                            <div class="sb-sidenav-menu">
                                <div class="nav">
                                    
                                    <a v-if="$store.state.userRole === 'Apoderado'" class="nav-link mt-3" 
                                        :class="{ 'active': activeButton === 'principal' }"
                                        @click="setActiveButton('principal')">
                                        <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                        
                                        <router-link class="nav-link" to="/home">Principal</router-link>
                                    </a>

                                    <a v-if="$store.state.userRole === 'Apoderado'" class="nav-link mt-3" 
                                        :class="{ 'active': activeButton === 'pupilos' }"
                                        @click="setActiveButton('pupilos')">
                                        <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                        
                                        <router-link class="nav-link" to="/choose_student">Pupilos</router-link>
                                    </a>
                                
            
                                </div>

                            </div>

                        </div>
                    </div>



                    

                    <div class="sb-sidenav-footer">
                        <div class="small">Iniciaste sesión como:</div>
                        {{ $store.state.userRole }}
                    </div>
                </nav>
            </div>
        </div>
    </div>
</template>

<script>
import '../assets/css/styles.css';
export default {
    name: 'SideBar',
    data() {
        return {
            activeButton: 'principal', // Guarda el botÃ³n activo
        };
    },
    methods: {
        setActiveButton(buttonName) {
            this.activeButton = buttonName === this.activeButton ? null : buttonName;
        },
        signOut() {
            this.$store.dispatch('updateLoginStatus', false);
            sessionStorage.removeItem('token');
        }
    },
    watch: {
        $route(to) {
            if (to.path === '/home') {
                this.activeButton = 'principal';
            }
        }
    }

}
</script>

<style scoped>
.nav-link.active {
    background-color: #E2F4CB;
    color: black !important;
    /* Agrega cualquier otro estilo necesario */
}
</style>