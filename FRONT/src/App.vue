<template>
  <router-view/>>
</template>

<script>
import LoginSection from './components/LoginSection.vue';
import RegisterSection from './components/RegisterSection.vue';

export default {
  name: 'App',
  components: {
    LoginSection,
    RegisterSection
  },
  data() {
    return {
      isLoggedIn: false
    };
  },
  methods: {
    handleLoginSuccess() {
      this.isLoggedIn = true;
    },
    handleRegisterSuccess() {
      this.isLoggedIn = true;
    }
  }
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
