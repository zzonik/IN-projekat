<template>
 <header>
    <div class="pre-header">
        <div class="container1">
            <div class="row1">
                <div class="imageheader1">
                    <div class="col-md-4">
                        <img src="images/Header left-right.png">
                    </div>
                </div>  
                <div class="imagelogo">
                    <div class="col-md-4">
                        <img src="images/Logo.png">
                    </div>
                </div> 
                <div class="imageheader2">
                    <div class="col-md-4">
                        <img src="images/Header left-right.png">
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
                    <li id="pretraga"><a href="#">Pretraga</a></li>
                    <li id="prijava"><a href="#">Log in</a></li>
                    <li id="zahtev"><a href="#">Zahtev za aktivaciju naloga</a></li>
                </ul>
            </div>
        </div>
    </div>
</header>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
