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
  <div style="margin-top: 50px;" class="knjige-table">
        <table class="center">
            <thead>
                <tr>
                    <th>Naslov</th>
                    <th>ISBN</th>
                    <th>Broj Strana</th>
                    <th>Datum Objavljivanja</th>
                    <th>Opis</th>
                    <th>Ocena</th>
                    <th>Zanr</th>
                    <th>Ažuriraj knjigu</th>
                    <th>Vidi</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="knjiga in knjige" :key="knjiga.id">
                    <td>{{ knjiga.naslov }}</td>
                    <td>{{ knjiga.isbn }}</td>
                    <td>{{ knjiga.brojStrana }}</td>
                    <td>{{ knjiga.datumObjavljivanja }}</td>
                    <td>{{ knjiga.opis }}</td>
                    <td>{{ knjiga.ocena }}</td>
                    <td>{{ knjiga.zanr?.naziv }}</td>
                    <td>
                        <button @click="azurirajKnjigu(knjiga.id)">Ažuriraj knjigu</button>
                    </td>
                    <td>
                        <button @click="seeMore(knjiga.id)">Vidi još</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="add-book">
            <div class="container3">
                <section class="add-book">
                    <form>
                        <div class="row5">
                            <h4 style="text-align: center; padding-top: 20px; padding-bottom: 10px; font-weight:bold;">
                                Dodaj novu knjigu</h4>
                        </div>
                        <div class="row6">
                            <input type="text" placeholder="Unesite naslov" v-model="knjigaNaslov">
                        </div>
                        <div class="row6">
                            <input type="text" placeholder="Unesite ISBN" v-model="knjigaIsbn">
                        </div>
                        <div class="row6">
                            <input type="text" placeholder="Unesite broj strana" v-model="knjigaBrojStrana">
                        </div>
                        <div class="row6">
                            <input type="date" placeholder="Unesite datum" v-model="knjigaDatum">
                        </div>
                        <div class="row6">
                            <input type="text" placeholder="Unesite opis" v-model="knjigaOpis">
                        </div>
                        <div class="row6">
                            <div class="select-wrapper">
                                <select style="margin-left: 18px;" class="custom-select" v-model="knjigaZanr">
                                    <option value="" disabled>Odaberite žanr</option>
                                    <option v-for="zanr in zanrovi" :value="zanr.id" :key="zanr.id">{{ zanr.naziv }}</option>
                                </select>
                            </div>
                        </div>
                    </form>
                </section>
            </div>
        </div>
        <div class="dodaj_knjigu">
            <button @click="dodajKnjigu">Dodaj knjigu</button>
        </div>
  </template>
  
  <script>
  import axios from 'axios';
  import Logout from '@/components/Logout.vue';

  export default {
    name: 'AutorKnjigeView',
    components:{
        Logout
    },
    data() {
        return {
            knjige: [],
            autorId: null,
            zahtevi: []
        };
    },
    mounted() {
        this.autorId = this.$route.query.autorId;
        // Retrieve the author's books here or in another lifecycle hook
        this.getKnjige();
        this.getZanrovi();
    },
    methods: {
        getKnjige() {
            const id = this.autorId;
            axios
            .get(`http://localhost:9090/api/search-knjigeByAutor/${id}`, { withCredentials: true })
            .then((response) => {
                this.knjige = response.data;
            })
            .catch((error) => {
                console.log(error);
                alert("Failed to fetch knjige");
            });
        },
        azurirajKnjigu(knjigaId) {
            this.$router.push(`/knjigaEdit/${knjigaId}`);
        },
        dodajKnjigu() {
          const novaKnjiga = {
              naslov: this.knjigaNaslov,
              naslovnaFotografija: this.naslovnaFotografija,
              isbn: this.knjigaIsbn,
              brojStrana: this.knjigaBrojStrana,
              datumObjavljivanja: this.knjigaDatum,
              opis: this.knjigaOpis,
              autorId: this.autorId,
              zanrId: this.knjigaZanr
          };
          
  
              axios
                  .post("http://localhost:9090/api/autor/add-knjiga", novaKnjiga, { withCredentials: true })
                  .then((response) => {
                      this.getKnjige();
                      this.knjigaNaslov = "";
                      this.naslovnaFotografija = "";
                      this.knjigaIsbn = "";
                      this.knjigaBrojStrana = "";
                      this.knjigaDatum = "";
                      this.knjigaOpis = "";
                      this.knjigaAutor = "";
                      this.knjigaZanr = "";
                  })
                  .catch((error) => {
                      console.log(error);
                      alert("Failed to add knjiga");
                  });
        },
        getZanrovi() {
            axios
                .get("http://localhost:9090/api/zanrovi", { withCredentials: true })
                .then((response) => {
                    this.zanrovi = response.data;
                })
                .catch((error) => {
                    console.log(error);
                    alert("Failed to fetch zanrovi");
                });
        },
        seeMore(knjigaId) {
        // Redirect to the book page for updating
        this.$router.push(`/knjigaPregled/${knjigaId}`);
        },

    }
  };
  </script>

<style>

.select-wrapper{
    display: inline-block;
    width: 20%;
    height: calc(1.5em + 0.75rem + 2px);
    padding: 0.375rem 1.75rem 0.375rem 0.75rem;
    font-size: 1rem;
    font-weight: 400;
    line-height: 1.5;
    color: #495057;
    vertical-align: middle;
    margin-top: 10px;
    margin-left: 5px;
    margin-bottom: 25px;
}

.dodaj_knjigu button {
    background-color: aquamarine;
    padding: 8px 14px;
    margin-left: 15px;
    margin-bottom: 50px;
    cursor: pointer;
    border-radius: 8px;
    color: black;
}

</style>
  