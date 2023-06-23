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
                                :to="{ path: '/homeCitalac', query: { citalacId: citalacId } }">Početna</router-link>
                        </li>
                        <li id="azuriraj">
                            <router-link :to="{ path: '/azurirajCitaoca', query: { citalacId: citalacId } }">Azuriraj
                                profil</router-link>
                        </li>
                        <li id="pretraga">
                            <router-link :to="{ path: '/pretragaCitalac', query: { citalacId: citalacId } }">Pretraga
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

    <main>
        <div class="container">
            <h1 style="text-align: center; padding-top: 20px; padding-bottom: 10px; font-weight: bold; font-size: 40px;">
                Moje primarne police
            </h1>
            <table class="table2">
                <thead>
                    <tr>
                        <th
                            style="text-align: center; padding-top: 20px; padding-bottom: 10px; font-weight: bold; font-size: 30px; background-color: rgb(137, 149, 146);">
                            Naziv primarne police</th>
                            
                        <th
                            style="text-align: center; padding-top: 20px; padding-bottom: 10px; font-weight: bold; font-size: 30px; background-color: rgb(137, 149, 146);">
                            Stavke police</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="polica in police2" :key="polica.id">
                        <td style="text-align: center; padding-top: 20px; padding-bottom: 10px; font-size: 20px;">
                        {{ polica.naziv }}
                        </td>
                        <td>
                            <select @change="redirectToKnjigaPregled" v-model="selectedStavka">
                                <option disabled selected>Izaberi knjigu sa police</option>
                                <option v-for="stavka in polica.stavkePolica" :key="stavka.id" :value="stavka">
                                {{ stavka.knjiga.naslov }}
                                </option>
                            </select>
                        </td>
                    </tr>
                </tbody>
            </table>


            <h3 style="text-align: center; padding-top: 20px; padding-bottom: 10px; font-weight: bold; font-size: 40px;">
                Moje police
            </h3>
            <table class="table">
                <thead>
                    <tr>
                        <th style="text-align: center; padding-top: 20px; padding-bottom: 10px; font-weight: bold; font-size: 30px; background-color: rgb(137, 149, 146);">
                            Naziv police</th>
                        <th style="text-align: center; padding-top: 20px; padding-bottom: 10px; font-weight: bold; font-size: 30px; background-color: rgb(137, 149, 146);">
                            Stavke police</th>
                        <th style="text-align: center; padding-top: 20px; padding-bottom: 10px; font-weight: bold; font-size: 30px; background-color: rgb(137, 149, 146);">
                            Akcije</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="polica in police" :key="polica.id">
                    <td style="text-align: center; padding-top: 20px; padding-bottom: 10px; font-size: 20px;">
                        {{ polica.naziv }}
                    </td>
                    <td>
                        <select @change="redirectToKnjigaPregled" v-model="selectedStavka">
                            <option disabled selected>Izaberi knjigu sa police</option>
                            <option v-for="stavka in polica.stavkePolica" :key="stavka.id" :value="stavka">
                            {{ stavka.knjiga.naslov }}
                            </option>
                        </select>
                    </td>
                    <td>
                        <button @click.once="obrisiPolica(polica.id)">Obriši policu</button>
                    </td>
                    </tr>
                </tbody>
            </table>


            <h2 style="text-align: center; padding-top: 20px; padding-bottom: 10px; font-weight: bold; font-size: 40px;">
                Dodaj novu policu:
            </h2>
            <form @submit.prevent="addPolica">
                <div class="form-group">
                    <h4
                        style="text-align: center; padding-top: 20px; padding-bottom: 10px; font-weight: bold; font-size: 20px;">
                        Naziv:</h4>
                    <input type="text" v-model="novaPolicaNaziv" placeholder="Unesite naziv police" name="nazivPolice">
                </div>
                <div class="dugme1">
                    <button @click.once="dodajPolicu">Dodaj novu policu</button>
                </div>
            </form>
        </div>
    </main>

    <footer>
        <p>&copy; 2023 BookBuddy. Sva prava zadržana.</p>
    </footer>
</template>

<script>
import Logout from '@/components/Logout.vue';
import axios from 'axios';

export default {
    name: 'HomeCitalacView',
    components: {
        Logout
    },
    data() {
    return {
      citalacId: null,
      police: [],
      police2: []
    };
  },
    mounted() {
        this.citalacId = this.$route.query.citalacId;
        this.getPolice();
        this.getPolice2();
    },
    methods: {
        getPolice() {
            const id = this.citalacId;
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
                    this.police = response.data;
                })
                .catch((error) => {
                    console.log(error);
                    alert("Failed to fetch police");
                });
        },
        getPolice2() {
            const id = this.citalacId;
            axios
            .get(`http://localhost:9090/api/citalac/${id}/police/primarne`, {
                withCredentials: true,
                headers: {
                    'Cache-Control': 'no-cache',
                    'Pragma': 'no-cache',
                    'Expires': '0',
                },
                })
                .then((response) => {
                    this.police2 = response.data;
                })
                .catch((error) => {
                    console.log(error);
                    alert("Failed to fetch primarne police");
                });
        },
        addPolica() {
            if (this.novaPolicaNaziv.trim() !== "") {
                const novaPolica = { naziv: this.novaPolicaNaziv };


                axios
                    .post(`http://localhost:9090/api/citalac/police-add`, novaPolica, { withCredentials: true })
                    .then((response) => {
                        console.log(response);
                        this.getPolice(); // Osvježavanje podataka o policama
                        this.getPolice2(); // Osvježavanje podataka o primarnim policama
                        console.log("Polica uspešno dodata");
                        this.novaPolicaNaziv = ''; // Resetiranje forme nakon dodavanja
                    })
                    .catch((error) => {
                        console.log(error);
                        // Greška prilikom dodavanja police
                    });
            }
        },
        obrisiPolica(policaId) {
            axios
                .delete(`http://localhost:9090/api/citalac/polica-remove/${policaId}`, { withCredentials: true })
                .then((response) => {
                    this.getPolice(); // Osvježavanje podataka o policama
                    this.getPolice2(); // Osvježavanje podataka o primarnim policama
                    console.log("Shelf deleted seccessfully");
                })
                .catch((error) => {
                    console.log(error);
                    alert("Failed to delete the shelf");
                });
        },
        redirectToKnjigaPregled() {
            if (this.selectedStavka) {
            const knjigaId = this.selectedStavka.knjiga.id;
            this.$router.push(`/knjigaPregled/${knjigaId}`);
            }
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
    justify-content: space-around;
    list-style: none;
    padding: 0;
}

a {
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


.container2 button {
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

.row6 button {
    background-color: aquamarine;
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

.row14 button {
    background-color: aquamarine;
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

table,
th,
td {
    border-top: 2px solid black;
    ;
    border-left: 2px solid black;
    ;
    border-right: 2px solid black;
    ;
    border-bottom: 2px solid black;
    ;
}


label {
    font-size: 25px;
    margin-left: 15px;
    font-weight: bold;
}

.table1,
.table2 {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
}

.table1 th,
.table1 td,
.table2 th,
.table2 td {
    padding: 8px;
    text-align: ce;
    border-bottom: 1px solid #ddd;
}

.table1 th,
.table2 th {
    background-color: #f2f2f2;
    font-weight: bold;
}

.form-group input {
    border: 1px solid black;
    border-radius: 8px;
    margin-bottom: 20px;
    margin-left: 25px;
    text-align: center;
}

.table1 tbody tr:hover {
    background-color: #f5f5f5;
}

.table2 tbody tr:hover {
    background-color: #f5f5f5;
}

h3 {
    text-align: center;
}

.dugme1 button {
    background-color: aquamarine;
    padding: 8px 14px;
    text-decoration: none;
    cursor: pointer;
    border-radius: 8px;
    color: black;
    margin-top: 15px;
    margin-left: 30px;
}

.dugme2 button {
    background-color: aquamarine;
    padding: 8px 14px;
    text-decoration: none;
    cursor: pointer;
    border-radius: 8px;
    color: black;
    margin-top: 15px;
    margin-left: 30px;
}

.table button {
    background-color: aquamarine;
    padding: 8px 14px;
    text-decoration: none;
    cursor: pointer;
    border-radius: 8px;
    color: black;
    margin-top: 15px;
    margin-left: 30px;

}

.table2 button {
    background-color: aquamarine;
    padding: 8px 14px;
    text-decoration: none;
    cursor: pointer;
    border-radius: 8px;
    color: black;
    margin-top: 15px;
    margin-left: 30px;
}

.nazivKnjige{
    color: black;
}

select {
    height: 30px;
    width: 230px;
}

footer {
    text-align: center;
    margin-top: 40px;
    font-weight: bold;
}</style>
  