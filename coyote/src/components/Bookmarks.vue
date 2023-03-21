<template>
    <div>
        <h1>This is Bookmarks page.</h1>
    </div>
</template>

<script>
import $ from "jquery";

export default {
    mounted(){
        $("#Bookmarks").css({"color": "#42b883", "filter":"brightness(100%)"});
    },
    unmounted(){
        $("#Bookmarks").removeAttr('style');
    }
}
</script>


<style scoped>

</style>