<script setup>
import LooksSwaiper from './LooksSwaiper.vue';
import BacksSwaiper from './BacksSwaiper.vue';
import HeaderMain from './HeaderMain.vue';
</script>

<template>
  <HeaderMain />
  <div class="cont1">
  <div ref="tamagotchiDiv" class="screenShot">
    <BacksSwaiper />
  </div>
  </div>
  <div class="cont2">
    <LooksSwaiper />
  </div>
  <div class="camera" @click="takeScreenshot">
    <img src="../assets/camera.png">
  </div>
</template>

<script>
import html2canvas from "html2canvas";

export default {
name: "Kitty",
setup() {
    const route = useRoute();

    // Показываем навигацию только на главной странице
    const showNavigation = computed(() => route.path === '/');

    return { showNavigation };
  },
methods: {
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
