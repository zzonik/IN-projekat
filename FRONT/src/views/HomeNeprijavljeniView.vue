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
                    <li id="pocetna"><a href="/">Prijavi se</a></li>
                    <li id="pocetna"><a href="/pregledNeprijavljeni">Pregled</a></li>
                    <li id="pretraga"><a href="/pretragaNeprijavljeni">Pretraga 🔍</a></li>
                </ul>
            </div>
        </div>
    </div>
</header>

<LoginSection />
<RegisterSection />

<footer>
    <p>&copy; 2023 BookBuddy. Sva prava zadržana.</p>
</footer>
</template>

<script>
import LoginSection from '@/components/LoginSection.vue';
import RegisterSection from '@/components/RegisterSection.vue';

export default {
  components: {
    LoginSection,
    RegisterSection
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

footer {
    text-align: center;
    margin-top: 40px;
    font-weight: bold;
}
</style>
