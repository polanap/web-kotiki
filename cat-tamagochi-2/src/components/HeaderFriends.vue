<template>
    <header class="header">
    <div class="container">
        <div class="header__inner">
            <nav class="nav">
                <router-link class="nav__link" to="/cat">Главная страница</router-link>
                <router-link class="nav__link" to="/profile">Мой профиль</router-link>
                <div
                class="nav__link"
                v-for="(item, index) in navItems"
                :key="index"
                :class="{ active: selectedIndex === index }"
                @click="selectDiv(index)"
                >
                {{ item }}
                </div>
                
            </nav>
            <div class="header__logo">
                <img src="../assets/cat.jpg" style="height: 40px; margin-bottom: -8px" alt="Logo" />
            </div>
        </div>
    </div>
</header>
</template>  

<style scoped>
.header {
    width: 100%;
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    z-index: 1000;
}

.container {
    width: 100%;
}

.header__inner {
    height: 110px;
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.header__logo {
    width: 5%;
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
    margin-left: 20px;
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

    cursor: pointer;
}

.nav__link.active {
  text-decoration: underline;
  color: #7373ca;
}

.nav__link:hover {
    color: #7373ca;
}

</style>
<script>
export default {
  data() {
    return {
      navItems: ["Исходящие заявки", "Входящие заявки", "Все пользователи"],
      selectedIndex: 2,
    };
  },
  methods: {
    selectDiv(index) {
      this.selectedIndex = index; 
    },
  },
};
</script>