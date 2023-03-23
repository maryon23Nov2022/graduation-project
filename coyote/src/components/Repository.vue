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
                <div class="btn-text">Size: {{ item.size }} bytes</div>
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
    <img :src="item.imageLink" alt="Some errors hava occured." class="exhibition-img">
</div>
</template>

<script>
import $ from "jquery";
import { useStore } from "vuex";
// import { ref } from "vue";

export default {
    setup(){
        let listData;
        const store = useStore();
        const toggleFav = function(svgId){
            const fileId = parseInt(svgId.substring(4, svgId.length));
            console.log(fileId);
            svgId = "#" + svgId + " > svg";
            console.log("Repository.vue", svgId);
            console.log("Repository.vue", $(svgId).attr("fill"));
            let type, url = "http://127.0.0.1:8080/catalina/collect";
            if($(svgId).attr("fill") === "#f00"){
                $(svgId).attr("fill", "none");
                type = "DELETE", url += "/" + fileId;
                console.log(url);
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
        $.ajax({
            url: "http://127.0.0.1:8080/catalina/list",
            type: "GET",
            async: false,
            data:{
                authorName: store.state.username,
                modelName: ""
            },
            xhrFields: {
                withCredentials: true
            },
            success: function(resp){
                listData = resp.data;
                console.log(listData);
            },
        });
        for(let data of listData){
            data.liked = data.liked ? "#f00" : "none";
        }
        return{
            listData,
            toggleFav,
        }
    },
    mounted(){
        const toggleFav = this.toggleFav;
        $(".like-btn").on("click", function(){
            console.log(this);
            toggleFav($(this).attr("id"));
        });
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
.frame{
    width: 100%;
    display: flex;
    border-radius: 32px;
    background-color: #242424;
    height: 256px;
    /* border: 1px solid peru; */
    align-items: center;
    justify-content: space-evenly;
    margin-bottom: 32px;
    /* overflow: hidden; */
}
.text-content{
    width: 512px;
    height: 100%;
    /* border: 1px solid salmon; */
    display: flex;
    flex-direction: column;
    justify-content: space-evenly;
}
.line{
    display: flex;
    width: 100%;
    /* justify-content: space-evenly; */
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
}
</style>