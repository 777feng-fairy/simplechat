<template>
	<div class="chat">
		<div class="chat-window" ref='win'>
			<div v-for="(value,index) in messages" :key='index'>
				<chat-message  :model="value"  ref='message'></chat-message>
			</div>
			
		</div>
		<div class="chat-input clearfix">
			<div style="float: left;width: 85%;">
				<el-input v-model="message"></el-input>
			</div>
			<div style="float: right;width: auto;">
				<el-button type='primary' round @click='postMsg'>发送</el-button>
				
			</div>			
		</div>
	</div>
</template>

<script>
	import ChatMessage from '../components/chatmessage.vue'
	import Stomp from 'stompjs'
	import SockJS  from 'sockjs-client'
	export default{
		components:{
			ChatMessage
		},
		methods:{
			postMsg(){			
				let value = this.message
				let date = this.dateFormat('HH:MM:SS',new Date())
				let from = this.user.account
				let chatmessage = {from:from,message:value,to:'',time:date}
				//this.messages.push(chatmessage)
				this.stompClient.send('/api/user/wechat',{},JSON.stringify(chatmessage))
				this.message=''								
			},
			scollBottom(){
				const el = this.$refs['win']
				el.scrollTop  = el.scrollHeight
			},
			messageType(){
				let index = this.messages.length
				let name = this.user.account
				if(index!==0 ){
					let value = this.messages[index-1]
					if(value.from !== name ){
						let el= this.$refs.message[index-1]
						el.$el.style.textAlign='left'
					}
					
				}
							
			},
			initWebSocket(){
				let sock = new SockJS('/wschat')
				this.stompClient = Stomp.over(sock)
				let that = this
				this.stompClient.connect({},function(frame){
					let tha = that
					that.stompClient.subscribe('/topic/groupchat',function(response){
						
						let message = JSON.parse(response.body)
						tha.messages.push(message)
					})
					
				})				
			},
			disconnect(){
				if(this.stompClient!==null){
					this.stompClient.disconnect()
				}
			}
			
			,
			dateFormat(fmt, date) {
			    let ret;
			    const opt = {
			        "Y+": date.getFullYear().toString(),        // 年
			        "m+": (date.getMonth() + 1).toString(),     // 月
			        "d+": date.getDate().toString(),            // 日
			        "H+": date.getHours().toString(),           // 时
			        "M+": date.getMinutes().toString(),         // 分
			        "S+": date.getSeconds().toString()          // 秒
			        // 有其他格式化字符需求可以继续添加，必须转化成字符串
			    };
			    for (let k in opt) {
			        ret = new RegExp("(" + k + ")").exec(fmt);
			        if (ret) {
			            fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
			        };
			    };
			    return fmt;
			}
		},
		data(){
			return{
				messages:[],
				message:'',
				stompClient:null
			}
		},
		updated() {
			this.scollBottom()
			this.messageType()
		},
		destroyed() {
			this.disconnect()
		},
		mounted() {
			this.initWebSocket()
		},
		computed:{
			user:function(){
				return this.$store.state.user
			}
		}
					
	}
</script>

<style>
	.chat{
		height: 100%;
		width: 100%;
		position: relative;
	}
	.chat-window{
		width: 40%;
		height: 25rem;
		margin: 0 auto;
		box-sizing: border-box;
		padding: 1rem;
		overflow: auto;
		position: relative;
		top:10%;
		border: 1px solid #DCDFE6;
		border-radius: 7px;
	}
	.chat-window::-webkit-scrollbar{
		display: none;
	}
	.chat-input{
		width: 40%;
		margin: 0 auto;
		position: relative;
		top:10%;
		margin-top:7px;
	}
</style>
