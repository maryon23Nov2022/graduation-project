<template>
    <div class="base">
        <div class="option-container">
            <div class="upload-image">
                <form id="modelForm" class="content">
                    <div class="layer">
                        <div class="option">
                            <label class="option-label">
                                <div class="title">选择要上传的模型文件</div>
                                <img class="empty-image" src="https://static.sketchfab.com/static/builds/web/dist/static/assets/images/placeholders/emptystates/0422baf7f0bda9df4a46858877a7cda3-v2.png" alt="">
                                <input id="modelInput" name="modelChosen" type="file" accept=".glb">
                                <div>{{ modelInfo }}</div>
                            </label>
                            <div class="info"></div>
                        </div>
                        <div class="option">
                            <label class="option-label">
                                <div class="title">选择用于展示的图片</div>
                                <img class="empty-image" src="https://static.sketchfab.com/static/builds/web/dist/static/assets/images/placeholders/emptystates/0422baf7f0bda9df4a46858877a7cda3-v2.png" alt="">
                                <input id="imgInput" name="imgChosen" type="file" accept=".jpg,.png,.jpeg">
                                <div>{{ imgInfo }}</div>
                            </label>
                            <div class="info"></div>
                        </div>
                    </div>
                    <div class="layer">
                        <label>
                            <span class="title">模型名：</span>
                            <input id="modelName" name="modelName" class="input-text" type="text">
                        </label>
                    </div>
                </form>
            </div>
            <div class="operation">
                <div style="width: 50%; text-align: center;">
                    <button id="revokeUpload" type="button" class="btn btn-primary btn-self">Cancel</button>
                </div>
                <div style="width: 50%; text-align: center;">
                    <button id="uploadModel" type="button" class="btn btn-primary btn-self">Submit</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import $ from "jquery";
import { ref } from "vue";

export default{
    name: "UploadPage",
    setup(){
        let modelInfo = ref(""), imgInfo = ref("");
        const send = function(data){
            // console.log(data);
            $.ajax({
                url: "http://127.0.0.1:8080/catalina/files",
                type: "POST",
                data: data,
                dataType: "json",
                contentType: false,
                processData: false,
                xhrFields: {
                    withCredentials: true
                },
                success: function(resp){
                    console.log(resp);
                    alert(resp.msg);
                },
            });
        }
        const submit = function(){
            const formData = new FormData($("#modelForm")[0]);
            send(formData);
            // console.log("submitting");
            // for (const [key, value] of formData) {
            //     console.log(key, value);
            // }
        };
        const revoke = function(){
            modelInfo.value = imgInfo.value = $("#modelName")[0].value = "";
            const myModel = $("#modelInput")[0].files;
            const myImg = $("#imgInput")[0].files;
            while(myModel.firstChild){
                myModel.removeChild(myModel.firstChild);
            }
            while(myImg.firstChild){
                myImg.removeChild(myImg.firstChild);
            }
        };
        const updateModelInfo = function(){
            // console.log($("#modelInput")[0].files);
            modelInfo.value = $("#modelInput")[0].files[0].name;
        };
        const updateImgInfo = function(){
            imgInfo.value = $("#imgInput")[0].files[0].name;
        };
        return{
            modelInfo, imgInfo,
            submit, updateModelInfo, updateImgInfo, revoke
        };
    },
    mounted(){
        $("#Upload").css({"color": "#42b883", "filter":"brightness(100%)"});
        $("#uploadModel").on("click", this.submit);
        $("#revokeUpload").on("click", this.revoke);
        $("#modelInput").on("change", this.updateModelInfo);
        $("#imgInput").on("change", this.updateImgInfo);
    },
    unmounted(){
        $("#Upload").removeAttr('style');
    }
}
</script>

<style scoped>
.base{
    display: flex;
    width: 100%;
    max-width: 1400px;
    justify-content: center;
    align-items: center;
    /* border: 1px solid yellow; */
    /* color: #e1e1e1; */
}
.option-container{
    width: 80%;
    min-width: 512px;
    height: 512px;
    /* border: 1px solid red; */
    /* background-color: aqua; */
    display: flex;
    flex-direction: column;
    align-items: center;
}
.upload-image{
    width: 100%;
    height: 80%;
}
.content{
    display: flex;
    /* border: 1px solid green; */
    /* background-color: gray; */
    width: 100%;
    height: 100%;
    flex-direction: column;
    justify-content: space-evenly;
}
.layer{
    display: flex;
    justify-content: center;
}
.option{
    width: 50%;
    height: 100%;
}
.option-label{
    display: flex;
    flex-direction: column;
    align-items: center;
}
.title{
    /* margin-top: 16px; */
    font-weight: 900;
    letter-spacing: 4px;
}
.empty-image{
    margin-top: 16px;
    width: 128px;
    height: 128px;
    border-radius: 10%;
    cursor: pointer;
    transition: 256ms;
    border: 1px solid #e1e1e1;
}

.empty-image:hover{
    box-shadow: 0px 0px 16px 2px rgba(224, 224, 224, 0.75);
    -webkit-box-shadow: 0px 0px 16px 2px rgba(224, 224, 224, 0.75);
}
.input-text{
    border-radius: 8px;
}
input[type=file]{
    display: none;
}
.operation{
    /* height: 25%; */
    width: 100%;
    height: 20%;
    /* margin-top: 48px; */
    display: flex;
    align-items: center;
    justify-content: space-evenly;
    /* border: 1px solid yellow; */
}
.btn-self{
    font-weight: bold;
    width: 128px;
    /* font-size: large; */
    /* letter-spacing: 4px; */
}
</style>