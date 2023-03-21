<template>
    <div>
        <h1>This is guide page.</h1>
    </div>
</template>

<script>
import $ from "jquery";

export default {
    mounted(){
        $("#Guide").css({"color": "#42b883", "filter":"brightness(100%)"});
    },
    unmounted(){
        $("#Guide").removeAttr('style');
    }
}
</script>


<style scoped>

</style>