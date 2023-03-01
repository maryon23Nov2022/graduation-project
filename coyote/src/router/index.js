import { createRouter, createWebHistory } from "vue-router";

import PageOne from "../components/PageOne.vue";
import PageTwo from "../components/PageTwo.vue";
import HomePage from "../components/HomePage.vue";
import DocsPage from "../components/DocsPage.vue";
import GuidePage from "../components/GuidePage.vue";
import Tutorial from "../components/Tutorial.vue";
import ModelPage from "../components/ModelPage.vue";

export default createRouter({
    history: createWebHistory(),
    routes: [
        { path: "/", component: HomePage},
        {
            path: "/docs",
            component: DocsPage,
            children: [
                { path: "guide", component: GuidePage },
                { path: "tutorial", component: Tutorial }
            ]
        },
        { path: "/pageOne", component: PageOne },
        { path: "/pageTwo", component: PageTwo },
        { path: "/models", component: ModelPage }
    ]
})