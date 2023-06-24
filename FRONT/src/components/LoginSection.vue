<template>
  <div class="pre-log">
    <div class="container3">
      <section class="login-section">
        <form>
          <div class="row3">
            <h1>Log In</h1>
          </div>
          <div class="row4">
            <input type="email" placeholder="Unesite Vas e-mail" v-model="mail">
          </div>
          <div class="row5">
            <input type="password" placeholder="Unesite Vasu lozinku" v-model="lozinka">
          </div>
          <div class="loginDugme">
            <button @click.prevent="submit">Log in</button>
          </div>
        </form>
      </section>
    </div>
  </div>
</template>
<script>
export default {
  name: 'LoginSection',
  data() {
    return {
      mail: '',
      lozinka: ''
    };
  },
  methods: {
    submit() {
      const payload = {
        mail: this.mail,
        lozinka: this.lozinka
      };

      fetch('http://localhost:9090/api/login', {
        method: 'POST',
        credentials: 'include',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
      })
        .then((res) => {
          console.log(res);
          if (res.ok) {
            return res.json();
          } else {
            throw new Error('Login failed');
          }
        })
        .then((data) => {
          console.log(data);
          localStorage.setItem('user', JSON.stringify(data));
          if (data.uloga === 'CITALAC') {
            this.$router.push(`/homeCitalac?citalacId=${data.id}`);
          } else if (data.uloga === 'AUTOR') {
            this.$router.push(`/homeAutor?autorId=${data.id}`);
          } else if (data.uloga === 'ADMINISTRATOR') {
            this.$router.push(`/homeAdministrator?adminId=${data.id}`);
          } else {
            this.$router.push('/');
          }
        })
        .catch((err) => {
          console.log(err);
          alert('Something went wrong!');
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

.pre-log {
    display: flex;
    justify-content: center;
    align-items: center;
}


h2 {
    font-size: 60px;
    margin-left:15px;
    font-weight: bold;
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

.loginDugme button{
    background-color:aquamarine;
    border: 1px solid black;
    color: black;
    padding: 16px 32px;
    text-decoration: none;
    cursor: pointer;
    margin-bottom: 20px;
    margin-left: 25px;
    border-radius: 8px;
}

.container3 input{
   width: 210px;
}

footer {
    text-align: center;
    margin-top: 40px;
    font-weight: bold;
}  </style>