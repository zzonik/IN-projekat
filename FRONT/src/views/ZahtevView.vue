<template>
  <div class="containerB">
    <h3 style="text-align: center; font-size: 17px; font-weight: bold;">Zahtev za aktivaciju autora</h3>
    <form @submit="submitZahtev">
      <div class="form-group">
        <label style="text-align: center; font-size: 12px;">Email:</label>
        <input type="text" v-model="zahtev.mail" required />
      </div>
      <div class="form-group">
        <label style="text-align: center; font-size: 12px;">Broj Telefona:</label>
        <input type="text" v-model="zahtev.brojTelefona" required />
      </div>
      <div class="form-group">
        <label style="text-align: center; font-size: 12px;">Dodatna Poruka:</label>
        <textarea style="text-align: center; font-size: 10px; margin-left: 50px;" v-model="zahtev.dodatnaPoruka"></textarea>
      </div>
      <button style="margin-left: 40px;" type="submit">Podnesi zahtev</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'ZahtevView',
  name: 'ZahtevView',
  props: ['id'], // Add this line to receive the prop
  data() {
    return {
      zahtev: {
        mail: '',
        brojTelefona: '',
        dodatnaPoruka: '',
        korisnik_id: this.id // Use the prop value to set korisnik_id
      }
    };
  },
  methods: {
    submitZahtev(event) {
      event.preventDefault();

      axios
        .post('http://localhost:9090/api/zahtev-create', this.zahtev, { withCredentials: true })
        .then((response) => {
          console.log(response.data);
          alert("Zahtev poslat");
        })
        .catch((error) => {
          console.error(error);
          // Handle error
        });
    }
  }
};

</script>

<style>

.containerB {
  display: flex;
  flex-direction: column;
  align-items: center;
}

h3 {
  margin-bottom: 10px;
}

.form-group {
  margin-bottom: 5px;
}

label {
  display: block;
}

input,
textarea {
  width: 50%;
}

button[type="submit"] {
  margin-top: 5px;

}
</style>