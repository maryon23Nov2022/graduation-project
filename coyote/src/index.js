require.context("../public", true, /.*/i, "sync");
require.context("!!file-loader?name=draco/[name].[ext]!./assets/draco", true, /.*/, "sync");

import { createApp } from "vue";
import { createStore } from "vuex";
import App from "./App.vue";
import router from "./router"
import "bootstrap/dist/css/bootstrap.min.css";
import 'bootstrap/dist/js/bootstrap.bundle.min.js';

const store = createStore({
    state(){
        return {
            logged: false,
            username: "zhuqi",
        }
    },
    mutations: {
        setLogged(state, res){
            state.logged = res;
        },
        setUsername(state, username){
            state.username = username;
        }
    }
})

const app = createApp(App);
app.use(router);
app.use(store);
app.mount("#app");