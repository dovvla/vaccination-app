const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy: {
      '^/api': {
        target: 'https://localhost:8081',
        changeOrigin: true,
        onProxyReq: function(request) {
          request.setHeader("origin", "https://localhost:8081");
        },
      },
    },
  },
})
