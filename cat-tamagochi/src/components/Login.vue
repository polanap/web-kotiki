<template>
    <div class="place">
    <h1>Добро пожаловать <br> в котячий клуб!</h1>
    <div class="buttons">
        <button @click="openModalLogup">Зарегистрироваться</button>
        <button @click="openModalLogin">У меня уже есть аккаунт</button>
    </div>

    <div class="modal" v-if="modalLoginVisible" @click.self="closeLoginModal">
      <div class="modal-content-login">
        <button class="close" @click="closeLoginModal" style="border-radius: 15px; border: none"><img src="../assets/no.png" alt="cross"></button>
      
      <h2 class="title">Вход</h2>
      <form @submit.prevent="login">
        <div class="form-group">
          <label for="name">Ваше имя:</label>
          <input type="text" id="name" v-model="nameIn" placeholder="Введите ваше имя" required />
        </div>
        <div class="form-group">
          <label for="password">Пароль:</label>
          <input type="password" id="password" v-model="passwordIn" placeholder="Введите пароль" required />
        </div>
        <button type="submit" class="submit-button">Войти</button>
      </form>
    </div>
    </div>

    <div class="modal" v-if="modalLogupVisible" @click.self="closeLogupModal">
      <div class="modal-content-logup">
        <button class="close" @click="closeLogupModal" style="border-radius: 15px; border: none"><img src="../assets/no.png" alt="cross"></button>
        <h2 class="title">Регистрация</h2>
      <form @submit.prevent="registerUser">
        <div class="form-group">
          <label for="name">Ваше имя:</label>
          <input type="text" id="name" v-model="nameUp" placeholder="Введите ваше имя" required />
        </div>
        <div class="form-group">
          <label for="password">Пароль:</label>
          <input type="password" id="password" v-model="passwordUp" placeholder="Введите пароль" required />
        </div>
        <div class="form-group">
          <label for="confirm-password">Повторите:</label>
          <input
            type="password"
            id="confirm-password"
            v-model="confirmPasswordUp"
            placeholder="Повторите пароль"
            required
          />
        </div>
        <button type="submit" class="submit-button">Зарегистрироваться</button>
      </form>
      </div>
    </div>
</div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      modalLoginVisible: false,
      modalLogupVisible: false,
      nameIn: '',
      passwordIn: '',
      nameUp: '',
      passwordUp: '',
      confirmPasswordUp: '',
    };
  },
  methods: {
    openModalLogin() {
      this.modalLoginVisible = true;
    },
    closeLoginModal() {
        this.modalLoginVisible = false;
    },
    openModalLogup() {
      this.modalLogupVisible = true;
    },
    closeLogupModal() {
        this.modalLogupVisible = false;
    },
  },
  submitForm() {
      if (this.password !== this.confirmPassword) {
        alert('Пароли не совпадают!');
        return false;
      }
      return true;
    },
    async login() {
      try {
        const response = await axios.post('https://virtserver.swaggerhub.com/polanap/kotiki/1.0.0/login', {
          username: this.usernameIn,
          password: this.passwordIn
        });
        alert('Успешная регистрация');
      } catch (error) {
        console.error('Ошибка:', error);
      }
    },
    async registerUser() {
      if (!this.submitForm()) {
        alert('пароли не совпадают');
        return;
      }
      try {
        const response = await axios.post('https://virtserver.swaggerhub.com/polanap/kotiki/1.0.0/registration', {
          username: this.usernameUp,
          password: this.passwordUp,
          confirmPassword: this.confirmPasswordUp
        });
        alert('Успешная регистрация:', response.data);
      } catch (error) {
        console.error('Ошибка:', error);
      }
    }
};
</script>

<style>
h1 {
    font-size: 85px;
    text-align: center;
    font-family: "Rubik Bubbles", sans-serif;
    font-weight: 5;
    margin-top: 12%;
}

.buttons {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin: 10% 16%;
}

.buttons button {
    height: 80px;
    width: 380px;
    font-size: 25px;
    font-weight:bold;
    background-color: #9E9EE2;
    border-radius: 8px;
    border: 0px;
}

.buttons button:hover {
    background-color: #8d8dd5;
}

.modal {
    display: flex;
    justify-content: center;
    align-items: center;
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(59, 29, 56, 0.3);
    z-index: 200;
    color: #9074A8;
  }

  .modal-content-logup {
    background-color: rgb(230 230 248 / .95);
    padding: 20px;
    border-radius: 8px;
    height: 500px;
    width: 464px;
	  text-align: center;
  }

  .modal-content-login {
    background-color: rgb(230 230 248 / .95);
    padding: 20px;
    border-radius: 8px;
    height: 364px;
    width: 415px;
	  text-align: center;
  }

  .modal-content h2 {
    margin-bottom: 6%;
    font-size: 30px;
  }

  .close {
    display: block;
    cursor: pointer;
    background-color: rgb(230 230 248 / .95);
  }
  .close img {
    width: 50px;
    height: 50px;
  }

.form-group {
    width: 80%;
    margin-bottom: 15px;
    text-align: left;
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-left: 10%;
}

label {
  font-size: 22px;
  font-weight: bold;
}

input {
  width: 210px;
  height: 50px;
  padding: 4px;
  border: 1px solid #ccccff;
  border-radius: 6px;
  font-size: 16px;
  box-sizing: border-box;
  outline: none;
  color: #9074A8;
  text-align: center;
}

input:focus {
  border-color: #8d8dd5; /* Цвет при фокусе */
  box-shadow: 0 0 5px rgba(122, 103, 238, 0.5);
}

.submit-button {
  background-color: #9E9EE2;
  border: none;
  padding: 15px 20px;
  border-radius: 6px;
  font-size: 16px;
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.3s;
  margin-top: 6%;
}

.submit-button:hover {
  background-color: #8d8dd5; /* Цвет при наведении */
}
</style>