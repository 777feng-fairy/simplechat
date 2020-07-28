<template>
  <div id="app">
    <router-view/>
  </div>
</template>
<script>
	export default{
		name:'app',
		created() {
			if(sessionStorage.getItem("store")){
				this.$store.replaceState(Object.assign({},this.$store.state,JSON.parse(sessionStorage.getItem("store"))))
				sessionStorage.removeItem("store")
			}
			window.addEventListener("beforeunload",()=>{
				sessionStorage.setItem("store",JSON.stringify(this.$store.state))
			})
			
		}
	}	
</script>

<style>
	
	@import url("../public/css/reset.css");
	
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}



html,body,#app{
	height: 100%;
	width: 100%;
}
</style>
