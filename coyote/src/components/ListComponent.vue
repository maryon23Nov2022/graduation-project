<template>
<div class="frame" v-for="item in listData">
    <div class="text-content">
        <h3>{{ item.modelName }}</h3>
        <div class="line">
            <div class="item">
                <a :href="item.downloadLink" class="download-link" download>
                    <span class="btn-text">Download</span>
                    <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="#fff" stroke-width="2" stroke-linecap="square" stroke-linejoin="arcs">
                        <path d="M3 15v4c0 1.1.9 2 2 2h14a2 2 0 0 0 2-2v-4M17 9l-5 5-5-5M12 12.8V2.5">
                        </path>
                    </svg>
                </a>
            </div>
            <div class="item">
                <div class="btn-text">Size: {{ item.size }} Kb</div>
            </div>
        </div>
        <div class="line">
            <div class="item">
                <div class="like-btn" :id="'btn-' + item.fileId">
                    <span class="btn-text">Like</span>
                    <svg width="24" height="24" viewBox="0 0 24 24" :fill="item.liked" stroke="#fff" stroke-width="1" stroke-linecap="round">
                        <path d="M20.84 4.61a5.5 5.5 0 0 0-7.78 0L12 5.67l-1.06-1.06a5.5 5.5 0 0 0-7.78 7.78l1.06 1.06L12 21.23l7.78-7.78 1.06-1.06a5.5 5.5 0 0 0 0-7.78z">
                        </path>
                    </svg>
                </div>
            </div>
            <div class="item">
                <div class="btn-text">Author: {{ item.authorName }}</div>
            </div>
        </div>
    </div>
    <img :src="item.imageLink" alt="Some errors hava occured." class="exhibition-img" :id="'img-' + item.downloadLink">
</div>
<Exhibition v-if="showModel" :modelLink="exhibitionLink" :showModel="showModel" @updateShowModel="updateShowModel"></Exhibition>
</template>

<script>
import $ from "jquery";
import { ref } from "vue";
import Exhibition from "./Exhibition.vue";
import { useStore } from "vuex";

export default {
    components:{
        Exhibition
    },
    props:{
        authorName: String,
        modelName: String,
        liked: Boolean
    },
    setup(props){
        let listData;
        let exhibitionLink = ref("");
        let showModel = ref(false);
        const store = useStore();
        const accessPath = store.state.accessPath;
        const filePath = store.state.filePath;
        const toggleFav = function(svgId){
            const fileId = svgId.substring(4, svgId.length);
            console.log(fileId);
            svgId = "#" + svgId + " > svg";
            console.log("Repository.vue", svgId);
            console.log("Repository.vue", $(svgId).attr("fill"));
            let type, url = accessPath + "/catalina/collect";
            if($(svgId).attr("fill") === "#f00"){
                $(svgId).attr("fill", "none");
                type = "DELETE", url += "/" + fileId;
            }
            else{
                $(svgId).attr("fill", "#f00");
                type = "POST";
            }
            $.ajax({
                url: url,
                type: type,
                async: false,
                data:{
                    fileId: fileId
                },
                xhrFields: {
                    withCredentials: true
                },
                success: function(resp){
                    console.log(resp);
                    console.log(fileId);
                },
            });
        }
        console.log("ListComponent.vue", props);
        $.ajax({
            url: accessPath + "/catalina/list",
            type: "GET",
            async: false,
            data:{
                authorName: props.authorName,
                modelName: props.modelName,
                liked: props.liked
            },
            xhrFields: {
                withCredentials: true
            },
            success: function(resp){
                listData = resp.data;
                console.log(listData);
                if(listData === null) listData = [];
            },
        });
        for(let data of listData){
            data.liked = data.liked ? "#f00" : "none";
            data.size = (data.size / 1024).toFixed(1);
            data.downloadLink = filePath + data.downloadLink;
            data.imageLink = filePath + data.imageLink;
            console.log(data.downloadLink);
            console.log(data.imageLink);
        }
        const exhibit = function(downloadLink){
            exhibitionLink.value = downloadLink;
            showModel.value = true;
            console.log("ListComponent.vue", exhibitionLink, showModel);
        }
        const updateShowModel = function(sonValue){
            showModel.value = sonValue;
            console.log("ListComponent.vue", showModel);
        }
        return{
            listData, exhibitionLink, showModel,
            toggleFav, exhibit, updateShowModel
        }
    },
    mounted(){
        const toggleFav = this.toggleFav;
        const exhibit = this.exhibit;
        const store = this.$store;
        $(".like-btn").on("click", function(){
            console.log(this);
            if(store.state.logged) toggleFav($(this).attr("id"));
        });
        $(".exhibition-img").on("click", function(){
            const downloadLink = $(this).attr("id");
            exhibit(downloadLink.substring(4, downloadLink.length));
        });
    },
}
</script>


<style scoped>
.frame{
    width: 100%;
    display: flex;
    border-radius: 32px;
    background-color: #242424;
    height: 256px;
    align-items: center;
    justify-content: space-evenly;
    margin-bottom: 32px;
}
.text-content{
    width: 512px;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: space-evenly;
}
.line{
    display: flex;
    width: 100%;
}
.item{
    width: 50%;
    box-sizing: border-box;
}
.download-link{
    text-decoration: none;
}
.like-btn{
    background-color: transparent;
    border: 0;
    display: flex;
    align-items: center;
    cursor: pointer;
}
.btn-text{
    color: white;
    font-weight: bold;
    letter-spacing: 2px;
    margin-right: 8px;
}
svg{
    transition: 300ms;
}
svg:hover{
    scale: 1.2 1.2;
}
.exhibition-img{
    height: 80%;
    aspect-ratio: 4 / 3;
    border-radius: inherit;
    cursor: pointer;
    transition: 300ms;
}
.exhibition-img:hover{
    scale: 1.05 1.05;
    box-shadow: 4px 4px 5px 0px rgba(255,255,255,0.75);
    -webkit-box-shadow: 4px 4px 5px 0px rgba(255,255,255,0.75);
    -moz-box-shadow: 4px 4px 5px 0px rgba(255,255,255,0.75);
}
</style>