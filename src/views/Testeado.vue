<template>
  <div class="container">
    <p>{{ this.$route.query.nombre }}</p>
    <p>{{ this.$route.query.modalidad }}</p>
    <p>{{this.$route.query.equi.equNombre}}</p>
  </div>
</template>


<script>
export default {
  name: 'NewTournamentGenerateMatches',

}
</script>
  
  
<style></style>