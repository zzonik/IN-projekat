<template>
    <div v-if="knjiga">
      <h1>{{ knjiga.naslov }}</h1>
      <p><strong>Naslovna fotografija:</strong> {{ knjiga.naslovnaFotografija }}</p>
      <p><strong>ISBN:</strong> {{ knjiga.ISBN }}</p>
      <p><strong>Datum objavljivanja:</strong> {{ knjiga.datumObjavljivanja }}</p>
      <p><strong>Broj strana:</strong> {{ knjiga.brojStrana }}</p>
      <p><strong>Opis:</strong> {{ knjiga.opis }}</p>
      <p><strong>Ocena:</strong> {{ knjiga.ocena }}</p>
      <p><strong>Zanr:</strong> {{ knjiga.zanr.naziv }}</p>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    name: 'KnjigaView',
    data() {
      return {
        knjiga: null
      };
    },
    created() {
      this.getKnjigaDetails();
    },
    methods: {
      getKnjigaDetails() {
        const knjigaId = this.$route.params.id;
  
        axios
          .get(`http://localhost:9090/api/knjige/${knjigaId}`)
          .then((response) => {
            this.knjiga = response.data;
          })
          .catch((error) => {
            console.log(error);
            alert('Failed to fetch book details');
          });
      }
    }
  };
  </script>
  