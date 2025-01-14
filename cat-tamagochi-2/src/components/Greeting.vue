<template>
    <div class="place">
    <h1>Добро пожаловать <br> в котячий клуб!</h1>
    <div class="buttons">
      <button class="but" @click="openModalRegistration()">Зарегистрироваться</button>
      <button class="but" @click="openModalLogin()">У меня уже есть аккаунт</button>
    </div>
    </div>
    <RegistrationModal
        v-if="modalVisibleRegistration"
        :isVisibleRegistration="modalVisibleRegistration"
        @close="modalVisibleRegistration = false"
    />
    <LoginModal
        v-if="modalVisibleLogin"
        :isVisibleLogin="modalVisibleLogin"
        @close="modalVisibleLogin = false"
    />
</template>

<script>
import LoginModal from './LoginModal.vue';
import RegistrationModal from './RegistrationModal.vue';

export default {
components: {
    RegistrationModal,
    LoginModal
},
  data() {
    return {
        modalVisibleRegistration: false,
        modalVisibleLogin: false
    };
  },
  methods: {
    openModalRegistration() {
      this.modalVisibleRegistration = true;
    },
    openModalLogin() {
      this.modalVisibleLogin = true;
    },
  },
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

.but {
    /* height: 80px; */
    width: 380px;
    font-size: 25px;
    font-weight:bold;
    background-color: #9E9EE2;
    color: black;
    border-radius: 8px;
    border: 0px;
    text-align: center;
    text-decoration: none;
    padding: 25px 0px;
}

.but:hover {
  background-color: #8383c9;
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