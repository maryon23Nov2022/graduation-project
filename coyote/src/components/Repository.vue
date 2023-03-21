<template>
    <div>
        <h1>This is Repository page.</h1>
    </div>
</template>

<script>
import $ from "jquery";

export default {
    mounted(){
        $("#Repository").css({"color": "#42b883", "filter":"brightness(100%)"});
    },
    unmounted(){
        // $("#Repository").css({"color": "#e1e1e1", "filter":"brightness(70%)"});
        $("#Repository").removeAttr('style');
    }
}
</script>


<style scoped>

</style>