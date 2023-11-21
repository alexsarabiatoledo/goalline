<template>
    <section>
  <div class="container my-5">
    <header class="mb-4">
      <h3 style="text-align: left; color: #525252">Nuestras categorías <span style="color: #E73C17; font-size: 20px"><i class="bi bi-stars"></i>
</span></h3>
    </header>
    <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel" data-bs-interval="3000">
      <div class="carousel-indicators">
        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="3" aria-label="Slide 4"></button>
        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="4" aria-label="Slide 5"></button>
      </div>
      <div class="carousel-inner mx-auto rounded" style="width: 100%">
        <div class="carousel-item active" style="height: 350px">
          <router-link :to="{ name: 'productCategory', params: { category: 'MLC1648' } }">
            <div class="d-flex overlay-container">
            <button class="btn btn-primary btn-top-left">Computación</button>
            <img src="../assets/categorias/computacion1.avif" class="d-block w-50" alt="...">
            <img src="../assets/categorias/computacion2.jpg" class="d-block w-50" alt="...">
            <div class="overlay"></div>
          </div>
          </router-link>
          <div class="carousel-caption d-none d-md-block custom-caption">
            <h5 style="font-size: 15px">Explora monitores, mouse, teclados y más</h5>
          </div>
        </div>
        <div class="carousel-item" style="height: 350px">
          <router-link :to="{ name: 'productCategory', params: { category: 'MLC1000' } }">
            <div class="d-flex">
            <button class="btn btn-primary btn-top-left">Audio</button>
            <img src="../assets/categorias/audio1.jpg" class="d-block w-50" alt="...">
            <img src="../assets/categorias/audio2.jpg" class="d-block w-50" alt="...">
          </div>
          </router-link>
          <div class="carousel-caption d-none d-md-block custom-caption">
            <h5 style="font-size: 15px">¿Buscas parlantes, audífonos o ambos?</h5>
          </div>
        </div>
        <div class="carousel-item" style="height: 350px">
          <router-link :to="{ name: 'productCategory', params: { category: 'MLC1051' } }">
            <div class="d-flex">
            <button class="btn btn-primary btn-top-left">Teléfonos</button>
            <img src="../assets/categorias/movil1.jpg" class="d-block w-50" alt="...">
            <img src="../assets/categorias/movil2.jpg" class="d-block w-50" alt="...">
          </div>
          </router-link>
          <div class="carousel-caption d-none d-md-block custom-caption">
            <h5 style="font-size: 15px">Celulares y accesorios para tu teléfono</h5>
          </div>
        </div>
        <div class="carousel-item" style="height: 350px">
          <router-link :to="{ name: 'productCategory', params: { category: 'MLC1039' } }">
            <div class="d-flex">
            <button class="btn btn-primary btn-top-left">Cámaras</button>
            <img src="../assets/categorias/camara1.jpg" class="d-block w-50" alt="...">
            <img src="../assets/categorias/camara2.jpg" class="d-block w-50" alt="...">
          </div>
          </router-link>
          <div class="carousel-caption d-none d-md-block custom-caption">
            <h5 style="font-size: 15px">Cámaras y accesorios de la mejor calidad</h5>
          </div>
        </div>
        <div class="carousel-item" style="height: 350px">
          <router-link :to="{ name: 'productCategory', params: { category: 'MLC1144' } }">
            <div class="d-flex">
            <button class="btn btn-primary btn-top-left">Videojuegos</button>
            <img src="../assets/categorias/juegos1.avif" class="d-block w-50" alt="...">
            <img src="../assets/categorias/juegos2.webp" class="d-block w-50" alt="...">
          </div>
          </router-link>
          <div class="carousel-caption d-none d-md-block custom-caption">
            <h5 style="font-size: 15px">Videojuegos, controles y consolas</h5>
          </div>
        </div>
      </div>
      <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>
    </div>
  </div>
  </section>
</template>
<script>
export default {
    name: 'CategoriesCarousel'
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

.shop{
    background-color: #E73C17;
}
.masthead{
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

.category-change:hover{
  background-color: rgb(255, 60, 0) !important;
}
</style>