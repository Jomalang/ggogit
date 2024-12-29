export default {
  root: true,
  env: {
    browser: true,
    node: true,
    es2021: true,
  },
  extends: [
    'eslint:recommended', // 기본 ESLint 규칙
    'plugin:vue/vue3-recommended', // Vue 3 권장 규칙
    'prettier', // Prettier와 충돌 방지
  ],
  parserOptions: {
    ecmaVersion: 2020,
    sourceType: 'module',
  },
  rules: {
    // 사용자 정의 규칙 추가 가능
    'vue/no-unused-vars': 'error', // 사용되지 않는 변수 금지
    'vue/multi-word-component-names': 'off', // 컴포넌트 이름 다중 단어 강제 해제
    'indent': ['error', 2], // 들여쓰기 크기: 2칸
    'quotes': ['error', 'single'], // 작은따옴표 강제
    'semi': ['error', 'always'], // 세미콜론 강제
  },
};