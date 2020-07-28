<template>
	<div class="chatmessage" >
		<div>
			<span>{{model.from}}</span><span>（{{model.time}}）</span>
		</div>
		<div >
			<span class="chatmessage-qipao">{{model.message}}</span>
		</div>
	</div>
</template>

<script>
	export default{
		props:{
			model:{
				type:Object,
				default:''
			}
			
		}
	}
</script>

<style>
	.chatmessage{
		text-align: right;
		height: auto;
	}
	.chatmessage-qipao{
		background-image: linear-gradient(to bottom,#8CC5FF,#ECF5FF);
		border-radius: 12px;
		padding: 12px;
		color: #303133;
		min-width:20px;
	}
	.chatmessage>div{
		box-sizing: border-box;
		padding: 10px 0;
	}
</style>
