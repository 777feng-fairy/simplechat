<template>
	<div class="login">
		<div class="login-content">
			<div>
				<el-form :model="form" :rules="rules" ref="form" status-icon>
					<el-form-item label="账号" prop="account">
						<el-input v-model="form.account" prefix-icon='el-icon-user'></el-input>
					</el-form-item>
					<el-form-item label="密码" prop="password">
						<el-input type='password' prefix-icon='el-icon-key' v-model="form.password" show-password></el-input>
					</el-form-item>
					<el-form-item  >
						<el-button type="primary"  @click="clickBtn" >登录</el-button>
					</el-form-item>
				</el-form>
			</div>
			
			<div class="login-popper">
				<el-popover trigger="manual" v-model="visiable" popper-class="popper">
					<slider-block  @ispass="pass" @close="close"></slider-block>
				</el-popover>
			</div>
			
		</div>		
	</div>
</template>
<script>
	import SliderBlock from '../components/slideblock.vue'
	export default{
		name:'login',
		components:{
			SliderBlock
		},
		data(){
			
			const isNull = (rule,value,callback)=>{
				if(!value){
					return callback(new Error("请填入"))
				}else{
					callback()
				}
			}
			
			return{
				visiable:false,
				checkpass:false,
				form:{
					account:'',
					password:''
				},
				rules:{
					account:[{validator: isNull,trigger: 'blur'}],
					password:[{validator: isNull,trigger: 'blur'}],
				}
			}
		},
		methods:{
			pass(val){
				this.visiable = false
				this.checkpass = val
			},
			clickBtn(){
				const form = this.$refs['form']
				form.validate((pass)=>{
					if(pass){
						this.visiable=true
					}				
				})
			},
			postLogin(){	
				const form = this.$refs['form']
				this.axios({
					url:'/api/user/login',
					method:'post',					
					params:{
						account:form.model.account,
						password:form.model.password
					}
					}).then(resp=>{
						if(resp.data.status==200){
							this.$store.commit('updateUser',resp.data.data)
							this.$router.replace({
								path:'/chat'
							})
						}
						if(resp.data.status==400){
							this.checkpass=false
							this.$message.warning("哎呀！登陆失败了")
							form.resetFields()
						}
					}).catch(resp=>{
						this.checkpass=false
						this.$message.error("哎呀！报错了0.0！请检查网络连接")
					})			
			},
			close(val){
				this.visiable=val
			}
			
		},
		watch:{
			checkpass:function(val){
				if(val){
					this.postLogin()
				}
			}
		}
	}
</script>
<style>
	.login{
		width: 100%;
		height: 100%;
		position: relative;
	}
	.login-content{
		width: 18.75rem;
		margin: 0 auto;
		position: relative;
		top:30%;
		text-align: left;
	}
	.login-popper{
		position: relative;
		bottom:16rem;
	}
	.popper{
		height: 315px;
		width: auto;
	}
</style>