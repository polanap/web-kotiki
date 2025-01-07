<script setup>
import LooksSwaiper from './components/LooksSwaiper.vue';
import BacksSwaiper from './components/BacksSwaiper.vue';
import HeaderMain from './components/Header.vue';
import Login from './components/Login.vue';
import Profile from './components/ProfilePage.vue';
</script>

<template>
  <Profile />
  <Login />
  <!-- <HeaderMain /> -->
  <div class="cont1">
  <div ref="tamagotchiDiv" class="screenShot">
    <BacksSwaiper />
  </div>
  </div>
  <div class="cont2">
    <LooksSwaiper />
  </div>
  <div class="camera" @click="takeScreenshot">
    <img src="./assets/camera.png">
  </div>
</template>

<script>
import html2canvas from "html2canvas";

export default {
name: "HomePage",
  data() {
    return {
      isLink: false,
    };
  },
methods: {
  setLink(value) {
      this.isLink = value;
    },
    async takeScreenshot() {
      try {
        // Найти div для скриншота
        const element = this.$refs.tamagotchiDiv;
        if (!element) {
          console.error("Элемент для скриншота не найден");
          return;
        }

        const canvas = await html2canvas(element);
        const dataURL = canvas.toDataURL("image/png");
        const link = document.createElement("a");
        link.href = dataURL;
        link.download = "tamagotchi-screenshot.png";
        link.click();
      } catch (error) {
        console.error("Ошибка при создании скриншота:", error);
      }
    },
  }
};
</script>

<style scoped>
/* *, * {
  width: 100vw;
  height: 100vh;
  background-color: #9E9EE2;
} */
.cont1{
  display: flex;
  align-items: center;
  justify-content: center;
  padding-bottom: 40px;
  margin-top: 150px;
}

.screenShot {
  width: 80%;
  display: flex;
  align-items: center;
  justify-content: center;
}
.cont2{
  display: flex;
  align-items: center;
  justify-content: center;
}

.camera {
  position: absolute;
  width: 50px;
  height: 50px;
  top: 20%;
  left: 78%;
  z-index: 30;
  background: white;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 8px;
}

.camera img {
  width: 90%;
  height: 90%;
}
</style>
