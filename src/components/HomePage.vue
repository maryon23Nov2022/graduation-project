<template>
    <div class="base" id="base">
        <div class="vertical-align">
            <div class="gradient home-font">基于ThreeJS的</div>
            <div class="gradient home-font">3D模型共享平台</div>
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

export default {
    mounted(){
        $("#HomePage").css("border-color", "#42b883");

        const scene = new THREE.Scene();
        const offsetX = 12;

        const camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.125, 256);
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
        // const axesHelper = new THREE.AxesHelper(12);
        // scene.add(axesHelper);

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
            // directionalLight.target = bust;
            spotLight.target = bust;
            render();
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

        // const controls = new OrbitControls(camera, renderer.domElement);

        $(window).on("resize", function(){
            camera.aspect = window.innerWidth / window.innerHeight;
            // camera.updateProjectionMatrix();
            renderer.setSize(window.innerWidth, window.innerWidth * 9 / 16);
            // renderer.setPixelRatio(window.devicePixelRatio);
        });

        let oriX, difX, flag;
        $(window).on("mousedown", function(event){
            oriX = event.clientX, flag = true;
        });
        $(window).on("mouseup", function(event){
            flag = false;
        });
        $(window).on("mousemove", function(moveEvent){
            if(flag){
                difX = moveEvent.clientX - oriX;
                bust.rotation.y += difX * Math.PI / window.innerWidth;
                oriX = moveEvent.clientX;
            }
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
    z-index: -1;
    left: 0;
    top: 0;
}
.base{
    display: flex;
    width: 100%;
    height: 100vh;
    max-width: 1400px;
    margin: 0 auto;
    padding: 24px 32px;
}
.vertical-align{
    align-self: center;
}
.home-font{
    font-size: 72px;
    font-weight: 900;
    text-align: center;
}
.gradient{
    /* background-image: linear-gradient(45deg, #f3ec78, #af4261); */
    background-image: linear-gradient(45deg, #006fab, #2d815c);
    background-clip: text;
    -webkit-background-clip: text;
    color: transparent;
}
</style>