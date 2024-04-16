<template>
  <main class="main-container">
    <h1 class="user-greeting">Konan님</h1>
    <p class="user-question">오늘 하루 기분은 어떠신가요?</p>
    <section class="image-grid">
      <div class="image-row">
        <div class="image-column">
          <img src="https://cdn.builder.io/api/v1/image/assets/TEMP/0611a07510d0a0b602dd26ed1b9373131d55af57f346abdb58d8c3a1d36acdad?apiKey=79a2b7eb54dd4ed5b8fcf22467729821&" alt="Recommended image" class="recommended-image" />
        </div>
        <div class="image-column">
          <img src="https://cdn.builder.io/api/v1/image/assets/TEMP/93bf189a3d84ff072154276653241e06fe75b7d4f0ced833542ae9db9eef5cc5?apiKey=79a2b7eb54dd4ed5b8fcf22467729821&" alt="Recommended image" class="recommended-image" />
        </div>
        <div class="image-column">
          <div class="ai-recommendations">
            <h2 class="ai-recommendations-title">AI 추천 목록</h2>
            <img src="https://cdn.builder.io/api/v1/image/assets/TEMP/cfc953b2f7dc58c3680dd24da19c3556e168aa03d70fd9710459183bf4e59218?apiKey=79a2b7eb54dd4ed5b8fcf22467729821&" alt="AI recommended image" class="ai-recommended-image" />
          </div>
        </div>
      </div>
    </section>
    <section class="image-grid">
      <div class="image-row">
        <div class="image-column">
          <img src="https://cdn.builder.io/api/v1/image/assets/TEMP/f6a0846a1aeba9255f0c603fcc961208c7825ded9d469151d1dff8010a7d7b20?apiKey=79a2b7eb54dd4ed5b8fcf22467729821&" alt="Recommended image" class="recommended-image" />
        </div>
        <div class="image-column">
          <img src="https://cdn.builder.io/api/v1/image/assets/TEMP/88e0f77ea3f67221ab657ca58b9d449f9b7c053b28400765184e26f830117d58?apiKey=79a2b7eb54dd4ed5b8fcf22467729821&" alt="Recommended image" class="recommended-image" />
        </div>
        <div class="image-column">
          <img src="https://cdn.builder.io/api/v1/image/assets/TEMP/76424190919effbc1f48bce6bcb977f5503fbf0f7fc453f6f55749c035a3a1a5?apiKey=79a2b7eb54dd4ed5b8fcf22467729821&" alt="Recommended image" class="recommended-image" />
        </div>
      </div>
    </section>
  </main>
</template>

<style scoped>
.main-container {
  align-self: center;
  display: flex;
  margin-top: 186px;
  width: 481px;
  max-width: 100%;
  flex-direction: column;
}

@media (max-width: 991px) {
  .main-container {
    margin-top: 40px;
  }
}

.user-greeting {
  color: #000;
  align-self: center;
  font: 700 40px Inter, sans-serif;
}

.user-question {
  color: #676767;
  align-self: center;
  margin-top: 43px;
  font: 400 25px Inter, sans-serif;
}

@media (max-width: 991px) {
  .user-question {
    margin-top: 40px;
  }
}

.image-grid {
  margin-top: 66px;
  padding: 0 1px;
}

@media (max-width: 991px) {
  .image-grid {
    max-width: 100%;
    margin-top: 40px;
  }
}

.image-row {
  gap: 20px;
  display: flex;
}

@media (max-width: 991px) {
  .image-row {
    flex-direction: column;
    align-items: stretch;
    gap: 0;
  }
}

.image-column {
  display: flex;
  flex-direction: column;
  line-height: normal;
  width: 33%;
  margin-left: 0;
}

@media (max-width: 991px) {
  .image-column {
    width: 100%;
  }
}

.recommended-image {
  aspect-ratio: 1.14;
  object-fit: auto;
  object-position: center;
  width: 141px;
  max-width: 100%;
  flex-grow: 1;
}

@media (max-width: 991px) {
  .recommended-image {
    margin-top: 28px;
  }
}

.ai-recommendations {
  border-radius: 15px;
  background-color: rgba(237, 237, 237, 1);
  display: flex;
  flex-grow: 1;
  flex-direction: column;
  font-size: 15px;
  color: #757575;
  font-weight: 700;
  width: 100%;
  padding: 20px 30px;
}

@media (max-width: 991px) {
  .ai-recommendations {
    margin-top: 28px;
    padding: 0 20px;
  }
}

.ai-recommendations-title {
  font-family: Inter, sans-serif;
}

.ai-recommended-image {
  aspect-ratio: 1.14;
  object-fit: auto;
  object-position: center;
  width: 64px;
  box-shadow: 3px 5px 6px 0 rgba(0, 0, 0, 0.87);
  margin-top: 14px;
}
</style>
