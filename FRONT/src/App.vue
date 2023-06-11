<template>
 <header>
    <div class="pre-header">
        <div class="container1">
            <div class="row1">
                <div class="imageheader1">
                    <div class="col-md-4">
                        <img src="../src/assets/headerLeftRight.png">
                    </div>
                </div>  
                <div class="imagelogo">
                    <div class="col-md-4">
                        <img src="../src/assets/logo.png">
                    </div>
                </div> 
                <div class="imageheader2">
                    <div class="col-md-4">
                        <img src="../src/assets/headerLeftRight.png">
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
    font-style: 8px;
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

.containter2 {
    margin-top: 10px;
    display: inline-block;
    
}

footer {
    text-align: center;
    margin-top: 40px;
    font-weight: bold;
}
</style>
