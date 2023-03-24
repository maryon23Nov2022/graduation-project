<template>
    <!-- <div>
        <h1>This is Bookmarks page.</h1>
    </div> -->
    <ListComponent :authorName="authorName" :modelName="modelName" :liked="liked"></ListComponent>
</template>

<script>
import $ from "jquery";
import ListComponent from "./ListComponent.vue";

export default{
    components:{
        ListComponent
    },
    setup(){
        const authorName = "";
        const modelName = "";
        const liked = true;
        return{
            authorName, modelName, liked
        }
    },
    activated(){
        $("#Bookmarks").css({"color": "#42b883", "filter":"brightness(100%)"});
    },
    deactivated(){
        $("#Bookmarks").removeAttr('style');
    }
}
</script>


<style scoped>

</style>