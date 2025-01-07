<template>
<div class="con_stat">
  <div class="part_cat">
    <img 
        v-if="isPetting" 
        src="../assets/palm.png" 
        class="hand" 
        :style="{ top: `${handPosition.y}px`, left: `${handPosition.x}px` }" 
        alt="Hand"
      >
    <img 
      src="../assets/head.png" 
      class="head" 
      @mousemove="moveHand" 
      @mouseleave="stopPetting" 
      @mouseenter="startPetting('head')" 
      alt="Cat's Head"
    >
    <img 
      src="../assets/body.png" 
      class="body" 
      @mousemove="moveHand" 
      @mouseleave="stopPetting" 
      @mouseenter="startPetting('body')" 
      alt="Cat's Body"
    >
    <img 
      src="../assets/tail.png" 
      class="tail" 
      @mousemove="moveHand" 
      @mouseleave="stopPetting" 
      @mouseenter="startPetting('tail')" 
      alt="Cat's Tail"
    >
  </div>
</div>
  </template>
  
  <script>

export default {
  data() {
    return {
      isPetting: false, 
      handPosition: { x: -30, y: 0 },
    };
  },
  methods: {
    startPetting(part) {
      this.isPetting = true;
      this.currentPart = part; 
    },
    stopPetting() {
      this.isPetting = false; 
      this.currentPart = null; 
    },
    moveHand(event) {
      if (this.isPetting) {
        const container = document.querySelector('.part_cat'); 
        const rect = container.getBoundingClientRect(); 
        this.handPosition = {
          x: event.clientX - rect.left - 25, 
          y: event.clientY - rect.top - 25  
        };
      }
    }
  }
};
  </script>
  
  <style scoped>
.con_stat {
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 400px;
  background-color: inherit;
}

.part_cat {
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.head, .body, .tail {
  max-width: 200px;
  height: auto;
  cursor: pointer;
}
.head {
  z-index: 10;
}
.body {
  margin-top: -42px;
  z-index: 9;
}

.tail {
  margin-top: -200px;
  margin-right: -120px;
}

.hand {
  position: absolute;
  width: 50px;
  height: auto;
  pointer-events: none; /* Ладонь не мешает движению */
  z-index: 11;
}
  </style>
  