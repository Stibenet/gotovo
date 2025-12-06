<template>
  <div class="container">
    <HeaderComponent/>

    <!-- Body -->
    <main class="body">
      <div class="register-form">
        <h2>Авторизация</h2>

        <form @submit.prevent="register">
          <div class="input-group">
            <input
                v-model="userEmail"
                type="email"
                placeholder="Email"
                :class="{ 'error': v$.userEmail.$error }"
                @blur="v$.userEmail.$touch"
            />
            <span v-if="v$.userEmail.$error" class="error-message">
              {{ v$.userEmail.$errors[0].$message }}
            </span>
          </div>

          <div class="input-group">
            <input
                v-model="userPassword"
                type="password"
                placeholder="Пароль"
                :class="{ 'error': v$.userPassword.$error }"
                @blur="v$.userPassword.$touch"
            />
            <span v-if="v$.userPassword.$error" class="error-message">
              {{ v$.userPassword.$errors[0].$message }}
            </span>
          </div>

          <button type="submit" :disabled="v$.$invalid || loading">
            {{ loading ? 'Войти...' : 'Войти' }}
          </button>
          <router-link to="/register">
            <button class="auth-button" style="margin-top: 15px;">Создать пользователя</button>
          </router-link>
        </form>
      </div>
    </main>

    <FooterComponent/>
  </div>
</template>

<script setup>
import {ref} from 'vue'
import {useVuelidate} from '@vuelidate/core'
import {required, email as emailValidator, minLength} from '@vuelidate/validators'
import HeaderComponent from "@/components/static/HeaderComponent.vue";
import FooterComponent from "@/components/static/FooterComponent.vue";

// Состояния формы
const userEmail = ref('')
const userPassword = ref('')
const loading = ref(false)

// Валидации
const rules = {
  userEmail: {required, email: emailValidator},
  userPassword: {required, minLength: minLength(8)}
}

const v$ = useVuelidate(rules, {userEmail, userPassword})

// Метод регистрации
const register = async () => {
  v$.value.$touch()
  if (v$.value.$invalid) return

  loading.value = true
  try {
    const response = await fetch('/api/auth/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        email: userEmail.value,
        password: userPassword.value
      })
    })

    if (response.ok) {
      // alert('Регистрация успешна!')
      console.log(response.body)
    } else {
      // Ошибка
      const error = await response.json()
      alert(error.message || 'Ошибка регистрации')
    }
  } catch (err) {
    alert('Ошибка сети')
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.container {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

.header {
  padding: 20px;
  background-color: #f8f9fa;
  border-bottom: 1px solid #dee2e6;
}

.logo {
  height: 40px;
  width: auto;
}

.body {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 20px;
  background-color: #f5f5f5;
}

.register-form {
  background: white;
  padding: 2rem;
  border-radius: 12px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;
  box-sizing: border-box;
}

h2 {
  text-align: center;
  margin-bottom: 1.5rem;
  color: #333;
}

.input-group {
  margin-bottom: 1rem;
  width: 100%;
}

input {
  width: 100%;
  padding: 0.75rem;
  border: 1px solid #ddd;
  border-radius: 8px;
  font-size: 1rem;
  transition: border-color 0.2s;
  box-sizing: border-box;
}

input:focus {
  outline: none;
  border-color: #007bff;
}

input.error {
  border-color: #dc3545;
}

.error-message {
  color: #dc3545;
  font-size: 0.875rem;
  margin-top: 0.25rem;
  display: block;
}

button {
  width: 100%;
  padding: 0.75rem;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 8px;
  font-size: 1rem;
  cursor: pointer;
  transition: background-color 0.2s;
  box-sizing: border-box;
}

button:disabled {
  background-color: #6c757d;
  cursor: not-allowed;
}

button:not(:disabled):hover {
  background-color: #0056b3;
}

.footer {
  padding: 15px 20px;
  background-color: #343a40;
  color: white;
  text-align: center;
  font-size: 0.875rem;
}

.footer p {
  margin: 0;
}
</style>