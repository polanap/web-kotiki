<template>
    <div class="modal" v-if="isVisible" @click.self="closeModal">
    <h1>Выберите косметик:</h1>
    <div class="image-container">
    <div 
      class="image-item" 
      v-for="(image, index) in images" 
      :key="index"
      :class="{ selected: selectedImage === index }"
      @click="selectImage(index)"
    >
      <img :src="image" :alt="`Slide ${index + 1}`" />
    </div>
  </div>
</div>
  </template>
  
  <script>
  export default {
    props: {
      isVisible: {
        type: Boolean,
        required: true,
      },
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
      'src/assets/1.jpg',
      'src/assets/2.jpg',
      'src/assets/3.jpg',
      'src/assets/1.jpg',
      'src/assets/2.jpg',
      'src/assets/3.jpg',
    ],
    selectedImage: null,
        }
    },
    methods: {
      closeModal() {
        this.$emit('close');
      },
      selectImage(index) {
      this.selectedImage = index;
    },
    }
};
  </script>
  
  <style scoped>
  .modal {
    display: flex;
    justify-content: center;
    align-items: center;
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(59, 29, 56, 0.5);
    z-index: 1000;
    color: #ae86d1;
  }

  h1 {
    position: absolute;
    top: 1%;
    font-size: 30px;
    background-color: rgb(246, 215, 250, 0.9);
    padding: 15px 15px;
    border-radius: 8px;
  }
  .image-container {
  display: grid;
  grid-template-columns: repeat(3, 150px); 
  gap: 10px;
  max-height: 400px; 
  overflow-y: auto; 
  padding: 10px;
  margin-top: 6%;
}

.image-item {
  width: 150px;
  height: 150px;
  cursor: pointer;
  transition: transform 0.2s;
  display: flex;
  justify-content: center;
  align-items: center;
}

.image-item img {
  max-width: 100%;
  max-height: 100%;
  object-fit: cover;
  border: 2px solid transparent; 
  border-radius: 8px;
}

.image-item:hover {
  transform: scale(1.05);
}

.image-item.selected img {
  border-color: #9E9EE2; 
}

  </style>
  