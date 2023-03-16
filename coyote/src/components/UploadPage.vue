<template>
    <div class="base">
        <div class="option-container">
            <div class="upload-image">
                <form id="form" class="content">
                    <div class="layer">
                        <div class="option">
                            <label class="option-label">
                                <div class="title">选择要上传的模型文件</div>
                                <img class="empty-image" src="https://static.sketchfab.com/static/builds/web/dist/static/assets/images/placeholders/emptystates/0422baf7f0bda9df4a46858877a7cda3-v2.png" alt="">
                                <input name="modelChosen" type="file" accept=".glb">
                            </label>
                            <div class="info"></div>
                        </div>
                        <div class="option">
                            <label class="option-label">
                                <div class="title">选择用于展示的图片</div>
                                <img class="empty-image" src="https://static.sketchfab.com/static/builds/web/dist/static/assets/images/placeholders/emptystates/0422baf7f0bda9df4a46858877a7cda3-v2.png" alt="">
                                <input name="imgChosen" type="file" accept=".jpg,.png,.jpeg">
                            </label>
                            <div class="info"></div>
                        </div>
                    </div>
                    <div class="layer">
                        <label>
                            <span class="title">模型名：</span>
                            <input name="modelName" class="input-text" type="text">
                        </label>
                    </div>
                </form>
            </div>
            <div class="operation">
                <div style="width: 50%; text-align: center;">
                    <button type="button" class="btn btn-primary btn-self">Cancel</button>
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

export default{
    setup(){
        const send = function(data){
            $.ajax({
                url: "http://127.0.0.1:8080/mvc_demo/files",
                type: "POST",
                data: data,
                dataType: "json",
                contentType: false,
                processData: false,
                success: function(resp){
                    console.log(resp);
                },
            });
        }
        const submit = function(){
            const formData = new FormData($("#form")[0]);
            send(formData);
        };
        $("#uploadModel").on("click", submit);
    },
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
    height: 100vh;
    margin: 0 auto;
    padding: 88px 32px;
    justify-content: center;
    align-items: center;
    /* border: 1px solid yellow; */
    /* color: #e1e1e1; */
}
.option-container{
    width: 64%;
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