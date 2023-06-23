<template>
  <div class="containerB">
    <div class="rowB">
      <div class="update-form">
        <div class="knjiga-card" v-if="knjiga">
          <!-- Display the book details -->
          <h1 id="naslov">{{ knjiga.naslov }}</h1>
          <div class="form-row">
            <label for="naslov">Naslov:</label>
            <input type="text" id="naslov" v-model="updatedNaslov">
          </div>
          <div class="form-row">
            <label for="isbn">ISBN:</label>
            <input type="text" id="isbn" v-model="updatedIsbn">
          </div>
          <div class="form-row">
            <label for="brojStrana">Broj strana:</label>
            <input type="number" id="brojStrana" v-model="updatedBrojStrana">
          </div>
          <div class="form-row">
            <label for="datumObjavljivanja">Datum Objavljivanja:</label>
            <input type="date" id="datumObjavljivanja" v-model="updatedDatumObjavljivanja">
          </div>
          <div class="form-row">
            <label for="opis">Opis:</label>
            <input type="text" id="opis" v-model="updatedOpis">
          </div>
          <div class="form-row">
            <label for="ocena">Ocena:</label>
            <input disabled type="number" id="ocena" v-model="updatedOcena">
          </div>
          <div class="form-row">
            <label for="updatedAutor">Autor:</label>
            <select class="custom-select" id="updatedAutor" v-model="updatedAutor">
              <option value="" disabled>Odaberite autora</option>
              <option v-for="autor in availableAutors" :value="autor.id" :key="autor.id">{{ autor.ime }} {{ autor.prezime }}</option>
            </select>
          </div>
          <div class="form-row">
            <label for="updatedZanr">Zanr:</label>
            <select class="custom-select" id="updatedZanr" v-model="updatedZanr">
              <option disabled value="">Odaberite Zanr</option>
              <option v-for="zanr in zanrovi" :value="zanr" :key="zanr.id">
                {{ zanr.naziv }}
              </option>
            </select>
          </div>
          <div class="form-row">
            <label for="slika">Slika:</label>
            <input type="file" id="slika" @change="handleSlikaUpload">
          </div>
          <div class="form-row">
            <button class="dugme" @click="sacuvajIzmene">Sacuvaj izmene</button>
          </div>
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
      updatedNaslov: '',
      updatedIsbn: '',
      updatedBrojStrana: '',
      updatedDatumObjavljivanja: '',
      updatedOpis: '',
      updatedOcena: '',
      updatedAutor: '',
      updatedZanr: '',
      knjige: [],
      korisnici: [],
      zanrovi: [],
      zahtevi: [],
      availableAutors: [],
    };
  },
  created() {
    this.getKnjigaDetails();
  },
  mounted() {
    this.getZanrovi();
    this.getAutori();
  },
  methods: {
    getAutori() {
      axios
        .get('http://localhost:9090/api/korisnici', { withCredentials: true })
        .then((response) => {
          this.korisnici = response.data;
          this.availableAutors = response.data.filter(
            (korisnik) => korisnik.uloga === 'AUTOR'
          );

          if (this.knjiga && this.knjiga.autor) {
            this.updatedAutor = this.knjiga.autor.id;
          }
        })
        .catch((error) => {
          console.log(error);
          alert('Failed to fetch korisnici');
        });
    },

    getZanrovi() {
      axios
        .get('http://localhost:9090/api/zanrovi', { withCredentials: true })
        .then((response) => {
          this.zanrovi = response.data;
        })
        .catch((error) => {
          console.log(error);
          alert('Failed to fetch zanrovi');
        });
    },

    getKnjigaDetails() {
      const knjigaId = this.$route.params.id;

      axios
        .get(`http://localhost:9090/api/knjige/${knjigaId}`)
        .then((response) => {
          this.knjiga = response.data;
          this.updatedNaslov = this.knjiga.naslov;
          this.updatedIsbn = this.knjiga.isbn;
          this.updatedBrojStrana = this.knjiga.brojStrana;
          this.updatedDatumObjavljivanja = this.knjiga.datumObjavljivanja;
          this.updatedOpis = this.knjiga.opis;
          this.updatedOcena = this.knjiga.ocena;
          this.updatedZanr = this.knjiga.zanr;
        })
        .catch((error) => {
          console.log(error);
          alert('Failed to fetch book details');
        });
    },
    

    sacuvajIzmene() {
      const knjigaId = this.$route.params.id;

      const updatedKnjiga = {
        naslov: this.updatedNaslov,
        isbn: this.updatedIsbn,
        brojStrana: this.updatedBrojStrana,
        naslovnaFotografija: "",
        datumObjavljivanja: this.updatedDatumObjavljivanja,
        opis: this.updatedOpis,
        autorId: this.updatedAutor,
        zanrId: this.updatedZanr.id,
      };

      axios
        .put(`http://localhost:9090/api/admin/knjiga/${knjigaId}/update_knjiga`, updatedKnjiga)
        .then((response) => {
          console.log('Book updated successfully');
          this.$router.push(`/homeAdministrator`);
        })
        .catch((error) => {
          console.log(error);
          alert('Failed to update book details');
        });
    },
  },
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

.update-form {
  background-color: green;
  padding: 15px;
  width: 700px;
  height: 750px;
}

.knjiga-card {
  background-color: rgb(150, 220, 200);
  border: 4px solid black;
  width: 300px;
  height: 700px;
  margin: 10px 0;
  padding: 20px;
  text-align: center;
}

.form-row {
  display: flex;
  align-items: center;
  margin: 10px 0;
}

input[type="text"],
input[type="number"],
input[type="date"],
select {
  width: 200px;
}

.knjiga-card input{
  margin-top: 10px;
  margin-bottom: 10px;
}
</style>
