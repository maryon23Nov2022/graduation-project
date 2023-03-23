<template>
    <div class="base">
        <div class="docs-index">
            <h3>Account</h3>
            <div class="horizontal-line"></div>
            <router-link class="normal-link vertical-gap dim-text hover-bright" to="/account/bookmarks" id="Bookmarks">Bookmarks</router-link>
            <router-link class="normal-link vertical-gap dim-text hover-bright" to="/account/repository" id="Repository">Repository</router-link>
            <router-link class="normal-link vertical-gap dim-text hover-bright" to="/account/upload" id="Upload">Upload</router-link>
        </div>
        <div class="account-content">
            <RouterView v-slot="{ Component }">
                <KeepAlive exclude="UploadPage">
                    <component :is="Component"/>
                </KeepAlive>
            </RouterView>
        </div>
    </div>
</template>

<script>
import $ from "jquery";

export default {
    mounted(){
        $("#Account").css("border-color", "#42b883");
    },
    unmounted(){
        $("#Account").css("border-color", "transparent");
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
    /* border: 1px solid yellow; */
}
.normal-link{
    text-decoration: none;
    color: #e1e1e1;
    font-weight: 500;
    transition: 300ms;
}
.dim-text{
    filter: brightness(70%);
}
.hover-bright:hover{
    filter: brightness(100%);
}
.vertical-gap{
    padding: 3px 0;
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
</style>