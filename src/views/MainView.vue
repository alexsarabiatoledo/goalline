<template>
    <div class="container pt-5" style="margin-left: 160px;">
      <div class="text-center">
        <h6>Bienvenido {{ $store.state.userRole }}</h6>
        <p>Esta plataforma le provee los servicios que usted puede<br>consultar a través del sistema de Goalline Manager.</p>
        <p>Si posees algún problema, no dudes en contactar al <br> servicio técnico escribiendo al siguiente email: 
         <br> <b>administracion@goalline.cl</b></p>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'MainView',
    data() {
      return {
      };
    },
  };
  </script>
  
  <style scoped>
 
  
  </style>
  