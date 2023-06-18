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
                    <li id="pocetna"><a href="/homeAdministrator">Početna</a></li>
                    <li id="pretraga"><a href="/pretragaAdministrator">Pretraga 🔍</a></li>
                    <li><Logout/></li>
                  </ul>
              </div>
          </div>
      </div>
  </header>
  <h3 style="text-align: center; padding-top: 20px; padding-bottom: 10px;">Knjige</h3>
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
            <td>{{ knjiga.zanr.naziv }}</td>
            </tr>
        </tbody>
        </table>
    </div>
    <h3 style="text-align: center; padding-top: 20px; padding-bottom: 10px;">Korisnici</h3>
    <div class="korisnici-table">
        <table class="center">
        <thead>
            <tr>
            <th>Ime</th>
            <th>Prezime</th>
            <th>Korisnicko Ime</th>
            <th>Datum Rodjenja</th>
            <th>Opis</th>
            <th>Uloga</th>
            <!-- Add more table headers for other properties -->
            </tr>
        </thead>
        <tbody>
            <tr v-for="korisnik in korisnici" :key="korisnik.id">
            <td>{{ korisnik.ime }}</td>
            <td>{{ korisnik.prezime }}</td>
            <td>{{ korisnik.korisnickoIme }}</td>
            <td>{{ korisnik.datumRodjenja }}</td>
            <td>{{ korisnik.opis }}</td>
            <td>{{ korisnik.uloga }}</td>
            <!-- Add more table cells for other properties -->
            </tr>
        </tbody>
        </table>
    </div>
</template>

<script>
import axios from 'axios';
import Logout from '@/components/Logout.vue';

export default {
  name: 'HomeAdministratorView',
  components: {
    Logout
  },
  data() {
    return {
      knjige: [],
      korisnici: []
    };
  },
  mounted() {
    this.getKnjige();
    this.getKorisnici();
  },
  methods: {
    getKnjige() {
      axios.get('http://localhost:9090/api/knjige', { withCredentials: true })
        .then((response) => {
          this.knjige = response.data;
        })
        .catch((error) => {
          console.log(error);
          alert('Failed to fetch knjige');
        });
    },
    getKorisnici() {
      axios.get('http://localhost:9090/api/korisnici', { withCredentials: true })
        .then((response) => {
          this.korisnici = response.data;
        })
        .catch((error) => {
          console.log(error);
          alert('Failed to fetch korisnici');
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
      background-color: rgb(0, 255, 173);
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
  
  .pre-log {
      display: flex;
      justify-content: center;
      align-items: center;
  }
  
  .pre-reg {
      display: flex;
      justify-content: center;
      align-items: center;
  }
  
  h1 {
      font-size: 60px;
      margin-left: 15px;
      font-weight: bold;
  }
  
  h2 {
      font-size: 60px;
      margin-left:15px;
      font-weight: bold;
  }
  
  .container2 button{
      font-weight: bold;
      font-style: 8px;
      font-size: 15px;
      color: rgb(36, 136, 102);
  }
  
  .row4 input {
      border: 2px solid rgb(127, 255, 212);
      border-radius: 8px;
      margin-top: 20px;
      margin-bottom: 20px;
      text-align: center;
  }
  
  .row5 input {
      border: 2px solid rgb(127, 255, 212);
      border-radius: 8px;
      margin-bottom: 20px;
      text-align: center;
  }
  
  .row6 button{
      background-color:aquamarine;
      border: none;
      color: white;
      padding: 16px 32px;
      text-decoration: none;
      margin: 4px 2px;
      cursor: pointer;
      margin-bottom: 20px;
      margin-left: 8px;
      border-radius: 8px;
  }
  
  .row8 input {
      border: 2px solid rgb(127, 255, 212);
      border-radius: 8px;
      margin-bottom: 20px;
      margin-left: 25px;
      text-align: center;
  }
  
  .row9 input {
      border: 2px solid rgb(127, 255, 212);
      border-radius: 8px;
      margin-bottom: 20px;
      margin-left: 25px;
      text-align: center;
  }
  
  .row10 input {
      border: 2px solid rgb(127, 255, 212);
      border-radius: 8px;
      margin-bottom: 20px;
      margin-left: 25px;
      text-align: center;
  }
  
  .row11 input {
      border: 2px solid rgb(127, 255, 212);
      border-radius: 8px;
      margin-bottom: 20px;
      margin-left: 25px;
      text-align: center;
  }
  
  .row12 input {
      border: 2px solid rgb(127, 255, 212);
      border-radius: 8px;
      margin-bottom: 20px;
      margin-left: 25px;
      text-align: center;
  }
  
  .row13 input {
      border: 2px solid rgb(127, 255, 212);
      border-radius: 8px;
      margin-bottom: 20px;
      margin-left: 25px;
      text-align: center;
  }
  
  .row14 button{
      background-color:aquamarine;
      border: none;
      color: white;
      padding: 16px 32px;
      text-decoration: none;
      margin: 4px 2px;
      cursor: pointer;
      margin-bottom: 20px;
      margin-left: 28px;
      border-radius: 8px;
  }
  
  footer {
      text-align: center;
      margin-top: 40px;
      font-weight: bold;
  }
  .knjige-table,
.korisnici-table {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 20px;
}

.knjige-table th,
.knjige-table td,
.korisnici-table th,
.korisnici-table td {
  padding: 8px;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

.knjige-table th,
.korisnici-table th {
  background-color: #f2f2f2;
  font-weight: bold;
}

.knjige-table tbody tr:hover,
.korisnici-table tbody tr:hover {
  background-color: #f5f5f5;
}
table.center {
    margin-left:auto; 
    margin-right:auto;
  }
  </style>
  