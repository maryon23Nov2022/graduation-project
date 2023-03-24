<template>
    <ListComponent :authorName="authorName" :modelName="modelName" :liked="liked"></ListComponent>
</template>

<script>
import $ from "jquery";
import { useStore } from "vuex";
import ListComponent from "./ListComponent.vue";

export default {
    components:{
        ListComponent
    },
    setup(){
        const store = useStore();
        const authorName = store.state.username;
        const modelName = "";
        const liked = false;
        return{
            authorName, modelName, liked
        }
    },
    activated(){
        console.log("Repository.vue", "activated");
        $("#Repository").css({"color": "#42b883", "filter":"brightness(100%)"});
        
    },
    deactivated(){
        $("#Repository").removeAttr('style');
    }
}
</script>


<style scoped>
</style>