module.exports = {
    devServer: {
        proxy: {
            '/o/oauth2': {
                target: 'http://localhost:3000', // 백엔드 Google OAuth 처리 서버 주소
                changeOrigin: true,
            },
        },
    },
};