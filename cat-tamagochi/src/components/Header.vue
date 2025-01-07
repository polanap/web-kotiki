<template>
    <header class="header">
    <div class="container">
        <div class="header__inner">
            <nav class="nav">
                <!-- <router-link class="nav__link" @click="setLink(true)" to="/link">Пригласить друга</router-link>
                <router-view :key="$route.fullPath"></router-view> -->
                <div class="nav__link" href="#">Мой аккаунт</div>
                <div class="nav__link" href="#">Мои друзья</div>
                <!-- <div class="nav__link" href="#">Пригласить друга</div> -->
            </nav>
            <div class="header__logo">
                <img src="../assets/cat.jpg" style="height: 40px; margin-bottom: -8px" alt="Logo" />
                Котомания
            </div>
        </div>
    </div>
</header>
</template>  

<style>
.header {
    width: 100%;
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    z-index: 1000;
}

.header__inner {
    height: 110px;
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.header__logo {
    width: 20%;
    font-size: 30px;
    font-weight: bold;
    color: #9E9EE2;
}

.header__logo img {
    border-radius: 20px;
}

/* Nav */
.nav {
    font-size: 18px;
    vertical-align: top;
    display: inline-block;
    text-transform: uppercase;
    margin-left: 40px;
    /* margin-right: 10%; */
}

.nav__link {
    display: inline-block;
    vertical-align: top;
    margin: 0 25px;
    position: relative;

    color: #9E9EE2;
    text-decoration: none;
    font-weight: bold;

    transition: color .1s linear;
}

.nav__link:after {
    content: "";
    display: block;
    width: 100%;
    height: 3px;

    background-color: #da70d6;
    opacity: 0;

    position: absolute;
    top: 100%;
    left: 0;
    z-index: 1;

    transition: opacity .1s linear;
}

.nav__link.active {
    color: #da70d6;
}

</style>
<script>
</script>