<template>
  <div class="containerB">
    <div class="rowB">
      <div class="update-form">
          <div class="knjiga-card" v-if="knjiga">
            <h1 style="font-size: 40px;">{{ knjiga.naslov }}</h1>
            <img :src="require(`@/assets/${imagePath}`)">
            <p style="font-size: 20px;"><strong>ISBN:</strong> {{ knjiga.isbn }}</p>
            <p style="font-size: 20px;"><strong>Datum objavljivanja:</strong> {{ knjiga.datumObjavljivanja }}</p>
            <p style="font-size: 20px;"><strong>Broj strana:</strong> {{ knjiga.brojStrana }}</p>
            <p style="font-size: 20px;"><strong>Opis:</strong> {{ knjiga.opis }}</p>
            <p style="font-size: 20px;"><strong>Ocena:</strong> {{ knjiga.ocena }}</p>
            <p style="font-size: 20px;"><strong>Zanr:</strong> {{ knjiga.zanr.naziv }}</p>

            <h2 style="font-size: 30px;">Recenzije</h2>
            <ul>
              <li v-for="recenzija in knjiga.recenzije" :key="recenzija.id">
                <p style="font-size: 20px;"><strong>Ocena:</strong> {{ recenzija.ocena }}</p>
                <p style="font-size: 20px;"><strong>Tekst:</strong> {{ recenzija.tekst }}</p>
                <p style="font-size: 20px;"><strong>Datum:</strong> {{ recenzija.datum }}</p>
                <p style="font-size: 20px;"><strong>Korisnik:</strong> {{ recenzija.korisnik?.ime }} {{ recenzija.korisnik?.prezime }}</p>
              </li>
            </ul>
          </div>
      </div>
    </div>
  </div> 
</template>

<script>
import axios from 'axios';

export default {
  name: 'KnjigaView',
  data() {
    return {
      knjiga: null,
      imagePath: null
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
          this.imagePath = response.data.naslovnaFotografija;
          this.getRecenzijeKnjige();
        })
        .catch((error) => {
          console.log(error);
          alert('Failed to fetch book details');
        });
    },
    getRecenzijeKnjige() {
      const knjigaId = this.$route.params.id;

      axios
        .get(`http://localhost:9090/api/search-recenzijeKnjige/${knjigaId}`)
        .then((response) => {
          const recenzije = response.data;
          this.knjiga.recenzije = recenzije;
        })
        .catch((error) => {
          console.log(error);
        });
    }
    
  }
};
</script>

<style>

.containerB {
  display: flex;
  justify-content: center;
  align-items: center;
}

.rowB {
  display: flex;
  justify-content: center;
  align-items: center;
}

.knjiga-card {
  background-color: rgb(150, 220, 200);
  border: 20px solid green;
  width: 650px;
  height: fit-content;
  margin-left: 8px;
  padding: 20px;
  text-align: center;
}


</style>
