const path = require('path');
const webpack = require('webpack');
const BUILD_DIR = path.resolve(__dirname, 'src/main/resources/static/js/');
const CompressionPlugin = require('compression-webpack-plugin');

module.exports = {
    entry: './src/main/resources/static/jsx/app',
    output: {
        path: BUILD_DIR + '',
        filename: 'bundle.js'
    },
    plugins: [
        new webpack.ProvidePlugin({
            $: "jquery",
            jQuery: "jquery"
        }),
        new CompressionPlugin()
    ],
    resolve: {
        modules: [path.resolve(__dirname, 'src/main/resources/static/jsx'), 'node_modules'],
        extensions: ['.js', '.jsx', '.json'],
    },
    module: {
        loaders: [
            {
                test: /\.(js|jsx)$/,
                exclude: [/node_modules[\\\/](?!callbag-\w+)/, /bower_components/],
                loader: 'babel-loader',
                options: {
                    cacheDirectory: true
                },
            },
            {
                test: /\.(css|scss)$/,
                use: [
                    {
                        loader: 'style-loader',
                    },
                    {
                        loader: 'css-loader',
                    },
                    {
                        loader: 'sass-loader',
                    },
                ],
            },
            {test: /\.(png|jpe?g)(\?.*)?$/, loader: 'url?limit=8182'},
            {test: /\.(svg|ttf|woff|eot)(\?.*)?$/, loader: 'file-loader'}
        ]
    }
};
