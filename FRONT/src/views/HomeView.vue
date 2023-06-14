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
                    <li id="pocetna"><a href="#">Pocetna</a></li>
                    <li id="pretraga"><a href="/pretraga">Pretraga</a></li>
                    <li id="prijava"><a href="#">Log in</a></li>
                    <li id="zahtev"><a href="#">Zahtev za aktivaciju naloga</a></li>
                </ul>
            </div>
        </div>
    </div>
</header>

<logsection>
  <div class="pre-log">
      <div class="container3">
          <section class="login-section">
              <form>
                  <div class="row3">
                      <h1>Log In</h1>
                  </div>
                  <div class="row4">
                    <input type="email" placeholder="Unesite Vas e-mail">
                  </div>
                  <div class="row5">
                    <input type="lozinka" placeholder="Unesite Vasu lozinku">
                  </div>
                  <div class="row6">
                    <button type="submit">LOG IN</button>
                  </div>
              </form>
          </section>
      </div>
  </div>
</logsection>

<regsection>
    <div class="pre-reg">
        <div class="container4">
            <section class="regiter-section">
                <form>
                    <div class="row7">
                        <h2>Register</h2>
                    </div>
                    <div class="row8">
                      <input type="ime" placeholder="Unesite Vase ime">
                    </div>
                    <div class="row9">
                      <input type="prezime" placeholder="Unesite Vase prezime">
                    </div>
                    <div class="row10">
                      <input type="korisnicko_ime" placeholder="Unesite Vas username">
                    </div>
                    <div class="row11">
                      <input type="email" placeholder="Unesite Vas e-mail">
                    </div>
                    <div class="row12">
                      <input type="loznika" placeholder="Unesite Vasu lozinku">
                    </div>
                    <div class="row13">
                      <input type="lozinkaripit" placeholder="Ponovite lozinku">
                    </div>
                    <div class="row14">
                      <button type="submit">REGISTER</button>
                    </div>
                </form>
            </section>
        </div>
    </div>
</regsection>


<footer>
    <p>&copy; 2023 BookBuddy. Sva prava zadržana.</p>
</footer>

</template>

<script>
export default {
  data() {
    return {
      firstName: '',
      lastName: '',
      username: '',
      email: '',
      password: '',
      confirmPassword: ''
    };
  },
  methods: {
    login() {
      // Your API call or backend logic here
      // You can access the email and password values using `this.email` and `this.password`

      // Example API call using axios:
      axios
        .post('/api/login', { email: this.email, password: this.password })
        .then(response => {
          // Handle the response here
        })
        .catch(error => {
          // Handle any error occurred during the API call
        });
    },
    register() {
      // Your API call or backend logic here
      // You can access the form field values using the data properties

      // Example API call using axios:
      axios
        .post('/api/register', {
          firstName: this.firstName,
          lastName: this.lastName,
          username: this.username,
          email: this.email,
          password: this.password,
          confirmPassword: this.confirmPassword
        })
        .then(res => {
          console.log(res);
        })
        .catch(error => {
          console.log(err);
          alert("Something went wrong!");
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
</style>
