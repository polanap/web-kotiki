<script setup>
import HeaderFriends from './HeaderFriends.vue';
</script>

<template>
<HeaderFriends />
<div class="app">
    <div class="search">
        <input v-model="searchQuery" type="text" placeholder="Поиск по имени пользователя..." />
    </div>
    <div class="users-list">
        <h3>{{ this.title }}</h3>
        <ul>
            <li v-for="user in filteredUsers" :key="user.id">
              <span>{{ user.name }}</span>
              <button 
                :disabled="user.isFriend"
                @click="sendFriendRequest(user)"
              >
              {{ user.isFriend ? 'Заявка отправлена' : 'Отправить заявку' }}
              </button>
            </li>
        </ul>
    </div>
</div>
</template>
        
        <script>
      
      export default {
        name: "LinkPage",
        data() {
      return {
        users: [
          { id: 1, name: 'Иван Иванов', isFriend: false },
          { id: 2, name: 'Алексей Петров', isFriend: false },
          { id: 3, name: 'Мария Смирнова', isFriend: false },
          { id: 4, name: 'Екатерина Петрова', isFriend: false },
          { id: 5, name: 'Дмитрий Кузнецов', isFriend: false }
        ],
        searchQuery: '',
        friends: [],
        title: 'Все пользователи',
        flags: [
          { id: 1, name: 'Входящие заявки', flag: false },
          { id: 2, name: 'Исходящие зявки', flag: false },
          { id: 3, name: 'Мои друзья', flag: false },
        ],
        colorFlag: '#9E9EE2',
      };
    },
    computed: {
      filteredUsers() {
        return this.users.filter(user =>
          user.name.toLowerCase().includes(this.searchQuery.toLowerCase())
        );
    },
    },
    methods: {
      sendFriendRequest(user) {
        // Отправка заявки в друзья
        if (!user.isFriend) {
          user.isFriend = true;
          this.friends.push(user);
        }
      },
    },
    changeFlag(id) {
      flags[1].flag = true;
    },
    styleFlag() {
        return {
          color: this.colorFlag,
        };
      },
    };
        </script>
        
<style scoped>
  .app {
    margin-top: 115px;
    width: 80%;
    margin-left: 5%;
  }

  .search input {
    padding: 10px;
    width: 300px;
    margin-bottom: 20px;
    border-radius: 8px;
  }
  .users-list h3 {
    margin-bottom: 2%;
  }

  .users-list ul {
    list-style-type: none;
    padding: 0;
  }
  
  .users-list li {
    background-color: #cbcbed;
    padding: 10px;
    margin-bottom: 10px;
    border-radius: 5px;
    box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  
  .users-list li span {
    font-size: 16px;
  }
  
  .users-list li button {
    background-color: #9E9EE2;
    border: none;
    padding: 5px 10px;
    color: black;
    cursor: pointer;
    border-radius: 3px;
  }
  
  .users-list li button:hover {
    background-color: #b6b6f2;
  }
  
        </style>
  