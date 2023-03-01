<template>
    <div class="base" id="base">
        <div class="home-base">
            <div class="text-container">
                <div class="page-block absolute-right">
                    <div>
                        <div class="gradient-yellow home-font">登录</div>
                        <form>
                            <div class="mb-3">
                                <label for="exampleInputEmail1" class="form-label">Email address</label>
                                <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
                            </div>
                            <div class="mb-3">
                                <label for="exampleInputPassword1" class="form-label">Password</label>
                                <input type="password" class="form-control" id="exampleInputPassword1">
                            </div>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form>
                    </div>
                </div>
                <div class="page-block absolute-left">
                    <div>
                        <div class="gradient-green home-font">基于ThreeJS的</div>
                        <div class="gradient-green home-font">3D模型共享平台</div>
                    </div>
                </div>
                <div class="page-block absolute-right">
                    <div>
                        <div class="gradient-yellow home-font">注册</div>
                        <form>
                            <div class="mb-3">
                                <label for="exampleInputEmail2" class="form-label">Email address</label>
                                <input type="email" class="form-control" id="exampleInputEmail2" aria-describedby="emailHelp">
                            </div>
                            <div class="mb-3">
                                <label for="exampleInputConfirm2" class="form-label">Email address</label>
                                <input type="email" class="form-control" id="exampleInputConfirm2" aria-describedby="emailHelp">
                            </div>
                            <div class="mb-3">
                                <label for="exampleInputPassword2" class="form-label">Password</label>
                                <input type="password" class="form-control" id="exampleInputPassword2">
                            </div>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div id="canvas-container">
        </div>
    </div>
</template>

<script>
import $ from "jquery";
import * as THREE from "three";
import { GLTFLoader } from "three/examples/jsm/loaders/GLTFLoader";
import { DRACOLoader } from "three/examples/jsm/loaders/DRACOLoader";
import { OrbitControls } from "three/examples/jsm/controls/OrbitControls.js";
import gsap from "gsap";

export default {
    mounted(){
        $("#HomePage").css("border-color", "#42b883");

        const scene = new THREE.Scene();
        const offsetX = 12;
        const verticalGap = 64;

        const camera = new THREE.PerspectiveCamera(75, window.innerWidth / (window.innerWidth * 9 / 16), 0.125, 256);
        camera.position.set(0, 0, 24);      //x, y, z
        scene.add(camera);

        const loader = new GLTFLoader();
        const dracoLoader = new DRACOLoader();
        require.context("!!file-loader?name=draco/[name].[ext]!../assets/draco", true, /.*/, "sync");
        dracoLoader.setDecoderPath("./draco/");
        loader.setDRACOLoader(dracoLoader);

        // const side = 12;
        // const directionalLight = new THREE.DirectionalLight(0xffffff, 4);
        // directionalLight.castShadow = true;
        // directionalLight.distance = 16;
        // directionalLight.position.set(0, 16, 0);
        // directionalLight.shadow.camera.top = side;
        // directionalLight.shadow.camera.bottom = -side;
        // directionalLight.shadow.camera.left = side;
        // directionalLight.shadow.camera.right = -side;
        // directionalLight.shadow.camera.far = 48;
        // scene.add(directionalLight);

        // const pointLight = new THREE.PointLight(0xff0000);
        // pointLight.position.set(8, 0, 0);
        // pointLight.castShadow = true;
        // pointLight.distance = 16;
        // pointLight.decay = 2;
        // scene.add(pointLight);

        const spotLight = new THREE.SpotLight(0xffffff, 1024);
        spotLight.position.set(0, offsetX, 0);
        spotLight.castShadow = true;
        spotLight.distance = 64;
        spotLight.penumbra = 1;
        scene.add(spotLight);

        // const shadowHelper = new THREE.CameraHelper(directionalLight.shadow.camera);
        // const shadowHelper = new THREE.CameraHelper(pointLight.shadow.camera);
        // const shadowHelper = new THREE.CameraHelper(spotLight.shadow.camera);
        // scene.add(shadowHelper);
        const axesHelper = new THREE.AxesHelper(12);
        scene.add(axesHelper);

        //y: 64
        let mario;
        loader.load(require("../assets/model/mario_lego.glb"), (gltf) => {
            mario = gltf.scene;
            mario.scale.set(4, 4, 4);
            mario.position.set(-offsetX - 4, -verticalGap - 8, 0);
            scene.add(mario);
            console.log(mario);
        });

        //y: 0
        let bust;
        loader.load(require("../assets/model/sculpture_bust_of_roza_loewenfeld.glb"), (gltf) => {
            bust = gltf.scene;
            bust.traverse(function(node){
                if(node.isMesh){
                    node.castShadow = true;
                    // console.log(node);
                }
            });
            bust.scale.set(1 / 32, 1 / 32, 1 / 32);
            bust.position.set(offsetX, 0, 0);
            scene.add(bust);
            spotLight.target = bust;
            render();
        });

        //y: -64
        let animeCar;
        loader.load(require("../assets/model/che.glb"), (gltf) => {
            animeCar = gltf.scene;
            animeCar.scale.set(2, 2, 2);
            animeCar.position.set(-offsetX + 2, verticalGap - 6, 0);
            scene.add(animeCar);
            console.log(animeCar);
        });

        // const sphereGeometry = new THREE.SphereGeometry(1, 20, 20);
        // const material = new THREE.MeshStandardMaterial();
        // const sphere = new THREE.Mesh(sphereGeometry, material);
        // sphere.position.set(5, 0, 5);
        // sphere.castShadow = true;
        // scene.add(sphere);

        const planeGeometry = new THREE.CircleGeometry(24, 48);
        const plane = new THREE.Mesh(planeGeometry, new THREE.MeshStandardMaterial({
            map: new THREE.TextureLoader().load(require("../assets/realistic-wood-texture-background_87521-3153.jpg")),
            // metalness: 0.125,
            // roughness: 0.125
        }));
        plane.position.set(offsetX, -7, 0);
        plane.rotation.x = -Math.PI / 2;
        plane.receiveShadow = true;
        scene.add(plane);

        const renderer = new THREE.WebGLRenderer();
        renderer.setClearAlpha(0);
        renderer.setSize(window.innerWidth, window.innerWidth * 9 / 16);
        renderer.shadowMap.enabled = true;
        renderer.physicallyCorrectLights = true;
        const res = renderer.domElement;
        $("#canvas-container").append(res);
        renderer.render(scene, camera);
        // const height = $("#canvas-container").css("height");
        // console.log(height);
        // $("#base").css("height", height + "");

        // console.log("before", camera.position.y);
        // const controls = new OrbitControls(camera, res);
        // controls.enableDamping = true;
        // camera.position.set(0, 0, 24);
        // controls.update();
        // console.log("after", camera.position.y);
        // camera.position.y = 0;

        $(window).on("resize", function(){
            camera.aspect = window.innerWidth / (window.innerWidth * 9 / 16);
            // camera.updateProjectionMatrix();
            renderer.setSize(window.innerWidth, window.innerWidth * 9 / 16);
            // renderer.setPixelRatio(window.devicePixelRatio);
        });

        let oriX, difX, flag;
        $(window).on("mousedown", function(event){
            oriX = event.clientX, flag = true;
        });
        $(window).on("mouseup", function(){
            flag = false;
        });
        $(window).on("mousemove", function(moveEvent){
            if(flag){
                difX = moveEvent.clientX - oriX;
                bust.rotation.y += difX * Math.PI / window.innerWidth;
                animeCar.rotation.y += difX * Math.PI / window.innerWidth;
                mario.rotation.y += difX * Math.PI / window.innerWidth;
                oriX = moveEvent.clientX;
            }
        });

        // let offsetY = ref(-100);
        const scroll = function(positionY){
            console.log(positionY, verticalGap, camera.position.y % verticalGap);
            if(camera.position.y % verticalGap === 0){
                gsap.to(camera.position, { y: positionY, duration: 1, ease: "power1.inOut" });
                gsap.to(".text-container", { y: positionY * 100 / 64 - 100 + "vh", duration: 1, ease: "power1.inOut" });
            }
        }
        $("#goToLogin").on("click", function(){
            scroll(verticalGap);
        });
        $("#goToRegister").on("click", function(){
            scroll(-verticalGap);
        });
        $("#HomePage").on("click", function(){
            scroll(0);
        });

        const clock = new THREE.Clock();
        function render(){
            let time = clock.getElapsedTime();
            spotLight.position.set(Math.sin(time / 2) * offsetX + offsetX, offsetX, Math.cos(time / 2) * offsetX);
            renderer.render(scene, camera);
            requestAnimationFrame(render);
        }
    },
    unmounted(){
        $("#HomePage").css("border-color", "transparent");
    }
}
</script>


<style scoped>
#canvas-container{
    position: absolute;
    left: 0;
    top: 0;
}
.base{
    display: flex;
    width: 100%;
    height: 100vh;
    max-width: 1400px;
    margin: 0 auto;
    padding: 0px 32px;
}
.home-base{
    width: 100%;
    overflow-y: hidden;
}
.text-container{
    /* display: flex; */
    width: 100%;
    /* height: 300vh; */
    /* flex-direction: column; */
    /* justify-content: space-around; */
    transform: translate(0, -100vh);
}
.page-block{
    width: 100%;
    height: 100vh;
    display: flex;
    /* flex-direction: column; */
    align-items: center;
}
/* .vertical-align{
    align-self: center;
} */
.home-font{
    font-size: 6vh;
    font-weight: 900;
    text-align: center;
}
.gradient-green{
    background-image: linear-gradient(45deg, #006fab, #2d815c);
    background-clip: text;
    -webkit-background-clip: text;
    color: transparent;
}
.gradient-yellow{
    background-image: linear-gradient(45deg, #f3ec78, #af4261);
    background-clip: text;
    -webkit-background-clip: text;
    color: transparent;
}
.absolute-right{
    justify-content: flex-end;
}
.absolute-left{
    justify-content: flex-start;
}
</style>