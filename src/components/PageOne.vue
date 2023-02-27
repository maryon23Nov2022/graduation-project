<template>
    <div class="base">
        <h1>This is page 1.</h1>
    </div>
</template>

<script>
import $ from "jquery";

export default {
    mounted(){
        $("#PageOne").css("border-color", "#42b883");
    },
    unmounted(){
        $("#PageOne").css("border-color", "transparent");
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
</style>