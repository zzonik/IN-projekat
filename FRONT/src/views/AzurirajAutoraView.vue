<template>
    <header>
        <div class="pre-header">
            <div class="container1">
                <div class="row1">
                    <div class="imageheader1">
                        <div class="col-md-4">
                            <img src="../assets/headerLeftRight.png">
                        </div>
                    </div>  
                    <div class="imagelogo">
                        <div class="col-md-4">
                            <img src="../assets/logo.png">
                        </div>
                    </div> 
                    <div class="imageheader2">
                        <div class="col-md-4">
                          <img src="../assets/headerLeftRight.png">
                        </div>
                    </div> 
                </div>
            </div>
        </div>
        <div class="container2">
          <div class="row2">
              <div>
                <ul class="menu">
                    <li id="pocetna">
                            <router-link
                                :to="{ path: '/homeAutor', query: { autorId : autorId } }">Početna</router-link>
                        </li>
                        <li id="azuriraj">
                            <router-link
                                :to="{ path: '/azurirajAutora', query: { autorId : autorId } }">Azuriraj profil</router-link>
                        </li>
                        <li id="knjige">
                        <router-link 
                                :to="{ path: '/autorKnjige', query: { autorId: autorId } }">Moje knjige</router-link>
                        </li>
                        <li id="pretraga">
                            <router-link
                                :to="{ path: '/pretragaAutor', query: { autorId : autorId } }">Pretraga
                                🔍</router-link>
                        </li>
                        <li>
                            <Logout />
                        </li>
                </ul>
              </div>
          </div>
      </div>
    </header>

    <div class="azuriranje">
        <div class="containerC">
            <div class="rowC">
                <form @submit="azurirajAutora">
                    <div class="form-group">
                        <h2 style="text-align: center; padding-top: 20px; padding-bottom: 10px; font-weight: bold; font-size: 40px;">
                            Ažuriraj profil:
                        </h2>
                    </div>
                    <div class="form-group">
                    <h4 style="text-align: center; padding-top: 20px; padding-bottom: 10px; font-weight: bold; font-size: 20px;">
                        Ime:</h4>
                    <input type="text" v-model="ime" placeholder="Unesite ime" name="ime">
                    </div>
                    <div class="form-group">
                    <h4 style="text-align: center; padding-top: 20px; padding-bottom: 10px; font-weight: bold; font-size: 20px;">
                        Prezime:</h4>
                    <input type="text" v-model="prezime" placeholder="Unesite prezime" name="prezime">
                    </div>
                    <div class="form-group">
                    <h4 style="text-align: center; padding-top: 20px; padding-bottom: 10px; font-weight: bold; font-size: 20px;">
                        Mail:</h4>
                    <input type="text" v-model="mail" placeholder="Unesite mail" name="mail">
                    </div>
                    <div class="form-group">
                    <h4 style="text-align: center; padding-top: 20px; padding-bottom: 10px; font-weight: bold; font-size: 20px;">
                        Datum rođenja:</h4>
                    <input type="date" v-model="datumRodjenja" name="datumRodjenja">
                    </div>
                    <div class="form-group">
                    <h4 style="text-align: center; padding-top: 20px; padding-bottom: 10px; font-weight: bold; font-size: 20px;">
                        Profilna slika:</h4>
                    <input type="file" @change="handleFileUpload" name="profilnaSlika">
                    </div>
                    <div class="form-group">
                    <h4 style="text-align: center; padding-top: 20px; padding-bottom: 10px; font-weight: bold; font-size: 20px;">
                        O meni:</h4>
                    <textarea v-model="oMeni" placeholder="Unesite nešto o sebi" name="oMeni"></textarea>
                    </div>
                    <div class="form-group">
                    <h4 style="text-align: center; padding-top: 20px; padding-bottom: 10px; font-weight: bold; font-size: 20px;">
                        Trenutna lozinka:</h4>
                    <input type="password" v-model="trenutnaLozinka" placeholder="Unesite trenutnu lozinku" name="trenutnaLozinka">
                    </div>
                    <div class="form-group">
                    <h4 style="text-align: center; padding-top: 20px; padding-bottom: 10px; font-weight: bold; font-size: 20px;">
                        Nova lozinka:</h4>
                    <input type="password" v-model="novaLozinka" placeholder="Unesite novu lozinku" name="novaLozinka">
                    </div>
                    <div class="form-group">
                    <h4 style="text-align: center; padding-top: 20px; padding-bottom: 10px; font-weight: bold; font-size: 20px;">
                        Ponovite novu lozinku:</h4>
                    <input type="password" v-model="ponoviNovuLozinku" placeholder="Ponovite novu lozinku" name="ponoviNovuLozinku">
                    </div>
                    <div class="dugme1">
                    <button>Ažuriraj profil</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <footer>
        <p>&copy; 2023 BookBuddy. Sva prava zadržana.</p>
    </footer>
</template>

<script>
import axios from 'axios';
import Logout from "@/components/Logout.vue";

export default {
  name: 'AzurirajAutoraView',
  components: { Logout },
  data() {
    return {
      autorId: null,
      ime: '',
      prezime: '',
      datumRodjenja: '',
      oMeni: '',
      trenutnaLozinka: '',
      novaLozinka: '',
      ponoviNovuLozinku: '',
      mail: '',
    };
  },
  mounted() {
    this.autorId = this.$route.query.autorId;
  },
  methods: {
    
    azurirajAutora() {
        if (this.novaLozinka !== this.ponoviNovuLozinku) {
        alert("Passwords do not match. Please make sure to enter the same password twice.");
        return;
        }
        const updateDto = {
        ime: this.ime,
        prezime: this.prezime,
        datumRodjenja: this.datumRodjenja,
        opis: this.oMeni,
        mail: this.mail,
        lozinka: this.trenutnaLozinka,
        newlozinka: this.ponoviNovuLozinku,
      };

      axios
        .put('http://localhost:9090/api/citalac/update-korisnik', updateDto, { withCredentials: true })
        .then(response => {
          console.log(response.data); // Možete prilagoditi postupanje s odgovorom
          alert('Korisnik uspešno ažuriran.');
          this.$router.push(`/korisnikPregled/${this.autorId}`);

        })
        .catch(error => {
          console.log(error);
          alert('Greška prilikom ažuriranja korisnika.');
        });
    }
  }
};
</script>

<style>
    * {
    margin: 0;
    padding: 0;
}

.pre-header {
    background-color: aquamarine;
    height: 190px;
}

.menu {
    display: flex;
    justify-content:space-around;
    list-style: none;
    padding: 0;
  }
  
a{
    font-weight: bold;
    font-style: italic;
    font-size: 15px;
    color: rgb(36, 136, 102);
}

.container2 {
    background-color: rgb(0, 255, 170);
    height: 40px;
    padding-top: 8px;
}

.container1 .row1 {
    display: flex;
    justify-content: center;
    align-items: center;
}
  
.container1 .col-md-4 {
    flex: 1;
}

.imagelogo img {
    height: 90px;
}

.container1 .col-md-4:nth-child(2) img {
    display: block;
    margin: 0 auto;
}

.imageheader1 img {
    opacity: 0.75;
    padding-top: 15px;
}

.imageheader2 {
    opacity: 0.75;
    padding-top: 15px;
}

footer {
    text-align: center;
    margin-top: 40px;
    font-weight: bold;
}

.search-section ::placeholder{
    margin-top: 25px;
    text-align: center;
}
.knjige-table{
    margin-top: 15px;
}

input[type="text"],
input[type="number"],
input[type="date"],
select {
    width: 210px; /* Updated width for form elements */
    text-align: center;
   margin: 0 auto;
}

.form-group {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-bottom: 10px;
}

.dugme1 {
    display: flex;
    justify-content: center;
    margin-top: 20px;
}

input, textarea {
    width: 210px;
}

.containerC {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
}

.rowC {
  display: flex;
  justify-content: center;
  align-items: center;
}

.rowC {
  background-color: rgb(150, 220, 200);
  border: 20px solid green;
  width: 650px;
  height: fit-content;
  margin-left: 8px;
  padding: 20px;
  text-align: center;
}

label {
  margin-right: 10px;
}

.containerC button {
    background-color: aquamarine;
    border: 5px solid green;
    color: black;
    padding: 16px 32px;
    text-decoration: none;
    margin: 4px 2px;
    cursor: pointer;
    margin-top: 15px;
    margin-bottom: 20px;
    margin-left: 30px;
    border-radius: 8px;
}

</style>