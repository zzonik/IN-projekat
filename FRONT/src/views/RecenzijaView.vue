<template>
  <div class="containerB">
    <div class="rowB">
      <div class="recenzija-form">
            <h1>Recenzija</h1>
            <form @submit="submitRecenzija">
              <div class="form-row">
                <label for="ocena">Ocena:</label>
                <input type="number" id="ocena" v-model="recenzija.ocena" required>
              </div>
              <div class="form-row">
                <label for="datum">Datum:</label>
                <input type="date" id="datum" v-model="recenzija.datum" required>
              </div>
              <div class="form-row">
                <label for="tekst">Tekst:</label>
                <textarea id="tekst" v-model="recenzija.tekst" required></textarea>
              </div>
              <div class="dugme">
                <button type="submit">Postavite recenziju</button>
              </div>
            </form>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      recenzija: {
        ocena: null,
        datum: null,
        tekst: '',
        knjigaId: null,
        korisnikId: null,
        policaId: null,
        police: []
      },
    }
  },
  mounted() {
      this.recenzija.korisnikId = this.$route.params.korisnikId;
      this.recenzija.knjigaId = this.$route.params.knjigaId;
      this.recenzija.policaId = this.$route.params.policaId;
      },
  methods: {
    submitRecenzija() {
      const knjigaId = this.recenzija.knjigaId; // Use knjigaId instead of knjiga
      const citalacId = this.recenzija.korisnikId; // Use citalacId instead of citalac
      const polica = this.recenzija.policaId;
      console.log(citalacId);

      const recenzijaAddDto = {
        ocena: this.recenzija.ocena,
        datum: this.recenzija.datum,
        tekst: this.recenzija.tekst,
        korisnikId: citalacId,
        knjigaId: knjigaId,
        police: [4] 
      };

      axios
    .post(`http://localhost:9090/api/citalac/polica/${polica}/stavka-police/add-recenzija`, recenzijaAddDto)
        .then(response => {
          // Handle the successful response
          console.log(response.data);
        })
        .catch(error => {
          // Handle any errors
          console.error(error);
        });
    }
  }
}
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
  background-color: rgb(150, 220, 200);
  border: 20px solid green;
  width: 650px;
  height: 400px;
}

.dugme button{
    background-color: aquamarine;
    border: 5px solid green;
    color: black;
    padding: 16px 32px;
    text-decoration: none;
    margin: 4px 2px;
    cursor: pointer;
    margin-bottom: 20px;
    margin-left: 8px;
    border-radius: 8px;
}

.form-row {
  display: flex;
  align-items: center;
  margin: 10px 0;
}

.recenzija-card input{
  margin-top: 10px;
  margin-bottom: 10px;
}

</style>
