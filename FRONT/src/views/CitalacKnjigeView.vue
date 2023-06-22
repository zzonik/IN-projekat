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
                    <router-link :to="{ path: '/homeCitalac', query: { korisnikId: korisnikId } }">Početna</router-link>
                </li>
                <li id="pretraga">
                    <router-link :to="{ path: '/pretragaCitalac', query: { korisnikId: korisnikId } }">Pretraga 🔍</router-link>
                </li>
                <li><Logout/></li>
                </ul>
              </div>
          </div>
      </div>
  </header>
  <div class="knjige-table">
        <table class="center">
            <thead>
                <tr>
                    <th>Naslov</th>
                    <th>ISBN</th>
                    <th>Broj Strana</th>
                    <th>Datum Objavljivanja</th>
                    <th>Opis</th>
                    <th>Ocena</th>
                    <th>Autor</th>
                    <th>Zanr</th>
                    <th>Ažuriraj knjigu</th>
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
                    <td>{{ knjiga.autor.ime }}</td>
                    <td>{{ knjiga.zanr?.naziv }}</td>
                    <td>
                        <button @click="azurirajKnjigu(knjiga.id)">Ažuriraj knjigu</button>
                    </td>
                </tr>
            </tbody>
        </table>
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
            korisnikId: null
        };
    },
    mounted() {
        this.korisnikId = this.$route.query.korisnikId;
        // Retrieve the author's books here or in another lifecycle hook
        this.getKnjige();
     },
    methods: {
        getKnjige() {
            const id = this.korisnikId;
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

    }
  };
  </script>
  