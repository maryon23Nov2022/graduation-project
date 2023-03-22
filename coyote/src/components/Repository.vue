<template>
<div class="frame">
    <div class="text-content">
        <h3>TITLE</h3>
        <div class="line">
            <div class="item">
                <a href="#" class="download-link" download>
                    <span class="btn-text">Download</span>
                    <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="#fff" stroke-width="2" stroke-linecap="square" stroke-linejoin="arcs">
                        <path d="M3 15v4c0 1.1.9 2 2 2h14a2 2 0 0 0 2-2v-4M17 9l-5 5-5-5M12 12.8V2.5">
                        </path>
                    </svg>
                </a>
            </div>
            <div class="item">
                <div class="btn-text">Size: 128bytes</div>
            </div>
        </div>
        <div class="line">
            <div class="item">
                <div class="like-btn" id="btn-1">
                    <span class="btn-text">Like</span>
                    <svg width="24" height="24" viewBox="0 0 24 24" fill="#f00" stroke="#fff" stroke-width="1" stroke-linecap="round">
                        <path d="M20.84 4.61a5.5 5.5 0 0 0-7.78 0L12 5.67l-1.06-1.06a5.5 5.5 0 0 0-7.78 7.78l1.06 1.06L12 21.23l7.78-7.78 1.06-1.06a5.5 5.5 0 0 0 0-7.78z">
                        </path>
                    </svg>
                </div>
            </div>
            <div class="item">
                <div class="btn-text">Author: zhuqi</div>
            </div>
        </div>
    </div>
    <img src="./../assets/realistic-wood-texture-background_87521-3153.jpg" alt="Some errors hava occured." class="exhibition-img">
</div>
</template>

<script>
import $ from "jquery";

export default {
    setup(){
        const toggleFav = function(svgId){
            svgId = "#" + svgId + " > svg";
            console.log("Repository.vue", svgId);
            console.log("Repository.vue", $(svgId).attr("fill"));
            if($(svgId).attr("fill") === "#f00") $(svgId).attr("fill", "none");
            else $(svgId).attr("fill", "#f00");
        }
        return{
            toggleFav,
        }
    },
    mounted(){
        const toggleFav = this.toggleFav;
        $("#Repository").css({"color": "#42b883", "filter":"brightness(100%)"});
        $(".like-btn").on("click", function(){
            console.log(this);
            toggleFav($(this).attr("id"));
        })
    },
    unmounted(){
        // $("#Repository").css({"color": "#e1e1e1", "filter":"brightness(70%)"});
        $("#Repository").removeAttr('style');
    }
}
</script>


<style scoped>
.frame{
    width: 100%;
    display: flex;
    border-radius: 32px;
    height: 256px;
    border: 1px solid peru;
    align-items: center;
    justify-content: space-evenly;
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