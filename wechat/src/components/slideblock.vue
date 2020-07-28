<template>
	<div class="sliderblock">
		
		<div class="sliderblock-cav">
			<canvas width="300px" height="250px" ref="bc"></canvas>
			<canvas width="300px" height="250px" ref="sc" ></canvas>
		</div>
		<div class="sliderblock-slider">
			<div class="sliderblock-slider-block">
				<span :class="{textcolorsuccess:checkpass}">{{tip}}</span>
			</div>
			<div class="el-icon-d-arrow-right sliderblock-slider-blocker" @mousedown.prevent="drop" >
				
			</div>
			<div class="sliderblock-close">
				<i class="el-icon-close" title="点击关闭" style="cursor: pointer;" @click="close"></i>
			</div>
		</div>
	</div>
</template>

<script>
	import backimage from '../assets/yzm.jpg'
	export default{
		data(){
			return{
				block:{
					width:40,
					circlur_r:10,
					x:0,
					y:120
				},
				backimage:backimage,
				tip:'滑动滑块完成验证',
				checkpass:false
			}
		},
		methods:{
			init(){
				const random = (min, max) => {
				  return Math.floor(Math.random() * (max - min + 1) + min)
				}
				let x = random(120,240)
				this.block.x=x
				this.createCanvas()
			},
			createCanvas(){
				const bc = this.$refs['bc'],sc = this.$refs['sc']
				const canvasWidth = bc.width,cnavasHeight = bc.height
				const bc_ctx = bc.getContext('2d'),sc_ctx = sc.getContext('2d')
				bc.width = canvasWidth
				bc.height = cnavasHeight
				sc.width = canvasWidth
				sc.height = cnavasHeight
				const image = document.createElement('img')
				const block = this.block
				image.onload = function(){
					bc_ctx.drawImage(image,0,0,300,250)
					sc_ctx.drawImage(image,0,0,300,250)
					const sb_width = block.width + block.circlur_r * 2 - 4
					const imageData = sc_ctx.getImageData(block.x, block.y - block.circlur_r * 2 + 4, sb_width, sb_width)
					sc.width = sb_width
					sc_ctx.putImageData(imageData, 0, block.y - block.circlur_r * 2 + 4)
				}
				image.src = this.backimage
				this.drawBlock(sc_ctx,block,'clip')
				this.drawBlock(bc_ctx,block,'fill')
			},
			drawBlock(ctx,block,type){
				ctx.beginPath()
				ctx.moveTo(block.x,block.y)
				ctx.lineTo(block.x + block.width/ 2, block.y)
				ctx.arc(block.x + block.width/ 2, block.y - block.circlur_r +4, block.circlur_r, 0, 2 * Math.PI)
				ctx.lineTo(block.x + block.width/ 2, block.y)
				ctx.lineTo(block.x+block.width,block.y)
				ctx.lineTo(block.x+block.width,block.y+block.width/2)
				ctx.arc(block.x+block.width+block.circlur_r-4,block.y+block.width/2,block.circlur_r,0,2*Math.PI)
				ctx.lineTo(block.x+block.width,block.y+block.width/2)
				ctx.lineTo(block.x+block.width,block.y+block.width)
				ctx.lineTo(block.x,block.y+block.width)
				ctx.lineTo(block.x,block.y)
				ctx[type]()
				ctx.beginPath()
				ctx.arc(block.x, block.y + block.width / 2, block.circlur_r, 1.5 * Math.PI, 0.5 * Math.PI)
				ctx.globalCompositeOperation = 'xor'
				ctx.fill()
			},
			drop(e){
				const el = e.target
				const block = this.$refs['sc']
				const downplace = {x:e.x,y:e.y}
				const place = this.block
				let movex=0
				const move =(event)=>{
					movex = event.x-downplace.x
					if(movex>300 || movex<0){
						return false
					}
					el.style.left=movex+'px'
					block.style.left=movex+'px'
				}
				const up = (event)=>{
					document.removeEventListener('mousemove',move)
					document.removeEventListener('mouseup',up)
					let upplace = {x:event.x,y:event.y}
					let x = upplace.x-downplace.x
					if((place.x-1) <= x && x <= (place.x+1)){
						this.checkpass=true
						this.tip='验证通过'
						el.style.left=""
						setTimeout(()=>{						
							this.$emit('ispass',true)
							block.style.left=""
							this.tip = '滑动滑块完成验证'
							this.checkpass=false
							
						},800)						
					}else{
						el.style.left=""
						block.style.left=""
						this.init()
					}
				}
				document.addEventListener('mousemove',move)
				document.addEventListener('mouseup',up)
			},
			close(){
				this.$emit('close',false)
				setTimeout(()=>{
					this.init()
				},200)
				
			}
			
			
		},
		mounted() {
			this.init()
		}
		
	}
	
</script>

<style>
	.sliderblock-cav{
		position: relative;
	}
	
	.sliderblock-cav>canvas:first-child{
		z-index: 10;
		position: relative;
	}
	.sliderblock-cav>canvas:last-child{
		z-index: 100000;
		top: 0;
		left: 0;
		position: absolute;
	}
	.sliderblock-slider{
		height: 60px;
		position: relative;
	}
	.sliderblock-slider-block{
		width: 100%;
		height: 30px;
		position: relative;
		top: 10px;
		background-color:#EBEEF5;
		border-radius: 50px;
		line-height: 30px;
		text-align: center;
	}
	.sliderblock-slider-blocker{
		position: absolute;
		top: 3px;
		font-size: 40px;
		width:40px;
		height: 40px;
		line-height: 40px;
		background-color: #FFFFFF;
		border: 1px solid #909399;
		box-shadow: 0 0 7px ;
		border-radius: 5px;
		cursor: pointer;
	}
	.textcolorsuccess{
		color: #67C23A;
	}
	.sliderblock-close{
		position: relative;
		top:15px;
		text-align: right;
	}
</style>
