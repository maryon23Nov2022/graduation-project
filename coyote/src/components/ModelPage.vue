<template>
    <div class="base">
        <div class="docs-index">
            <h3>3D Models</h3>
            <div class="horizontal-line"></div>
            <form class="main-content">
                <div class="mb-3">
                    <label class="form-label label-self">
                        作者名
                        <input type="text" v-model="authorName" class="form-control">
                    </label>
                </div>
                <div class="mb-3">
                    <label class="form-label label-self">
                        作品名
                        <input type="text" v-model="modelName" class="form-control">
                    </label>
                </div>
                <button id="searchButton" type="button" class="btn btn-primary btn-self">查询</button>
            </form>
        </div>
        <div class="account-content">
            <ListComponent :authorName="authorName" :modelName="modelName" :liked="liked" :key="keyValue">
            </ListComponent>
        </div>
    </div>
</template>

<script>
import $ from "jquery";
import { ref } from "vue";
import ListComponent from "./ListComponent.vue";

export default {
    components:{
        ListComponent
    },
    setup(){
        let authorName = ref("");
        let modelName = ref("");
        let keyValue = ref(0);
        const liked = false;
        const rebuildFunc = function(){
            console.log("ModelPage.vue", authorName, authorName.value, keyValue.value);
            ++ keyValue.value;
            console.log("ModelPage.vue", keyValue.value);
        }
        return{
            authorName, modelName, liked, keyValue,
            rebuildFunc
        }
    },
    mounted(){
        $("#Models").css("border-color", "#42b883");
        $("#searchButton").on("click", this.rebuildFunc);
    },
    unmounted(){
        $("#Models").css("border-color", "transparent");
    }
}
</script>

<style scoped>
.base{
    display: flex;
    width: 100%;
    max-width: 1400px;
    margin: 0 auto;
    padding: 88px 32px;
}
.docs-index{
    display: flex;
    padding: 0 16px;
    margin-right: 32px;
    flex-direction: column;
    width: 24%;
}
.horizontal-line{
    border: solid;
    border-width: 0 0 1px 0;
    border-color: #525252;
    margin: 16px 0;
}
.account-content{
    width: calc(76% - 32px);
    /* border: 1px solid yellow; */
}
.label-self{
    width: 100%;
}
.btn-self{
    margin-top: 16px;
    width: 100%;
    font-weight: bold;
    letter-spacing: 16px;
}
</style>