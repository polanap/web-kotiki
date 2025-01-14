<template>
    <div class="swiper-container looks ">
      <button class="nav-button prev">&#10094;</button>
      <button class="nav-button next">&#10095;</button>
      <div class="swiper-wrapper">
        <div v-for="(image, index) in images" :key="index" class="swiper-slide">
          <img :src="image" :alt="`Slide ${index + 1}`" @click="openModal()" />
        </div>
      </div>
    </div>
    <LookModal
      v-if="modalVisible"
      :isVisible="modalVisible"
      @close="modalVisible = false"
    />
</template>

<script>
import Swiper from 'swiper/bundle';
import 'swiper/swiper-bundle.css';
import LookModal from './LookModal.vue';
export default {
  components: {
    LookModal
},
data() {
  return {
    images: [
      'src/assets/1.jpg',
      'src/assets/2.jpg',
      'src/assets/3.jpg',
      'src/assets/1.jpg',
      'src/assets/2.jpg',
      'src/assets/3.jpg',
    ],
    modalVisible: false,
  };
},
mounted() {
    new Swiper('.looks', {
      freeMode: true,
      speed: 700,
      direction: 'horizontal',
      mousewheel: true,
      slidesPerView: 5,
      spaceBetween: 0,
      // loop: true,
      navigation: {
        nextEl: '.next',
        prevEl: '.prev',
        clickable: true,
      },
    });
},
methods: {
  openModal() {
      this.modalVisible = true;
    },
}
};
</script>  

<style scoped>
  .swiper-container {
    position: relative;
    width: 70%;
    height: 105px;
    overflow: hidden;
    display: flex;
    align-items: center;
  }
  
  .swiper-wrapper {
    display: flex; 
  }
  
  .swiper-slide {
    flex-shrink: 0;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  
  .swiper-slide img {
    height: 100%;
    width: 150px;
    object-fit: cover;
    border-radius: 4px;
    cursor: pointer;
    z-index: 300;
  }
  .nav-button {
    position: absolute;
    transform: translateY(-50%);
    background-color: transparent;
    color: #9E9EE2;
    font-size: 80px;
    border: none;
    padding: 15px;
    cursor: pointer;
    z-index: 10;
    top: 50%;
  }
  .nav-button.prev {
left: 1%;
}

.nav-button.next {
right: 1%;
}
  
  .nav-button:disabled {
    opacity: 0.5;
    cursor: not-allowed;
  }
</style>
  