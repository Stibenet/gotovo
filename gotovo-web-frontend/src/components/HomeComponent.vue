<template>
  <div class="container">
    <!-- Header -->
    <HeaderComponent />

    <!-- Body -->
    <main class="body">
      <div class="hero-section">
        <h1>Создаем веб-решения мечты</h1>
        <p class="subtitle">Профессиональная разработка сайтов и веб-приложений под ключ</p>
        <div class="cta-buttons">
          <button class="primary-button">Начать проект</button>
          <button class="secondary-button">Посмотреть примеры</button>
        </div>
      </div>

      <div class="features-section">
        <div class="feature-card">
          <h3>Современные технологии</h3>
          <p>Используем последние фреймворки и библиотеки для создания высокопроизводительных решений</p>
        </div>

        <div class="feature-card">
          <h3>Адаптивный дизайн</h3>
          <p>Все наши сайты идеально выглядят на любых устройствах и экранах</p>
        </div>

        <div class="feature-card">
          <h3>SEO оптимизация</h3>
          <p>Закладываем SEO возможности с первого дня разработки</p>
        </div>
      </div>
    </main>

    <!-- Footer -->
    <FooterComponent />
  </div>
</template>

<script setup>

import FooterComponent from "@/components/static/FooterComponent.vue";
import HeaderComponent from "@/components/static/HeaderComponent.vue";
</script>

<style scoped>
.container {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}


.body {
  flex: 1;
  background-color: #f8f9fa;
}

.hero-section {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  padding: 4rem 2rem;
  text-align: center;
}

.hero-section h1 {
  font-size: 3rem;
  margin-bottom: 1rem;
  font-weight: 700;
}

.subtitle {
  font-size: 1.25rem;
  margin-bottom: 2rem;
  opacity: 0.9;
}

.cta-buttons {
  display: flex;
  gap: 1rem;
  justify-content: center;
  flex-wrap: wrap;
}

.primary-button {
  padding: 1rem 2rem;
  background-color: #28a745;
  color: white;
  border: none;
  border-radius: 8px;
  font-size: 1.1rem;
  font-weight: 600;
  cursor: pointer;
  transition: background-color 0.2s;
}

.primary-button:hover {
  background-color: #218838;
}

.secondary-button {
  padding: 1rem 2rem;
  background-color: transparent;
  color: white;
  border: 2px solid white;
  border-radius: 8px;
  font-size: 1.1rem;
  font-weight: 600;
  cursor: pointer;
  transition: background-color 0.2s;
}

.secondary-button:hover {
  background-color: rgba(255,255,255,0.1);
}

.features-section {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 2rem;
  padding: 4rem 2rem;
  max-width: 1200px;
  margin: 0 auto;
}

.feature-card {
  background: white;
  padding: 2rem;
  border-radius: 12px;
  box-shadow: 0 4px 6px rgba(0,0,0,0.1);
  text-align: center;
}

.feature-card h3 {
  margin-bottom: 1rem;
  color: #333;
}

.feature-card p {
  color: #666;
  line-height: 1.6;
}

.footer {
  background-color: #343a40;
  color: white;
  padding: 2rem;
}

.footer-content {
  max-width: 1200px;
  margin: 0 auto;
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 2rem;
  margin-bottom: 2rem;
}

.footer-section h4 {
  margin-bottom: 1rem;
  color: #adb5bd;
}

.footer-section p {
  margin: 0.5rem 0;
  color: #adb5bd;
}

.social-links {
  display: flex;
  gap: 1rem;
}

.social-links a {
  color: #adb5bd;
  text-decoration: none;
  transition: color 0.2s;
}

.social-links a:hover {
  color: white;
}

.copyright {
  text-align: center;
  padding-top: 2rem;
  border-top: 1px solid #495057;
  color: #adb5bd;
  margin: 0;
}

@media (max-width: 768px) {
  .header-content {
    flex-direction: column;
    gap: 1rem;
    padding: 1rem;
  }

  .nav-list {
    gap: 1rem;
    flex-wrap: wrap;
    justify-content: center;
  }

  .hero-section h1 {
    font-size: 2rem;
  }

  .cta-buttons {
    flex-direction: column;
    align-items: center;
  }
}
</style>