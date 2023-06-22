<template>
    <div v-if="korisnik">
      <h1>{{ korisnik.ime }} {{ korisnik.prezime }}</h1>
      <p><strong>Korisnicko ime:</strong> {{ korisnik.korisnickoIme }}</p>
      <p><strong>Email:</strong> {{ korisnik.mail }}</p>
      <p><strong>Datum rodjenja:</strong> {{ korisnik.datumRodjenja }}</p>
      <p><strong>Profilna slika:</strong> {{ korisnik.profilnaSlika }}</p>
      <p><strong>Opis:</strong> {{ korisnik.opis }}</p>
      <p><strong>Uloga:</strong> {{ korisnik.uloga }}</p>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    name: 'KorisnikView',
    data() {
      return {
        korisnik: null
      };
    },
    created() {
      this.getKorisnikDetails();
    },
    methods: {
      getKorisnikDetails() {
        const korisnikId = this.$route.params.id;
  
        axios
          .get(`http://localhost:9090/api/korisnici/${korisnikId}`)
          .then((response) => {
            this.korisnik = response.data;
          })
          .catch((error) => {
            console.log(error);
            alert('Failed to fetch user details');
          });
      }
    }
  };
  </script>
  