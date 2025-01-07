<script setup>
 import HeaderProfile from './HeaderProfile.vue';
</script>

<template>
    <HeaderProfile />
    <div class="profile-container">
  
      <div class="profile-content">
        <div class="profile-image">
          <img :src="profileImage" alt="Profile Image" />
        </div>
        <div class="profile-info">
          <h2>Пользователь: {{ fullName }}</h2>
          <h2>День рождения: {{ formattedBirthDate }}</h2>
          <h2>О себе: {{ aboutMe }}</h2>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: "ProfilePage",
    data() {
      return {
        profile: null, 
        profileImage: "src/assets/cat.jpg",
      };
    },
    computed: {
      fullName() {
        return `${this.profile?.firstname || ""} ${this.profile?.lastname || ""}`;
      },
      formattedBirthDate() {
        if (!this.profile?.birth) return "Не указано";
        const date = new Date(this.profile.birth);
        return date.toLocaleDateString("ru-RU", {
          day: "2-digit",
          month: "2-digit",
          year: "numeric",
        });
      },
      aboutMe() {
        return this.profile?.["about-me"] || "Нет информации";
      },
    },
    methods: {
      async fetchProfile() {
        try {
          const response = await fetch(
            "https://virtserver.swaggerhub.com/polanap/kotiki/1.0.0/profile?user_id=12"
          );
          if (!response.ok) {
            throw new Error("Ошибка загрузки профиля");
          }
          this.profile = await response.json();
        } catch (error) {
          console.error("Ошибка:", error);
        }
      },
    },
    mounted() {
      this.fetchProfile();
    },
  };
  </script>
  
  <style scoped>
  .profile-container {
    font-family: Arial, sans-serif;
    background-color: #e6e6fa; 
    padding: 20px;
    width: 100%;
  }
  
  .profile-content {
    display: flex;
    align-items: center;
    margin-top: 13%;
    margin-left: 13%;
  }
  
  .profile-image img {
    width: 440px;
    height: 440px;
    border-radius: 50%;
    object-fit: cover;

  }
  
  .profile-info {
    margin-left: 8%;
    margin-top: 70px;
  }
  
  .profile-info h2 {
    font-size: 24px;
    font-weight: bold;
    color: #7e7eb9;
    margin-bottom: 90px;
  }
  </style>
  