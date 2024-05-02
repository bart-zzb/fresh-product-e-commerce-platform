const { defineConfig } = require('@vue/cli-service')
const { VantResolver } = require('@vant/auto-import-resolver');
const ComponentsPlugin = require('unplugin-vue-components/webpack');

module.exports = {
  transpileDependencies: true,
  configureWebpack: {
    plugins: [
      // 当 unplugin-vue-components 版本小于 0.26.0 时，使用以下写法
      //ComponentsPlugin({ resolvers: [VantResolver()] }),
      //当 unplugin-vue-components 版本大于等于 0.26.0 时，使用以下写法
      ComponentsPlugin.default({ resolvers: [VantResolver()] }),
    ],
  },

  chainWebpack: (config) => {
    config.plugin('define').tap((definitions) => {
      Object.assign(definitions[0], {
        __VUE_OPTIONS_API__: 'true',
        __VUE_PROD_DEVTOOLS__: 'false',
        __VUE_PROD_HYDRATION_MISMATCH_DETAILS__: 'false'
      })
      return definitions
    })
  }
};
