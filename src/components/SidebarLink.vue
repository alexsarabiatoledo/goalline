<!-- SidebarLink.vue -->

<template>
  <div>
    <a v-if="shouldShowLink" class="nav-link" :class="{ 'active': isActive }" @click="handleClick">
      <div class="sb-nav-link-icon"><i :class="iconClass"></i></div>
      {{ label }}
      <div v-if="hasSublinks" class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
    </a>
    <div v-if="hasSublinks" class="collapse" :id="collapseId" :aria-labelledby="headingId" data-bs-parent="#sidenavAccordion">
      <nav class="sb-sidenav-menu-nested nav">
        <!-- Sublinks go here -->
        <router-link v-if="hasSublink" class="nav-link" :to="sublinkTo">{{ sublinkLabel }}</router-link>
      </nav>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    userRole: String,
    activeButton: String,
    requiredRole: String,
    label: String,
    iconClass: String,
    hasSublinks: Boolean,
    sublinkLabel: String,
    sublinkTo: String
  },
  computed: {
    shouldShowLink() {
      return this.userRole === this.requiredRole;
    },
    isActive() {
      return this.activeButton === this.label.toLowerCase();
    },
    collapseId() {
      return `collapse${this.label}`;
    },
    headingId() {
      return `heading${this.label}`;
    },
    hasSublink() {
      return this.hasSublinks && this.sublinkLabel && this.sublinkTo;
    }
  },
  methods: {
    handleClick() {
      if (this.hasSublinks) {
        // Handle click for links with sublinks
      } else {
        this.$emit('click', this.label.toLowerCase());
      }
    }
  }
};
</script>

<style scoped>
/* Add any custom styles for your component here */
</style>
