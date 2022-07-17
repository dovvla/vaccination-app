const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy: {
      '^/api': {
        target: 'http://localhost:8081',
        changeOrigin: true,
        onProxyReq: function(request) {
          request.setHeader("origin", "http://localhost:8081");
        },
      },
    },
  },
})
