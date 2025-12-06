<script setup>

</script>

<template>
  <header class="header">
    <div class="header-content">
      <div class="logo-section">
        <img src="../../../public/logo.png" alt="WebStudio" class="logo" />
      </div>

      <nav class="nav-menu">
        <ul class="nav-list">
          <li><router-link to="/">Главная</router-link></li>
          <li><router-link to="/services">Услуги</router-link></li>
          <li><router-link to="/portfolio">Портфолио</router-link></li>
          <li><router-link to="/about">О нас</router-link></li>
          <li><router-link to="/contact">Контакты</router-link></li>
        </ul>
      </nav>

      <div class="auth-section">
        <router-link to="/auth">
          <button class="auth-button">Авторизоваться</button>
        </router-link>
      </div>
    </div>
  </header>
</template>

<style scoped>
.header {
  background-color: #ffffff;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  position: sticky;
  top: 0;
  z-index: 100;
}

.header-content {
  max-width: 1200px;
  margin: 0 auto;
  padding: 1rem 2rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.logo-section {
  display: flex;
  align-items: center;
  gap: 0.5rem;
}

.logo {
  height: 40px;
  width: auto;
}

.brand-name {
  font-size: 1.5rem;
  font-weight: bold;
  color: #333;
}

.nav-menu {
  display: flex;
  align-items: center;
}

.nav-list {
  display: flex;
  list-style: none;
  margin: 0;
  padding: 0;
  gap: 2rem;
}

.nav-list li a {
  text-decoration: none;
  color: #333;
  font-weight: 500;
  transition: color 0.2s;
}

.nav-list li a.router-link-active,
.nav-list li a:hover {
  color: #007bff;
}

.auth-section {
  display: flex;
  align-items: center;
}

.auth-button {
  padding: 0.5rem 1rem;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  font-size: 1rem;
  transition: background-color 0.2s;
}

.auth-button:hover {
  background-color: #0056b3;
}

</style>