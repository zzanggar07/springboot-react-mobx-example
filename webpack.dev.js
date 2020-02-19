const path = require('path');
const webpack = require('webpack');
const BUILD_DIR = path.resolve(__dirname, 'src/main/resources/static/js/');

module.exports = {
    entry: './src/main/resources/static/tsx/App',
    output: {
        path: BUILD_DIR + '',
        filename: 'bundle.js'
    },
    plugins: [
        new webpack.ProvidePlugin({
            $: "jquery",
            jQuery: "jquery"
        })
    ],
    resolve: {
        modules: [path.resolve(__dirname, 'src/main/resources/static/tsx'), 'node_modules'],
        extensions: ['.js', '.jsx', '.json', '.ts','.tsx'],
    },
    devServer: {
        historyApiFallback: true,
        watchContentBase: true,
        contentBase: BUILD_DIR,
        publicPath: '/',
        host: '0.0.0.0',
        port: 8082,
        proxy: {
            '**': 'http://127.0.0.1:8081'
        },
    },
    module: {
        loaders: [
            {
                test: /\.(js|jsx|ts|tsx)$/,
                exclude: [/node_modules[\\\/](?!callbag-\w+)/, /bower_components/],
                loader: 'babel-loader',
                options: {
                    cacheDirectory: true,
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
            {test: /\.(svg|ttf|woff|eot)(\?.*)?$/, loader: 'file'}
        ]
    }
};
