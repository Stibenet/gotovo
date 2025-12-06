<template>
    <footer class="footer">
      <div class="footer-content">
        <div class="footer-section">
          <h4>WebStudio</h4>
          <p>Создаем веб-решения, которые работают</p>
        </div>

        <div class="footer-section">
          <h4>Контакты</h4>
          <p>info@webstudio.ru</p>
          <p>+7 (XXX) XXX-XX-XX</p>
        </div>

        <div class="footer-section">
          <h4>Следите за нами</h4>
          <div class="social-links">
            <a href="#">VK</a>
            <a href="#">Telegram</a>
            <a href="#">GitHub</a>
          </div>
        </div>
      </div>

      <p class="copyright">© {{ new Date().getFullYear() }} WebStudio. Все права защищены.</p>
    </footer>
</template>

<script setup>

</script>

<style scoped>
.container {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
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
</style>