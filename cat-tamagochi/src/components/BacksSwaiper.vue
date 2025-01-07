<script setup>
import Some from './Some.vue';
</script>

<template>
   <div class="cat">
    <Some />
  </div>
  <div class="stat">
    <div class="con_stat">
      <div class="stat_fish">
        <div class="update_palm" :style="{ width: food + '%' }"></div>
      </div>
      <img class="fish_img" src="../assets/fish.png">
    </div>
    <div class="con_stat">
      <div class="stat_palm">
        <div class="update_palm" :style="{ width: palm + '%' }"></div>
      </div>
      <img class="palm_img" src="../assets/palmstat.png">
    </div>
    <div class="fish">
    <img v-if="!isPopped" src="../assets/fish.png" alt="Fish" class="fish_img" :class="{ animated: isMoving, popped: isPopped }"
    @click="moveFish">
  </div>
  </div>
  <div class="backs swiper-container">
    <button class="nav-button left">&#10094;</button>
    <button class="nav-button right">&#10095;</button>
    <div class="swiper-wrapper">
      <div v-for="(image, index) in images" :key="index" class="swiper-slide">
        <img :src="image" :alt="`Slide ${index + 1}`" />
      </div>
    </div>
  </div>
</template>

<script>
import Swiper from 'swiper/bundle';
import 'swiper/swiper-bundle.css';
import axios from 'axios';

export default {
  data() {
    return {
      isMoving: false, // Флаг анимации движения
      isPopped: false,  // Флаг лопнувшей рыбки
      stats: {},
      palm: 0,
      food: 0,
      loading: true,
      error: null,
      images: [
        'src/assets/1.jpg',
        'src/assets/2.jpg',
        'src/assets/3.jpg',
      ],
    };
  },
  mounted() {
      this.fetchStats();
      new Swiper('.backs', {
      //   freeMode: true,
        centeredSlides: true,
        speed: 900,
        direction: 'horizontal',
        mousewheel: true,
        slidesPerView: 1,
      //   loop: true,
        navigation: {
          nextEl: '.right',
          prevEl: '.left',
          clickable: true,
        },
      }); 
      this.interval = setInterval(this.fetchStats, 3000); // Обновление каждую минуту
},
methods: {
  moveFish() {
      if (this.isMoving || this.isPopped) return;
      this.isMoving = true;
      setTimeout(() => {
        this.isMoving = false;
        this.isPopped = true;
        if (this.food >= 98) {
          this.food = 100;
        } else {
          this.food += 2;
        }
        setTimeout(() => {
          this.isPopped = false;
        }, 1000);
      }, 2000);
    },
    async takeScreenshot() {
      try {
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
    async fetchStats() {
        try {
          const response = await axios.get('https://virtserver.swaggerhub.com/polanap/kotiki/1.0.0/cat/stat?id=12');
          this.palm = response.data['happy-degree'];
          this.palm *= 100;
          this.food = response.data['satiety-degree'];
          this.food *= 100;
        } catch (error) {
          this.error = error.response?.data?.message || error.message || 'Неизвестная ошибка';
        }
      },
    },
};
</script>

<style scoped>
.swiper-container {
  position: relative;
  width: 100%;
  height: 480px;
  overflow: hidden;
  display: flex;
  align-items: center;
}

.swiper-wrapper {
  display: flex;
  transition: transform 0.3s ease-in-out;
}

.swiper-slide {
  flex-shrink: 0;
  /* width: 100px;
  height: 80px; */
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}

.swiper-slide img {
  /* width: 100%; */
  height: 100%;
  width: 925px;
  object-fit: cover;
  border-radius: 4px;
}

.nav-button {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  background-color: transparent;
  color: #9E9EE2;
  font-size: 80px;
  border: none;
  padding: 15px;
  cursor: pointer;
  z-index: 10;
}

.nav-button.left {
  left: 1%;
}

.nav-button.right {
  right: 1%;
}

.nav-button:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}

.stat_fish {
  width: 130px;
  height: 30px;
  background-color: #E6E6FA;
  z-index: 2;
  border-radius: 8px;
  border: 2px solid #9E9EE2;
}
.stat_palm {
  width: 130px;
  height: 30px;
  background-color: #E6E6FA;
  z-index: 2;
  border-radius: 8px;
  border: 2px solid #9E9EE2;
  margin-top: 40px;
}

.update_palm {
  max-width: 130px;
  height: 30px;
  background-color: #9E9EE2;
  z-index: 3;
  border-radius: 6px;
  /* border: 2px solid #7b7be5; */
}

.palm_img{
  margin-top: 40px;
}

.fish {
  width: 80px;
  height: 80px;
  background-color: #E6E6FA;
  z-index: 2;
  position: absolute;
  border-radius: 40px;
  left: 12%;
  top: 50%;
  border: 2px solid #9E9EE2;
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 230;
}

.fish img {
  width:70%;
  height:70%;
}

.fish_img.animated {
  transform: translate(600%, 80%); /* Двигаем рыбку к коту */
  z-index: 23;
}

.con_stat {
  display: flex;
  align-items: center; 
  gap: 10px; 
  position: absolute;
  left: 12%;
  top: 8%;
  z-index: 2;
}

.con_stat img {
  height: 30px;
}

.stat {
  position: absolute;
  width: 150px;
  height: 480px;
  left: 18%;
}
</style>
