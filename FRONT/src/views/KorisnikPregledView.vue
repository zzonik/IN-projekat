<template>
    <div class="containerK">
      <div class="rowK">
        <div class="update-form">
          <div class="korisnik-card" v-if="korisnik">
              <h1>{{ korisnik.ime }} {{ korisnik.prezime }}</h1>
              <img :src="require(`@/assets/${imagePath}`)">
              <p><strong>Korisnicko ime:</strong> {{ korisnik.korisnickoIme }}</p>
              <p><strong>Email:</strong> {{ korisnik.mail }}</p>
              <p><strong>Datum rodjenja:</strong> {{ korisnik.datumRodjenja }}</p>
              <p><strong>Opis:</strong> {{ korisnik.opis }}</p>
              <p><strong>Uloga:</strong> {{ korisnik.uloga }}</p>

              <h2>Police</h2>
              <ul>
                <li v-for="polica in police" :key="polica.id">
                  <select>
                    <option value="">{{polica.naziv}}</option>
                    <option v-for="stavkaPolice in polica.stavkePolica" :key="stavkaPolice.id" :value="stavkaPolice.knjiga.naslov">{{ stavkaPolice.knjiga.naslov }}</option>
                  </select>
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
  name: 'KorisnikView',
  data() {
    return {
      korisnik: null,
      imagePath: null,
      police: []
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
          this.imagePath = response.data.naslovnaFotografija;
          this.getPolicasOfKorisnik(); // Fetch policas after getting korisnik details
        })
        .catch((error) => {
          console.log(error);
          alert('Failed to fetch user details');
        });
    },
    getPolicasOfKorisnik() {
      const korisnikId = this.korisnik.id; // Use this.korisnik instead of this.knjiga.korisnik

      axios
        .get(`http://localhost:9090/api/korisnici/${korisnikId}/police`)
        .then((response) => {
          const Polica = response.data;
          this.police = Polica; // Assign police data to this.korisnik.policas
        })
        .catch((error) => {
          console.log(error);
        });
    },
  }
};
</script>

<style>

.containerK {
  display: flex;
  justify-content: center;
  align-items: center;
}

.rowK {
  display: flex;
  justify-content: center;
  align-items: center;
}

.korisnik-card {
  background-color: rgb(150, 220, 200);
  border: 20px solid green;
  width: 650px;
  height: fit-content;
  margin-top: 30px;
  margin-left: 8px;
  padding: 20px;
  text-align: center;
}

</style>
