<template>
<div class="background">
    <div class="btn-container">
        <svg id="close-btn" width="48px" height="48px" viewBox="-2.4 -2.4 28.80 28.80" fill="none" xmlns="http://www.w3.org/2000/svg" transform="rotate(0)">
            <g id="SVGRepo_bgCarrier" stroke-width="0"></g>
            <g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round" stroke="#ffffffCCCCCC" stroke-width="0.144"></g>
            <g id="SVGRepo_iconCarrier">
                <g id="Menu / Close_MD">
                    <path id="Vector" d="M18 18L12 12M12 12L6 6M12 12L18 6M12 12L6 18" stroke="#ffffff" stroke-width="2.4" stroke-linecap="round" stroke-linejoin="round">
                    </path>
                </g>
            </g>
        </svg>
    </div>
    <div id="exhibition"></div>
</div>
</template>

<script>
import $ from "jquery"
import * as THREE from "three";
import { GLTFLoader } from "three/examples/jsm/loaders/GLTFLoader";
import { DRACOLoader } from "three/examples/jsm/loaders/DRACOLoader";
import { OrbitControls } from "three/examples/jsm/controls/OrbitControls.js";

export default{
    props:{
        showModel: Boolean,
        modelLink: String
    },
    mounted(){
        const scene = new THREE.Scene();

        const camera = new THREE.PerspectiveCamera(75, 16 / 9, 0.125, 256);
        camera.position.set(8, 8, 8);      //x, y, z
        scene.add(camera);

        const loader = new GLTFLoader();
        const dracoLoader = new DRACOLoader();
        dracoLoader.setDecoderPath("./draco/");
        loader.setDRACOLoader(dracoLoader);
        
        const ambientLight = new THREE.AmbientLight(0xffffff);
        scene.add(ambientLight);

        let model;
        console.log("Exhibition.vue", this.modelLink);
        console.log("Exhibition.vue", this);
        loader.load(this.modelLink, (gltf) => {
            model = gltf.scene;
            scene.add(model);
        });

        const renderer = new THREE.WebGLRenderer();
        renderer.setSize(800, 450);
        const res = renderer.domElement;
        console.log("Exhibition.vue", $("#exhibition")[0]);
        // console.log("Exhibition.vue", renderer);
        // console.log("Exhibition.vue", res);
        $("#exhibition").append(res);
        renderer.render(scene, camera);

        const controls = new OrbitControls(camera, res);
        controls.enableDamping = true;

        const axesHelper = new THREE.AxesHelper(12);
        scene.add(axesHelper);

        function render(){
            renderer.render(scene, camera);
            requestAnimationFrame(render);
            controls.update();
        };
        render();

        const emit = this.$emit;
        const showModel = this.showModel;
        console.log("Exhibition.vue", this, showModel);
        $("#close-btn").on("click", function(){
            console.log("Exhibition.vue", showModel);
            emit("updateShowModel", false);
        })
    },
    beforeUnmount(){
        // The "while" statement here is crucial, because the v-if directive removes the parent
        // element from the DOM, but we did not clean up the additional DOM pieces
        // created by the last renderer.domElement, which will causing a memory leak.
        while($("#exhibition")[0].firstChild){
            $("#exhibition")[0].removeChild($("#exhibition")[0].firstChild);
        }
    }
    // unmounted(){
    //     console.log("Exhibition.vue", "unmounted");
    // }
}
</script>

<style>
.background{
    position: fixed;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    /* background-color: darkslateblue; */
    /* z-index: 2048; */
}
#exhibition{
    width: 800px;
    height: 450px;
    border-radius: 32px;
    overflow: hidden;
    background-color: aliceblue;
}
.btn-container{
    width: 800px;
}
#close-btn{
    cursor: pointer;
    float: right;
    transition: 300ms;
}
#close-btn:hover{
    scale: 1.2 1.2;
}
</style>