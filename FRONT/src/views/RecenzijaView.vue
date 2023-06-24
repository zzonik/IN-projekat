<template>
  <div class="containerR">
    <div class="rowR">
      <div class="update-form">
        <div class="recenzija-card">
          <h1>Dodaj Recenziju</h1>
            <form @submit="submitRecenzija">
              <label for="ocena">Ocena:</label>
              <input type="number" id="ocena" v-model="recenzija.ocena" required>
        
              <label for="datum">Datum:</label>
              <input type="date" id="datum" v-model="recenzija.datum" required>
        
              <label for="tekst">Tekst:</label>
              <textarea id="tekst" v-model="recenzija.tekst" required></textarea>
        
              <label for="polica">Polica:</label>
              <select class="custom-select" v-model="recenzija.selectedPolica" required>
                <option v-for="polica in recenzija.policeDodavanje" :value="polica" :key="polica.id">{{ polica.naziv }}</option>
              </select>
              <div class="dugmeRecenzija">
                <button type="submit">Dodaj</button>
              </div>
            </form>
          </div>
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
          police: [],
          policeDodavanje: [],
          selectedPolica: null
        },
      }
    },
    mounted() {
      this.recenzija.korisnikId = this.$route.params.korisnikId;
      this.recenzija.knjigaId = this.$route.params.knjigaId;
      this.recenzija.policaId = this.$route.params.policaId;
      this.getPolice();
    },
    methods: {
        getPolice() {
            const id = this.recenzija.korisnikId;
            axios
            .get(`http://localhost:9090/api/citalac/${id}/police`, {
                withCredentials: true,
                headers: {
                    'Cache-Control': 'no-cache',
                    'Pragma': 'no-cache',
                    'Expires': '0',
                },
                })
                .then((response) => {
                    this.recenzija.policeDodavanje = response.data;
                })
                .catch((error) => {
                    console.log(error);
                    alert("Failed to fetch police");
                });
        },
      submitRecenzija() {
        const knjigaId = this.recenzija.knjigaId;
        const citalacId = this.recenzija.korisnikId;
        const polica = this.recenzija.policaId;
        const policeD = this.recenzija.selectedPolica.id;
        
  
        const recenzijaAddDto = {
          ocena: this.recenzija.ocena,
          datum: this.recenzija.datum,
          tekst: this.recenzija.tekst,
          korisnikId: citalacId,
          knjigaId: knjigaId,
          police: [policeD]
        };
  
        axios.post(`http://localhost:9090/api/citalac/polica/${polica}/stavka-police/add-recenzija`, recenzijaAddDto)
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

.containerR {
  display: flex;
  justify-content: center;
  align-items: center;
}

.rowR {
  display: flex;
  justify-content: center;
  align-items: center;
}

.recenzija-card {
  background-color: rgb(150, 220, 200);
  border: 20px solid green;
  width: 650px;
  height: fit-content;
  margin-top: 30px;
  margin-left: 8px;
  padding: 20px;
  text-align: center;
}

.dugmeRecenzija button {
  background-color: aquamarine;
    border: 5px solid green;
    color: black;
    padding: 16px 32px;
    text-decoration: none;
    margin: 4px 2px;
    cursor: pointer;
    margin-top: 15px;
    margin-bottom: 20px;
    margin-left: 20px;
    border-radius: 8px;
}

</style>