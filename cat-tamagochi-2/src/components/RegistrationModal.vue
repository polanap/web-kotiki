<template>
    <div class="modal" v-if="isVisibleRegistration" @click.self="closeModal">
        <div class="modal-content-login">
            <button class="close" @click="closeModal" style="border-radius: 15px; border: none"><img src="../assets/no.png" alt="cross"></button>
            <h2 class="title">Регистрация</h2>
            <form @submit.prevent="registerUser">
                <div class="form-group">
                    <label for="name">Ваше имя:</label>
                    <input v-model="form.username" type="text" id="name" placeholder="Введите ваше имя" required />
                </div>
                <div class="form-group">
                    <label for="password">Пароль:</label>
                    <input v-model="form.password" type="password" id="password"  placeholder="Введите пароль" required />
                </div>
                <div class="form-group">
                    <label for="confirm-password">Повторите:</label>
                    <input
                        type="password"
                        id="confirm-password"
                        v-model="form.confirmPassword"
                        placeholder="Повторите пароль"
                        required
                    />
                </div>
                <p class="error">{{ this.error }}</p>
                <button type="submit" class="submit-button">Зарегистрироваться</button>
            </form>
        </div>
    </div>
</template>

<script>
import axios from 'axios';

export default {
  props: {
    isVisibleRegistration: {
      type: Boolean,
      required: true,
    },
  },
  data() {
    return {
      form: {
        username: "",
        password: "",
        confirmPassword: "",
      },
      error: "",
    };
  },
  methods: {
    closeModal() {
      this.$emit('close');
    },
    async registerUser() {
  try {
    this.error = "";
    var response = await axios.post("/api/registration", {
      username: this.form.username,
      password: this.form.password,
      confirmPassword: this.form.confirmPassword,
}, {
  headers: {
    "Content-Type": "application/json"
  },
  withCredentials: true,
});
    console.log("Пользователь зарегистрирован:", response.data);
    this.$router.push('/cat');
  } catch (err) {
    console.error(err.response.data.errorMessage);
    this.error = err.response.data.errorMessage;
  }
}
  },
};
</script>

<style scoped>
h1 {
    font-size: 85px;
    text-align: center;
    font-family: "Rubik Bubbles", sans-serif;
    font-weight: 5;
    margin-top: 12%;
}

h2 {
  margin-bottom: 20px;
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

  .modal-content-login {
    background-color: rgb(230 230 248 / .95);
    padding: 20px;
    border-radius: 8px;
    height: 500px;
    width: 480px;
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
  margin-top: 2%;
}

.submit-button:hover {
  background-color: #8d8dd5; /* Цвет при наведении */
}

.error {
  font-size: 18px;
}
</style>